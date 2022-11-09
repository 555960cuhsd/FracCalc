import java.util.Scanner;
public class FracCalc {

    public static void main(String[] args)
    {
        // TODO: Read the input from the user and call produceAnswer with an equation
        Scanner in = new Scanner(System.in);
        boolean quit = false;
        while (!quit){
            System.out.println("What is your fraction expression? (N/D + N/D)");
            String fraction = in.nextLine();
            if (fraction.equals("quit")){
                quit = true;
                break;
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
        expression.useDelimiter(" "); // Add expression.java?
        if (!expression.hasNext()){
            return "ERROR: Input is in an invalid format.";
        }

        // Initializing the fractions
        String fraction1 = expression.next();
        Fraction frac1 = new Fraction(fraction1);
        if (!validateFraction(fraction1)){
            return frac1.returnError();
        }

        if (!expression.hasNext()){return "ERROR: Input is in an invalid format.";} // ERROR CHECK
        String operator = expression.next();
        String validOperators = "+-/*";
        if (!validOperators.contains(operator)){
            return "ERROR: Invalid operator in input";
        }

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
