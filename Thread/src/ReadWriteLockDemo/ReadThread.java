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
				//一直读，无延时
				char[] readbuf = data.read();
				System.out.println(Thread.currentThread().getName() + "reads " + String.valueOf(readbuf));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
