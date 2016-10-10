package WorkerThreadDemo;

/*
 * 
 * �̳߳�(���̹߳����Ĺ���)
 * 1.�����˶�������߳�ʵ����ÿ�������̶߳���һ�������е��̡߳�
 * 2.������Request���С�
 * 3.�����߳�ʵʱ���̳߳�����ȡRequest�����Channel�����еĹ����̺߳����е�Client�߳�(�������߳�)�����еĶ���
 * 
 * 
 */
public class Channel {
	private static final int MAX_REQUEST = 100;
	private final Request[] requestQueue;
	private int tail;	//��һ��  putRequest�ĵط�
	private int head;	//��һ��takeRequest�ĵط�
	private int count;	//Request����
	
	private final WorkerThread[] threadPool;	//�������������Worker�߳�
	
	//�������̴߳���
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
	
	//�������̴߳���
	public void StartWorkers(){
		for(int i=0; i<threadPool.length; i++){
			threadPool[i].start();
		}
	}
	
	//�ɶ��ClientThread�̹߳���÷��������Request��
	public synchronized void putRequest(Request request){
		while(count >= requestQueue.length){
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		
		requestQueue[tail] = request;
		tail = (tail + 1)%requestQueue.length;
		count++;
		notifyAll();		//Ŀ���ǻ���������Worker����ΪWorker�����ڿյ�RequestQueue������
	}
	
	//�ɶ��WorkerThread����÷�������ȡRequest����û��Request�������Լ����������̸߳����ִ��������
	public synchronized Request takeRequest(){
		while(count <= 0){
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		
		Request request = requestQueue[head];
		head = (head + 1)%requestQueue.length;
		count--;
		notifyAll();		//Ŀ���ǻ���Client�̣߳���ΪClient�����ڶ�����������������Producer-Consumer Pattern��
		
		return request;
	}
	
}
