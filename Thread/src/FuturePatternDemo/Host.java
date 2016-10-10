package FuturePatternDemo;

public class Host {
	public Data request(final int count, final char c){
		
		System.out.println("	request(" + count + ", " + c + ") BEGIN");
		
		final FutureData future = new FutureData();
		
		new Thread(){
			@Override
			public void run(){	//�����߳���ִ�����󣬲�����������future
				RealData realdata = new RealData(count, c);		//����ڹ��캯���н��м���
				future.SetRealData(realdata);					//������ɣ��õ���������¹���future
			}
		}.start();
		
		System.out.println("	request(" + count + ", " + c + ") END");
		
		return future;
	}
}
