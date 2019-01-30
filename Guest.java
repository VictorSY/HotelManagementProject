import java.util.Scanner;

public class Guest {
    // all discounts apply 0.8 to total coast
    private boolean isMembership;
    private boolean isMilitary;
    private boolean isGovernment;


    private int numOfSeniors; //*4.99
    private int numOfAdults; //*9.99
    private int numOfChildren; //*3.99

    private String name;
    private long cardNum;
    private String bedType;
    private int bedNum;
    private int roomSize = 0;
    private boolean hasPets;

    // added Room object to facilitate the integration of the class
    private Room room;


    public Guest() {
        this.isMembership = false;
        this.isMilitary = false;
        this.isGovernment = false;
        this.numOfSeniors = 0;
        this.numOfAdults = 0;
        this.numOfChildren = 0;
        this.hasPets = false;

        this.name = "";
        this.cardNum = 0;

        this.bedType = "";
        this.bedNum = 0;
    }

    public Guest(boolean isMembership, boolean isMilitary, boolean isGovernment,
                 int numOfSeniors, int numOfAdults, int numOfChildren,
                 boolean hasPets, String name, int cardNum, String bedType
            , int bedNum, int roomSize) {
        this.isMembership = isMembership;
        this.isMilitary = isMilitary;
        this.isGovernment = isGovernment;
        this.numOfSeniors = numOfSeniors;
        this.numOfAdults = numOfAdults;
        this.numOfChildren = numOfChildren;
        this.hasPets = hasPets;

        this.name = name;
        this.cardNum = cardNum;

        this.bedType = bedType;
        this.bedNum = bedNum;
        this.roomSize = roomSize;

    }

    // Guest creation through scanner
    public Guest(Scanner console) {
        System.out.print("What is your full name?\n");
        this.name = console.nextLine().trim();
        System.out.println();

        System.out.println("For the next few questions answer with a single integer.");
        this.cardNum = getCreditCard(console);
        this.numOfAdults = numberOfQuestions("How many adults?\n", console);
        this.numOfSeniors = numberOfQuestions("How many seniors?\n", console);
        this.numOfChildren = numberOfQuestions("How many children?\n", console);
        this.roomSize = numberOfQuestions("How big does your room need to be? (square feet)\n Suite is 600"
                                            + "\n Deluxe is 500\n Standard is 300\n", console);
        this.bedNum = numberOfQuestions("How many beds? (1 or 2, NOTE: Standard only has 1)\n", console);
        int bedType = numberOfQuestions("What bed type? (king = 1, queen = 2, twin = 3)\n"
                                          + " Suite: has King single bed or Queen double beds\n" +
                                        " Deluxe: has Queen single bed or Twin double beds\n" +
                                        " Standard: only has Twin single bed\n", console);
        switch (bedType) {
            case 1:
                this.bedType = "king";
                break;
            case 2:
                this.bedType = "queen";
                break;
            case 3:
                this.bedType = "twin";
                break;
            case 4:
                this.bedType = "full";
                break;
            default:
                this.bedType = "twin";
        }
        System.out.println("For the next few questions answer with Y or N.");
        this.isMembership = yesOrNoQuestions("Do you have a hotel membership?\n", console);
        this.isMilitary = yesOrNoQuestions("Are you a veteran?\n", console);
        this.isGovernment = yesOrNoQuestions("Are you a government employee?\n", console);
        this.hasPets = yesOrNoQuestions("Do you have pets?\n", console);

    }

    // Asks for credit card number (16 digits)
    public static long getCreditCard(Scanner console) {
        System.out.print("What is your card number?\n");
        long cardNumber;
        String input;
        while(true) {
            try {
                input = console.nextLine().replaceAll("[^0-9]", "");
                if(input.length() < 16) {
                    throw new NumberFormatException();
                }
                cardNumber = Long.parseLong(input);
                break;
            } catch(NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
                System.out.print("What is your card number?\n");
            }
        }
        return cardNumber;
    }

    // Asks a question that requires yes or no response
    public static boolean yesOrNoQuestions(String question, Scanner console) {
        while(true) {
            try {
                System.out.print(question);
                String answer = console.nextLine().toLowerCase();
                if(answer.contains("n")) {
                    return false;
                } else if(answer.contains("y")) {
                    return true;
                } else {
                    throw new IllegalArgumentException();
                }
            } catch(IllegalArgumentException e) {
                System.out.println("Invalid input. Try again.");
            }
        }
    }

    // Asks a question that requires integer response
    public static int numberOfQuestions(String question, Scanner console) {
        int answer;
        System.out.print(question);
        while (true) {
            try {
                answer = Integer.parseInt(console.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Try again.");
                System.out.print(question);
            }
        }
        return answer;
    }

    public double costOfGuests() {
        // calculate the total cost of the Guest
        double total = (numOfSeniors * 4.99 + numOfAdults * 9.99 + numOfChildren * 3.99);
        if (isMembership || isMilitary || isGovernment) {
            total *= 0.8;
        }
        return total;
    }

    // Adds guest to room if room is empty
    public boolean checkIn(Room room) {
        // Guest check in (use Room class)
        if(this.room != null) {
            System.out.println(name + " already has a room");
            return false;
        }
        this.room = room;
        this.room.setGuest(this);
        return true;
    }

    public void checkOut() {
        // Guest check out (use Room class)
        room.removeGuest();
        room = null;
    }


    public boolean isMembership() {
        return isMembership;
    }

    public boolean isMilitary() {
        return isMilitary;
    }

    public boolean isGovernment() {
        return isGovernment;
    }

    public boolean hasPets() {
        return hasPets;
    }

    public int getNumOfSeniors() {
        return numOfSeniors;
    }

    public int getNumOfAdults() {
        return numOfAdults;
    }

    public int getNumOfChildren() {
        return numOfChildren;
    }

    public String getName() {
        return name;
    }

    public long getCardNum() {
        return cardNum;
    }

    public String getBedType() {
        return bedType;
    }

    public int getBedNum() {
        return bedNum;
    }

    public int getRoomSize() {
        return roomSize;
    }

    public Room getRoom() {
        return room;
    }


    // the toString method
    public String toString() {
        return "Guest Info " +
                "\n\tName: " + name +
                "\n\tCard Number: " + cardNum +
                "\n\tMembership: " + isMembership +
                "\n\tMilitary Discount: " + isMilitary +
                "\n\tGovernment Discount: " + isGovernment +
                "\n\tHas Pets: " + hasPets +
                "\n\tNumber of Seniors: " + numOfSeniors +
                "\n\tNumber of Adults: " + numOfAdults +
                "\n\tNumber of Children: " + numOfChildren +
                "\n\tBed Type: " + bedType +
                "\n\tBed Number: " + bedNum +
                "\n\tRoom Size: " + roomSize +
                "\n\tRoom: " + room;
    }
}
