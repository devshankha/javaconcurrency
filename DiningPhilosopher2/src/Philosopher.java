
import java.util.Random;
import java.util.concurrent.locks.Lock;

public class Philosopher extends Thread {
	Lock leftLock;
	Lock rightLock;

	public Philosopher(Lock l, Lock r) {
		super();
		this.leftLock = l;
		this.rightLock = r;
	}

	private void doSomething(String action) {
		System.out.println("Doing action " + action + " " + Thread.currentThread().getName());
	}

	private int getRandomValue() {
		Random r = new Random();
		return r.nextInt(100);
	}

	@Override
	public void run() {
		while (true) {
			int sleepTime = getRandomValue();
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			doSomething("thinking");
			leftLock.lock();
			System.out.println("Acquired left lock "+Thread.currentThread().getName());
			try {
				if (!rightLock.tryLock()) {
					leftLock.unlock();
					continue;
					
				}
				System.out.println("Acquired right lock "+Thread.currentThread().getName());
				
			} finally {
				leftLock.unlock();
				rightLock.unlock();
				
				System.out.println("Leaving left and right lock "+Thread.currentThread().getName());
			}
		
			System.out.println("Giving left fork " + Thread.currentThread().getName());

		}

	}

}
