import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);
        Hotel hotel = new Hotel("SampleHotelData.txt", console);
        hotel.rooms.printTreeInOrder();

    }
}
