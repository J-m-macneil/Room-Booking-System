package roomBookingSystemCoursework;

public class RoomBookingSystem {

	//Set private variables to be directly accessed by the Java class that owns them.
	private int roomNumber;
	private String roomType;
	private double roomPrice;
	private boolean hasBalcony;
	private boolean hasLounge;
	private String reserved;

	
	public RoomBookingSystem(int roomNumber, String roomType, double roomPrice, boolean hasBalcony, boolean hasLounge,
			String reserved) {
        
		//Declare a this.variable to allow the current object from the method to be called.
		this.roomNumber = roomNumber;
		this.roomType = roomType;
		this.roomPrice = roomPrice;
		this.hasBalcony = hasBalcony;
		this.hasLounge = hasLounge;
		this.reserved = reserved;
	}

	//Declare a int 'getRoomNumber', to allow the private variable to be accessed by a java class. 
	public int getRoomNumber() {
		return roomNumber;
	}

	//Declare a String 'getRoomType', to allow the private variable to be accessed by a java class. 
	public String getRoomType() {
		return roomType;
	}

	//Declare a double 'getRoomPrice', to allow the private variable to be accessed by a java class. 
	public double getRoomPrice() {
		return roomPrice;
	}

	//Declare a boolean 'getRoomNumber', to allow the private variable to be accessed by a java class. 
	public boolean getHasBalcony() {
		return hasBalcony;
	}

	//Declare a boolean 'getRoomNumber', to allow the private variable to be accessed by a java class. 
	public boolean getHasLounge() {
		return hasLounge;
	}

	//Declare a String 'getReserved', to allow the private variable to be accessed by a java class. 
	public String getReserved() {
		return reserved;
	}

	//Declare a 'setResrved', to allow the private variable to be accessed and update the value in a java class. 
	public void setReserved(String reserved) {
		this.reserved = reserved;
	}

	//Declare a 'toStringheader' to print and format a message alongside the varible types when executed.
	public String toStringHeader() {
		return "Room Number:" + roomNumber + " " + " Room Type: " + roomType + " " + "Room Price:"
				+ String.format("%.2f", roomPrice) + " " + " Balcony: " + hasBalcony + " " + "Lounge:" + hasLounge + " "
				+ "Reserved:" + reserved;
	}

	@Override
	//Declare a 'toString' to format the new file data, when 'saveToFile' to executed.
	public String toString() {
		return roomNumber + " " + roomType + " " + String.format("%.2f", roomPrice) + " " + hasBalcony + " " + hasLounge
				+ " " + reserved;
	}

	//Declare a 'meetsBookingCriteria' class, with the variables needed to display suitable rooms.
	public boolean meetsBookingCritiria(String roomType, double roomPrice, Boolean hasBalcony, Boolean hasLounge,
			String reserved) {
		//Declare an if statement to get the values,set the values and return the values of the user input.
		if (this.getRoomType().equals(roomType) && this.getRoomPrice() <= roomPrice
				&& this.getHasBalcony() == hasBalcony && this.getHasLounge() == hasLounge
				&& this.reserved.equals(reserved)) {
			//Set a true value, of 'meetsBookingCriteria'.
			return true;
		}
		//Set a false value, of 'meetsBookingCriteria'.
		return false;
	}
	
	//Declare a 'meetsInput' class, with the variables needed to book a room.
	public boolean meetsInput(int roomNumber, String email) {
		//Declare an if statement to get the values,set the values and return the values of the user input.
		if (this.getRoomNumber() == roomNumber && this.getReserved().equals(email)) {
			//Set a true value, of 'meetsInput'.
			return true;
		}
		//Set a false value, of 'meetsInput'.
		return false;
	}
}
		

