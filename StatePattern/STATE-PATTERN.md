#状态模式
状态机是某种具有多种状态机器的抽象。状态模式是采用面向对象机制对状态机作编程，通常分为两类，一类是由外部用户驱动的`外部状态模式`，另一类是由状态机内部状态驱动的`内部状态模式`。

##外部状态模式
外部状态模式，是由用户使用状态机，而使状态机的状态进行改变的。<br>
一个状态机将具体的状态封装到了机器的内部，状态机为用户提供了各种的操作方法接口，用户通过这些接口控制状态机。状态机的状态会在执行了对应的操作后，会进行改变。<br>
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
	
	void StateOne(StateMachine Machine)
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
		...							//具体的StateOne的Opera2操作逻辑
		Machine.SetState(...);		//StateOne在Opera2操作后，进行状态转移
	}
}

class StateTwo : State
{
	private StateMachine Machine;
	
	void StateTwo(StateMachine Machine)
	{
		this.Machine = Machine;
	}
	
	void Opera1(StateMachine Machine)
	{
		...							//具体的StateTwo的Opera1操作逻辑
		Machine.SetState(...);		//StateTwo在Opera1操作后，进行状态转移
	}
	
	void Opera2()
	{
		...							//具体的StateTwo的Opera2操作逻辑
		Machine.SetState(...);		//StateTwo在Opera2操作后，进行状态转移
	}
}
```

```C#
class StateMachine
{
	private State State1 = null;
	private State State2 = null;
	private State State3 = null;
	private State State = null;
	
	public StateMachine()
	{
		State1 = new StateOne(this);
		State2 = new StateTwo(this);
		State3 = new StateThree(this);
	}
	
	public void Initial()
	{
		...
		State = State-n		//初始化，更加状态机初始情况，设置初始状态。
	}
	
	public void Opera1()
	{
	
	}
	
	public void Opera2()
	{
	
	}
}

```


##内部状态模式


##内部状态机的多线程编程