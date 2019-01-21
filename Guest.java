public class Guest{
  // all discounts apply 0.8 to total coast
  private boolean isMembership; 
  private boolean isMilitary; 
  private boolean isGovernment;
  private boolean hasPets;
  
  private double basicPricePerPerson = 100;
  private int numOfSeniors; //50
  private int numOfAdults; //100
  private int numOfChildren; //30
  
  private String name;
  private int cardNum;
  // reservationDays
  
  private String bedType;
  private int bedNum;
  // added Room object to facilitate the integration of the class
  private Room room;
  
  public Guest(){
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
                 , int bedNum){
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
  
  public double costOfGuests(){
    // calculate the total cost of the Guest
    // I haven't use basicPricePerPerson yet for the cost
    
    // possible other option with reasonable additional charges for cost of guests, would be added to the cost of room
    //double total = (numOfSeniors*4.99 + numOfAdults*9.99 + numOfChildren*3.99);
    //if(isMembership == true || isMilitary == true || isGovernment == true){
      //total *= 0.8;
    //}         
    //return total;
  //}
    
    double total = (numOfSeniors*50 + numOfAdults*100 + numOfChildren*30);
    if(isMembership == true || isMilitary == true || isGovernment == true){
      total *= 0.8;
    }         
    return total;
  }
  
  public void checkIn(){
    // Guest check in (use Room class)
    room.setGuest(this);
  }
  
  public void checkOut(){
    // Guest check out (use Room class)
    room.removeGuest();
  }
  
  // the toString method
  public String toString() {
    return "Membership: " + isMembership +
      "\nMilitary Discount: " + isMilitary +
      "\nGovernment Discount: " + isGovernment +
      "\nHas Pets: " + hasPets +
      "\nNumber of Seniors: " + numOfSeniors +
      "\nNumber of Adults: " + numOfAdults +
      "\nNumber of Children: " + numOfChildren;
  }
  
}
