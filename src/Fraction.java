public class Fraction {
    private int wholeNum;
    private int numerator;
    private int denominator;
    private boolean isValid = true;

    private String message = "";
    private final String SYNTAX_ERROR = "ERROR: Input is in an invalid format.";
    private final String DIVIDE_BY_ZERO_ERROR = "ERROR: Cannot divide by zero.";

    public Fraction(String fraction){
        if (fraction.contains("_") && !fraction.contains("/")){ // ERROR CHECK
            isValid = false;
            message = SYNTAX_ERROR;
        }
        else if (!fraction.contains("/")){
            // Initializes numerator as the whole number w/ 1 as denominator
            try {
                numerator = Integer.parseInt(fraction);
            } catch (NumberFormatException e) {isValid = false; message = SYNTAX_ERROR;}
            denominator = 1;
        }
        else {
            String[] split = fraction.split("/");

            // Initialize the whole number
            if (fraction.contains("_")) {
                // Makes the fraction split between the whole number and the fraction.
                String[] mixSplit = fraction.split("_");
                try {
                    wholeNum = Integer.parseInt(mixSplit[0]);
                } catch (NumberFormatException e) {isValid = false; message = SYNTAX_ERROR;} // ERROR CHECK
                // Sets up to split the numerator and denominator
                split = mixSplit[1].split("/");
            } else {wholeNum = 0;}

            // Initialize the numerator
            try {
                numerator = Integer.parseInt(split[0]);
            }
            catch (NumberFormatException e) {isValid = false; message = SYNTAX_ERROR + "this numerrator";} // ERROR CHECK
            // Initialize the denominator
            try {
                denominator = Integer.parseInt(split[1]);
            }
            catch (NumberFormatException e) {isValid = false; message = SYNTAX_ERROR+ "this denominator";} // ERROR CHECK

            if (denominator == 0){ // ERROR CHECK
                isValid = false;
                message = DIVIDE_BY_ZERO_ERROR;
            }
        }
    }

    public boolean valid(){
        return isValid;
    }
    public String returnError() {
        return message;
    }
    public int getwholeNum(){
        return wholeNum;
    }
    public int getNumerator(){
        return numerator;
    }
    public int getDenominator(){
        return denominator;
    }
    public String returnString(){
        simplify();
        if (numerator == 0){
            return "" + wholeNum;
        }
        if (wholeNum == 0){
            return numerator + "/" + denominator;
        }
        return wholeNum + "_" + numerator + "/" + denominator;
    }

    public void add(Fraction other){
        mixedToNumerator();
        other.mixedToNumerator();
        numerator = numerator*other.getDenominator()+other.getNumerator()*denominator;
        denominator = denominator*other.getDenominator();

    }
    public void subtract(Fraction other){
        mixedToNumerator();
        other.mixedToNumerator();
        numerator = numerator*other.getDenominator()-other.getNumerator()*denominator;
        denominator = denominator*other.getDenominator();
    }
    public void multiply(Fraction other){
        mixedToNumerator();
        other.mixedToNumerator();
        numerator *= other.getNumerator();
        denominator *= other.getDenominator();
    }
    public void divide(Fraction other){
        mixedToNumerator();
        other.mixedToNumerator();
        numerator *= other.getDenominator();
        denominator *= other.getNumerator();
        if (denominator == 0){ //ERROR CHECK
            isValid = false;
            message = DIVIDE_BY_ZERO_ERROR;
        }
    }

    // Greatest common denominator algorithm https://www.baeldung.com/java-greatest-common-divisor
    public int findGcd(int n1, int n2) {
        if (n2 == 0) {
            return n1;
        }
        return findGcd(n2, n1 % n2);
    }
    public void simplify(){
        int gcd = findGcd(Math.abs(numerator), Math.abs(denominator));
        numerator /= gcd;
        denominator /= gcd;

        // Rearranging negative sign
        if (numerator < 0 && denominator < 0){ // If both num and den are negative, get rid of both negatives
            numerator *= -1;
            denominator *= -1;
        }
        else if (numerator > 0 && denominator < 0){ // if only the denominator is negative, set the negative to either the mixed number or numerator
            denominator *= -1;
            if (wholeNum != 0){
                wholeNum *= -1;
            } else {numerator *= -1;}
        }
        else if (numerator < 0 && denominator > 0){ // if only the numerator is negative, set the negative to the whole number
            if (wholeNum != 0){
                wholeNum *= -1;
                numerator *= -1;
            }
        }

        if (numerator >= denominator){
            wholeNum += numerator / denominator;
            numerator %= denominator;
        }
        else if (-1*numerator >= denominator){
            wholeNum += Math.abs(numerator) / denominator;
            wholeNum *= -1;
            numerator %= denominator;
            numerator *= -1;
        }

        if (denominator == 1 && wholeNum == 0){
            wholeNum = numerator;
            numerator = 0;
        }
    }
    public void mixedToNumerator(){
        if (wholeNum>=0){
            numerator += wholeNum*denominator;
        }
        else if (wholeNum < 0){
            numerator += Math.abs(wholeNum)*denominator;
            numerator *= -1;
        }
        wholeNum = 0;
    }
}
