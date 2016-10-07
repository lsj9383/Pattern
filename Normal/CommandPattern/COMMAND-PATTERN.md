#����ģʽ
����ģʽ�������װΪ�����Ա�ʹ��ʵ�ʲ�ͬ��������������������ģʽ�����<br>
��������������:
 * [���߳���ͨ����-�ӿ�ʵ��](https://github.com/lsj9383/Pattern/blob/master/Normal/CommandPattern/SingleThread/SingleThread/Program.cs)
 * [���߳�����-ί��ʵ��](https://github.com/lsj9383/Pattern/blob/master/Normal/CommandPattern/MultiThread/MultiThread/Program.cs)

##Ŀ��
����ģʽ��Ŀ���ǽ�����ķ����ߣ��������ִ��֮��ʵ�ֽ������һ���ǳ�������OOP��̷�����������˵��һ���������а�����������Ķ��󣬶���Щ������Ӧ���ǳ���ģ�����ʹ��ʲô��Ӧ�������û������ģ�����Ӧ���ɹ�����������̶�����
�ٸ���������:���̵߳��¼�����ģ�͡��Ȿ����Ҳ��һ������Ĺ۲���ģʽ���ڶ��߳̽���ʱ���Ը�֪ĳ�����󣬻���ִ��ĳ�������������ģ���У�Ҫ����̵߳Ĺ����;����¼������Ϊ����ƶ��̹߳��ߵ�ʱ�򣬲���֪���û��ڶ��߳�ִ����ɺ�ϣ��ִ��ʲô���Ĳ�����

##��׼UML
![](https://github.com/lsj9383/Pattern/blob/master/icon/CommandUML.png)
##��׼������
 + Command. ����ִ�еĽӿڡ�
 + ConcreteCommand. ���������ִ�з�ʽ��
 + Receiver. ֪�����ʵʩ��ִ��һ��������صĲ�����
 + Invoker. Ҫ������ִ�и�����
 + Client. ���������Command������Invoker����

��ʵ��Ӧ���У�ConcreteCommand��һ��Ҫ�ж�Ӧ��Receiver��������ConcreteCommand�оͷ�װ���������߼���������ȫ���Եġ���˽�Receiver��ΪConcreteCommandִ���߼�����Ҫ����Դ�������һЩ�������ǵ�����У�ͨ�����ߵ�����߳��Ĺ�����Invoker����Command��Receiver�ǽ����û���������Ƶġ�

##����ģʽ�ṹ

###Command�ӿ�

```C#
interface Command
{
	void Execute();
}
```
����ӿ����ṩ��Invokerʹ�õģ����Գ������������������һ����С������ӿڣ�ֻ������һ��Execute��������Invoker�б��ǵ��ø÷�������ִ�о��������

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
Invoker���������Ա��ְ��������ʹ�ñ�Ķ���ʱ��ϣ���������ڸþ��������˽���Щ���ܵĶ��󣬳�������γ�Command�����ԴﵽInvoker��Command�Ľ��

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
CommandImpl����ConcreteCommand���Ǿ����ʵ���߼���

###Receiver

```C#
class ReceiverA
{
	...
}
```
Receiver�����ConcreteCommand�߼����ࡣ