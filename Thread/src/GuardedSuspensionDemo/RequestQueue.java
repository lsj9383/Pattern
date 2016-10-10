package GuardedSuspensionDemo;

import java.util.LinkedList;

//��������Ķ��У����߳�֮���ǹ���ġ�

public class RequestQueue {
	private final LinkedList queue = new LinkedList();
	
	public synchronized Request getRequest(){
		while(queue.size() <= 0){
			try {
				wait();		//����Ϊ�գ����߳�ֱ����������wait�ǻ��ͷŵ����ģ��ڼ����ʱ���᳢ֻ�Ի������
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return (Request)queue.removeFirst();
	}
	
	public synchronized void putRequest(Request request){
			queue.addLast(request);
			notifyAll();	//ÿ�����һ�����󣬶�Ҫ�������д���wait�ȴ����߳�
	}
}
