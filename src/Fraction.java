public class Fraction {
    // Instance variables
    private int wholeNum;
    private int numerator;
    private int denominator;
    // Variable to record if the fraction is valid
    private boolean isValid = true;

    // Error messages
    private String message = "";
    private final String SYNTAX_ERROR = "ERROR: Input is in an invalid format.";
    private final String DIVIDE_BY_ZERO_ERROR = "ERROR: Cannot divide by zero.";

    /**
     * Constructs a Fraction object.
     * @param fraction the inputted fraction string to be formatted and organized.
     */
    public Fraction(String fraction){
        // Special cases:
        if (fraction.contains("_") && !fraction.contains("/")){ // ERROR CHECK: Mixed number with underscore but no fraction
            isValid = false;
            message = SYNTAX_ERROR;
        }
        else if (!fraction.contains("/")){ // Initializes the numerator as the whole number with 1 as denominator
            try {
                numerator = Integer.parseInt(fraction);
            } catch (NumberFormatException e) {isValid = false; message = SYNTAX_ERROR;}
            denominator = 1;
        }

        // General case:
        else {
            String[] split = fraction.split("/");

            // Initializes the whole number
            if (fraction.contains("_")) {
                // Makes the fraction split between the whole number and the fraction.
                String[] mixSplit = fraction.split("_");
                try {
                    wholeNum = Integer.parseInt(mixSplit[0]);
                } catch (NumberFormatException e) { // ERROR CHECK: Whole number is not an integer.
                    isValid = false; message = SYNTAX_ERROR;
                }
                // Sets up to split the numerator and denominator
                split = mixSplit[1].split("/");
            } else {wholeNum = 0;}

            // Initializes the numerator
            try {
                numerator = Integer.parseInt(split[0]);
            }
            catch (NumberFormatException e) { // ERROR CHECK: Numerator is not an integer.
                isValid = false; message = SYNTAX_ERROR;
            }

            // Initializes the denominator
            try {
                denominator = Integer.parseInt(split[1]);
            }
            catch (NumberFormatException e) { // ERROR CHECK: Denominator is not an integer.
                isValid = false; message = SYNTAX_ERROR;
            }

            if (denominator == 0){ // ERROR CHECK: Denominator is zero.
                isValid = false;
                message = DIVIDE_BY_ZERO_ERROR;
            }
        }
    }

    /**
     * Returns if the fraction is valid or not.
     * @return the isValid boolean value
     */
    public boolean valid(){
        return isValid;
    }

    /**
     * Returns the error message when the fraction is invalid.
     * @return the error message string
     */
    public String returnError() {
        return message;
    }

    /**
     * Gets the whole number value.
     * @return whole number integer
     */
    public int getwholeNum(){
        return wholeNum;
    }

    /**
     * Gets the numerator value.
     * @return numerator integer
     */
    public int getNumerator(){
        return numerator;
    }

    /**
     * Gets the denominator value.
     * @return denominator integer
     */
    public int getDenominator(){
        return denominator;
    }

    /**
     * Converts the fraction into the printable format.
     * Example: num = 1, den = 4, whole_num = 3
     * Turns into --> 3_1/4
     * @return Full formatted fraction string
     */
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

    /**
     * Updates this fraction by adding another fraction
     * @param other Fraction to add to existing fraction
     */
    public void add(Fraction other){
        mixedToNumerator();
        other.mixedToNumerator();
        numerator = numerator*other.getDenominator()+other.getNumerator()*denominator;
        denominator = denominator*other.getDenominator();
    }

    /**
     * Updates this fraction by subtracting another fraction.
     * @param other Fraction to subtract to existing fraction
     */
    public void subtract(Fraction other){
        mixedToNumerator();
        other.mixedToNumerator();
        numerator = numerator*other.getDenominator()-other.getNumerator()*denominator;
        denominator = denominator*other.getDenominator();
    }

    /**
     * Updates this fraction by multiplying another fraction
     * @param other Fraction to multiply to existing fraction
     */
    public void multiply(Fraction other){
        mixedToNumerator();
        other.mixedToNumerator();
        numerator *= other.getNumerator();
        denominator *= other.getDenominator();
    }

    /**
     * Updates this fraction by dividing another fraction
     * @param other Fraction to divide to existing fraction
     */
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

    /**
     * Finds the greatest common denominator of the numerator and denominator. Used to simplify the fraction.
     * @param n1 the numerator integer
     * @param n2 the denominator integer
     * @return the greatest common denominator between the two values
     */
    // Greatest common denominator algorithm https://www.baeldung.com/java-greatest-common-divisor
    public int findGcd(int n1, int n2) {
        if (n2 == 0) {
            return n1;
        }
        return findGcd(n2, n1 % n2);
    }

    /**
     * Simplifies the fraction by reducing the numerator and denominator and by making the whole number the highest possible value.
     */
    public void simplify(){
        // Reduces the numerator and denominator.
        int gcd = findGcd(Math.abs(numerator), Math.abs(denominator));
        numerator /= gcd;
        denominator /= gcd;

        // Rearranging negative sign (i.e. moving the negative sign to the front-most number).
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

        // Makes the whole number the highest number possible.
        if (numerator >= denominator){ // with a positive numerator, reduce the numerator by bringing it to the whole number.
            wholeNum += numerator / denominator;
            numerator %= denominator;
        }
        else if (-1*numerator >= denominator){ // with a negative numerator, reduce the numerator by bringing it to the whole number and transfer the negative sign.
            wholeNum += Math.abs(numerator) / denominator;
            wholeNum *= -1;
            numerator %= denominator;
            numerator *= -1;
        }

        // Makes the whole number the same as the numerator if the denominator is 1.
        if (denominator == 1 && wholeNum == 0){
            wholeNum = numerator;
            numerator = 0;
        }
    }

    /**
     * Converts the fraction into an improper fraction (used for calculation).
     * Example: 5_1/2 --> 11/2
     */
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
