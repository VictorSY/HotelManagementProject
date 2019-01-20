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
      
      double total = (numOfSeniors*50 + numOfAdults*100 + numOfChildren*30);
      if(isMembership == true || isMilitary == true || isGovernment == true){
          total *= 0.8;
        }         
      return total;
  }
  
  public void checkIn(){
    // Guest check in (use Room class)
  }
  
  public void checkOut(){
    // Guest check out (use Room class)
  }

}
