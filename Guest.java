import java.util.Scanner;

public class Guest {
    // all discounts apply 0.8 to total coast
    private boolean isMembership;
    private boolean isMilitary;
    private boolean isGovernment;
    private boolean hasPets;

    private int numOfSeniors; //*4.99
    private int numOfAdults; //*9.99
    private int numOfChildren; //*3.99

    private String name;
    private int cardNum;
    // reservationDays

    private String bedType;
    private int bedNum;
    private int roomSize;

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
            , int bedNum) {
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

    }

    // Guest creation through scanner
    public Guest(Scanner console) {
        System.out.print("What is your full name? ");
        this.name = console.nextLine().trim();
        System.out.println();

        System.out.println("For the next few questions answer with a single integer.");
        this.cardNum = numberOfQuestions("What is your credit card number? (no spaces)", console);
        this.numOfAdults = numberOfQuestions("How many adults? ", console);
        this.numOfSeniors = numberOfQuestions("How many seniors? ", console);
        this.numOfChildren = numberOfQuestions("How many children? ", console);
        this.bedNum = numberOfQuestions("How many beds? ", console);
        int bedType = numberOfQuestions("What bed type? (king = 1, queen = 2, twin = 3, single = 4) ", console);
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
                this.bedType = "single";
                break;
            default:
                this.bedType = "twin";
        }
        System.out.println("For the next few questions answer with Y or N.");
        this.isMembership = yesOrNoQuestions("Do you have a hotel membership? ", console);
        this.isMembership = yesOrNoQuestions("Are you a veteran? ", console);
        this.isGovernment = yesOrNoQuestions("Are you a government employee? ", console);
        this.hasPets = yesOrNoQuestions("Do you have pets? ", console);

    }

    public static boolean yesOrNoQuestions(String question, Scanner console) {
        System.out.print(question);
        String answer = console.nextLine().toLowerCase();
        if (answer.contains("n")) {
            return false;
        } else if (answer.contains("y")) {
            return true;
        } else {
            System.out.println("Invalid input. Please try again.");
            return yesOrNoQuestions(question, console);
        }
    }

    public static int numberOfQuestions(String question, Scanner console) {
        int answer;
        System.out.print(question);
        while (true) {
            try {
                answer = Integer.parseInt(console.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Try again.");
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

    public void checkIn() {
        // Guest check in (use Room class)
        room.setGuest(this);
    }

    public void checkOut() {
        // Guest check out (use Room class)
        room.removeGuest();
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

    public int getCardNum() {
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

    public void assignRoom(Room room) {
        if (room == null) {
            this.room = room;
        } else {
            System.out.println(name + " already has a room");
        }
    }

    // the toString method
    public String toString() {
        return "Name: " + name +
                "\nCard Number: " + cardNum +
                "\nMembership: " + isMembership +
                "\nMilitary Discount: " + isMilitary +
                "\nGovernment Discount: " + isGovernment +
                "\nHas Pets: " + hasPets +
                "\nNumber of Seniors: " + numOfSeniors +
                "\nNumber of Adults: " + numOfAdults +
                "\nNumber of Children: " + numOfChildren;
    }


}
