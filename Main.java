import java.io.FileNotFoundException;
import java.util.ArrayList;
/*
 * The Main class to run the Hotel Project
 * @authors
 * Ray Van Hollebeke
 * Victor Shan
 * Huiseon Choi
 * Fabrizio Ferreyra
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
      // allows for the creation of hotels
        ArrayList<Hotel> hotels = new ArrayList<Hotel>();
        // adds the hotel for the .txt sample file
        hotels.add(new Hotel("SampleHotelData.txt"));
        // loops through the command-line promps
        while(true) {
          // creates guests to run the program
            hotels.get(0).createGuest();
            // creates neat formatting
            System.out.println("\n\n\n\n");
        }
    }
}
