import java.util.Scanner;

public class Guest {
    // CONASTANTS
    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String NAME_REGEX = "^[A-Za-z]*(\\s)+[A-Z][a-z]*$";
    private static final int MAX_PER_ROOM = 10;
    
    // all discounts apply 0.8 to total coast
    private boolean isMembership;
    private boolean isMilitary;
    private boolean isGovernment;
    private boolean hasPets;

    private int numOfSeniors; //*4.99
    private int numOfAdults; //*9.99
    private int numOfChildren; //*3.99

    private String name;
    private String email;
    private String cardNum;
    // reservationDays

    private int bedType;
    private int bedNum;
    private int roomSize;

    // added Room object to facilitate the integration of the class
    private Room room;


    public Guest() {
        this(false, false, false, false, 0, 0, 0, "", "", "", 0, 0);
    }

    public Guest(boolean isMembership, boolean isMilitary, boolean isGovernment, boolean hasPets,
                 int numOfSeniors, int numOfAdults, int numOfChildren, String name, String email, 
                 String cardNum, int bedType, int bedNum) {
        setMembership(isMembership);
        setMilitary(isMilitary);
        setGovernment(isGovernment);
        setPets(hasPets);
        setSeniors(numOfSeniors);
        setAdults(numOfAdults);
        setChildren(numOfChildren);
        setName(name);
        setEmail(email);
        setBedType(bedType);
        setBedNum(bedNum);
    }
    
    // Guest creation through scanner
    public Guest(Scanner sc) {
        System.out.print("What is your full name? ");
        setName(sc.nextLine().trim());
        System.out.print("What is your email? ");
        setEmail(sc.nextLine());
        System.out.print("What is your Credit Card");
        setCardNum(sc.nextLine());
        System.out.println();

        System.out.println("For the next few questions answer with a single integer.");
        setSeniors(numberOfQuestions("How many seniors? ", sc));
        setAdults(numberOfQuestions("How many adults? ", sc));
        setChildren(numberOfQuestions("How many children? ", sc));
        setBedNum(numberOfQuestions("How many beds? ", sc));
        setBedType(numberOfQuestions("What bed type? (Twin = 1, Double = 2, Queen = 3, King = 4) ", sc));
        
        System.out.println("For the next few questions answer with Y or N.");
        setMembership(yesOrNoQuestions("Do you have a hotel membership? ", sc));
        setMilitary(yesOrNoQuestions("Are you a veteran? ", sc));
        setGovernment(yesOrNoQuestions("Are you a government employee? ", sc));
        setPets(yesOrNoQuestions("Do you have pets? ", sc));
    }
    
    
    /* Mutators */
    public void setMembership(boolean isMembership){
        this.isMembership = isMembership;
    }
    
    public void setMilitary(boolean isMilitary){
        this.isMilitary = isMilitary;
    }
    
    public void setGovernment(boolean isGovernment){
        this.isGovernment = isGovernment;
    }
    
    public void setPets(boolean hasPets){
        this.hasPets = hasPets;
    }
    
    public void setSeniors(int numOfSeniors){
        this.numOfSeniors = numOfSeniors;
    }
    
    public void setAdults(int numOfAdults){
        this.numOfAdults = numOfAdults;
    }
    
    public void setChildren(int numOfChildren){
        this.numOfChildren = numOfChildren;
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
    
    public void setCardNum(String cardNum){
        if (cardNum.length() < 13 || cardNum.length() > 16){
            throw new IllegalArgumentException("The card number is not valid");
        }
    }
    
    public void setBedType(int bedType){
        switch (bedType) {
            case 1:
                this.bedType = 1;
                break;
            case 2:
                this.bedType = 2;
                break;
            case 3:
                this.bedType = 3;
                break;
            case 4:
                this.bedType = 4;
                break;
            default:
                this.bedType = 1;
        }
    }
    
    public void setBedNum(int bedNum){
        this.bedNum = bedNum;
    }
    

    public static boolean yesOrNoQuestions(String question, Scanner sc) {
        System.out.print(question);
        String answer = sc.nextLine().toLowerCase();
        if (answer.contains("n")) {
            return false;
        } else if (answer.contains("y")) {
            return true;
        } else {
            System.out.println("Invalid input. Please try again.");
            return yesOrNoQuestions(question, sc);
        }
    }

    public static int numberOfQuestions(String question, Scanner sc) {
        int answer;
        System.out.print(question);
        while (true) {
            try {
                answer = Integer.parseInt(sc.nextLine());
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


    // added this method to make an easy connection between the two objects, much more is needed
    // public void createARoom() {
      // room = new Room(42, 330, bedType, bedNum, 89.99 + costOfGuests(), this, hasPets);
    // }


    /* Accessors */
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

    public String getEmail(){
        return email;
    }
    
    public String getCardNum() {
        return cardNum;
    }

    public int getBedType() {
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

    public Guest getGuest(){
        return this;
    }

    public void assignRoom(Room room) {
        if (room == null) {
            this.room = room;
        } else {
            System.out.println(name + " already has a room");
        }
    }


    public String toString() {
        if (this == null) { // This doesn't make sense. You can't call if it is null.
            return null;
        } else {
            return "Guest: " +
                   "\nName: " + name +
                   "\nEmail: " + email +
                   "\nCard Number: " + cardNum +
                   "\nMembership: " + isMembership +
                   "\nMilitary Discount: " + isMilitary +
                   "\nGovernment Discount: " + isGovernment +
                   "\nHas Pets: " + hasPets +
                   "\nNumber of Seniors: " + numOfSeniors +
                   "\nNumber of Adults: " + numOfAdults +
                   "\nNumber of Children: " + numOfChildren;
                   //"\nRoom: " + room;
        }
    }
}
