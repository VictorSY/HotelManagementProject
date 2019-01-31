public class Date{
    // instance variables
    private int day;
    private int month;
    private int year;

    /* Constructor */
    public Date(int day, int month, int year){
        setDay(day);
        setMonth(month);
        setYear(year);
    }
    
    
    /* Accesors */
    public int getDay(){
        return this.day;
    }
    
    public int getDaysInMonth(){
        int days = 31;
        if (this.month == 2){
            days = 28;
            if (isLeapYear(this.year))days = 29;
        }else if(this.month == 4 || this.month == 6 || this.month == 9 || this.month == 11){
            days = 30;
        }
        return days;
    }

    public int getMonth(){
        return this.month;
    }
    
    public int getDaysInYear(){
        int days = 365;
        if(isLeapYear(this.year)) days = 366;
        return days;
    }

    public int getYear(){
        return this.year;
    }
    
    
    /* Mutators */
    public void setDay(int day){
        if (day < 1 || day > 31){
            throw new IllegalArgumentException("Days must be from 1 to 31");
        }
        this.day = day;
    }
    
    public void setMonth(int month){
        if (month < 1 || month > 12){
            throw new IllegalArgumentException("Months must be from 1 to 12");
        }
        this.month = month;
    }

    public void setYear(int year){
        if (year < 0 || year > 3000){
            throw new IllegalArgumentException("Years must be from 1 to 3000");
        }
        this.year = year;
    }
    
    
    
    public void nextDay(){
        int day = this.day++;
        if (day > getDaysInMonth()) {
            day = 1;
            month++;
            if (month > 12) {
                month = 1;
                year++;
            }
        }
    }

    
    public boolean isLeapYear(int year){
        return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
    }

    
    public String toString(){
        String date = this.day + "/" + this.month + "/" + this.year;
        return date;
    }
}
