package Demo.ThreadDemo;

import java.util.concurrent.Callable;

import Demo.ThreadDemo.ThreadPool.*;

public class Main {
	
	static void SimpleThreadPoolTest(){
		SimpleThreadPool pool = new SimpleThreadPool(2);
		pool.Start();
		
		pool.AddJob(new Runnable(){
			@Override
			public void run() {
				for(int i=0; i<10; i++){
					System.out.println(Thread.currentThread().getId() + " : " + i);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {}
				}
			}
		});
		
		pool.AddJob(new Runnable(){
			@Override
			public void run() {
				for(int i=3; i<10; i++){
					System.out.println(Thread.currentThread().getId() + " : " + i);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {}
				}
			}
		});
		
		pool.AddJob(new Runnable(){
			@Override
			public void run() {
				for(int i=5; i<10; i++){
					System.out.println(Thread.currentThread().getId() + " : " + i);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {}
				}
			}
		});
	}
	
	static void FutureThreadPoolTest(){
		FutureThreadPool pool = new FutureThreadPool(2);
		
		pool.Start();
		
		Future<Integer> future1 = pool.AddJob(new Callable(){
									@Override
									public Object call() throws Exception {
										Thread.sleep(1000);
										return 1;
									}
								});
		
		System.out.println(future1.GetResult());
	}
	
	
	
	public static void main(String[] args){
		FutureThreadPoolTest();
		System.out.println("Main Done!");
	}
}
