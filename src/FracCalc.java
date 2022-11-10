import java.util.Scanner;
public class FracCalc {

    public static void main(String[] args)
    {
        // TODO: Read the input from the user and call produceAnswer with an equation
        Scanner in = new Scanner(System.in);
        while (true){
            System.out.println("What is your fraction expression? Enter \"quit\" to exit and enter \"help\" for formatting/input help.");
            String fraction = in.nextLine();
            if (fraction.equals("quit")){
                break;
            }
            if (fraction.equals("help")){
                System.out.println("************************************************* FORMATTING HELP *************************************************");
                System.out.println("Your input should be an expression of two terms:");
                System.out.println("[Fraction1 {operator} Fraction2]");
                System.out.println("The operator has to be one of the four: \"+\", \"-\", \"*\", and \"/\".");
                System.out.println("");
                System.out.println("The fractions can expressed as either whole numbers, mixed numbers, improper fractions, or proper fractions.");
                System.out.println("Proper and improper fractions are entered as \"n/d\", where \"n\" is the numerator number and \"d\" is the denominator number.");
                System.out.println("Mixed numbers are put in the format \"w_n/d\", where \"w\" is the whole number. Whole numbers can be entered as just \"w\".");
                System.out.println("If a fraction is negative, the negative sign \"-\" can be put in front of either w, n, or d.");
                System.out.println("");
                System.out.println("EXAMPLE INPUT: \"-4_3/5 - -7/3\"");
                System.out.println("********************************************************************************************************************");
                System.out.println("");
                continue;
            }
            System.out.println(produceAnswer(fraction));
        }
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
        Scanner expression = new Scanner(input);
        expression.useDelimiter(" ");
        if (!expression.hasNext()){
            return "ERROR: Input is in an invalid format.";
        }

        // Initializing the first fraction
        String fraction1 = expression.next();
        Fraction frac1 = new Fraction(fraction1);
        if (!validateFraction(fraction1)){
            return frac1.returnError();
        }

        // Initializing the operator
        if (!expression.hasNext()){return "ERROR: Input is in an invalid format.";} // ERROR CHECK
        String operator = expression.next();
        if (!operator.equals("+") && !operator.equals("-") && !operator.equals("*") && !operator.equals("/")){
            return "ERROR: Invalid operator in input";
        }

        // Initializing the second fraction
        if (!expression.hasNext()){return "ERROR: Input is in an invalid format.";} // ERROR CHECK
        String fraction2 = expression.next();
        Fraction frac2 = new Fraction(fraction2);
        if (!validateFraction(fraction2)){
            return frac2.returnError();
        }

        switch (operator) {
            case "+":
                frac1.add(frac2);
                break;
            case "-":
                frac1.subtract(frac2);
                break;
            case "/":
                frac1.divide(frac2);
                break;
            case "*":
                frac1.multiply(frac2);
                break;
        }
        return frac1.returnString();
    }

    // TODO: Fill in the space below with any helper methods that you think you will need
    public static boolean validateFraction(String fraction){
        Fraction frac = new Fraction(fraction);
        if (!frac.valid()){
            return false;
        }
        return true;
    }
}
