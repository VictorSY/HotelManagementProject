import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        // commented out all things .txt file because it causes null values
        // ArrayList<Hotel> hotels = new ArrayList<Hotel>();
        // hotels.add(new Hotel("SampleHotelData.txt"));
        Scanner scanner = new Scanner(System.in);
        Guest guest = new Guest(scanner);
        guest.createARoom();
        System.out.println(guest.toString());
        System.out.println(guest.getRoom().toString());
    }
}
