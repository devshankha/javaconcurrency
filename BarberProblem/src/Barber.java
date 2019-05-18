import java.util.List;
import java.util.Random;

public class Barber extends Thread {
	List<Customer> customers;
	Barber(List<Customer> l) {
		customers = l;
	}
	public void run() {
		Customer s = null; 
		while (true) {
			synchronized(customers) {
			while(customers.isEmpty()) {
				try {
				customers.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
					
				}
			}
			s = customers.remove(0);
			}
			try {
			Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			serviceCustomer(s);
		}
		
	}
	private void serviceCustomer(Customer c) {
		System.out.println("Servicing the customer "+c);
		//Random r = new Random();
		//int time = r.nextInt(100000);
		
		
	}

}

class Customer {
	int id;
	Customer (int i) {
		id = i;
	}
	public String toString() {
		return new Integer(id).toString();
	}
	
}

class Coordinator extends Thread {
	int count;
	private List<Customer> customers;
	int size;
	
	public Coordinator(List<Customer> customers, int size) {
		super();
		this.customers = customers;
		this.size = size;
	}
	public void run() {
		while (true) {
			Random r = new Random();
			int time = r.nextInt(100);
			try {
			Thread.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (customers) {
				if (customers.size() > size) {
					System.out.println("Customer list is full, hence leaving");
					continue;
				}
				count++;
				Customer c = new Customer(count);
				System.out.println("Adding a new customer "+c);
				customers.add(new Customer(count));
				customers.notify();
				
			}
		}
	}

	
}
