package SingleThreadExe;

public class User extends Thread{
	final private SharedResource sr;
	
	public User(SharedResource sr){
		this.sr = sr;
	}
	
	@Override
	public void run(){
		for(int i=0; i<2; i++){
			sr.Method(null);
		}
		System.out.println("User Thread Done!");
	}
}
