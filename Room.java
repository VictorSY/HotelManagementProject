import java.util.Arrays;

public class Room {
    // the number assigned to a room
    private int roomNum;
    // the square footage of the room, the size
    private int roomSize;
    // the type of bed used in the room
    private int bedSize;
    // the number of beds in the room
    private int bedNum;
    // the cost of reserving this room
    private double cost;
    // the guest attached to this room
    private Guest guest;
    // whether the room allows for pets
    private boolean allowsPets;
    // whether the room isCleaned
    private boolean isCleaned = true;
    
    private boolean isReserved;

    // the default constructor for the Room object
    public Room() {
        this(0, 330, 1, 1, 89.99, new Guest(), false, false);
    }

    // the constructor that will typically be used for Rooms
    public Room(int roomNum, int roomSize, int bedSize, int bedNum, double cost, Guest guest, boolean allowPets, boolean isReserved) {
        setRoomNum(roomNum);
        setRoomSize(roomSize);
        setBedSize(bedSize);
        setBedNum(bedNum);
        setCost(cost);
        setGuest(guest);
        setAllowPets(allowPets);
        setReservation(isReserved);
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
                        this.setBedSize(Integer.parseInt(roomData[element]));
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

    /* Mutators */
    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    public void setRoomSize(int roomSize) {
        this.roomSize = roomSize;
    }
    
    public void setBedSize(int bedSize) {
        this.bedSize = bedSize;
    }
    
    public void setBedNum(int bedNum) {
        this.bedNum = bedNum;
    }
    
    public void setCost(double cost) {
        this.cost = cost;
    }
    
    public void setGuest(Guest guest) {
        this.guest = guest;
    }
    
     public void setAllowPets(boolean allowPets) {
        this.allowsPets = allowPets;
    }
    
    public void setReservation(boolean isReserved){
        this.isReserved = isReserved;
    }
    
    
    /* Accessors */
    public int getRoomNum() {
        return this.roomNum;
    }

    public int getRoomSize() {
        return this.roomSize;
    }

    public int getBedSize() {
        return this.bedSize;
    }

    public int getBedNum() {
        return this.bedNum;
    }

    public double getCost(boolean hasDiscount) {
        if (hasDiscount) {
            return this.cost * 0.8;
        } else {
            return this.cost;
        }
    }

    public Guest getGuest() {
        return this.guest;
    }

    public boolean getAllowPets() {
        return this.allowsPets;
    }

    public boolean getReservation(){
        return this.isReserved;
    }
    
    
    
    // detaches any Guest from the room
    public void removeGuest(Guest guest) {
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
        String bedSize;
        if (this.bedSize == 1)
            bedSize = "Twin";
        else if(this.bedSize == 2)
            bedSize = "Double";
        else if(this.bedSize == 3)
            bedSize = "Queen";
        else if(this.bedSize == 4)
            bedSize = "King";
        else
            bedSize = "Twin";
        
        // checks to see if the guest exists
        if(guest != null) {
            // prints out the details of the room, removed repeat values other than allowsPets
            return "Room Number: " + roomNum +
                   "\nRoom Cost: " + cost +
                   "\nRoom Size: " + roomSize +
                   "\nBed Number: " + bedNum +
                   "\nBed Size: " + bedSize +
                   "\nPet allowed: " + allowsPets +
                   "\nCleaned: " + isCleaned;
        } else {
            return "";
        }
    }
}
