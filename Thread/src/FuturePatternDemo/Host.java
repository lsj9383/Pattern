package FuturePatternDemo;

public class Host {
	public Data request(final int count, final char c){
		
		System.out.println("	request(" + count + ", " + c + ") BEGIN");
		
		final FutureData future = new FutureData();
		
		new Thread(){
			@Override
			public void run(){	//在新线程中执行请求，并将求结果付给future
				RealData realdata = new RealData(count, c);		//这里，在构造函数中进行计算
				future.SetRealData(realdata);					//计算完成，得到结果，将德国给future
			}
		}.start();
		
		System.out.println("	request(" + count + ", " + c + ") END");
		
		return future;
	}
}
