public class Hotel {

    private int numberOfFloors = 2;
    private int numberOfRooms = 3;

    private Room[][] floors = new Room[numberOfFloors][numberOfRooms];

    public Hotel(String[] args) {
        createHotelFloorsAndRooms();
    }

    // Creates rooms on each floor with default constructor
    private void createHotelFloorsAndRooms() {
        for (Room[] floor : floors) {
            for (int i = 0; i < floor.length; i++) {
                floor[i] = new Room();
            }
        }
    }

    // Uses scanner to prompt for guest info
    public Guest createGuest() {
        return new Guest();
    }

    //
    public double totalCost(Guest guest, int roomNumber) {
        return 0.0;
    }
    public void makeReservation(Guest guest, int roomNumber) {}
    public void cancelReservation(Guest guest, int roomNumber) {}

    public String receipt(Guest guest, int roomNumber) {
        return "";
    }

}
