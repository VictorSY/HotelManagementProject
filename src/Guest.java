import java.util.Scanner;

public class Guest implements Comparable {
    // all discounts apply 0.8 to total coast
    private boolean isMembership;
    private boolean isMilitary;
    private boolean isGovernment;
    private boolean hasPets;

    private int numOfSeniors; //*4.99
    private int numOfAdults; //*9.99
    private int numOfChildren; //*3.99

    private String name;
    private long cardNum;
    // reservationDays

    private String bedType;
    private int bedNum;
    private int roomSize = 0;

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
        this.cardNum = getCreditCard(console);
        this.numOfAdults = numberOfQuestions("How many adults? ", console);
        this.numOfSeniors = numberOfQuestions("How many seniors? ", console);
        this.numOfChildren = numberOfQuestions("How many children? ", console);
        this.roomSize = numberOfQuestions("How big does your room need to be? (square feet) ", console);
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
        this.isMilitary = yesOrNoQuestions("Are you a veteran? ", console);
        this.isGovernment = yesOrNoQuestions("Are you a government employee? ", console);
        this.hasPets = yesOrNoQuestions("Do you have pets? ", console);

    }

    public static long getCreditCard(Scanner console) {
        System.out.print("What is our card number? ");
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
                System.out.print("What is our card number? ");
            }
        }
        return cardNumber;
    }

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

    public boolean checkIn(Room room) {
        // Guest check in (use Room class)
        if(this.room != null) {
            System.out.println(name + " already has a room");
            return false;
        }
        this.room = room;
        this.room.addGuest(this);
        return true;
    }

    public void makeReservation(Room room) {
        this.room = room;
        this.room.addGuest(this);
    }

    public void deleteReservation() {
        room.removeGuest(this);
    }

    public void checkOut() {
        // Guest check out (use Room class)
        room.removeGuest();
        room = null;
    }


    // compares this guest to another guest
    // Guest compare order
    //      String name;
    //      long cardNum;
    //      boolean isMilitary;
    //      boolean isGovernment;
    //      boolean isMembership;
    public int compareTo(Guest other) {
        if(this.name.compareTo(other.getName()) != 0) {
            return this.name.compareTo(other.getName());
        } else if(this.cardNum < other.getCardNum()) {
            return -1;
        } else if(this.cardNum > other.getCardNum()) {
            return 1;
        } else if(this.isMilitary != other.isMilitary()) {
            if(this.isMilitary) {
                return -1;
            } else {
                return 1;
            }
        } else if(this.isGovernment != other.isGovernment()) {
            if(this.isGovernment) {
                return -1;
            } else {
                return 1;
            }
        } else if(this.isMembership != other.isMembership()) {
            if(this.isMembership) {
                return -1;
            } else {
                return 1;
            }
        }
        return 0;
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
    public String toFormattedString() {
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


    public String toString() {
        return name + " " + cardNum;
    }


    @Override
    public int compareTo(Object o) {
        if(o instanceof Guest) {
            return compareTo((Guest) o);
        } else {
            throw new IllegalArgumentException("Guest compared with invalid type.");
        }
    }
}
