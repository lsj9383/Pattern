package WorkerThreadDemo;

import java.util.Random;

public class ClientThread extends Thread{
	private final Channel channel;			//各个ClientThread间共享的线程池，用于接收ClientThread的线程执行请求
	private static final Random random = new Random();
	
	public ClientThread(String name, Channel channel){
		super(name);
		this.channel = channel;
	}
	
	@Override
	public void run(){
		for(int i=0; true; i++){
			Request request = new Request(getName(), i);
			channel.putRequest(request);
			try {
				Thread.sleep(random.nextInt(1000));
			} catch (InterruptedException e) {}
		}
	}
}
