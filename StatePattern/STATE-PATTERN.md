#状态模式
状态机是某种具有多种状态机器的抽象。状态机适合机器根据当前不同的状态，对不同的请求执行不同的操作的业务。<br>
状态模式是采用面向对象机制对状态机作编程，通常分为两类:
* 外部驱动的状态模式
	* [例子](https://github.com/lsj9383/Pattern/blob/master/StatePattern/OutDrive/OutDrive/Program.cs)
* 内部驱动的状态模式
	* [单线程例子](https://github.com/lsj9383/Pattern/blob/master/StatePattern/InnerDrive/InnerDrive/Program.cs)
	* [多线程例子]()

##外部驱动的状态模式
外部驱动的状态模式，是由用户使用状态机，而使状态机的状态进行改变的。<br><br>
一个状态机将具体的状态封装到了机器的内部，状态机为用户提供了各种的操作方法接口，用户通过这些接口控制状态机。状态机的状态会在执行了对应的操作后，会进行状态改变。<br>
为了将各个状态间的操作独立开来，为每个状态设置一个类，并且这些具体状态类将会继承与一个抽象状态类。状态类掌管了具体的操作。每个状态的每个操作，状态的转化都是不相同的，因此需要让状态类的操作里面实施自己的状态转移。
状态类的定义如下代码所示:
```C#
interface State
{
	void Opera1();
	void Opera2();
}

class StateOne : State
{
	private StateMachine Machine;
	
	void StateOne(StateMachine Machine)		//将机器本身传入，一方面是为了方便进行机器的状态转换，另一方面是方便使用机器本身的一些操作。
	{
		this.Machine = Machine;
	}
	
	void Opera1(StateMachine Machine)
	{
		...							//具体的StateOne的Opera1操作逻辑
		Machine.SetState(...);		//StateOne在Opera1操作后，进行状态转移
	}
	
	void Opera2()
	{
		...									//具体的StateOne的Opera2操作逻辑
		Machine.SetState(Machine.State2);	//StateOne在Opera2操作后，进行状态转移
	}
}

class StateTwo : State
{
	...		//类似StateOne
}
```

状态类定义完成后，可以完成状态机的定义。状态机是面向用户使用的，它的操作由用户驱动。状态机执行一个操作后，会阻塞线程，直到操作和状态转移完成。

```C#
class StateMachine
{
	public State State1 { get; private set; }
	public State State2 { get; private set;}
	
	private State State = null;			//标识当前的状态
	
	public StateMachine()
	{
		State1 = new StateOne(this);
		State2 = new StateTwo(this);
	}
	
	public void Initial()
	{
		...
		State = StateOne;				//初始化，更加状态机初始情况，设置初始状态。
	}
	
	public void Opera1()
	{
		state.Opera1();
	}
	
	public void Opera2()
	{
		state.Opera2();
	}
}

```
在[这里](https://github.com/lsj9383/Pattern/blob/master/StatePattern/OutDrive/OutDrive/Program.cs)已经给出了一个简单的外部驱动的状态模式实例, 该实例是参考《Head First 设计模式》一书中的状态模式示例编写的。

##内部驱动的状态模式
内部驱动的状态模式述说了两种特性:
* 1.状态机的状态形成环状，并且状态在内部运行该环。该状态机的所有状态都只有一个操作，即next方法，用于根据当前状态机的情况，转移到下一个状态。
* 2.用户不能直接控制状态机，而是向状态机发送请求，这些请求将会在一个队列中缓冲，状态机会不断的从队列中读出请求，并处理这些请求。
这类状态机的优势是可以将用户主线程和状态机的执行切分开来，而不是融杂在一起，更适合后面的多线程的引入。这类状态机面向过程和面向对象的两种结构。

###面向过程结构
这类状态机，是通过大循环中的switch-case格式实现的。首先，还是得确认状态机的状态图，并明确有哪些状态。
```C#
enum State
{
	State1,
	State2,
	StateProcess,	//可以处理缓冲区的状态
	...				//其余状态
}
```
在确认状态后，就需要进行状态机的编程。需要注意的是，对于单线程的情况，这将会形成一个大循环，用户的一切操作均是在该循环下执行的。
```C#
class Machine
{
	Queue<Job> Jobs;			//工作缓冲区队列
	State state;
	
	void Initial()
	{
		...
		state = State.State1;	//根据机器的初始情况，设置初始状态。
	}
	
	void Start()
	{
		while(true)
		{
			switch(state)
			{
				case State.State1:
					...
					state = ...;		//状态转移
					break;
				case State.State2:
					...
					state = ...;		//状态转移
					break;
				case State,StateProcess
					Job job = Job.dequeue();
					job.process();
					state = ...;		//状态转移
					break;
				default : break;
			}
			....					//用户线程业务逻辑
			Thread.sleep(10);		//小延时，释放线程，在后期的多线程中很重要。
		}
	}
}
```
主线程中，是需要对Machine.Start()，就开启了状态机线程。这类状态机有个很鲜明的特点，就是状态机没有对外的具体操作，对外只有对工作的添加，并且不同的工作最后执行的Process也可以是不同的，也就是可以执行不同的工作。状态机内部实现了一个自动处理机制，对每个状态，都有自己对应的处理方案，处理完后根据状态机的状态，再进行状态转移。<br>

###面向对象结构
这个类结构和`外部驱动的状态模式`非常相似，也是同样针对状态和机器设计。状态机有个类，每个状态有对应的类。但是所不同的，所有类都只有**Next方法**。造成这个情况的原因是，状态机不受外部控制，也就没有针对外部的方法，只有一个推动内部状态转移的方法，也就是Next。<br>
下图是一个状态的情况，它无论怎样，都会有下一个状态(可以将自己作为下一转台)。当前状态只需要处理当前状态需要处理的逻辑，再转移到下一个状态即可。这个转移的依据就是状态机内部属性。
![](https://github.com/lsj9383/Pattern/blob/master/icon/State.png)
<br>更具体的说，Next可以处理当前状态的逻辑，处理完成后，根据状态机的属性，再设置新的状态。换句话说Next是推动状态机内部状态更迭关键，它和面向过程的状态机相比，它省略了繁琐的switch-case，而是直接针对状态类编程。先看状态类:
```C#
interface State
{
	void Next();
}

class StateOne : State
{
	StateMachine Machine = null;

	public StateOne(StateMachine machine)
	{
		Machine = machine;
	}

	public void Next()
	{
		...
		Machine.JobDequeue().Process();			//阻塞，等待任务处理完毕。
		Machine.SetState(Machine.State2);		//状态转移
	}
}

class StateTwo : State
{
	...
}
```
下面更进一步，定义状态机
```C#
class StateMachine
{
	public State State1{get; private set;}
	public State State2{get; private set;}
	
	private State state
	
	Queue<Job> Jobs;			//工作缓冲区队列
	
	public StateMachine()
	{
		State1 = new StateOne();
		State2 = new StateTwo();
		state = state1;
	}
	
	public void JobEnqueue(Job job)
	{
		Jobs.enqueue(job);
	}
	
	public Job JobDequeue()
	{
		return Jobs.dequeue();
	}
	
	void Start()
	{
		while(true)
		{
			state.Next();
			Thread.Sleep(10);		//小延时，释放当前线程。
		}
		//用户其他业务逻辑
	}
}
```
在[这里](https://github.com/lsj9383/Pattern/blob/master/StatePattern/InnerDrive/InnerDrive/Program.cs) 给出了两个状态相互切换的小案例，这里包含了对Job的定义。 <br>
<br>由以上介绍的方案，这种状态模式有个很明显的缺点: 用户将会永远在状态机线程中。马上介绍的多线程编程模型，将会克服这个缺点。单线程的该模型，只能是个最外层的程序框架。
##内部状态机的多线程编程
多线程处理的状态模式，主要在两方面:
* 多线程执行状态机主循环
* 多线程执行状态机的Job
要认识到，一方面若用单一线程，将会无法添加额外的状态机，并且状态机线程和用户线程本就相互较为独立。用户有什么需求，只需要向状态提出Job请求即可。另一方面，Job操作认为是比较耗时的，因此Job操作全部采用异步操作。也就是说状态机在处理一个Job的时候，会再开一个线程处理Job。需要注意的是，在一个Job还未处理完时，状态机不应再处理其他Job，而是进入BUSY状态，等待该Job线程处理完毕。<br>

###状态类
有四种必要的状态类是多线程状态模式所必须，也是核心稳健的控制机制的关键类。
* LOOP
* BUSY
* ERROR
* EMPTY
首先来定义抽象状态基类，该基类有共同的操作。(之前的定义都是对接口的，这里是抽象基类，是因为可能有很多基类，这些基类的构造函数势必有相同的部分)
```C#
abstract class State
{
	protected StateMachine sMachine;
	public State(StateMachine machine)		//抽象基类，得到状态机，方便在状态类中对状态机进行状态转移等处理。
	{
		sMachine = machine;
	}
	public abstract void Next();
}
```

####EMPTY状态
这个状态是针对初始化这一特殊操作进行的。状态机的当前状态，在建立类的时候，都是EMPTY状态。这个状态意味着等待人工初始化。之所以要将初始化单独做一个接口，是因为初始化并不是由状态机驱动的，可以认为是该类状态机唯一一个需要由外部进行驱动的状态。起始状态之所以不由状态机内部驱动，是因为初始化操作可以是在开启状态机之前进行的。如果将初始化操作放在状态机线程开启之后由状态机自动驱动其初始化，势必造成开启状态机线程时有较长延时，另一方面这个初始化操作若将初始化的时候开启线程，又会造成不必要的CPU性能的浪费。因此应该将初始化操作独立出来，由外部用户驱动。这样，一个状态就会有两个方法，一个是Initial，一个是Next

```C#
class Empty : State
{
	public Empty(StateMachine machine) : base(machine) { }


	public override void Initial(EventHandler<EventArgs> eHandler)
	{
		sMachine.SetState(sMachine.BUSY);       //初始化线程打开前，先将状态置为繁忙.

		Task<bool> task = new Task<bool>(InitialProcess);
		task.ContinueWith(t =>
			{
				eHandler(sMachine, new EventArgs());
				if (t.Result == false)
				{   //初始化失败，这个状态机不可用, 返回错误代码
					sMachine.jobResult.SetStatus(-2);
				}
			});
	}

	public override void Next()
	{
		throw new NotImplementedException();
	}

	//初始化逻辑，返回初始化成功或是失败
	private bool InitialProcess()
	{
		...
	}
}
```

####LOOP状态
这个状态是状态模式对业务逻辑处理的关键类，它虽然不控制业务逻辑，但是它执行提取Job，并执行Job的工作。至于工作类，在后面再解决。
```C#
class Looping : State
{
	public Looping(StateMachine machine) : base(machine) { }
	public override void Next()
	{
		if (sMachine.JobsCount() > 0)
		{
			sMachine.SetState(sMachine.BUSY);               //设为忙状态
			sMachine.DequeueJobs().Process(sMachine);       //对工作出队，并进行处理
		}
	}
}
```

####ERROR状态
虽然该状态并不是影响状态机工作的关键，但是却会极大的增强状态机的稳定性。这个状态也是出错后可以和用户主线程的接口。
```C#
class Error : State
{
	private EventHandler<EventArgs> errorHandler;
	public Error(StateMachine machine, EventHandler<EventArgs> handler) : base(machine) { errorHandler = handler; }

	public override void Next()
	{
		errorHandler(sMachine, new EventArgs());            //错误处理
		sMachine.SetState(sMachine.LOOP);                   //恢复轮询状态            
	}
}
```

####BUSY状态
用来标识当前的Job是否处于运行，若有BUSY状态在处理，则会进入BUSY态。BUSY状态相当特殊，它是一个Job操作后的等待状态，因此，在操作完成以前，是不能进入其他状态的。而在操作完成后，BUSY状态需要根据完成的情况，进行状态转移。这样就势必需要让Job能够返回一个操作结果的信息。
```C#
//繁忙状态
class Busy : State
{
	public Busy(StateMachine machine):base(machine){ }

	public override void Next()
	{
		int result = sMachine.jobResult.GetStatus();       //获得结果，在获得操作结果后，会立刻将内部结果置为0
		if (result == 1)
		{
			sMachine.SetState(sMachine.LOOP);               //恢复轮询job状态
		}
		else if (result == -1)
		{
			sMachine.SetState(sMachine.ERROR);				//进入错误状态，将会进行错误处理
		}
		else
		{
			;       //still BUSY, No action
		}
	}
}
```