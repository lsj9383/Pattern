package ReadWriteLockDemo;

public class ReadWriteLock {
	private int readingReaders = 0;		//正在读取的线程数量。rw.readLock()后，但未rw.readUnlock()的线程数。
	private int waitingWriters = 0;		//正在等待的写者线程数量。rw.writeLock()后，被wait的线程数。
	private int writingWriters = 0;		//正在写的线程数，非0即1。
	
	private boolean preferWriter = true;
	
	public synchronized void readLock() throws InterruptedException{
		while((writingWriters == 1) || (preferWriter && waitingWriters>0)){		//若有写者，或者有写者等待，则wait
			wait();			//每次wake，都要检查是否满足条件，满足条件则继续wait()
		}
		readingReaders++;	//读者线程+1
	}
	
	public synchronized void readUnlock() throws InterruptedException{
		readingReaders--;		//读者线程-1
		preferWriter = true;	//当有读者退出，在readLock中就要重新重视等待的写者了，新加入的读者将会因为等待的写者而阻塞。
		notifyAll();			//
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
		preferWriter = false;	//每次有写者退出，都会在readLock()的wait处暂时无视等待的写者。通过一批等待的读者，避免在写者拥挤时，会让读者一直阻塞。
		notifyAll();
	}
}
