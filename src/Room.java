import java.util.Arrays;

public class Room implements Comparable {
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
    private CustomLinkedList<Guest> reservations = new CustomLinkedList<>();
    // whether the room allows for pets
    private boolean allowsPets;

    // the default constructor for the Room object
    public Room() {
        this.roomNum = 0;
        this.roomSize = 330;
        this.bedSize = "single";
        this.bedNum = 1;
        this.cost = 89.99;
        // changed the Guest value to default so the toString method would work properly
        this.allowsPets = false;
    }

    // the constructor that will typically be used for Rooms
    public Room(int roomNum, int roomSize, String bedSize, int bedNum, double cost, Guest guest, boolean allowPets) {
        this.roomNum = roomNum;
        this.roomSize = roomSize;
        setBedSize(bedSize);
        this.bedNum = bedNum;
        this.cost = cost;
        reservations.add(guest);
        this.allowsPets = allowPets;
    }

    // room creation
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

    // ideal room creation
    public Room(int roomSize, String bedSize, int bedNum, boolean allowPets) {
        this.roomSize = roomSize;
        this.bedSize = bedSize;
        this.bedNum = bedNum;
        this.allowsPets = allowPets;
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
        return bedSize;
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
        return this.reservations.findNode(0).data;
    }

    // adds a guest
    public void addGuest(Guest guest) {
        System.out.printf("Added guest to room %s.", roomNum);
        if(guest == null) {
            System.out.println("Null guest");
        } else if(reservations == null) {
            System.out.println("Reservations is null");
        }
        reservations.add(guest);
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
        this.reservations.remove(getGuest());
    }

    public void removeGuest(Guest guest) {
        this.reservations.remove(guest);
    }

    // determines if the Room is empty
    public boolean isEmpty() {
        return this.reservations.findNode(0) == null;
    }

    // compares this room with another room
    // int roomSize;
    // String bedSize;
    // int bedNum;
    // allowsPets;
    public int compareTo(Room other) {
        if(this.roomSize < other.getRoomSize()) {
            return -1;
        }
        if(this.roomSize > other.getRoomSize()) {
            return 1;
        }
        if(allowsPets != other.getAllowPets()) {
            if(allowsPets) {
                return 1;
            }
            return -1;
        }
        if(this.bedNum < other.getBedNum()) {
            return -1;
        }
        if(this.bedNum > other.getBedNum()) {
            return 1;
        }
        return bedSize.compareTo(other.getBedSize());
    }

    public String toString() {
        return "Room Number: " + roomNum +
                "\n\tRoom Cost: " + cost +
                "\n\tRoom Size: " + roomSize +
                "\n\tBed Number: " + bedNum +
                "\n\tBed Size: " + bedSize +
                "\n\tPet allowed: " + allowsPets;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Room) {
            return compareTo((Room) o);
        } else {
            throw new IllegalArgumentException("Invalid Room comparison");
        }
    }
}
