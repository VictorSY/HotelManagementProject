import java.util.Scanner;
// The guest object to be associated with our Hotel
public class Guest {
    // CONASTANTS
    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String NAME_REGEX = "^[A-Za-z]*(\\s)+[A-Z][a-z]*$";
    private static final int MAX_PER_ROOM = 10;

  // all discounts apply 0.8 to total coast
  private boolean isMembership;
  private boolean isMilitary;
  private boolean isGovernment;

  // the additional cost of persons
  private int numOfSeniors; //*4.99
  private int numOfAdults; //*9.99
  private int numOfChildren; //*3.99

  // fields that are attached to who the guest is
  private String name;
  private String email;
  private long cardNum;
  private String bedType;
  private int bedNum;
  private int roomSize = 0;
  private boolean hasPets;
    private Date startDateReservation;
    private Date endDateReservation;

  // a unique id that attaches name and cardNum to track customers
  private String uniqueId;

  // added Room object to facilitate the integration of the class
  private Room room;

  // creates a default version of a Guest
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

  // creates a specified version of a Guest with every field to be initialized
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

    this.uniqueId = this.name + this.cardNum;

  }

  // Guest creation through scanner, the ideal Guest creation.
  public Guest(Scanner console) {

    // Welcomes a guest to the Hotel and provides info to cancel if already booked.
    System.out.println("Welcome to our Hotel!\n\n");
    System.out.println("If you already have a reservation and would like to cancel please re-enter your information.");
    // Begins the information gathering process
    System.out.print("What is your full name?\n");
    this.name = console.nextLine().trim().toLowerCase().replaceAll("[^a-z]", " ");
    System.out.println();
      setEmail(console);
    // Gives further instructions
    System.out.println("For the next few questions answer with a single integer.");
    // initializes fields for every question
    this.cardNum = getCreditCard(console);
    this.uniqueId = this.name + this.cardNum;
    this.numOfAdults = numberOfQuestions("How many adults?\n", console);
    this.numOfSeniors = numberOfQuestions("How many seniors?\n", console);
    this.numOfChildren = numberOfQuestions("How many children?\n", console);
    this.roomSize = numberOfQuestions("How big does your room need to be? (square feet)\n Suite is 600"
                                        + "\n Deluxe is 500\n Standard is 300\n", console);
    this.bedNum = numberOfQuestions("How many beds? (1 or 2, NOTE: Standard only has 1)\n", console);
    askBedType(console);

    // Changes the instructions but still initializes the fields
    System.out.println("For the next few questions answer with Y or N.");
    this.isMembership = yesOrNoQuestions("Do you have a hotel membership?\n", console);
    this.isMilitary = yesOrNoQuestions("Are you a veteran?\n", console);
    this.isGovernment = yesOrNoQuestions("Are you a government employee?\n", console);
    this.hasPets = yesOrNoQuestions("Do you have pets?\n", console);

  }

  // Asks a Gues what bed they would like
  private void askBedType(Scanner console) {
    int bedType;
    while(this.bedType == null) {
      // uses switch, case to find what bed a Guest would like
      bedType = numberOfQuestions("What bed type? (king = 1, queen = 2, twin = 3)\n"
                                    + " Suite: has King single bed or Queen double beds\n" +
                                  " Deluxe: has Queen single bed or Twin double beds\n" +
                                  " Standard: only has Twin single bed\n", console);
      switch(bedType) {
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
          // prompts for invalid responses
          System.out.println("Invalid number. Please try again.");
      }
    }
  }

  // Asks for credit card number (16 digits) uses a Scanner in the parameter
  public static long getCreditCard(Scanner console) {
    System.out.print("What is your card number? (16 digits)\n");
    long cardNumber;
    String input;
    while(true) {
      try {
        input = console.nextLine().replaceAll("[^0-9]", "");
        if(input.length() < 16) {
          throw new NumberFormatException();
        }
        // stores the cardNumber to return
        cardNumber = Long.parseLong(input);
        break;
      } catch(NumberFormatException e) {
        // catches an invalid response and re-asks the question
        System.out.println("Invalid number. Try again.");
        System.out.print("What is your card number? (16 digits)\n");
      }
    }
    // returns the card number
    return cardNumber;
  }

  // Asks a question that requires yes or no response or y or n
  public static boolean yesOrNoQuestions(String question, Scanner console) {
    while(true) {
      try {
        // prints the question to be asked
        System.out.print(question);
        // stores the answer
        String answer = console.nextLine().toLowerCase();
        // determines if the answer was yes or no or y or n
        if(answer.contains("n")) {
          return false;
        } else if(answer.contains("y")) {
          return true;
        } else {
          throw new IllegalArgumentException();
        }
      } catch(IllegalArgumentException e) {
        // catches an answer that doesn't involve yes/no/y/n
        System.out.println("Invalid input. Try again.");
      }
    }
  }

  // Asks a question that requires integer response
  public static int numberOfQuestions(String question, Scanner console) {
    // stores the return value
    int answer;
    // asks the question
    System.out.print(question);
    while (true) {
      try {
        // stores the value if an interger is entered
        answer = Integer.parseInt(console.nextLine());
        break;
      } catch (NumberFormatException e) {
        // catches an invalid response and re-asks question
        System.out.println("Invalid input. Try again.");
        System.out.print(question);
      }
    }
    return answer;
  }

    // Sets email
    public void setEmail(Scanner console) {
        System.out.println("Please enter your email:");
        String email = console.nextLine().trim();
        while(!email.matches(EMAIL_REGEX)) {
            System.out.println("Invalid email. Please try again.");
            System.out.println("Enter your email:");
            email = console.nextLine().trim();
        }
        this.email = email;
    }

  // calculates the total cost of the Guest
  public double costOfGuests() {
    double total = (numOfSeniors * 4.99 + numOfAdults * 9.99 + numOfChildren * 3.99);
    if (isMembership || isMilitary || isGovernment) {
      total *= 0.8;
    }
    return total;
  }

    public void setName(String name){
        if (name.isEmpty() || !name.matches(NAME_REGEX)){
            throw new IllegalArgumentException("Name is not valid");
        }
        this.name = name;
    }

    public void setEmail(String email){
        if (email.isEmpty() || !email.matches(EMAIL_REGEX)){
            throw new IllegalArgumentException("Email is not valid");
        }
        this.email = email;
    }

  // Adds guest to room if room is empty
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

  public void checkOut() {
    // Guest check out (use Room class)
      room.removeGuest(this);
    room = null;
  }


  // accessor methods that are rarely used, could be used more in future
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

  public String getUniqueId() {
    return uniqueId;
  }


  // Creates a String representation of the object
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
