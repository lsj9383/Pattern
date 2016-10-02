package SingleThreadExe;

public class SharedResource {
	Object LockRoot = new Object();
	int MethodCallerTime = 0;
	int ProcessCallerTime = 0;
	
	public synchronized void Method(Run r){
		try {
			MethodCallerTime++;
			if(r.m==false && r.p==false){
				System.out.println("Thread ID " + Thread.currentThread().getId() + " Start");
			}
			Thread.sleep(1000);
			r.m = true;
			if(r.p == false){
				Process(r);
			}
			else{
				System.out.println("Thread ID " + Thread.currentThread().getId() + " end");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void Process(Run r){
		try {
			if(r.m==false && r.p==false){
				System.out.println("Thread ID " + Thread.currentThread().getId() + " Start");
			}
			Thread.sleep(2000);
			r.p = true;
			if(r.m == false){
				Method(r);
			}
			else{
				System.out.println("Thread ID " + Thread.currentThread().getId() + " end");
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void ShowTime()
	{
		System.out.println("MethodCallerTime : " + MethodCallerTime);
	}
}
