import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Hotel {

    private String hotelName;
    Scanner console = new Scanner(System.in);

    private ArrayList<Guest> guestList = new ArrayList<>();
    private ArrayList<ArrayList<Room>> floors = new ArrayList<>();
    
    private Room room;

    public Hotel(String hotelInfoText) throws FileNotFoundException {
        Scanner hotelData = new Scanner(new File(hotelInfoText));
        this.hotelName = hotelData.nextLine().trim();
        createHotelFloorsAndRooms(hotelData);
        for (ArrayList<Room> floor : floors) {
            for (Room room : floor) {
                System.out.println(room.toString());
            }
        }
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


    /*    private Room createRoomFromText(String roomStringData) {
        Removes all non-letter/non-digit characters
        roomStringData = roomStringData.toLowerCase().replaceAll("[^a-z0-9. \\s+]", " ");
        String[] roomData = roomStringData.split("\\s+"); // splits by whitespace
        Room room = new Room();
        String word;
        for (int element = 0; element < roomData.length; element++) {
            word = roomData[element];
            switch (word) {
                case "pet":
                    element++;
                    String[] formsOfYes = {"yes", "true", "allow", "allowed"};
                    if (Arrays.asList(formsOfYes).contains(roomData[element])) {
                        room.setAllowPets(true);
                    }
                    break;
                case "room":
                    element++;
                    if (roomData[element].equals("size")) {
                        element++;
                        room.setRoomSize(Integer.parseInt(roomData[element]));
                    } else if (roomData[element].equals("number")) {
                        element++;
                        room.setRoomNum(Integer.parseInt(roomData[element]));
                    }
                    break;
                case "bed":
                    element++;
                    if (roomData[element].equals("size")) {
                        element++;
                        room.setBedSize(roomData[element]);
                    } else if (roomData[element].equals("number")) {
                        element++;
                        room.setBedNum(Integer.parseInt(roomData[element]));
                    } else {
                        System.out.print("Text file error: bed _____ invalid");
                        throw new IllegalArgumentException();
                    }
                    break;
                case "cost":
                    element++;
                    room.setCost(Double.parseDouble(roomData[element]));
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }
        return room;
    } */

    // Uses scanner to prompt for guest info
    public void createGuest() {
        Guest guest = new Guest(console);
        if (findRoomForGuest(guest) == 0) {
            System.out.println("Sorry, no room found.");
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
    
    
    public int findRoomForGuest(Guest guest) {
        for (ArrayList<Room> floor : floors) {
            for (Room room : floor) {
                if (!room.isEmpty()) { // Check if room is empty
                    continue;
                } else if (!room.isCleaned()) {  // Check if room is cleaned
                    continue;
                } else if (guest.getBedNum() > room.getBedNum()) { // Check guest req for number of beds
                    continue;
                } else if (guest.getBedType() == room.getBedSize()) { // Check guest req for bed type
                    continue;
                } else if (guest.getRoomSize() > room.getRoomSize()) { // Check guest req for room size
                    continue;
                } else if (guest.hasPets() == room.getAllowPets()) { // Check guest req for pets
                    continue;
                } else if (makeReservation(guest, room.getRoomNum(), console)) { // Check if guest accepted reservation
                    break;
                }
            }
        }
        return 0;
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

    public void cancelReservation(Room room, Guest guest) {
        if (Guest.yesOrNoQuestions("Are you sure you want to cancel? ", console)) {
            checkOut(room, guest);
            String receipt = receipt(guest);
            guestList.remove(guest);
            int dollarSignLocation = receipt.indexOf("$");
            receipt = receipt.substring(0, dollarSignLocation) + "-" + receipt.substring(dollarSignLocation); // adds negative sign to signify refund
            System.out.println(receipt);
        } else {
            System.out.println("Exited cancellation process.");
        }
    }

    
    //check reservation dates so rooms don't get double booked (bound)
    //public boolean checkRoomState(Room);
    
    public String receipt(Guest guest) {
        return "Receipt\n" +
                "Hotel: " + hotelName + "\n" +
                "Billing Info: " + guest.getCardNum() + "\n" +
                guest.getRoom().toString() + "\n" +
                "Cost: $" + totalCost(guest, guest.getRoom()) + "\n";
    }

    public Guest findGuestInList(String name) {
        for (Guest guest : guestList) {
            if (guest.getName().equals(name)) {
                return guest;
            }
        }
        throw new IllegalArgumentException();
    }

}
