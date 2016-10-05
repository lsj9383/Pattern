package ReadWriteLockDemo;

import java.util.Random;

public class WriteThread extends Thread{
	private static final Random random = new Random();
	
	private final Data data;
	private final String filler;
	private int index = 0;
	
	
	public WriteThread(Data data, String filler){
		this.data = data;
		this.filler = filler;
	}
	
	@Override
	public void run(){
		try {
			while(true){
				data.write(nextChar());
				Thread.sleep(random.nextInt(3000));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private char nextChar(){
		char c = filler.charAt(index);
		index = (++index)%filler.length();
		return c;
	}
}
