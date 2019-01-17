public class Hotel {

    private int numberOfFloors = 2, numberOfRooms = 3;

    private Array[] floors = new Array[numberOfFloors];
    private Room[] floor = new Array[numberOfRooms];

    public static void main(String[] args) {

    }

    public void createHotelFloorsAndRooms() {}
    public Guest createGuest() { return new Guest(); }
    public Room[] createFloor() { return new Room[]; }
    public double totalCost(Guest guest, int roomNumber) {}
    public void makeReservation(Guest guest, int roomNumber) {}
    public void cancelReservation(Guest guest, int roomNumber) {}
    public String receipt(Guest guest, int roomNumber) { return ''; }

}
