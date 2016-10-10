package Demo.ThreadDemo.ThreadPool;

public class Worker extends Thread{
	private final ThreadPool threadPool;
	
	public Worker(ThreadPool threadPool){
		this.threadPool = threadPool;
	}
	
	@Override
	public void run(){
		while(true){
			Runnable job = threadPool.ExtractJob();
			job.run();
		}
	}
}
