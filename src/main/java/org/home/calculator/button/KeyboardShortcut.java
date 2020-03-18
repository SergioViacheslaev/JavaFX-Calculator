package org.home.calculator.button;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyEvent;

import java.util.HashMap;
import java.util.Map;

import static javafx.scene.input.KeyCombination.CONTROL_DOWN;
import static org.home.calculator.button.CalculatorButton.*;

/**
 * Class represents keyboard shortcuts and simply fires correspond calculator button
 */
public class KeyboardShortcut {

    /**
     * Map contains single keyboard button shortcut maps to calculator button
     */
    private static Map<KeyCode, CalculatorButton> buttonMap = new HashMap<>();

    /**
     * Map contains combination keyboard buttons shortcut maps to calculator button
     */
    private static Map<KeyCode, CalculatorButton> ctrlComboMap = new HashMap<>();

    // Maps keyboard button to calculator button mapping
    static {
        //number buttons
        buttonMap.put(KeyCode.DIGIT0, NUMBER_0);
        buttonMap.put(KeyCode.DIGIT1, NUMBER_1);
        buttonMap.put(KeyCode.DIGIT2, NUMBER_2);
        buttonMap.put(KeyCode.DIGIT3, NUMBER_3);
        buttonMap.put(KeyCode.DIGIT4, NUMBER_4);
        buttonMap.put(KeyCode.DIGIT5, NUMBER_5);
        buttonMap.put(KeyCode.DIGIT6, NUMBER_6);
        buttonMap.put(KeyCode.DIGIT7, NUMBER_7);
        buttonMap.put(KeyCode.DIGIT8, NUMBER_8);
        buttonMap.put(KeyCode.DIGIT9, NUMBER_9);

        //number numpad buttons
        buttonMap.put(KeyCode.NUMPAD0, NUMBER_0);
        buttonMap.put(KeyCode.NUMPAD1, NUMBER_1);
        buttonMap.put(KeyCode.NUMPAD2, NUMBER_2);
        buttonMap.put(KeyCode.NUMPAD3, NUMBER_3);
        buttonMap.put(KeyCode.NUMPAD4, NUMBER_4);
        buttonMap.put(KeyCode.NUMPAD5, NUMBER_5);
        buttonMap.put(KeyCode.NUMPAD6, NUMBER_6);
        buttonMap.put(KeyCode.NUMPAD7, NUMBER_7);
        buttonMap.put(KeyCode.NUMPAD8, NUMBER_8);
        buttonMap.put(KeyCode.NUMPAD9, NUMBER_9);

        //decimal point buttons
        buttonMap.put(KeyCode.DECIMAL, POINT);

        //clear buttons
        buttonMap.put(KeyCode.BACK_SPACE, BACKSPACE);
        buttonMap.put(KeyCode.SPACE, CLEAR_ALL);

        //binary operation buttons
        buttonMap.put(KeyCode.ENTER, EQUAL);
        buttonMap.put(KeyCode.EQUALS, EQUAL);
        buttonMap.put(KeyCode.PLUS, ADD);
        buttonMap.put(KeyCode.ADD, ADD);
        buttonMap.put(KeyCode.MINUS, SUBTRACT);
        buttonMap.put(KeyCode.SUBTRACT, SUBTRACT);
        buttonMap.put(KeyCode.DIVIDE, DIVIDE);
        buttonMap.put(KeyCode.SLASH, DIVIDE);
        buttonMap.put(KeyCode.MULTIPLY, MULTIPLY);
        buttonMap.put(KeyCode.STAR, MULTIPLY);


        //with ctrl pressed
        //binary operation buttons
        ctrlComboMap.put(KeyCode.DIGIT8, MULTIPLY);
        ctrlComboMap.put(KeyCode.EQUALS, ADD);
    }


    /**
     * Finds keyboard key and pushes corresponding calculator button
     *
     * @param event keyboard key event
     */
    public static void findAncExecuteKey(KeyEvent event) {
        KeyCode keyCode = event.getCode();

        //finds combination using combination map
        CalculatorButton cb = ctrlComboMap.get(keyCode);
        //if exist fires correspond button
        if (cb != null) {
            KeyCodeCombination combination = new KeyCodeCombination(keyCode, CONTROL_DOWN);
            if (combination.match(event)) {
                cb = ctrlComboMap.get(keyCode);
                cb.getButton().fire();
                return;
            }
        }

        //if no combinations found use single button mapping
        cb = buttonMap.get(keyCode);
        if (cb != null) {
            cb = buttonMap.get(keyCode);
            cb.getButton().fire();
        }
    }
}
