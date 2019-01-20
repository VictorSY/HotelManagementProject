import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Hotel {

    private String hotelName;


    private ArrayList<ArrayList<Room>> floors = new ArrayList<>();

    public Hotel(String hotelInfoText) throws FileNotFoundException {
        Scanner hotelData = new Scanner(new File(hotelInfoText));
        this.hotelName = hotelData.nextLine().trim();
        createHotelFloorsAndRooms(hotelData);
    }

    // Creates rooms on each floor with text file
    /*
     * What files should look like
     *
     * Hotel Name
     * Floor 1's Number of Rooms    4
     * Room1 Data
     * Room2 Data
     * Room3 Data
     * Room4 Data
     * Floor 2's Number of Rooms    1
     * Room Data
     * Floor 3's Number of Rooms    1
     * Room Data
     */
    private void createHotelFloorsAndRooms(Scanner hotelData) {
        while (hotelData.hasNextLine()) {
            int numberOfRooms = hotelData.nextInt();
            for (int room = 0; room < numberOfRooms; room++) {
                hotelData.nextLine();
            }
        }
    }


    private Room createRoomFromText(String roomData) {
        return new Room();
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
