package Demo.ThreadDemo.ThreadPool;

import java.util.LinkedList;
import java.util.Queue;

public abstract class ThreadPool {
	protected Queue<Runnable> Jobs;
	protected Thread[] WorkerThreads;		//工人线程
	
	//创建线程池，由Client线程调用
	public ThreadPool(int RunSize){
		Jobs = new LinkedList<>();
		WorkerThreads = new Thread[RunSize];
		for(int i=0; i<WorkerThreads.length; i++){
			WorkerThreads[i] = new Worker(this);
		}
	}
	
	//开启线程池的所有线程，由Client线程调用
	public void Start(){
		for(Thread thread : WorkerThreads){
			thread.start();
		}
	}
	
	//该方法由Client线程共享使用，且其中的Jobs变量由Client线程和Worker共享使用
	public synchronized void AddJob(Runnable newJob){
		Jobs.add(newJob);
		notifyAll();		//添加一个Job会通知所有Worker
	}
	
	//该方法由多个Worker线程共享使用，且其中的Jobs变量由Client线程和Worker共享使用
	public synchronized Runnable ExtractJob(){
		
		while(Jobs.size() == 0){
			try {
				wait();	//若没有Job，工人线程等待
			} catch (InterruptedException e) {}
		}
		
		return Jobs.remove();	//取出最新的Job
	}
	
}
