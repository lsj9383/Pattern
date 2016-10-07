#观察者模式
观察者模式，又名订阅-发布，它将观察者与被观察者之间实现解耦。在使用这个模式的时候，要明确观察者(Observer)是什么，被观察者(Subject)是什么。以下是3个例子:
* [按钮事件-接口实现](https://github.com/lsj9383/Pattern/blob/master/Normal/ObserverPattern/InterfaceAchieve/InterfaceAchieve/Program.cs)
* [按钮事件-委托实现](https://github.com/lsj9383/Pattern/blob/master/Normal/ObserverPattern/DelegateAchieve/DelegateAchieve/Program.cs)
* [多线程的事件驱动模型]()
* [Digitial Simulation](https://github.com/lsj9383/digital-simulator)

##目的
定义对象间的一种一对多的一来关系，当一个对象的状态发生变化时，所有依赖与它的对象都得到通知并被自动更新。在事件驱动模型编程中，观察者模式是核心。

##标准UML
![](https://github.com/lsj9383/Pattern/blob/master/icon/ObserberUML.png)

##标准参与者
 * Subject.目标，也就是被观察者，它的状态改变，将会发送通知至观察者。当然，它提供了添加观察者的接口，某些时候，也会有删除观察者的接口。
 * Observer.观察者接口，提供了可以让目标通知观察者的接口。
 * ConcreteObserver.具体观察者，它实现了观察者接口，以接收来自目标的
 Subject可以设置为接口，再添加ConcreteSubject来作具体实现，以进一步将Subject和Observer之间进行解耦。但实际应用中往往直接就让Subject作为一个实际的类。另外，还有一个EventArgs也是可选的，这作为Subject状态改变的消息参数传递给观察者。通常类的设计者，是设计Subject，并留出添加观察者的接口，以便用户添加观察者。用户设计的便是具体的观察者，里面是用户的观察者收到来自Subject后的处理逻辑。
 
##观察者模式结构
###Observer接口
```C#
interface Observer
{
	void Caller(object o, object e);
}
```
观察者接口，关键是要提供一个函数，这个函数是Subject通知Observer的关键。该函数通常有两个参数，一个参数代表了发出该通知的对象，另一个参数是附带的消息信息。之所以如此设计，一方面是因为一个观察者的观察对象可能不止一个，通过第一个参数告知观察者是哪个Subject的通知，另一方面一个Subject状态的改变可能是多个原因构成的，通过第二个参数可以告知观察者更多的信息。

###Subject
```C#
class SubjectEventArgs
{
	...
}

class Subject
{
	List<Observer> observers = new List<Observer>();       //观察者们

	//添加观察者
	public void AddOberver(Observer obse)
	{
		observers.Add(obse);
	}

	//Event
	public void EventTarget()         //按下事件触发
	{
		foreach (Observer obs in observers)
		{
			obs.Caller(this, new SubjectEventArgs(...));
		}
	}
}
```
被观察者的核心是两个，一个是添加观察者的接口**AddObserver**，用户通过AddObserver接口，可以为Subject添加观察者，一旦需要通知时，对被添加的所有观察者发送通知。另一个是消息通知**SubjectEventTarget**，往往一个Subject有很多种通知，这里为了简单只写了一个。枚举所有的观察者，并向其发送通知。

###ConcreteObserver
```C#
class ConcreteObserver : Observer
{
	public void Caller(object o, object e)
	{
		...
	}
}
```
通常具体观察者由用户实现，里面编写了当收到Subject的消息时，需要如何处理的具体逻辑。<br>

对于委托类型的结构，请直接看[实例](https://github.com/lsj9383/Pattern/blob/master/Normal/ObserverPattern/DelegateAchieve/DelegateAchieve/Program.cs)
##其他
* 推模型
* 拉模型