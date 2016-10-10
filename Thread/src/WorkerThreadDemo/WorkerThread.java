package WorkerThreadDemo;

public class WorkerThread extends Thread {
	private final Channel channel;
	
	public WorkerThread(String name, Channel channel){
		super(name);
		this.channel = channel;
	}
	
	@Override
	public void run(){
		while(true){
			Request request = channel.takeRequest();	//取出请求，并且执行
			request.execute();
		}
	}
}
