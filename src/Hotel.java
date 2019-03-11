import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// The Hotel object to store information and data regarding guests and rooms
public class Hotel {

    // A field to store the name of a Hotel
    private String hotelName;
    // The scanner to be used for command line prompts
    private Scanner console;

    // Keeps a log of everything that happens
    private Log log;

    // stores the guests in the hotel
    //private ArrayList<Guest> guestList = new ArrayList<>();
    // stores the floors of the hotel, with and ArrayList of an ArrayList of Rooms
    //private ArrayList<ArrayList<Room>> floors = new ArrayList<>();

    public BinarySearchTree<Room> rooms = new BinarySearchTree();
    private HashMap<Room, CustomLinkedList<Guest>> reservations = new HashMap<>();
    private AlphabetIndex<Guest> guestIndex = new AlphabetIndex<>();


    // creates the Hotel object using a .txt file
    public Hotel(String hotelInfoText, Scanner console, Log log) throws FileNotFoundException {
        // stores a log of processes
        this.log = log;
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
        log.write("Adding rooms to binary trees...");
        int floor = 0;
        // loops through the file
        while(hotelData.hasNextLine()) {
            // changes the floor number for each set of rooms
            floor++;
            // grabs the number of Rooms for a floor from the file
            int numberOfRooms = hotelData.nextInt();
            hotelData.nextLine(); // takes escape characters at the end of the line into account
            // loops through the numberOfRooms in a floor
            for(int room = 0; room < numberOfRooms; room++) {
                // grabs the data from the file
                String textData = hotelData.nextLine();
                // stores the data for the room
                if(room + 1 < 10) {
                    textData += " room number " + floor + "0" + (room + 1);
                } else {
                    textData += " room number " + floor + (room + 1);
                }
                // adds the Room to the ArrayList of Rooms
                rooms.add(new Room(textData)); // Implementing BST
            }
        }
        log.write("Finished adding rooms to binary trees.");
    }


    // Uses scanner to prompt for guest info
    public void createGuest() {
        log.write("Started guest creation in Hotel...");
        Guest newGuest = new Guest(console);
        guestIndex.add(newGuest);
        if(findRoomForGuest(newGuest) == -1) {
            log.write("No room found for guest " + newGuest.getName());
            if(Guest.yesOrNoQuestions("Would you like to try again?", console)) {
                log.write("Trying again...");
                newGuest.askRoomQuestions(console);
            }
        }
        log.write("Finished guest creation.");
    }

    // Calculates the cost of room and cost of total guests
    public double totalCost(Guest guest, Room room) {
        return Math.round((room.getCost(guest.isGovernment() || guest.isMilitary() || guest.isMembership()) +
                guest.costOfGuests()) * 100D) / 100D;
    }

    public int findRoomForGuest(Guest guest) {
        log.write("Finding room for guest...");
        Room idealRoom = new Room(guest.getRoomSize(), guest.getBedType(), guest.getBedNum(), guest.hasPets());
        CustomLinkedList<Room> possibleRooms = rooms.binarySearch(idealRoom);
        try {
            LinkedNode<Room> current = possibleRooms.findNode(0);
            while(current != null) {
                Room roomFound = current.data;

                if(makeReservation(guest, roomFound, console)) {
                    return roomFound.getRoomNumber();
                }

                current = current.nextNode;
            }
            return -1;
        } catch(NullPointerException e) {
            log.write("" + e);
            return -1;
        }
    }

    // Confirms that the user wants to make a reservation
    public boolean makeReservation(Guest guest, Room room, Scanner console) {
        log.write("Making reservation for " + guest.getName() + "...");
        System.out.println("Do you want to reserve room: " + room.getRoomNumber());
        System.out.print("Cost: $");
        System.out.printf(totalCost(guest, room) + "\n", "%.2f");
        System.out.println(room);
        if(Guest.yesOrNoQuestions("Confirm (Yes or No): ", console)) {
            if(!guest.makeReservation(room)) {
                return false;
            }
            reservations.put(room, new CustomLinkedList<>(guest));
            System.out.println("You have reserved room " + room + ".\n\n" +
                    "Thank you for choosing " + hotelName + ".");
            System.out.println(receipt(guest));
            log.write("Guest " + guest.getName() + " reserved room " + room.getRoomNumber());
            return true;
        } else {
            log.write(guest.getName() + " did not confirm.");
            System.out.println("Looking for other rooms...");
            return false;
        }
    }

    public void cancelReservation(Guest guest) {
        log.write("Cancelling reservation for " + guest.getName());
        if(Guest.yesOrNoQuestions("Are you sure you want to cancel? ", console)) {
            reservations.get(guest.getRoom()).remove(guest);
            String receipt = receipt(guest);
            int dollarSignLocation = receipt.indexOf("$");
            receipt = receipt.substring(0, dollarSignLocation) + "-" + receipt.substring(dollarSignLocation); // adds negative sign to signify refund
            System.out.println(receipt);
            log.write(guest.getName() + " has cancelled their reservation.");
            guest.deleteReservation();
        } else {
            log.write(guest.getName() + "'s cancellation was cancelled.");
            System.out.println("Cancelled reservation cancellation.");
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
                guest.toFormattedString();
    }

    // Finds the guest in guestList and can be used to see what room they're in
    public Guest findGuestInList(String name) {
        log.write("Finding guest " + name + " in guest index.");
        ArrayList<Guest> list = guestIndex.find(name);
        for(Guest guest : list) {
            if(guest.getName().equals(name)) {
                log.write("Guest found.");
                return guest;
            }
        }
        log.write("Guest not found.");
        return null;
    }

    // Provides a String representation of the Hotel
    public String toString() {
        String returnString = hotelName + "\n";
        rooms.printTreeInOrder();
        return returnString;
    }

    // accessor method for the guestList
    public AlphabetIndex getGuestIndex() {
        return guestIndex;
    }

}
