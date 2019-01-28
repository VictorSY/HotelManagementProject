import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Hotel {

    private String hotelName;
    Scanner console = new Scanner(System.in);

    private ArrayList<Guest> guestList = new ArrayList<>();
    private ArrayList<ArrayList<Room>> floors = new ArrayList<>();

    public Hotel(String hotelInfoText) throws FileNotFoundException {
        Scanner hotelData = new Scanner(new File(hotelInfoText));
        this.hotelName = hotelData.nextLine().trim();
        createHotelFloorsAndRooms(hotelData);
//        for (ArrayList<Room> floor : floors) {
//            for (Room room : floor) {
//                System.out.println(room.toString() + '\n');
//            }
//        }
    }

    // Creates rooms on each floor with text file
    private void createHotelFloorsAndRooms(Scanner hotelData) {
        int floor = 0;
        while (hotelData.hasNextLine()) {
            floor++;
            ArrayList<Room> newFloor = new ArrayList<>();
            int numberOfRooms = hotelData.nextInt();
            hotelData.nextLine(); // takes escape characters at the end of the line into account
            for (int room = 0; room < numberOfRooms; room++) {
                String textData = hotelData.nextLine();
                if (room < 10) {
                    textData += " room number " + floor + "0" + (room + 1);
                } else {
                    textData += " room number " + floor + (room + 1);
                }
                newFloor.add(new Room(textData)); // previous argument createRoomFromText(textData)
            }
            floors.add(newFloor);
        }
    }


    // Uses scanner to prompt for guest info
    public void createGuest() {
        Guest guest = new Guest(console);
        if (findRoomForGuest(guest) == 0) {
            System.out.println("Sorry, no room found.");
        } else {
            guestList.add(guest);
        }
    }

    // returns cost of room and cost of total guests
    public double totalCost(Guest guest, Room room) {
        return (room.getCost(guest.isGovernment() || guest.isMilitary() || guest.isMembership()) + guest.costOfGuests());
    }

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
            System.out.println(receipt(guest));
            return true;
        } else {
            System.out.println("Looking for other rooms...");
            return false;
        }
    }

    public void cancelReservation(Guest guest) {
        if (Guest.yesOrNoQuestions("Are you sure you want to cancel? ", console)) {
            guest.checkOut();
            String receipt = receipt(guest);
            guestList.remove(guest);
            int dollarSignLocation = receipt.indexOf("$");
            receipt = receipt.substring(0, dollarSignLocation) + "-" + receipt.substring(dollarSignLocation); // adds negative sign to signify refund
            System.out.println(receipt);
        } else {
            System.out.println("Exited cancellation process.");
        }
    }

    public String receipt(Guest guest) {
        return "Receipt\n" +
                "\tHotel: " + hotelName +
                "\n\tGuest: " + guest.getName() +
                "\n\tBilling Info: " + guest.getCardNum() +
                guest.getRoom().toString() +
                "\n\tCost: $" + totalCost(guest, guest.getRoom()) + "\n" +
                guest.toString();
    }

    public Guest findGuestInList(String name) {
        for (Guest guest : guestList) {
            if (guest.getName().equals(name)) {
                return guest;
            }
        }
        throw new IllegalArgumentException();
    }

    public String toString() {
        String returnString = hotelName + "\n";
        for(ArrayList<Room> floor : floors) {
            for(Room room : floor) {
                returnString += (room.toString() + "\n\n");
            }
        }
        return returnString;
    }

    public ArrayList<Guest> getGuestList() {
        return guestList;
    }

}
