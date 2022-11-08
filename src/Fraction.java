public class Fraction {
    private int mixNum;
    private int numerator;
    private int denominator;
    private boolean isValid = true;

    private String message = "";
    private final String SYNTAX_ERROR = "ERROR: Input is in an invalid format.";
    private final String ARITHMETIC_ERROR = "ERROR: Cannot divide by zero.";

    public Fraction(String fraction){
        if (!fraction.contains("/")){
            numerator = Integer.parseInt(fraction);
            denominator = 1;
        }
        else if (fraction.contains("_") && !fraction.contains("/")){ //ERROR CHECK
            isValid = false;
            message = SYNTAX_ERROR;
        }
        else {
            String[] split = fraction.split("/");
            if (fraction.contains("_")) {
                String[] mixSplit = fraction.split("_");
                mixNum = Integer.parseInt(mixSplit[0]);
                split = mixSplit[1].split("/");
            } else {mixNum = 0;}

            numerator = Integer.parseInt(split[0]);
            denominator = Integer.parseInt(split[1]);
            if (denominator == 0){ //ERROR CHECK
                isValid = false;
                message = ARITHMETIC_ERROR;
            }
        }
    }

    public boolean valid(){
        return isValid;
    }
    public String returnError() {
        return message;
    }
    public int getMixNum(){
        return mixNum;
    }
    public int getNumerator(){
        return numerator;
    }
    public int getDenominator(){
        return denominator;
    }
    public String toString(){
        if (numerator == 0){
            return "" + mixNum;
        }
        if (mixNum == 0){
            return numerator + "/" + denominator;
        }
        return mixNum + "_" + numerator + "/" + denominator;
    }

    public void sum(Fraction other){
        mixNum += other.getMixNum();
        numerator = numerator*other.getDenominator()+other.getNumerator()*denominator;
        denominator = denominator*other.getDenominator();
    }

    public void product(Fraction other){
        mixedToNumerator();
        other.mixedToNumerator();
        numerator *= other.getNumerator();
        denominator *= other.getDenominator();
    }

    public void reciprocal(){
        mixedToNumerator();
        int tempNum = numerator;
        numerator = denominator;
        denominator = tempNum;
    }

    public void simplify(){
        int i; // common number between numerator and denominator
        for (i = 2; i <= 9; i++){
            if (numerator % i == 0 && denominator % i == 0){
                break;
            }
        }
        if (i != 10){
            numerator /= i;
            denominator /= i;
        }
    }
    public void mixedToNumerator(){
        numerator += mixNum*denominator;
        mixNum = 0;
    }

}
