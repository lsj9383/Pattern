#״̬ģʽ
״̬����ĳ�־��ж���״̬�����ĳ���״̬ģʽ�ǲ������������ƶ�״̬������̣�ͨ����Ϊ���࣬һ�������ⲿ�û�������`�ⲿ״̬ģʽ`����һ������״̬���ڲ�״̬������`�ڲ�״̬ģʽ`��

##�ⲿ״̬ģʽ
�ⲿ״̬ģʽ�������û�ʹ��״̬������ʹ״̬����״̬���иı�ġ�<br>
һ��״̬���������״̬��װ���˻������ڲ���״̬��Ϊ�û��ṩ�˸��ֵĲ��������ӿڣ��û�ͨ����Щ�ӿڿ���״̬����״̬����״̬����ִ���˶�Ӧ�Ĳ����󣬻���иı䡣<br>
Ϊ�˽�����״̬��Ĳ�������������Ϊÿ��״̬����һ���࣬������Щ����״̬�ཫ��̳���һ������״̬�ࡣ״̬���ƹ��˾���Ĳ�����ÿ��״̬��ÿ��������״̬��ת�����ǲ���ͬ�ģ������Ҫ��״̬��Ĳ�������ʵʩ�Լ���״̬ת�ơ�
״̬��Ķ������´�����ʾ:
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
		...							//�����StateOne��Opera1�����߼�
		Machine.SetState(...);		//StateOne��Opera1�����󣬽���״̬ת��
	}
	
	void Opera2()
	{
		...							//�����StateOne��Opera2�����߼�
		Machine.SetState(...);		//StateOne��Opera2�����󣬽���״̬ת��
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
		...							//�����StateTwo��Opera1�����߼�
		Machine.SetState(...);		//StateTwo��Opera1�����󣬽���״̬ת��
	}
	
	void Opera2()
	{
		...							//�����StateTwo��Opera2�����߼�
		Machine.SetState(...);		//StateTwo��Opera2�����󣬽���״̬ת��
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
		State = State-n		//��ʼ��������״̬����ʼ��������ó�ʼ״̬��
	}
	
	public void Opera1()
	{
	
	}
	
	public void Opera2()
	{
	
	}
}

```


##�ڲ�״̬ģʽ


##�ڲ�״̬���Ķ��̱߳��