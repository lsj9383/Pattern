package TwoPhaseTerminationDemo;

public class ComputeThread extends Thread{
	private long counter = 0;
	
	private volatile boolean shutdownRequested = false;
	
	public void shutdownRequest(){
		shutdownRequested = true;		//终止请求
		interrupt();					//打断运行，主要是针对sleep/wait中不能马上打断的问题
	}
	
	public boolean isShutdownRequested(){
		return shutdownRequested;
	}
	
	@Override
	public final void run(){
		while(!shutdownRequested){
			try {
				doWork();
			} catch (InterruptedException e) {}
			finally{
				doShutdown();
			}
		}
	}
	
	private void doWork() throws InterruptedException
	{
		counter++;
		System.out.println("doWork: counter = " + counter);
		Thread.sleep(1000);
	}
	
	//终止处理
	private void doShutdown(){
		System.out.println("doShutdown: counter = " + counter);
	}
}
