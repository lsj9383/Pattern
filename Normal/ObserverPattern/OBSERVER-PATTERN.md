#�۲���ģʽ
�۲���ģʽ����������-�����������۲����뱻�۲���֮��ʵ�ֽ����ʹ�����ģʽ��ʱ��Ҫ��ȷ�۲���(Observer)��ʲô�����۲���(Subject)��ʲô��������3������:
* [��ť�¼�-�ӿ�ʵ��](https://github.com/lsj9383/Pattern/blob/master/Normal/ObserverPattern/InterfaceAchieve/InterfaceAchieve/Program.cs)
* [��ť�¼�-ί��ʵ��](https://github.com/lsj9383/Pattern/blob/master/Normal/ObserverPattern/DelegateAchieve/DelegateAchieve/Program.cs)
* [���̵߳��¼�����ģ��]()
* [Digitial Simulation](https://github.com/lsj9383/digital-simulator)

##Ŀ��
���������һ��һ�Զ��һ����ϵ����һ�������״̬�����仯ʱ���������������Ķ��󶼵õ�֪ͨ�����Զ����¡����¼�����ģ�ͱ���У��۲���ģʽ�Ǻ��ġ�

##��׼UML
![](https://github.com/lsj9383/Pattern/blob/master/icon/ObserberUML.png)

##��׼������
 * Subject.Ŀ�꣬Ҳ���Ǳ��۲��ߣ�����״̬�ı䣬���ᷢ��֪ͨ���۲��ߡ���Ȼ�����ṩ����ӹ۲��ߵĽӿڣ�ĳЩʱ��Ҳ����ɾ���۲��ߵĽӿڡ�
 * Observer.�۲��߽ӿڣ��ṩ�˿�����Ŀ��֪ͨ�۲��ߵĽӿڡ�
 * ConcreteObserver.����۲��ߣ���ʵ���˹۲��߽ӿڣ��Խ�������Ŀ���
 Subject��������Ϊ�ӿڣ������ConcreteSubject��������ʵ�֣��Խ�һ����Subject��Observer֮����н����ʵ��Ӧ��������ֱ�Ӿ���Subject��Ϊһ��ʵ�ʵ��ࡣ���⣬����һ��EventArgsҲ�ǿ�ѡ�ģ�����ΪSubject״̬�ı����Ϣ�������ݸ��۲��ߡ�ͨ���������ߣ������Subject����������ӹ۲��ߵĽӿڣ��Ա��û���ӹ۲��ߡ��û���Ƶı��Ǿ���Ĺ۲��ߣ��������û��Ĺ۲����յ�����Subject��Ĵ����߼���
 
##�۲���ģʽ�ṹ
###Observer�ӿ�
```C#
interface Observer
{
	void Caller(object o, object e);
}
```
�۲��߽ӿڣ��ؼ���Ҫ�ṩһ�����������������Subject֪ͨObserver�Ĺؼ����ú���ͨ��������������һ�����������˷�����֪ͨ�Ķ�����һ�������Ǹ�������Ϣ��Ϣ��֮���������ƣ�һ��������Ϊһ���۲��ߵĹ۲������ܲ�ֹһ����ͨ����һ��������֪�۲������ĸ�Subject��֪ͨ����һ����һ��Subject״̬�ĸı�����Ƕ��ԭ�򹹳ɵģ�ͨ���ڶ����������Ը�֪�۲��߸������Ϣ��

###Subject
```C#
class SubjectEventArgs
{
	...
}

class Subject
{
	List<Observer> observers = new List<Observer>();       //�۲�����

	//��ӹ۲���
	public void AddOberver(Observer obse)
	{
		observers.Add(obse);
	}

	//Event
	public void EventTarget()         //�����¼�����
	{
		foreach (Observer obs in observers)
		{
			obs.Caller(this, new SubjectEventArgs(...));
		}
	}
}
```
���۲��ߵĺ�����������һ������ӹ۲��ߵĽӿ�**AddObserver**���û�ͨ��AddObserver�ӿڣ�����ΪSubject��ӹ۲��ߣ�һ����Ҫ֪ͨʱ���Ա���ӵ����й۲��߷���֪ͨ����һ������Ϣ֪ͨ**SubjectEventTarget**������һ��Subject�кܶ���֪ͨ������Ϊ�˼�ֻд��һ����ö�����еĹ۲��ߣ������䷢��֪ͨ��

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
ͨ������۲������û�ʵ�֣������д�˵��յ�Subject����Ϣʱ����Ҫ��δ���ľ����߼���<br>

����ί�����͵Ľṹ����ֱ�ӿ�[ʵ��](https://github.com/lsj9383/Pattern/blob/master/Normal/ObserverPattern/DelegateAchieve/DelegateAchieve/Program.cs)
##����
* ��ģ��
* ��ģ��