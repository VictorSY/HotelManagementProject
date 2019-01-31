import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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
        // Scanner for asking questions
        Scanner console = new Scanner(System.in);
        // allows for the creation of hotels
        ArrayList<Hotel> hotels = new ArrayList<Hotel>();
        // adds the hotel for the .txt sample file
        hotels.add(new Hotel("SampleHotelData.txt", console));
        // loops through the command-line prompts
        hotels.get(0).createGuest();
        while(true) {
            //if(Guest.yesOrNoQuestions(""))
            if(Guest.yesOrNoQuestions("Would you like to cancel a reservation? " +
                    "If so type \"yes\" if not type \"no\"\n", new Scanner(System.in))) {
                System.out.println("Enter your full name (exactly as you made your reservation as): ");
                String name = console.nextLine().toLowerCase().replaceAll("[^a-z]", " ");
                try {
                    Guest guest = hotels.get(0).findGuestInList(name);
                    hotels.get(0).cancelReservation(guest);
                    System.out.println("Successfully cancelled.");
                } catch(IllegalArgumentException e) {
                    if(Guest.yesOrNoQuestions("Guest not found. Try again?", console)) {
                        continue;
                    }
                }
            }

            // Ends the program if user wants to
            if(Guest.yesOrNoQuestions("Would you like to end the program? " +
                    "If so type \"yes\" if not type \"no\"\n", console)) {
                System.out.println("Thank you. Goodbye.");
                System.exit(0);
            }

            // creates guests to run the program
            hotels.get(0).createGuest();


            // creates neat formatting
            System.out.println("\n\n\n\n");
        }
    }
}
