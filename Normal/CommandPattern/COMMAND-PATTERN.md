#命令模式
命令模式将请求封装为对象，以便使用实际不同的请求。这个请求就是命令模式的命令。<br>
以下是两个例子:
 * [单线程普通例子-接口实现](https://github.com/lsj9383/Pattern/blob/master/Normal/CommandPattern/SingleThread/SingleThread/Program.cs)
 * [多线程例子-委托实现](https://github.com/lsj9383/Pattern/blob/master/Normal/CommandPattern/MultiThread/MultiThread/Program.cs)

##目的
命令模式的目的是将请求的发送者，和请求的执行之间实现解耦，这是一个非常常见的OOP编程方法。具体来说，一个工具类中包含了其他类的对象，而这些其他类应该是抽象的，具体使用什么类应该是由用户决定的，而不应该由工具类把这个类固定死。
举个具体例子:多线程的事件驱动模型。这本质上也是一个另类的观察者模式，在多线程结束时，以告知某个对象，或是执行某个函数。在这个模型中，要求多线程的工作和具体事件解耦。因为在设计多线程工具的时候，并不知道用户在多线程执行完成后希望执行什么样的操作。

##标准UML
![](https://github.com/lsj9383/Pattern/blob/master/icon/CommandUML.png)
##标准参与者
 + Command. 申明执行的接口。
 + ConcreteCommand. 具体的命令执行方式。
 + Receiver. 知道如何实施与执行一个请求相关的操作。
 + Invoker. 要求命令执行该请求。
 + Client. 创建具体的Command对象与Invoker对象。

在实际应用中，ConcreteCommand不一定要有对应的Receiver，而是在ConcreteCommand中就封装好完整的逻辑，这是完全可以的。因此将Receiver视为ConcreteCommand执行逻辑所需要的资源对象更好一些。在我们的设计中，通常工具的设计者出的工具是Invoker，而Command和Receiver是交给用户来进行设计的。

##命令模式结构

###Command接口

```C#
interface Command
{
	void Execute();
}
```
命令接口是提供给Invoker使用的，可以出多个方法，上述这是一个最小的命令接口，只包含了一个Execute方法，在Invoker中便是调用该方法，以执行具体的任务。

###Invoker

```C#
class Invoker
{
	private Command m_cCommand;

	public void SetCommand(Command cmd)
	{
		m_cCommand = cmd;
	}

	public void Action()
	{
		m_cCommand.Execute();
	}
}
```
Invoker是类设计人员的职责，类中在使用别的对象时，希望不受限于该具体对象，因此将这些可能的对象，抽象出来形成Command对象，以达到Invoker和Command的解耦。

###CommandImpl

```C#
class Command1 : Command
{
	public ReceiverA receiver;

	public Command1(ReceiverA receiver)
	{
		this.receiver = receiver;
	}

	public void Execute()
	{
		...
	}
}

class Command2 : Command
{
	...
}
```
CommandImpl就是ConcreteCommand，是具体的实现逻辑。

###Receiver

```C#
class ReceiverA
{
	...
}
```
Receiver是配合ConcreteCommand逻辑的类。