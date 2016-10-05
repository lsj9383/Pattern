package ReadWriteLockDemo;

/*
 * 写者优先: 意味着若有写者等待，则后面的读者均wait。
 * 
 * 读者优先: 意味着就算有写者等待，所有读者也都不会wait。要在没有读者的时候，才会将锁交给写者。
 * 
 * */

public class ReadWriteLock {
	private int readingReaders = 0;		//正在读取的线程数量。rw.readLock()后，但未rw.readUnlock()的线程数。
	private int waitingWriters = 0;		//正在等待的写者线程数量。rw.writeLock()后，被wait的线程数。
	private int writingWriters = 0;		//正在写的线程数，非0即1。
	
	private boolean preferWriter = true;	//写者优先
	
	public synchronized void readLock() throws InterruptedException{
		while((writingWriters == 1) || (preferWriter && waitingWriters>0)){		//若有写者，或者有写者等待，则wait
			wait();			//每次wake，都要检查是否满足条件，满足条件则继续wait()
		}
		readingReaders++;	//读者线程+1
	}
	
	public synchronized void readUnlock() throws InterruptedException{
		readingReaders--;	//读者线程-1
		preferWriter = true;
		notifyAll();		//
	}
	
	public synchronized void writeLock() throws InterruptedException{
		waitingWriters++;		//等待写+1
		try{
			while(readingReaders>0 || writingWriters >0 ){		//有读者，或有写者，则等待。
				wait();
			}
		} finally {
			waitingWriters--;	//等待写者-1
		}
		writingWriters++;		//可以开始写了，写者+1
	}
	
	public synchronized void writeUnlock() throws InterruptedException{
		writingWriters--;
		preferWriter = false;
		notifyAll();
	}
}
