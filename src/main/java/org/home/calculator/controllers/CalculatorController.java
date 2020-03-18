package org.home.calculator.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.home.calculator.button.CalculatorButton;
import org.home.calculator.button.KeyboardShortcut;

import java.math.BigDecimal;

import static org.home.calculator.model.CalculationOperations.*;

public class CalculatorController {
    private static final String APP_NAME = "JavaFX Calculator";
    @FXML
    private GridPane calculatorControlsMenu;
    @FXML
    private TextField resultField;
    private boolean start = false;
    private BigDecimal number1 = BigDecimal.ZERO;
    private String calculateOperator = "";

    @FXML
    void initialize() {
    }

    /**
     * Handles backspace button action.
     *
     * @param event
     */
    @FXML
    void handleBackspaceAction(ActionEvent event) {
        String currentResult = resultField.getText();
        if (currentResult.length() != 1) {
            resultField.setText(currentResult.substring(0, currentResult.length() - 1));
        }
    }

    /**
     * Handles 'ON / C' button action
     * Removes focus from button.
     *
     * @param event
     */
    @FXML
    void handleClearAllDataAction(ActionEvent event) {
        resultField.setText("0");
        calculateOperator = "";
        start = true;
        number1 = new BigDecimal("0");
        for (Node child : calculatorControlsMenu.getChildren()) {
            child.setDisable(false);
        }
    }

    /**
     * Handles point input button action
     *
     * @param event
     */
    @FXML
    void handlePointAction(ActionEvent event) {
        if (!resultField.getText().contains(".")) {
            resultField.setText(resultField.getText() + ".");
        }
    }

    /**
     * Handles digits user's choice
     *
     * @param event
     */
    @FXML
    void handleDigitsAction(ActionEvent event) {
        if (start) {
            resultField.setText("");
            start = false;
        }

        Button buttonNumber = (Button) event.getSource();
        resultField.setText(resultField.getText() + buttonNumber.getText());
    }

    /**
     * Handles operations ('*','/','+','-','=') actions
     *
     * @param event
     */
    @FXML
    void handleOperationsAction(ActionEvent event) {
        String currentOperator = ((Button) event.getSource()).getText();

        if (!"=".equals(currentOperator)) {
            calculateOperator = currentOperator;
            number1 = new BigDecimal(resultField.getText());
            start = true;
        } else {
            if (!calculateOperator.isEmpty()) {
                BigDecimal number2 = new BigDecimal(resultField.getText());

                if ("÷".equals(calculateOperator) && number2.intValue() == 0) {
                    start = true;
                    number1 = BigDecimal.ZERO;
                    showErrorMessage("На ноль делить нельзя !");
                    blockControlsPane(calculatorControlsMenu);
                    return;
                }
                resultField.setText(executeCalculation(number1, number2).stripTrailingZeros().toPlainString());
            }
        }
    }

    private BigDecimal executeCalculation(BigDecimal number1, BigDecimal number2) {
        BigDecimal result = new BigDecimal(0);

        if (calculateOperator.equals("÷")) {
            result = DIVIDE.compute(number1, number2);
        }

        if (calculateOperator.equals("*")) {
            result = MULTIPLY.compute(number1, number2);
        }

        if (calculateOperator.equals("+")) {
            result = ADD.compute(number1, number2);
        }

        if (calculateOperator.equals("-")) {
            result = SUBTRACT.compute(number1, number2);
        }

        return result;

    }


    public void initStageParams(Stage primaryStage) {
        Scene scene = primaryStage.getScene();
        primaryStage.getIcons().add(new Image("static/images/logo.png"));
        primaryStage.setTitle(APP_NAME);
        primaryStage.setMinWidth(420);
        primaryStage.setMinHeight(450);
        primaryStage.setMaxWidth(768);
        primaryStage.setMaxHeight(1024);

        setUpCalculatorButtons(scene);
        initKeyboardShortcutListeners(scene);
    }

    /**
     * Initializes Keyboard shortcuts
     *
     * @param scene current scene
     */
    private void initKeyboardShortcutListeners(Scene scene) {
        scene.setOnKeyPressed(KeyboardShortcut::findAncExecuteKey);
    }

    /**
     * Set Javafx node button to enum
     *
     * @param scene current scene
     */
    private void setUpCalculatorButtons(Scene scene) {
        for (CalculatorButton cb : CalculatorButton.values()) {
            cb.setButton((Button) scene.lookup("#" + cb.name().toLowerCase()));
        }
    }

    private void showErrorMessage(String message) {
        resultField.setText(message);
    }

    /**
     * Disables all controls buttons, besides 'ON/C' button.
     *
     * @param pane
     */
    private void blockControlsPane(Pane pane) {
        for (Node child : pane.getChildren()) {
            if (!(child.getId() != null && child.getId().equals("clear_all"))) {
                child.setDisable(true);
            }
        }
    }

}






