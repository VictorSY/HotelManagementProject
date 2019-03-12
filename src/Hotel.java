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

    // A binary search tree that contains CustomLinkedLists of similar rooms
    public BinarySearchTree<Room> rooms = new BinarySearchTree();

    // A hash map for looking for occupants of certain rooms
    private HashMap<Room, CustomLinkedList<Guest>> reservations = new HashMap<>();

    // A Index of guests that have been to the hotel
    private AlphabetIndex<Guest> guestIndex = new AlphabetIndex<>();


    // creates the Hotel object using a .txt file
    public Hotel(String hotelInfoText, Scanner console) throws FileNotFoundException {
        // stores a log of processes
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
    }


    // Uses scanner to prompt for guest info
    public void createGuest() {
        Guest newGuest = new Guest(console);
        guestIndex.add(newGuest);
        boolean tryAgain = true;
        while(findRoomForGuest(newGuest) == -1 && tryAgain) {
            tryAgain = false;
            System.out.println("No room found.");
            if(Guest.yesOrNoQuestions("Would you like to try again?", console)) {
                tryAgain = true;
                newGuest.askRoomQuestions(console);
            }
        }
    }


    // Calculates the cost of room and cost of total guests
    public double totalCost(Guest guest, Room room) {
        return Math.round((room.getCost(guest.isGovernment() || guest.isMilitary() || guest.isMembership()) +
                guest.costOfGuests()) * 100D) / 100D;
    }

    // Finds a room for guest and asks if they want it
    public int findRoomForGuest(Guest guest) {
        Room idealRoom = new Room(guest.getRoomSize(), guest.getBedType(), guest.getBedNum(), guest.hasPets()); // creation of the ideal room
        // for the customer
        CustomLinkedList<Room> possibleRooms = rooms.binarySearch(idealRoom); // find room matches for customer
        try {
            LinkedNode<Room> current = possibleRooms.findNode(0);
            while(current != null) {
                Room roomFound = current.data;

                // Tries to make a reservation. If confirmed by customer, the room number is returned
                if(makeReservation(guest, roomFound, console)) {
                    return roomFound.getRoomNumber();
                }
                current = current.nextNode;
            }
            // if no matched rooms are accepted
            return -1;
        } catch(NullPointerException e) {
            // Sometimes the ideal room cannot find any matches in the binary tree
            return -1;
        }
    }

    // Confirms that the user wants to make a reservation
    public boolean makeReservation(Guest guest, Room room, Scanner console) {
        System.out.println("Do you want to reserve room: " + room.getRoomNumber());
        System.out.println(room);
        System.out.printf("\tOccupant Cost: $%.2f\n", guest.costOfGuests());
        System.out.println("Total Cost (including discounts): $" + totalCost(guest, room));
        if(Guest.yesOrNoQuestions("Confirm (Yes or No): ", console)) {
            // if time is ever implemented, it will be implemented in Guest.makeReservation(Room)
            if(!guest.makeReservation(room)) {
                return false;
            }
            reservations.put(room, new CustomLinkedList<>(guest));
            System.out.println("You have reserved room " + room + ".\n\n" +
                    "Thank you for choosing " + hotelName + ".");
            System.out.println(receipt(guest));
            return true;
        } else {
            System.out.println("Looking for other rooms...");
            return false;
        }
    }

    // Cancels a reservation given a guest
    public void cancelReservation(Guest guest) {
        if(Guest.yesOrNoQuestions("Are you sure you want to cancel? ", console)) {

            // adds negative sign to signify refund
            String receipt = receipt(guest);

            // needs to be FIXED
            receipt.replaceAll("\\$", "hhh");
            System.out.println(receipt);

            // remove guest from multiple places
            reservations.get(guest.getRoom()).remove(guest);
            guestIndex.remove(guest);
            guest.deleteReservation();

        } else {
            System.out.println("Cancelled reservation cancellation.");
        }
    }

    // Prints receipt
    public String receipt(Guest guest) {
        return "Receipt" +
                "\n\tHotel: " + hotelName +
                "\n\tGuest: " + guest.getName() +
                "\n\tBilling Info: " + guest.getCardNum() +
                "\n\t" + guest.getRoom().toString() +
                "\n\tOccupants Cost: $" + guest.costOfGuests() +
                "\n\tTotal Cost: $" + totalCost(guest, guest.getRoom()) + "\n";
    }

    // Finds the guest in guestList and can be used to see what room they're in
    public Guest findGuestInList(String name) {
        ArrayList<Guest> list = guestIndex.find(name);
        if(list.size() == 1) {
            return list.get(0);
        }
        if(list.size() > 1) {
            for(Guest guest : list) {
                if(Guest.yesOrNoQuestions("Is this you? \n" + guest.toFormattedString(), console)) {
                    return guest;
                }
            }
        }
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
