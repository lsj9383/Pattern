package BlakingPatternDemo;

import java.io.IOException;

public class SaverThread extends Thread{
	private Data data;
	
	public SaverThread(String name, Data data){
		super(name);
		this.data = data;
	}
	
	@Override
	public void run(){
		try {
			while(true)
			{
				data.save();			//后台在不停的存档
				Thread.sleep(1000);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
