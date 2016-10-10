package Demo.ThreadDemo;

import Demo.ThreadDemo.ThreadPool.*;

public class Main {
	public static void main(String[] args){
		ThreadPool pool = new SimpleThreadPool(2);
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
		
		System.out.println("Main Done!");
	}
}
