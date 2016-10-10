package WorkerThreadDemo;

import java.util.Random;

//�����˾��������������ClientThread��������WorkerThread���ղ�����
public class Request {
	private final String name;	//ί����
	private final int number;	//������
	private static final Random random = new Random();
	
	public Request(String name, int number){
		this.name = name;
		this.number = number;
	}
	
	public void execute(){
		//Thread.currentThread()�ǹ����߳�Ŷ
		System.out.println(Thread.currentThread().getName() + " executes " + this);		//ָ�����ĸ�Worker��ִ�������ĸ�Client������
		try {
			Thread.sleep(random.nextInt(1000));
		} catch (InterruptedException e) {}
	}
	
	@Override
	public String toString(){
		return "[ Request form " + name + " No." + number + "]";
	}
	
}
