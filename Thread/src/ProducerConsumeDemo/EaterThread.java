package ProducerConsumeDemo;

import java.util.Random;

public class EaterThread extends Thread{
	private final Random random;
	private final Table table;
	
	public EaterThread(String name, Table table, long seed){
		super(name);
		this.table=table;
		this.random=new Random(seed);
	}
	
	@Override
	public void run(){
		while(true){
			try {
				String cake = table.take();
				Thread.sleep(random.nextInt(1000));
			} catch (InterruptedException e) {}
		}
	}
}
