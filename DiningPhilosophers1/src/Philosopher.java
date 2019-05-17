import java.util.Random;

public class Philosopher extends Thread  {
	Object leftFork;
	Object rightFork;
	public Philosopher(Object leftFork, Object rightFork) {
		super();
		this.leftFork = leftFork;
		this.rightFork = rightFork;
	}	
	
	private void doSomething(String action) {
		System.out.println("Doing action "+action+" "+Thread.currentThread().getName());
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
			synchronized(leftFork) {
				System.out.println("Got left fork "+Thread.currentThread().getName());
				synchronized (rightFork) {
					System.out.println("Got right fork "+Thread.currentThread().getName());
					doSomething("eating");
					
				}
				System.out.println("Giving right fork "+Thread.currentThread().getName());
			}
			System.out.println("Giving left fork "+Thread.currentThread().getName());
			
		}
		
	}
	

}
