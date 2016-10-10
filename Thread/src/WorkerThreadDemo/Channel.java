package WorkerThreadDemo;

/*
 * 
 * 线程池(多线程工作的管理)
 * 1.保存了多个工人线程实例，每个工人线程都是一个运行中的线程。
 * 2.保存了Request队列。
 * 3.工人线程实时从线程池中提取Request，因此Channel由所有的工人线程和所有的Client线程(包括主线程)所共有的对象。
 * 
 * 
 */
public class Channel {
	private static final int MAX_REQUEST = 100;
	private final Request[] requestQueue;
	private int tail;	//下一个  putRequest的地方
	private int head;	//下一个takeRequest的地方
	private int count;	//Request数量
	
	private final WorkerThread[] threadPool;	//在这里管理数个Worker线程
	
	//仅由主线程触发
	public Channel(int threads){
		this.requestQueue = new Request[MAX_REQUEST];
		this.head = 0;
		this.tail = 0;
		this.count = 0;
		this.threadPool = new WorkerThread[threads];
		for(int i=0; i<threadPool.length; i++){
			threadPool[i] = new WorkerThread("Worker-"+i, this);
		}
	}
	
	//仅由主线程触发
	public void StartWorkers(){
		for(int i=0; i<threadPool.length; i++){
			threadPool[i].start();
		}
	}
	
	//由多个ClientThread线程共享该方法，添加Request。
	public synchronized void putRequest(Request request){
		while(count >= requestQueue.length){
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		
		requestQueue[tail] = request;
		tail = (tail + 1)%requestQueue.length;
		count++;
		notifyAll();		//目的是唤醒阻塞的Worker，因为Worker会由于空的RequestQueue而阻塞
	}
	
	//由多个WorkerThread共享该方法，提取Request，若没有Request则阻塞自己，给其他线程更多的执行能力。
	public synchronized Request takeRequest(){
		while(count <= 0){
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		
		Request request = requestQueue[head];
		head = (head + 1)%requestQueue.length;
		count--;
		notifyAll();		//目的是唤醒Client线程，因为Client会由于队列满而阻塞。类似Producer-Consumer Pattern。
		
		return request;
	}
	
}
