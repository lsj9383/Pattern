package SingleThreadExe;

import com.sun.xml.internal.ws.client.sei.ResponseBuilder.Body;

public class Main {
	public static void main(String[] args) throws Exception{
		final SharedResource sr = new SharedResource();
		
		Thread t1 = new Thread(new Run1(sr));
		Thread t2 = new Thread(new Run2(sr));
		
		t1.start();
		t2.start();
		
		while(t1.getState().toString() != "TERMINATED" || t2.getState().toString() != "TERMINATED");
		
		System.out.println("Main Thread Done!");
		
		sr.ShowTime();
	} 
}

abstract class Run implements Runnable
{
	protected SharedResource sr;
	public boolean m = false;
	public boolean p = false;
	
	public Run(SharedResource sr){
		this.sr = sr;
	}
}

class Run1 extends Run
{
	public Run1(SharedResource sr){
		super(sr);
	}
	
	@Override
	public void run() {
		sr.Method(this);
	}
}

class Run2 extends Run
{
	public Run2(SharedResource sr){
		super(sr);
	}
	
	@Override
	public void run() {
		sr.Process(this);
	}
}