package FuturePatternDemo;


//����ʵ�����ɶ��client��ִ���̹߳���ִ���߳̽���ִ�����󣬲���ִ�еĽ��������FutureData��
//client�߳̽�����еõ�realdata�Ľ����
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
