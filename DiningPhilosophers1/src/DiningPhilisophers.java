import java.util.Arrays;

public class DiningPhilisophers {
	public static void main(String[] args) {
		Philosopher[] philosophers = new Philosopher[5];
		Object locks[] = new Object[philosophers.length];
		for (int i =0; i < 5;i++) {
			locks[i] = new Object();
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
