package Demo.ThreadDemo.ThreadPool;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Callable;

public class FutureThreadPool {
	protected Queue<Callable> Jobs;
	protected Queue<Future> Futures;
	
	protected Thread[] WorkerThreads;		//工人线程
	
	//创建线程池，由Client线程调用
	public FutureThreadPool(int RunSize){
		Jobs = new LinkedList<>();
		Futures = new LinkedList<>();
		WorkerThreads = new Thread[RunSize];
		
		for(int i=0; i<WorkerThreads.length; i++){
			WorkerThreads[i] = new FutureWorker(this);
		}
	}
	
	//开启线程池的所有线程，由Client线程调用
	public void Start(){
		for(Thread thread : WorkerThreads){
			thread.start();
		}
	}
	
	//
	public synchronized <T> Future<T> AddJob(Callable<T> newJob){
		Future<T> future = new Future<>();
		Jobs.add(newJob);
		Futures.add(future);
		notifyAll();
		return future;
	}
	
	public synchronized Object[] ExtractJob(){
		
		while(Jobs.size() == 0){
			try {
				wait();	//若没有Job，工人线程等待
			} catch (InterruptedException e) {}
		}
		
		Object[] objects = {Jobs.remove(), Futures.remove()};
		return objects;
	}
}

class FutureWorker extends Thread{
	private final FutureThreadPool threadPool;
	private final Future future;
	
	public FutureWorker(FutureThreadPool threadPool){
		this.threadPool = threadPool;
		this.future = null;
	}
	
	@Override
	public void run(){
		while(true){
			
			Object[] objects = threadPool.ExtractJob();		//提取工作，以及对应工作结果的保存对象
			Callable job = (Callable)objects[0];
			Future future= (Future) objects[1];
			
			try {
				future.SetResult(job.call());
			} catch (Exception e) {}
		}
	}
}
