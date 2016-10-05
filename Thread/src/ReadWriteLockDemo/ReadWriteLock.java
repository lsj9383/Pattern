package ReadWriteLockDemo;

public class ReadWriteLock {
	private int readingReaders = 0;		//���ڶ�ȡ���߳�������rw.readLock()�󣬵�δrw.readUnlock()���߳�����
	private int waitingWriters = 0;		//���ڵȴ���д���߳�������rw.writeLock()�󣬱�wait���߳�����
	private int writingWriters = 0;		//����д���߳�������0��1��
	
	private boolean preferWriter = true;	//д������
	
	public synchronized void readLock() throws InterruptedException{
		while((writingWriters == 1) || (preferWriter && waitingWriters>0)){		//����д�ߣ�������д�ߵȴ�����wait
			wait();			//ÿ��wake����Ҫ����Ƿ������������������������wait()
		}
		readingReaders++;	//�����߳�+1
	}
	
	public synchronized void readUnlock() throws InterruptedException{
		readingReaders--;		//�����߳�-1
		preferWriter = true;	//��д���˳�ʱ����ָ����߼���ʱ�Եȴ�д�ߵ����ӡ�
		notifyAll();			//
	}
	
	public synchronized void writeLock() throws InterruptedException{
		waitingWriters++;		//�ȴ�д+1
		try{
			while(readingReaders>0 || writingWriters >0 ){		//�ж��ߣ�����д�ߣ���ȴ���
				wait();
			}
		} finally {
			waitingWriters--;	//�ȴ�д��-1
		}
		writingWriters++;		//���Կ�ʼд�ˣ�д��+1
	}
	
	public synchronized void writeUnlock() throws InterruptedException{
		writingWriters--;
		preferWriter = false;	//д���˳�ʱ������ʱ�ö��ߵļ���������ڵȴ���д�ߣ�����д��ӵ��ʱ�������޷����롣
		notifyAll();
	}
}
