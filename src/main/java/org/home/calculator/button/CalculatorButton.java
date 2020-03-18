package org.home.calculator.button;

import javafx.scene.control.Button;

/**
 * This enum represents calculator button. Each constant consist string representation and button node which used in Controller class
 * to handle button events
 */
public enum CalculatorButton {

    //Digit number buttons
    NUMBER_1,
    NUMBER_2,
    NUMBER_3,
    NUMBER_4,
    NUMBER_5,
    NUMBER_6,
    NUMBER_7,
    NUMBER_8,
    NUMBER_9,
    NUMBER_0,

    //binary operation buttons
    ADD,
    SUBTRACT,
    DIVIDE,
    MULTIPLY,
    EQUAL,

    //decimal point button
    POINT,

    //clear buttons
    CLEAR_ALL,

    //backspace button
    BACKSPACE;

    /**
     * Javafx Button corresponds calculator button
     */
    private Button button;

    public void setButton(Button button) {
        this.button = button;
    }

    public Button getButton() {
        return button;
    }

}
