package org.home.calculator.model;

/**
 * @author Sergei Viacheslaev
 */
public class Calculation {
    public long compute(long number1, long number2, String operator) {
        switch (operator) {
            case "/":
                return number1 / number2;
            case "*":
                return number1 * number2;
            case "+":
                return number1 + number2;
            case "-":
                return number1 - number2;
            default:
                return 0;
        }

    }
}
