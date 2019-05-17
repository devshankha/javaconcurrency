
import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilisophers {
	public static void main(String[] args) {
		Philosopher[] philosophers = new Philosopher[5];
		Lock locks[] = new Lock[philosophers.length];
		for (int i =0; i < 5;i++) {
			locks[i] = new ReentrantLock();
		}
		System.out.println(Arrays.toString(locks));
		for (int i=0; i < 5;i++) {
			philosophers[i]  = new Philosopher(locks[i],locks[(i+1)%locks.length]);
		    System.out.println("Prining "+i+" and value "+(i+1)%locks.length);
		    if (i == 4)
		    	philosophers[i]  = new Philosopher(locks[(i+1)%locks.length],locks[i]);
		}
		for (int i=0; i < 5;i++) {
			philosophers[i].start();
		}
		
		
	}

}
