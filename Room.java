import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

// The Room object to be associated with the Hotel
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
    private boolean isCleaned = true;
    // all the dates which this room reserved
    private ArrayList<Date> resDates;

    private boolean isReserved;

    // the default constructor for the Room object
    public Room() {
        this.roomNum = 0;
        this.roomSize = 300;
        this.bedSize = "twin";
        this.bedNum = 1;
        this.cost = 89.99;
        // changed the Guest value to default so the toString method would work properly
        this.guest = guest; //now guest variable, so a guest is added to the guests arrayList
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
    
    // gets whether a Room allows for pets
    public boolean getAllowPets() {
        return this.allowsPets;
    }

    // sets whether a Room allows for pets
    public void setAllowPets(boolean allowPets) {
        this.allowsPets = allowPets;
    }


    // determines if the Room is empty
    public boolean isEmpty() {
        if (guest != null){
            return false;
        }
        return true;
    }

    // Sets if room is clean
    public void setCleaned(boolean isCleaned) {
        this.isCleaned = isCleaned;
    }

    // determines if the Room is cleaned
    public boolean isCleaned() {
        return isCleaned;
    }

    public boolean setResDate(String date1, String date2){
        String d1 = date1.replace("/", "");
        String d2 = date2.replace("/", "");

        int month1 = Integer.parseInt(d1.substring(0, 2)) - 1; //Date's months range goes from 0 to 11.
        int day1 = Integer.parseInt(d1.substring(2, 4));
        int year1 = Integer.parseInt(d1.substring(4, 6)) + 100; // Date sets year like this: YY + 1900. If 0 is passed then 1900 is set.

        int month2 = Integer.parseInt(d2.substring(0, 2)) - 1;
        int day2 = Integer.parseInt(d2.substring(2, 4));
        int year2 = Integer.parseInt(d2.substring(4, 6)) + 100;

        Date startDate = new Date(year1, month1, day1);
        Date endDate = new Date(year2, month2, day2);
        
        if (!isReserved(startDate, endDate)){ //if room is not reserved, reservation dates are added to the room resDates and guest
            resDates.add(startDate);
            resDates.add(endDate);
        
            guest.setResDates(startDate, endDate);
            return true;
        }else{
            System.out.println("Sorry,this room is booked for the following dates: " + displayDates());
            return false;
        }
    }
    
    // Get whether room is reserved.
    public boolean isReserved(Date from, Date to) {
        for (int i = 0; i < resDates.size() - 1; i+=2){
            if((from.compareTo(resDates.get(i)) >= 0 && from.compareTo(resDates.get(i+1)) <= 0) || (to.compareTo(resDates.get(i)) >= 0 && to.compareTo(resDates.get(i+1)) <= 0)){
                return true;
            }
        }
        return false;
    }
    
    public String displayDates(){
        String dates = "";
        for(int i = 0; i < resDates.size() - 1; i+=2){
            dates += "From: " + resDates.get(i).getMonth() + "/ " + resDates.get(i).getDate() + "/ " + (resDates.get(i).getYear() + 1900) + "   ";
            dates += "To: " + resDates.get(i+1).getMonth() + "/ " + resDates.get(i+1).getDate() + "/ " + (resDates.get(i+1).getYear() + 1900) + "\n";
        }
        return dates;
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
