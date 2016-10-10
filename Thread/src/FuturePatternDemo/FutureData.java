package FuturePatternDemo;


//该类实例，由多个client和执行线程共享。执行线程将会执行请求，并将执行的结果交付给FutureData。
//client线程将会从中得到realdata的结果。
public class FutureData implements Data {

	private RealData realdata = null;
	private boolean ready = false;
	
	
	public synchronized void SetRealData(RealData realdata){
		if(ready){
			return ;
		}
		this.realdata = realdata;
		ready = true;
		notifyAll();
	}	
	
	@Override
	public synchronized String getContent() {
		while(!ready){
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		return realdata.getContent();
	}

}
