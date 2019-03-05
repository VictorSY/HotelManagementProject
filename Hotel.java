import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// The Hotel object to store information and data regarding guests and rooms
public class Hotel {
  
  // A field to store the name of a Hotel
  private String hotelName;
  // The scanner to be used for command line prompts
  Scanner console;
  
  // stores the guests in the hotel
  private ArrayList<Guest> guestList = new ArrayList<>();
  // stores the floors of the hotel, with and ArrayList of an ArrayList of Rooms
  private ArrayList<ArrayList<Room>> floors = new ArrayList<>();
  // stores the unique id's of guests as a list, used for cancelling a reservation
  private ArrayList<String> idList = new ArrayList<>();

  
  // creates the Hotel object using a .txt file
  public Hotel(String hotelInfoText, Scanner console) throws FileNotFoundException {
      // Console input scanner assigned to object
      this.console = console;
    Scanner hotelData = new Scanner(new File(hotelInfoText));
    // grabs the name of the Hotel from the file
    this.hotelName = hotelData.nextLine().trim();
    // uses the "createHotelFloorsAndRooms" method to create the Floors and Rooms from the file
    createHotelFloorsAndRooms(hotelData);
  }
  
  // Creates rooms on each floor with text file
  private void createHotelFloorsAndRooms(Scanner hotelData) {
    int floor = 0;
    // loops through the file
    while (hotelData.hasNextLine()) {
      // changes the floor number for each set of rooms
      floor++;
      // creates the floor ArrayList of Rooms
      ArrayList<Room> newFloor = new ArrayList<>();
      // grabs the number of Rooms for a floor from the file
      int numberOfRooms = hotelData.nextInt();
      hotelData.nextLine(); // takes escape characters at the end of the line into account
      // loops through the numberOfRooms in a floor
      for (int room = 0; room < numberOfRooms; room++) {
        // grabs the data from the file
        String textData = hotelData.nextLine();
        // stores the data for the room
          if(room + 1 < 10) {
          textData += " room number " + floor + "0" + (room + 1);
        } else {
          textData += " room number " + floor + (room + 1);
        }
        // adds the Room to the ArrayList of Rooms
        newFloor.add(new Room(textData)); // previous argument createRoomFromText(textData)
      }
      // adds a floor to the floors field
      floors.add(newFloor);
    }
  }
  
  
  // Uses scanner to prompt for guest info
  public void createGuest() {

  }
  
  // Calculates the cost of room and cost of total guests
  public double totalCost(Guest guest, Room room) {
    return Math.round((room.getCost(guest.isGovernment() || guest.isMilitary() || guest.isMembership()) + 
                       guest.costOfGuests())*100D)/100D;
  }
  
  // Finds a room int the hotel given guest's requirements
  public int findRoomForGuest(Guest guest) {
    System.out.println(guest);    
    for (ArrayList<Room> floor : floors) {
      for (Room room : floor) {
        if (!room.isEmpty()) { // Check if room is empty
          continue;
        } else if (!room.isCleaned()) {  // Check if room is cleaned
          continue;
        } else if (guest.getBedNum() > room.getBedNum()) { // Check guest req for number of beds
          continue;
        } else if(!guest.getBedType().equals(room.getBedSize())) { // Check guest req for bed type
          continue;
        } else if (guest.getRoomSize() > room.getRoomSize()) { // Check guest req for room size
          continue;
        } else if(guest.hasPets() != room.getAllowPets()) { // Check guest req for pets
          continue;
        } else if(makeReservation(guest, room, console)) { // Check if guest accepted reservation
          return room.getRoomNumber();
        }
      }
    }
    return 0;
  }
  
  // Confirms that the user wants to make a reservation
  public boolean makeReservation(Guest guest, Room room, Scanner console) {
    System.out.println("Do you want to reserve room: " + room.getRoomNumber());
    System.out.print("Cost: $");
    System.out.printf(totalCost(guest, room) + "\n", "%.2f");
    System.out.println(room);
    if (Guest.yesOrNoQuestions("Yes or No: ", console)) {
      if(!guest.checkIn(room)) {
        return false;
      }
      guestList.add(guest);
      System.out.println("You have reserved room " + room + ".\n\nThank you for choosing " + hotelName + ".");
      System.out.println(receipt(guest));
      return true;
    } else {
      System.out.println("Looking for other rooms...");
      return false;
    }
  }

    public void cancelReservation(Guest guest) {
        if(Guest.yesOrNoQuestions("Are you sure you want to cancel? ", console)) {
            String receipt = receipt(guest);
            int dollarSignLocation = receipt.indexOf("$");
            receipt = receipt.substring(0, dollarSignLocation) + "-" + receipt.substring(dollarSignLocation); // adds negative sign to signify refund
            System.out.println(receipt);
            guestList.remove(guest);
          //idList.remove(guest.getUniqueId());
          //guest.cancelReservation();
        } else {
            System.out.println("Cancelled cancellation process.");
        }
    }
  
  // Prints receipt
  public String receipt(Guest guest) {
    return "Receipt\n" +
      "\tHotel: " + hotelName +
      "\n\tGuest: " + guest.getName() +
      "\n\tBilling Info: " + guest.getCardNum() +
      "\n\t" + guest.getRoom().toString() +
      "\n\tCost: $" + totalCost(guest, guest.getRoom()) + "\n" +
      guest.toString();
  }
  
  // Finds the guest in guestList and can be used to see what room they're in
  public Guest findGuestInList(String name) {
    for (Guest guest : guestList) {
      if (guest.getName().equals(name)) {
        return guest;
      }
    }
    throw new IllegalArgumentException();
  }
  
  // Provides a String representation of the Hotel
  public String toString() {
    String returnString = hotelName + "\n";
    for(ArrayList<Room> floor : floors) {
      for(Room room : floor) {
        returnString += (room.toString() + "\n\n");
      }
    }
    return returnString;
  }
  
  // accessor method for the guestList
  public ArrayList<Guest> getGuestList() {
    return guestList;
  }
  
}
