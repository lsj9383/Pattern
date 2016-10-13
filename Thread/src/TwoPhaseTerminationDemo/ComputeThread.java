package TwoPhaseTerminationDemo;

public class ComputeThread extends Thread{
	private long counter = 0;
	
	private volatile boolean shutdownRequested = false;
	
	public void shutdownRequest(){
		shutdownRequested = true;		//��ֹ����
		interrupt();					//������У���Ҫ�����sleep/wait�в������ϴ�ϵ�����
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
	
	//��ֹ����
	private void doShutdown(){
		System.out.println("doShutdown: counter = " + counter);
	}
}
