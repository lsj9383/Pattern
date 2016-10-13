package ProducerConsumeDemo;

import java.util.Random;

public class MakerThread extends Thread {

	private final Random random;
	private final Table table;
	private static int id=0;		//����������MakerThread���ã���Ϊ��static������Զ����ã�
	
	public MakerThread(String name, Table table, long seed){
		super(name);
		this.random = new Random(seed);
		this.table=table;
	}
	
	@Override
	public void run(){
		while(true){
			try {
				Thread.sleep(random.nextInt(1000));
				String cake = "[Cake No."+nextId()+"by "+getName()+"";
				table.put(cake);
			} catch (InterruptedException e) {}
			
		}
	}
	
	private static synchronized int nextId(){
		return id++;
	}
}
