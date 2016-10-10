package BlakingPatternDemo;

import java.io.IOException;
import java.util.Random;

public class ChangerThread extends Thread {
	
	private Data data;
	private Random random = new Random();
	
	public ChangerThread(String name, Data data){
		super(name);
		this.data = data;
	}
	
	@Override
	public void run(){
		for(int i=0; true; i++){
			try {
				data.change("No." + i);
				Thread.sleep(random.nextInt(1000));
				data.save();		//延时一段时间后，明确要求存档，在延时的时间里，或许会被后台的SaverThread线程作存档
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
