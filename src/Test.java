import java.io.IOException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws IOException {
        Scanner console = new Scanner(System.in);
        Hotel hotel = new Hotel("SampleHotelData.txt", console);
        hotel.rooms.printTreeInOrder();
        hotel.createGuest();
        System.out.println(hotel.getGuestIndex().toString());
    }
}
