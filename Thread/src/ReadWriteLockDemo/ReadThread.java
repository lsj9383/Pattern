package ReadWriteLockDemo;

import java.util.Random;

public class ReadThread extends Thread {
	private final Data data;

	public ReadThread(Data data){
		this.data = data;
	}
	
	@Override
	public void run(){
		try {
			while(true){
				//һֱ��������ʱ
				char[] readbuf = data.read();
				System.out.println(Thread.currentThread().getName() + "reads " + String.valueOf(readbuf));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
