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
        if (room < 10) {
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
    // creates a Guest using the console scanner as the parameter
    Guest guest = new Guest(console);
    // makes sure the Guest hasn't made a reservation
    if(!idList.contains(guest.getUniqueId())) {
      // finds a room for a guest and adds them to the guesList if they book the room
      if (findRoomForGuest(guest) == 0) {
        System.out.println("Sorry, no room found.");
      } else {
        guestList.add(guest);
        idList.add(guest.getUniqueId());
      }
      // if a Guest has made a reservation
    } else {
      // asks the Guest if they would like to cancel the Reservation
        if(Guest.yesOrNoQuestions("Seems like you have a reservation. Would you like to cancel? (Y or N)\n"
                                  , console)) {
        // removes Guest from the guestList if they wanted to cancel the reservation
        guestList.remove(guest);
        // removes their Unique ID from the database
        idList.remove(guest.getUniqueId());
        // gives the Guest a notificion that their cancellation was a success
        System.out.println("Success! Thank you for your stay!");
      }
    }

    public void setResDate(String date1, String date2){
        String d1 = date1.replace("/", "");
        String d2 = date2.replace("/", "");

        int month1 = Integer.parseInt(d1.substring(0, 2));
        int day1 = Integer.parseInt(d1.substring(2, 4));
        int year1 = Integer.parseInt(d1.substring(4, 8));

        int month2 = Integer.parseInt(d2.substring(0, 2));
        int day2 = Integer.parseInt(d2.substring(2, 4));
        int year2 = Integer.parseInt(d2.substring(4, 8));

        Date from = new Date(day1, month1, year1);
        Date to = new Date(day2, month2, year2);
    }

    //returns cost of room and cost of total guests
    public double totalCost(Guest guest, Room room) {
        return (room.getCost(guest.isGovernment() || guest.isMilitary() || guest.isMembership()) + guest.costOfGuests());
    }

    public void checkIn(Room room, Guest guest) {
        // Guest check in (use Room class)
        room.setGuest(guest);
    }

    public void checkOut(Room room, Guest guest) {
        // Guest check out (use Room class)
        room.removeGuest(guest);
        room = null;
    }


    public boolean makeReservation(Guest guest, int roomNumber, Scanner console) {
        System.out.println("Do you want to reserve room: " + roomNumber);
        System.out.println("Cost: $" + totalCost(guest, floors.get(roomNumber / 100 - 1).get(roomNumber % 100 - 2)));
        System.out.println(floors.get(roomNumber / 100 - 1).get(roomNumber % 100 - 2)); // Minus 2 because element 0 is room 1 so room 3 is stored in element 1
        if (Guest.yesOrNoQuestions("Yes or No: ", console)) {
            System.out.println("Please type year, month and day of the reservation in this format (MM/DD/YYYY)\nFrom: ");
            String from = console.nextLine();
            System.out.println("\nTo:");
            String to = console.nextLine();
            setResDate(from, to);

            checkIn(floors.get(roomNumber / 100 - 1).get(roomNumber % 100 - 2), guest); // Minus 1 because first floor is element 0
            guestList.add(guest);
            System.out.println("You have reserved room " + roomNumber + ". Thank you for choosing " + hotelName + ".");
            System.out.println(receipt(guest));
            return true;
        } else {
            System.out.println("Looking for other rooms...");
            return false;
        }
    }
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
      System.out.println("You have reserved room " + room + ". Thank you for choosing " + hotelName + ".");
      guest.checkIn(room);
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
            guest.checkOut();
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
