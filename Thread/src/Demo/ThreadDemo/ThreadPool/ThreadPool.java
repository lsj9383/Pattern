package Demo.ThreadDemo.ThreadPool;

import java.util.LinkedList;
import java.util.Queue;

public abstract class ThreadPool {
	protected Queue<Runnable> Jobs;
	protected Thread[] WorkerThreads;		//�����߳�
	
	//�����̳߳أ���Client�̵߳���
	public ThreadPool(int RunSize){
		Jobs = new LinkedList<>();
		WorkerThreads = new Thread[RunSize];
		for(int i=0; i<WorkerThreads.length; i++){
			WorkerThreads[i] = new Worker(this);
		}
	}
	
	//�����̳߳ص������̣߳���Client�̵߳���
	public void Start(){
		for(Thread thread : WorkerThreads){
			thread.start();
		}
	}
	
	//�÷�����Client�̹߳���ʹ�ã������е�Jobs������Client�̺߳�Worker����ʹ��
	public synchronized void AddJob(Runnable newJob){
		Jobs.add(newJob);
		notifyAll();		//���һ��Job��֪ͨ����Worker
	}
	
	//�÷����ɶ��Worker�̹߳���ʹ�ã������е�Jobs������Client�̺߳�Worker����ʹ��
	public synchronized Runnable ExtractJob(){
		
		while(Jobs.size() == 0){
			try {
				wait();	//��û��Job�������̵߳ȴ�
			} catch (InterruptedException e) {}
		}
		
		return Jobs.remove();	//ȡ�����µ�Job
	}
	
}
