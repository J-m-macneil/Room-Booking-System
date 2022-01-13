package roomBookingSystemCoursework;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Room {
	// Declare the Scanner for keyboard input, to allow the user to input through the console.
	private static Scanner input = new Scanner(System.in);

	// Declare an new array list 'Rooms', to store the 'rooms.txt' data.
	private static final ArrayList<RoomBookingSystem> Rooms = new ArrayList<RoomBookingSystem>();

	public static void main(String[] args) throws Exception {
		// Access and load the rooms data.
		loadRooms();
		// Set a default value to choice, to allow user input.
		String choice = "";

		// Declare a do while loop, to repeat through the room booking systems main menu.
		do {
			// Print a repeating main menu, inside the do while loop.
			System.out.println("\n-- MAIN MENU --");
			System.out.println("1 - Reserve Room");
			System.out.println("2 - Cancel Room");
			System.out.println("3 - View Room Reservations");
			System.out.println("Q - Quit");
			System.out.print("Pick : ");

			// Allow the user to specify which option on they would like to select.
			// Allow the lower case value 'q' to be entered when exiting the program.
			choice = input.next().toUpperCase();

			// Declare a switch statement, to select one of the menu code blocks to be executed.
			switch (choice) {

			case "1": {
				// Set a choice for the method 'reserveRoom' to be executed.
				reserveRoom();
				break;
			}
			case "2": {
				// Set a choice for the method 'cancelRoom' to be executed.
				cancelRoom();
				break;
			}
			case "3": {
				// Set a choice for the method 'displayRooms' to be executed.
				displayRooms(Rooms);
				break;
			}
			case "Q": {
				// Set the inputed data to save to the file on exit.
				saveToFile();
				// Print a message to the console when the application is quit.
				System.out.println("-- GOODBYE --");
				break;
			}
			// Set a default value, for when errors occur in the console application.
			default: {
				// Set a default message, to allow the user to know when an incorrect value has been entered.
				System.out.println("Im sorry you have entered an incorrect value, please try again:");
			}

			}
			// Declare a while loop, to loop through the menu until the program is quit.
		} while (!choice.equals("Q"));
	}

	// Declare a 'loadRooms' method, to allow the rooms data to be accessed through the method.
	private static void loadRooms() throws FileNotFoundException {
		// Declaring a Scanner with 'rooms.txt' source, to allow the system to read the rooms data.
		Scanner file = new Scanner(new FileReader("rooms.txt"));

		// Loop through the files data.
		while (file.hasNext()) {
			// Set the roomNumber to the next int in the file.
			int roomNumber = file.nextInt();
			// Set the roomType to the next string in the file.
			String roomType = file.next().toLowerCase();
			// Set the roomPrice to the next double in the file.
			double roomPrice = Double.parseDouble(file.next());
			// Set the hasBalcony to the next boolean in the file.
			boolean hasBalcony = Boolean.parseBoolean(file.next());
			// Set the hasLounge to the next boolean in the file.
			boolean hasLounge = Boolean.parseBoolean(file.next());
			// Set the reserved to the next string in the file.
			String reserved = file.next().toLowerCase();

			// Add the variables to the 'Rooms' array list.
			Rooms.add(new RoomBookingSystem(roomNumber, roomType, roomPrice, hasBalcony, hasLounge, reserved));
		}
		// Close the scanner file.
		file.close();
	}

	// Declare a 'displayRooms' method, to allow the rooms to be displayed through the method.
	private static void displayRooms(ArrayList<RoomBookingSystem> rooms) {
		System.out.println("\n-- ROOMS AVAILABLE --");
		// For loop through the 'rooms' array list.
		for (int i = 0; i < rooms.size(); i++) {
			// Print to console the 'rooms' array list data and formatted string header.
			System.out.println(rooms.get(i).toStringHeader());
		}
	}

	// Declare a 'reserveRoom' method.
	private static void reserveRoom() {
		// Declare a new array list 'suitebale rooms' to represent suitable matches
		// Against the users input.
		ArrayList<RoomBookingSystem> suitableRooms = new ArrayList<RoomBookingSystem>();
		// Set a false value to the boolean 'roomReserved'.
		boolean roomReserved = false;

		System.out.print("\n--RESERVE A ROOM--");
		// Print to console a message, to ask user to select from the 'roomType' options.
		System.out.print("\nPlease enter to the console from the options below, which room type you wish to reserve.\n[Single/Double/Suite]:");
		// Allow the user to specify which room they wish to reserve in the console.
		String roomType = input.next().toLowerCase();

		// Print to console a message, to ask user to select yes or no for the balcony option.
		System.out.print("\nPlease enter to the console from the options below, if you wish to have a balcony.\n[Y/N]:");
		// Allow the user to specify if they wish to have a balcony in the room.
		String balconyAnswer = input.next();
		// Declare a false value, to the boolean hasBalcony.
		boolean hasBalcony = false;

		// Declare the upper and lower case 'y' to the true value of hasBalcony.
		if (balconyAnswer.toLowerCase().equals("y")) {
			// Declare a true value, to the boolean hasBalcony.
			hasBalcony = true;
		}

		// Print to console a message, to ask user to select yes or no for the lounge option.
		System.out.print("\nPlease enter to the console from the options below, if you wish to have a lounge.\n[Y/N]:");
		// Allow the user to specify if they wish to have a lounge in the room.
		String loungeAnswer = input.next();
		// Declare a false value, to the boolean hasLounge.
		boolean hasLounge = false;
		// Declare the upper and lower case 'y' to the true value of hasLounge.
		if (loungeAnswer.toLowerCase().equals("y")) {
			// Declare a true value, to the boolean hasLounge.
			hasLounge = true;
		}

		// Print to console a message, to allow the user to type a price limit.
		System.out.print("\nPlease enter to the console your maximun price limit: \n�");
		// Allow the user to specify a price limit in the console.
		double priceLimit = input.nextDouble();

		// Loop through rooms array lists data.
		for (int i = 0; i < Rooms.size(); i++) {
			// Get the files data.
			RoomBookingSystem currentRoom = Rooms.get(i);
			// Declare an if statement, to match the user input against 'the
			// meetsBookingCriteria' method.
			if (currentRoom.meetsBookingCritiria(roomType, priceLimit, hasBalcony, hasLounge, "free")) {
				suitableRooms.add(currentRoom);
			}
		}
		// Declare an if statement, to set any suitable rooms to display, unless their are none matching the criteria.
		if (suitableRooms.size() != 0) {
			displayRooms(suitableRooms);

			System.out.print("\nEnter room number: ");
			// Set the roomNumber to the next int, inputed in the console application.
			int roomNumber = input.nextInt();

			System.out.print("Enter email address: ");
			// Set the email to the next string,inputed in the console application.
			String email = input.next().toLowerCase();

			// Loop through the suitableRooms array list data.
			for (int i = 0; i < suitableRooms.size(); i++) {
				// Get the suitableRooms file data.
				RoomBookingSystem currentRoom = suitableRooms.get(i);
				// Declare an if statement to match the inputed roomNumber, to the suitableRooms data.
				if (currentRoom.getRoomNumber() == roomNumber) {
					// If the user input matches the suitableRooms file data, set reserved to the
					// users email.
					currentRoom.setReserved(email);
					// Declare a true value, for roomNumber and email match.
					roomReserved = true;
					// Print a message to the console, to allow the user to know when the room reservation has been accepted.
					System.out.print("\nThankyou for booking room:" + roomNumber + "\nIt will now be reserved in your email: " + email + "\n");
					break;

				}
			}
			// Declare an if statement, for roomNumber and email to have false match.
			if (!roomReserved) {
				// Print a message to the console, to warn the user they have entered an incorrect room number.
				System.out.println("\nSorry, there was no such room as: " + roomNumber);
				System.out.println("Please select the 'Reserve room' option and try again.Thankyou.");
			}
			// Declare an else statement, if no details match a suitable room from the user
			// input.
		} else {
			// Print to console to warn the user no rooms have fit their inputted criteria and allow them to try again.
			System.out.println("\nSorry no rooms are available with this critiria.\nPlease press '1' for 'Reserve Room' and try again:");
		}
	}

	// Declare a 'cancelRoom' method.
	private static void cancelRoom() {
		// Print to the console the array list 'Rooms', to show the full list of rooms data.
		displayRooms(Rooms);
		System.out.println("\n-- CANCEL A ROOM --");
		System.out.println("Enter room number: ");
		// Set the roomNumber to the next int, typed in the console application.
		int roomNumber = input.nextInt();
		System.out.print("Enter your email: ");
		// Set the email to the next int, typed in the console application.
		String email = input.next().toLowerCase();
		boolean roomCancelled = false;

		// Loop through the rooms file data.
		for (int i = 0; i < Rooms.size(); i++) {
			// Get the rooms file data.
			RoomBookingSystem cancel = Rooms.get(i);
			// Declare an if statement to match the roomNumber and email, agaisnt the rooms file data.
			if (cancel.meetsInput(roomNumber, email)) {
				// Set the reserved to 'free', in the rooms file data.
				cancel.setReserved("free");
				// Declare a true value, for the boolean 'roomCancelled'.
				roomCancelled = true;
				// Print a message for the true value, allowing the user to know when the cancellation has been accepted.
				System.out.println("\nThankyou, your room: " + roomNumber + " has been cancelled with the email:" + email);
				System.out.println("If you would like to book a different room, please press 'Reserve Room'.");
			}
		}
		// Set a false value, for the boolean 'roomCancelled, allowing the user to know when the cancellation has been declined.
		if (!roomCancelled) {
			//// Print a message for the boolean 'roomCancelled' false value.
			System.out.println("\nIm sorry, there was no booking with the room number: " + roomNumber + " and the email: " + email);
			System.out.println("Please select the 'Cancel room' option and try again.Thankyou.");
		}
	}

	// Declare a saveToFile method, which loops through rooms arraylist and maps the data to the rooms.txt file
	private static void saveToFile() {
		// Declare a try statement, to allow the saveToFile data to be tested for errors while it is being executed.
		try {
			// Declare a 'FileWriter' to write the data to the 'rooms.txt'
			FileWriter writer = new FileWriter("rooms.txt");
			// Declare a for loop to loop through the rooms array list data.
			for (int i = 0; i < Rooms.size(); i++) {
				// Declare a writer to write the text back to the rooms array list.
				writer.write(Rooms.get(i).toString() + "\n");
			}
			// Close the 'FileWriter' stream.
			writer.close();
			// Set a catch statement, to handle any exception of the try statement.
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}