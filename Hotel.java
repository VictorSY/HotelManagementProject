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
        // Removes all non-letter/non-digit characters
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
    public Guest createGuest() {
        return new Guest();
    }

    // returns cost of room and cost of total guests
    public double totalCost(Guest guest, Room room) {
        return (room.getCost() + guest.costOfGuests());
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
                } else if (guest.getBedType().equals(room.getBedSize())) { // Check guest req for bed type
                    continue;
                } else if (guest.getRoomSize() > room.getRoomSize()) { // Check guest req for room size
                    continue;
                } else if (guest.hasPets() == room.getAllowPets()) { // Check guest req for pets
                    continue;
                } else if (makeReservation(guest, room.getRoomNumber(), console)) { // Check if guest accepted reservation
                    break;
                }
            }
        }
        return 0;
    }

    private boolean makeReservation(Guest guest, int roomNumber, Scanner console) {
        System.out.println("Do you want to reserve room: " + roomNumber);
        System.out.println(floors.get(roomNumber / 100 - 1).get(roomNumber % 100 - 2)); // Minus 2 because element 0 is room 1 so room 3 is stored in element 1
        if (Guest.yesOrNoQuestions("Yes or No: ", console)) {
            floors.get(roomNumber / 100 - 1).get(roomNumber % 100 - 2).setGuest(guest);
            guest.assignRoom(floors.get(roomNumber / 100 - 1).get(roomNumber % 100 - 2)); // Minus 1 because first floor is element 0
            System.out.println("You have reserved room " + roomNumber + ". Thank you for choosing " + hotelName + ".");
            return true;
        } else {
            System.out.println("Looking for other rooms...");
            return false;
        }
    }

    public void cancelReservation(Guest guest, int roomNumber) {
        if (Guest.yesOrNoQuestions("Are you sure you want to cancel? ", console)) {

        }

    }

    public String receipt(Guest guest, int roomNumber) {
        return "";
    }

}
