import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 *
 */
public class Main {

    public static void main(String[] args) throws IOException {
        // Create a log of what happens
        Log log = new Log();
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
                    log.write("Starting 'Make Reservation' process...");
                    hotels.get(0).createGuest();
                    log.write("Finished 'Make Reservation' process.");
                    break;
                case 2:
                    log.write("Starting 'Cancel Reservation' process...");
                    while(true) {
                        System.out.println("Enter your full name (exactly as you made your reservation as): ");
                        String name = console.nextLine().toLowerCase().replaceAll("[^a-z]", " ");
                        try {
                            Guest guest = hotels.get(0).findGuestInList(name);
                            hotels.get(0).cancelReservation(guest);
                            System.out.println("Successfully cancelled.");
                            log.write("Cancelled 'Cancel Reservation' process.");
                            break;
                        } catch(IllegalArgumentException e) {
                            if(Guest.yesOrNoQuestions("Guest not found. Try again?\n", console)) {
                                continue;
                            } else {
                                log.write("Finished 'Cancel Reservation' process.");
                                break;
                            }
                        }
                    }
                    log.write("Finished 'Cancel Reservation' process.");
                    break;
                case 3:
                    log.write("Starting 'End Program' process...");
                    if(Guest.yesOrNoQuestions("Are you sure you would you like to end the program? " +
                            "If so type \"yes\" if not type \"no\"\n", console)) {
                        System.out.println("Thank you for using this program. Goodbye.");
                        log.write("Finishing 'End Program' process.");
                        log.close();
                        System.exit(0);
                    }
                    log.write("Cancelled 'End Program' process.");
                    break;
                default:
                    System.out.println("Unknown command please try again.");
                    log.write("Unknown Command Detected");
                    continue;
            }

            // creates neat formatting
            System.out.println("\n\n\n\n");
        }

    }

}
