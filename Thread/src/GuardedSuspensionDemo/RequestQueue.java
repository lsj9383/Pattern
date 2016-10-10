package GuardedSuspensionDemo;

import java.util.LinkedList;

//保存请求的队列，在线程之间是共享的。

public class RequestQueue {
	private final LinkedList queue = new LinkedList();
	
	public synchronized Request getRequest(){
		while(queue.size() <= 0){
			try {
				wait();		//队列为空，该线程直接阻塞掉。wait是会释放掉锁的，在激活的时候，又会尝试获得锁。
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return (Request)queue.removeFirst();
	}
	
	public synchronized void putRequest(Request request){
			queue.addLast(request);
			notifyAll();	//每次添加一个请求，都要激活所有处于wait等待的线程
	}
}
