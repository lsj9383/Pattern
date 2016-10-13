package TwoPhaseTerminationDemo;

public class Main {
	public static void main(String[] args){
		System.out.println("main: BEGIN");
		
		
		try {
			
			ComputeThread t = new ComputeThread();
			t.start();
			
			Thread.sleep(10000);
			
			System.out.println("main: shutdownRequest");
			t.shutdownRequest();
			
			System.out.println("main: join");
			t.join();	//等待线程结束
			
		} catch (InterruptedException e) {}
		
		System.out.println("main: END");
	}
}
