package org.home.calculator.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

import static org.home.calculator.model.CalculationOperations.*;

public class CalculatorController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private GridPane calculatorControlsMenu;

    @FXML
    private TextField resultField;
    private boolean start = false;
    private BigDecimal number1 = BigDecimal.ZERO;
    private String calculateOperator;

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
     *
     * @param event
     */
    @FXML
    void handleClearAllDataAction(ActionEvent event) {
        resultField.setText("0");
        start = true;
        number1 = new BigDecimal("0");
    }

    /**
     * Handles coma input button action
     *
     * @param event
     */
    @FXML
    void handlePointAction(ActionEvent event) {
        resultField.setText(resultField.getText() + ".");
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

        if (!currentOperator.equals("=")) {
            calculateOperator = currentOperator;
            number1 = new BigDecimal(resultField.getText());
            start = true;
        } else {
            BigDecimal number2 = new BigDecimal(resultField.getText());

            if (calculateOperator.equals("÷") && number2.intValue() == 0) {
                resultField.setText("На ноль делить нельзя !");
                start = true;
                number1 = BigDecimal.ZERO;
                return;
            }
            resultField.setText(executeCalculation(number1, number2).stripTrailingZeros().toPlainString());
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


}






