import java.util.ArrayList;
import java.util.Arrays;
// The Room object to be associated with the Hotel
public class Room {
    // the number assigned to a room
    private int roomNum;
    // the square footage of the room, the size
    private int roomSize = 300;
    // the type of bed used in the room
    private String bedSize = "twin";
    // the number of beds in the room
    private int bedNum = 1;
    // the cost of reserving this room
    private double cost = 89.99;
    // the guest attached to this room
    private ArrayList<Guest> guests;
    // whether the room allows for pets
    private boolean allowsPets = false;
    // whether the room isCleaned
    private boolean isCleaned = true;

    private boolean isReserved;

    // the default constructor for the Room object
    public Room() {
        this.roomNum = 0;
        this.roomSize = 300;
        this.bedSize = "twin";
        this.bedNum = 1;
        this.cost = 89.99;
        // changed the Guest value to default so the toString method would work properly
        this.guest = new Guest();
        this.allowsPets = false;
    }

    // the constructor that will typically be used for Rooms
    public Room(int roomNum, int roomSize, String bedSize, int bedNum, double cost, Guest guest, boolean allowPets) {
        this.roomNum = roomNum;
        this.roomSize = roomSize;
        this.bedSize = bedSize;
        this.bedNum = bedNum;
        this.cost = cost;
        this.guest = guest;
        this.allowsPets = allowPets;
    }


    public Room(String roomStringData) {
        // Removes all non-letter/non-digit characters
        roomStringData = roomStringData.toLowerCase().replaceAll("[^a-z0-9. \\s+]", " ");
        String[] roomData = roomStringData.split("\\s+"); // splits by whitespace
        String word;
        for (int element = 0; element < roomData.length; element++) {
            word = roomData[element];
            switch (word) {
                case "pet":
                    element++;
                    String[] formsOfYes = {"yes", "true", "allow", "allowed"};
                    if (Arrays.asList(formsOfYes).contains(roomData[element])) {
                        this.setAllowPets(true);
                    }
                    break;
                case "room":
                    element++;
                    if (roomData[element].equals("size")) {
                        element++;
                        this.setRoomSize(Integer.parseInt(roomData[element]));
                    } else if (roomData[element].equals("number")) {
                        element++;
                        this.setRoomNum(Integer.parseInt(roomData[element]));
                    }
                    break;
                case "bed":
                    element++;
                    if (roomData[element].equals("size")) {
                        element++;
                        this.setBedSize(roomData[element]);
                    } else if (roomData[element].equals("number")) {
                        element++;
                        this.setBedNum(Integer.parseInt(roomData[element]));
                    } else {
                        System.out.print("Text file error: bed _____ invalid");
                        throw new IllegalArgumentException();
                    }
                    break;
                case "cost":
                    element++;
                    this.setCost(Double.parseDouble(roomData[element]));
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }
    }

    // sets the number for the Room
    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    // gets the room number
    public int getRoomNumber() {
        return this.roomNum;
    }

    // gets the size of the Room
    public int getRoomSize() {
        return this.roomSize;
    }

    // sets the size of the Room
    public void setRoomSize(int roomSize) {
        this.roomSize = roomSize;
    }

    // gets the type of bed for the Room
    public String getBedSize() {
        return this.bedSize;
    }

    // sets type of bed for the Room
    public void setBedSize(String bedSize) {
        this.bedSize = bedSize;
    }

    // gets the number of beds for the Room
    public int getBedNum() {
        return this.bedNum;
    }

    // sets the number of beds in the Room
    public void setBedNum(int bedNum) {
        this.bedNum = bedNum;
    }

    // gets the cost of the Room
    public double getCost(boolean hasDiscount) {
        if (hasDiscount) {
            return this.cost * 0.8;
        } else {
            return this.cost;
        }
    }

    // sets the cost of the Room
    public void setCost(double cost) {
        this.cost = cost;
    }

    // gets the Guest object attached to the Room
    public ArrayList<Guest> getGuestList() {
        return this.guests;
    }

    // assigns a Guest object to the Room
    public void addGuest(Guest guest) {
        this.guests.add(guest);
    }

    // gets whether a Room allows for pets
    public boolean getAllowPets() {
        return this.allowsPets;
    }

    // sets whether a Room allows for pets
    public void setAllowPets(boolean allowPets) {
        this.allowsPets = allowPets;
    }

    // detaches any Guest from the room
    public void removeGuest(Guest guest) {
        guests.remove(guest);
    }

    // determines if the Room is empty
    public boolean isEmpty(Guest newGuest) {
        for(Guest guest : guests) {

        }
        return guests.isEmpty();
    }

    // Sets if room is clean
    public void setCleaned(boolean isCleaned) {
        this.isCleaned = isCleaned;
    }

    // determines if the Room is cleaned
    public boolean isCleaned() {
        return isCleaned;
    }

    // Get whether room is reserved.
    public boolean isReserved() {

    }

    // creates a String to resemble the Object
    public String toString() {
        return "Room Number: " + roomNum +
                "\n\tRoom Cost: " + cost +
                "\n\tRoom Size: " + roomSize +
                "\n\tBed Number: " + bedNum +
                "\n\tBed Size: " + bedSize +
                "\n\tPet allowed: " + allowsPets +
                "\n\tCleaned: " + isCleaned;
    }
}
