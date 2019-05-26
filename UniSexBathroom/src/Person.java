import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Person implements Runnable{

	private final String name;

	private final Sex sex;

	private final Bathroom bathroom;

	private boolean canLeave;

	private boolean needBathroom;

	public Person(String name, Sex sex, Bathroom bathroom) {

		this.name = name;

		this.sex = sex;

		this.bathroom = bathroom;

		this.canLeave = false;

		this.needBathroom = true;

	}

	public String getName() {

		return this.name;

	}

	/**
	 * 
	 * Get person sex.
	 *
	 * 
	 * 
	 * @return Person sex
	 * 
	 */

	public Sex getSex() {

		return this.sex;

	}

	public void useBathroom() {

		// Enter the bathroom

		this.bathroom.addUser(this);

		// System.out.println(this.getName() + " entered the bathroom.");

		if (this.bathroom.isInTheBathroom(this)) {

			try {

				// Spend the time inside

				TimeUnit.SECONDS.sleep((new Random()).nextInt(1) + 1);

				this.canLeave = true;

				System.out.println(getName() + " Done");

			} catch (InterruptedException ex) {

			}

		}

	}

	public void leaveBathroom() {

		// Leave the bathroom

		this.bathroom.removeUser(this);

		// System.out.println(this.getName() + " left the bathroom.");

		this.canLeave = false;

		this.needBathroom = false;

	}

	@Override

	public void run() {

		System.out.println(this.getName());

		// If the person needs to go to the bathroom

		while (this.needBathroom) {

			try {

				Thread.sleep(500);

			} catch (InterruptedException ex) {

				ex.printStackTrace();

			}

			// If they want to use

			if ((this.bathroom.getCurrentSex().equals(this.getSex())

					|| this.bathroom.getCurrentSex().equals(Sex.NONE))

					&& !this.bathroom.isFull()

					&& !this.bathroom.isInTheBathroom(this)) {

				this.useBathroom();

			}

			// If they want to leave

			if (this.canLeave) {

				this.leaveBathroom();

			}

		}

	}

	@Override

	public String toString() {

		return "Person{" + "name = " + this.name + ", sex = " + this.sex + '}';

	}

}
