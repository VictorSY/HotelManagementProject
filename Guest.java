public class Guest{
  // all discounts apply 0.8 to total coast
  private boolean membership; 
  private boolean military; 
  private boolean government;
  
  private double basicPricePerPerson = 100;
  private int numOfSeniors; //50
  private int numOfAdults; //100
  private int numOfChildren; //30
  
  
  private boolean pets;
  
  private String name;
  private int cardNum;
  // reservationDays
  
  private String bedType;
  private int bedNum;
  
  public Guest(){
      this.membership = false;
      this.military = false;
      this.government = false;
      this.numOfSeniors = 0;
      this.numOfAdults = 0;
      this.numOfChildren = 0;
      this.pets = false;
      
      this.name = "";
      this.cardNum = 0;
      
      this.bedType = "";
      this.bedNum = 0;
  }  
  
  public Guest(boolean membership, boolean military, boolean government,
                int numOfSeniors, int numOfAdults, int numOfChildren,
                boolean pets, String name, int cardNum, String bedType
                , int bedNum){
      this.membership = membership;
      this.military = military;
      this.government = government;
      this.numOfSeniors = numOfSeniors;
      this.numOfAdults = numOfAdults;
      this.numOfChildren = numOfChildren;
      this.pets = pets;
      
      this.name = name;
      this.cardNum = cardNum;
      
      this.bedType = bedType;
      this.bedNum = bedNum;
                
    }
  
  public double costOfGuests(){
      // calculate the total cost of the Guest
      // I haven't use basicPricePerPerson yet for the cost
      
      double total = (numOfSeniors*50 + numOfAdults*100 + numOfChildren*30);
      if(membership == true || military == true || government == true){
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
