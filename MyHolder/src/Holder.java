import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Holder {
	int value;
	boolean isSet;
	Lock lock = new ReentrantLock();
	Condition canRead = lock.newCondition();
	Condition canWrite = lock.newCondition();
	public Holder(int v) {
		value = v;
		isSet = false;
	}
	public int get() {
		try {
		lock.lock();
		while (!isSet)
			try {
			canRead.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		canWrite.signal();
		isSet = false;
		return value;
		} finally {
			lock.unlock();
		}
	}
	
	public void set(int v) {
		try {
			lock.lock();
			while (isSet)
				try {
				canWrite.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			canRead.signal();
			isSet = true;
			
		} finally {
			lock.unlock();
		}
	}
	

}
