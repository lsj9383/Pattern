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

##内部状态模式


##内部状态机的多线程编程