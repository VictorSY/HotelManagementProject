import java.util.Arrays;

public class Room {
    // the number assigned to a room
    private int roomNum;
    // the square footage of the room, the size
    private int roomSize;
    // the type of bed used in the room
    private String bedSize;
    // the number of beds in the room
    private int bedNum;
    // the cost of reserving this room
    private double cost;
    // the guest attached to this room
    private Guest guest;
    // whether the room allows for pets
    private boolean allowsPets;
    // whether the room isCleaned
    private boolean isCleaned;

    // the default constructor for the Room object
    public Room() {
        this.roomNum = 0;
        this.roomSize = 330;
        this.bedSize = "Twin";
        this.bedNum = 1;
        this.cost = 89.99;
        // changed the Guest value to default so the toString method would work properly
        this.guest = new Guest();
        this.allowsPets = false;
        this.isCleaned = true;
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

    // Temporary room creation
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
    public Guest getGuest() {
        return this.guest;
    }

    // assigns a Guest object to the Room
    public void setGuest(Guest guest) {
        this.guest = guest;
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
    public void removeGuest() {
        guest = null;
    }

    // determines if the Room is empty
    public boolean isEmpty() {
        return guest == null;
    }

    // determines if the Room is cleaned
    public boolean isCleaned() {
        return isCleaned;
    }

    public String toString() {
        return "Room Number: " + roomNum +
                "\nRoom Cost: " + cost +
                "\nRoom Size: " + roomSize +
                "\nBed Number: " + bedNum +
                "\nBed Size: " + bedSize +
                "\nGuest: " + guest.toString() +
                "\nPet allowed: " + allowsPets +
                "\nCleaned: " + isCleaned;
    }
}
