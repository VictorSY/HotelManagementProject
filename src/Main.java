import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 *
 */
public class Main {

    public static void main(String[] args) throws IOException {
        // Create a log of what happens
        BufferedWriter log = new BufferedWriter(new FileWriter("Logs/" + java.time.LocalDate.now().toString() + ".txt"));
        // Scanner for asking questions
        Scanner console = new Scanner(System.in);
        // allows for the creation of hotels
        ArrayList<Hotel> hotels = new ArrayList<Hotel>();
        // adds the hotel for the .txt sample file
        hotels.add(new Hotel("SampleHotelData.txt", console, log));

        while(true) {
            int command = Guest.numberOfQuestions("What would you like to do?\n" +
                    "1 - Make a reservation\n" +
                    "2 - Cancel a reservation\n" +
                    "3 - End the program\n", console);
            switch(command) {
                case 1:
                    hotels.get(0).createGuest();
                    break;
                case 2:
                    while(true) {
                        System.out.println("Enter your full name (exactly as you made your reservation as): ");
                        String name = console.nextLine().toLowerCase().replaceAll("[^a-z]", " ");
                        try {
                            Guest guest = hotels.get(0).findGuestInList(name);
                            hotels.get(0).cancelReservation(guest);
                            System.out.println("Successfully cancelled.");
                            break;
                        } catch(IllegalArgumentException e) {
                            if(Guest.yesOrNoQuestions("Guest not found. Try again?\n", console)) {
                                continue;
                            } else {
                                break;
                            }
                        }
                    }
                    break;
                case 3:
                    // Ends the program if user wants to
                    if(Guest.yesOrNoQuestions("Are you sure you would you like to end the program? " +
                            "If so type \"yes\" if not type \"no\"\n", console)) {
                        System.out.println("Thank you for using this program. Goodbye.");
                        log.close();
                        System.exit(0);
                    }
                    break;
                default:
                    System.out.println("Unknown command please try again.");
                    continue;
            }

            // creates neat formatting
            System.out.println("\n\n\n\n");
        }

    }

}
