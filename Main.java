/* First of all - thanks to my friend Yurii Tatarintsev for this task, 
 * it was really interesting (and hard, but mainly - interesting))) to solve it. 
 * 
 * So, the task is:
 * The user enters positive integer number, and the program have to return 
 * the smallest available palindrome (mirrored, read the same way from left to 
 * right and from right to left, like 101, 1551, 19091, etc.) number, 
 * bigger than the entered one*/

package Yurii;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main{
public static void main(String[] args) {

	int number = 0;
	String stringNumber = "";
	int middle = 0;
	int answer = 0;
	int counter = 0;
	boolean only9checker = true;
	boolean numberFlag = true;
	
	System.out.println("Hello! Please enter your positive integer number here: ");
    Scanner scan = new Scanner(System.in);
	try {
		number = scan.nextInt();
	}
	catch (InputMismatchException ex) {
		System.out.println ("Sorry, it was not the number");
		numberFlag = false;
	}
	stringNumber = Integer.toString(number);
	middle = stringNumber.length()/2;
	int [] numDigitsArray = new int [stringNumber.length()];

	for (int i = 0; i < numDigitsArray.length; i++) {
		numDigitsArray[i] = Character.getNumericValue(stringNumber.charAt(i));
		if (numDigitsArray[i] != 9) only9checker = false; 
	}
  if (number >=0) {
	if (number >= 0 && number <= 8) {   // checking is number between -1 and 9 
    answer = number+1;
    }
      else {
      if (only9checker) { // checking if number consists of "9"s only
      answer = number+2;
      }
        else {
        if (numDigitsArray.length%2 == 0) { // checking does the number has Even digits (2/4/6/8/etc.)
          if (firstHalfReversed (numDigitsArray, middle) > secondHalf (number, middle)) {
    		answer = ((int) Math.round(firstHalf(number, middle)*Math.pow(10, middle) + firstHalfReversed (numDigitsArray, middle)));
    	  }
    	  else {
    		if ((int)(number / Math.pow(10, middle))%10 == 9) {
    			answer = (firstHalf(number, middle)+1)*(int)Math.pow(10, middle) + mirrored((firstHalf(number, middle)+1)); 
    		} else
    		answer = ((int) Math.round((firstHalf(number, middle)+1)*Math.pow(10, middle) + firstHalfReversed (numDigitsArray, middle) + Math.pow(10, middle-1)));
    	  }
        }
    	  else { // if the number has Odd digits (1/3/5/7/etc.)
    		  if (firstHalfReversed (numDigitsArray, middle) > secondHalf (number, middle)){
            	answer = ((int) Math.round(firstHalf(number, middle)*Math.pow(10, middle) + firstHalfReversed (numDigitsArray, middle)));
            }
            else if (middlePart(number, middle) == 9) {
            	 answer = ((int) Math.round((firstHalf(number, middle)+1)*Math.pow(10, middle) + firstHalfReversed (numDigitsArray, middle) + Math.pow(10, middle-1)));
              }
            else {
              answer = ((int) Math.round((firstHalf(number, middle))*Math.pow(10, middle) + Math.pow(10, middle) + firstHalfReversed (numDigitsArray, middle)));
            }
    	  }
        }
      }
  if (numberFlag) {System.out.println ("And the smallest available palindrome number, bigger than entered number " + number + " is: " + answer + ", thank you! :-)");}
  } 
  else System.out.println ("Sorry, I asked for POSITIVE number, it means >= 0. Thank you! :-)");
}
private static int mirrored(int mirror) {
	String Mirror = Integer.toString(mirror);
	int res = 0;
	int [] mirrorArray = new int [Mirror.length()];

	for (int i = 0; i < mirrorArray.length; i++) {
		mirrorArray[i] = Character.getNumericValue(Mirror.charAt(i));
	}
	for (int j = 0; j<mirrorArray.length; j++) {
		res +=  mirrorArray[j] * Math.pow(10, j);
	}
	return res;
}
private static int firstHalf(int number, int middle) {
	return (int) (number / Math.pow(10, middle));
}
private static int firstHalfReversed(int[] numDigitsArray, int middle) {
	int res = 0;
	for (int i = 0; i < middle; i++) {
		res += Math.pow(10, i)*numDigitsArray[i];
	}
	return res;
}
private static int secondHalf(int number, int middle) {
	return (int) (number - (int)(number / Math.pow(10, middle))*Math.pow(10, middle));
}
private static int secondHalfReversed(int[] numDigitsArray, int middle) {
	int res = 0;
	int digitCounter = 1;
	for (int i = middle; i < numDigitsArray.length; i++) {
		res += digitCounter*numDigitsArray[i];
		digitCounter *= 10;
	}
	return res;
}
private static int middlePart(int number, int middle) { // it is a middle digit for number with odd digits
	return (int)((number / Math.pow(10, middle))%10);
  }
}
// System.out.println ("firstHalfReversed " + firstHalfReversed (numDigitsArray, middle))
// System.out.println ("secondHalf is " + secondHalf (number, middle));
// System.out.println ("firstHalf+1 " + firstHalf (number, middle+1)); 
// System.out.println ("secondHalfReverced+1 is " + secondHalfReversed (numDigitsArray, middle+1));
// System.out.println ("middledigit " + middlePart(number, middle));
// System.out.println ("middle position has # " + (middle+1));