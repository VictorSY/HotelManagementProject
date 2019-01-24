import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Hotel> hotels = new ArrayList<Hotel>();
        hotels.add(new Hotel("SampleHotelData.txt"));
        Scanner scanner = new Scanner(System.in);
        Guest guest = new Guest(scanner);
        System.out.println(guest.toString());
    }
}
