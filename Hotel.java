
import java.util.*;

public class Hotel {

    private int numberOfFloors = 2;
    private int numberOfRooms = 3;

    private Array[] floors = new Array[numberOfFloors];
    private Room[] floor = new Array[numberOfRooms];

    public static void main(String[] args) {
        createHotelFloorsAndRooms();
    }

    // Creates rooms on each floor with default constructor
    public void createHotelFloorsAndRooms() {
        for (Room[] floor : floors) {
            for (int i = 0; i < Array.length; i++) {
                floor[i] = new Room[];
            }
        }
    }

    // Uses scanner to prompt for guest info
    public Guest createGuest() {
        return new Guest();
    }

    //
    public Room[] createFloor() { return new Room[]; }
    public double totalCost(Guest guest, int roomNumber) {}
    public void makeReservation(Guest guest, int roomNumber) {}
    public void cancelReservation(Guest guest, int roomNumber) {}
    public String receipt(Guest guest, int roomNumber) { return ''; }

}
