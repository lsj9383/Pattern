package Demo.ThreadDemo.ThreadPool;

public class Future<T>
{
	T result = null;
	boolean ready = false;
	
	public synchronized void SetResult(T result){
		this.result = result;
		ready = true;
		notifyAll();
	}
	
	public synchronized T GetResult(){
		while(!ready){
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		
		return result;
	}
}