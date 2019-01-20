import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Hotel> hotels = new ArrayList<Hotel>();
        hotels.add(new Hotel("SampleHotelData.txt"));
    }
}
