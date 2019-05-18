import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue<T> {
	T arr[];
	int size;
	int insertIndex;
	int removeIndex;
	int count;
	Lock lock = new ReentrantLock();
	Condition emptyStack = lock.newCondition();
	Condition fullStack = lock.newCondition();
	public BlockingQueue(int s) {
		size = s;
		arr = (T[])new Object[s];
		insertIndex = 0;
		removeIndex = 0;	
		count = 0;
	}
	void put(T x) {
		lock.lock();
		try {
			if (count == size)
				try {
				emptyStack.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			arr[insertIndex++] = x;
			count++;
			fullStack.signal();
			
		}  finally {
			lock.unlock();
			
		}
		
	}
	
	T take() {
		lock.lock();
		try {
			if (count == 0)
				try {
				fullStack.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			T val =arr[removeIndex--];
			count--;
			emptyStack.signal();
			return val;
			
		}  finally {
			lock.unlock();
			
		}
		
	}
	public static void main(String[] args) {
		BlockingQueue<String> m = new BlockingQueue<String>(5);
		m.put("a");
		m.put("b");
		m.put("c");
		m.put("d");
		m.put("e");
		m.put("f");
		System.out.println("items inserted");
	}

}
