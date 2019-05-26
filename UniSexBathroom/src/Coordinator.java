import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Coordinator {
	public static void main(String[] args) {
		  Bathroom bathroom = Bathroom.getInstance();
	      List<Person> users = new ArrayList<>();
	      for (int i = 0; i < 7; i++) {
	    	  if (new Random().nextInt(2) == 1) {

	                // Creates man

	                users.add(new Person(("David" + i),Sex.MALE, bathroom));

	            } else {

	                // Creates    woman

	                users.add(new Person(("Maria" + i), Sex.FEMALE, bathroom));

	            }
	    	  
	      }
	   // Stats persons

	        users.stream().map((Person) -> new Thread(Person)).forEach((t) -> {

	            t.start();

	        });

	        // 

		
		  users.stream().map((Person) -> new Thread(Person)).forEach((t) -> {
		  
		  try {
		  
		  t.join();
		  
		  } catch (InterruptedException ex) {
		  
		  ex.printStackTrace();
		  
		  }
		  
		  });
		 
	}

}
