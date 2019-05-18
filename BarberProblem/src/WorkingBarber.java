import java.util.ArrayList;
import java.util.List;

public class WorkingBarber {
	public static void main(String[] args) {
		List<Customer> l1 = new ArrayList<Customer>();
		int size = 5;
		Thread barber = new Barber(l1);
		Thread coordinator = new Coordinator(l1, size);
		coordinator.start();
		barber.start();
	}

}
