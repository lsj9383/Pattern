package FuturePatternDemo;

//这个类保存多线程的结果, 该类实例只会在新线程中。虽然在client线程会从实例中获取答案，但是获取中间封装了一层FutureData类
//FutureData类只要线程安全，则该类不需要进行上锁等操作。
public class RealData implements Data{

	private final String content;
	
	
	//多线程的具体执行，是在该类的构造函数中执行的，这样可以确保final的成员进行赋值。之所以用final，也是为了安全，答案不可变。
	public RealData(int count, char c){
		
		//*************Thread to Make Answer***************
		System.out.println("		making RealData(" + count + ", " + c + ") BEGIN");
		char[] buffer = new char[count];
		
		for(int i=0; i<count; i++){
			buffer[i] = c;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {}
		}
		System.out.println("		making RealData(" + count + ", " + c + ") END");
		//*************************************************
		
		this.content = new String(buffer);
	}
	
	@Override
	public String getContent() {
		return content;
	}

}
