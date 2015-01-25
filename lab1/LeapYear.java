
public class LeapYear {

	


	public static void main(String[] args) {
	int year = 2000;
	//checkLeapYear(year)
	//	if(Year.isLeapYear(year)){
	//		System.out.println (year + " is  a leap year.");
	//	}
	//	else{
	//		System.out.println (year + " is not a leap year.");
	//	}
	if (year % 400 == 0) {
    	System.out.println (year + " is  a leap year.");
    }
	else if (year % 100 != 0 && year % 4 == 0){
 		System.out.println (year + " is a leap year.");
 	}
 	else{
 		System.out.println (year + " is not a leap year.");
 	}
 	/*

 	    static boolean isLeapYear(int year) {
    if (year % 400 == 0) {
   
        return true;
    }
    else if (year % 100 != 0 && year % 4 == 0){
        return true;
    }
    else{
        return false;
    }
    */

    
 	}
 	


}


