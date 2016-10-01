#状态模式
状态机是某种具有多种状态机器的抽象。状态机适合机器根据当前不同的状态，对不同的请求执行不同的操作的业务。<br>
状态模式是采用面向对象机制对状态机作编程，通常分为两类，一类是由用户驱动的`外部驱动的状态模式`，另一类是由状态机内部状态驱动的`内部状态模式`。

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
这个类结构和`外部驱动的状态模式`非常相似，也是同样针对状态和机器设计。状态机有个类，每个状态有对应的类。但是所不同的，所有类都只有**Next方法**。造成这个情况的原因是，状态机不受外部控制，也就没有针对外部的方法，只有一个推动内部状态转移的方法，也就是**Next()**。<br>
下图是一个状态的情况，它无论怎样，都会有下一个状态(可以将自己作为下一转台)。当前状态只需要处理当前状态需要处理的逻辑，再转移到下一个状态即可。这个转移的依据就是状态机内部属性。
!()[]
更具体的说，**Next方法**可以处理当前状态的逻辑，处理完成后，根据状态机的属性，再设置新的状态。换句话说**Next方法**是推动状态机内部状态更迭关键，它和面向过程的状态机相比，它省略了繁琐的switch-case，而是直接针对状态类编程。先看状态类:
```C#
interface State
{
	void Next();
}
```


<br>由以上介绍的方案，这种状态模式有个很明显的缺点: 用户将会永远在状态机线程中。马上介绍的多线程编程模型，将会克服这个缺点。单线程的该模型，只能是个最外层的程序框架。
##内部状态机的多线程编程