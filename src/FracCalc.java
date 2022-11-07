import java.util.Scanner;
public class FracCalc {

    public static void main(String[] args) 
    {
        // TODO: Read the input from the user and call produceAnswer with an equation
        Scanner in = new Scanner(System.in);
        System.out.println("What is your fraction expression? (N/D + N/D)");
        String fraction = in.nextLine();
        System.out.println(produceAnswer(fraction));

    }
    
    // ** IMPORTANT ** DO NOT DELETE THIS FUNCTION.  This function will be used to test your code
    // This function takes a String 'input' and produces the result
    //
    // input is a fraction string that needs to be evaluated.  For your program, this will be the user input.
    //      e.g. input ==> "1/2 + 3/4"
    //        
    // The function should return the result of the fraction after it has been calculated
    //      e.g. return ==> "1_1/4"



    public static String produceAnswer(String input)
    { 
        // TODO: Implement this function to produce the solution to the input
        Scanner frac = new Scanner(input);
        frac.useDelimiter(" ");
        String fraction1 = frac.next();
        String operator = frac.next();
        String fraction2 = frac.next();
        validateFraction(fraction2);

        return fraction2;
    }

    // TODO: Fill in the space below with any helper methods that you think you will need
    public static boolean validateFraction(String fraction){

        Scanner frac = new Scanner(fraction);
        String[] split = fraction.split("/");
        int numerator = Integer.parseInt(split[0]);
        int denominator = Integer.parseInt(split[1]);
        return true;
    }

    public String addFraction(String fraction){
        /*
        validateFraction(fraction);
        String[] fracSplit = fraction.split("+");
        String frac1 = fracSplit.split[0];

        int num1 = Integer.parseInt(split[0]);
        int den1 = Integer.parseInt(split[1]);
         */
        return "";
    }
}
