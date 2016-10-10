package WorkerThreadDemo;

import java.util.Random;

//定义了具体的请求工作，由ClientThread发出，由WorkerThread接收并处理
public class Request {
	private final String name;	//委托者
	private final int number;	//请求编号
	private static final Random random = new Random();
	
	public Request(String name, int number){
		this.name = name;
		this.number = number;
	}
	
	public void execute(){
		//Thread.currentThread()是工人线程哦
		System.out.println(Thread.currentThread().getName() + " executes " + this);		//指明了哪个Worker，执行来自哪个Client的请求
		try {
			Thread.sleep(random.nextInt(1000));
		} catch (InterruptedException e) {}
	}
	
	@Override
	public String toString(){
		return "[ Request form " + name + " No." + number + "]";
	}
	
}
