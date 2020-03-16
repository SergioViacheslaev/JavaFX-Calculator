package org.home.calculator.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.home.calculator.model.Calculation;

import java.net.URL;
import java.util.ResourceBundle;

public class CalculatorController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField resultField;
    private Calculation calculation;
    private boolean start = false;
    private long number1 = 0L;
    private String operator;

    @FXML
    void processControls(ActionEvent event) {
        String controlButtonName = ((Button) event.getSource()).getText();

        switch (controlButtonName) {
            case "ON / C":
                resultField.setText("0");
                start = true;
                break;
            case ",":
                resultField.setText(resultField.getText() + ",");
            default:
                break;
        }
    }

    @FXML
    void processNumbers(ActionEvent event) {
        if (start) {
            resultField.setText("");
            start = false;
        }

        Button buttonNumber = (Button) event.getSource();
        resultField.setText(resultField.getText() + buttonNumber.getText());
    }

    @FXML
    void processOperators(ActionEvent event) {
        String operatorValue = ((Button) event.getSource()).getText();

        if (!operatorValue.equals("=")) {
            operator = operatorValue;
            number1 = Long.parseLong(resultField.getText());
            start = true;
        } else {
            long number2 = Long.parseLong(resultField.getText());

            if (operator.equals("/") && number2 == 0) {
                resultField.setText("На ноль делить нельзя !");
                return;
            }


            long calcResult = calculation.compute(number1, number2, operator);
            resultField.setText(String.valueOf(calcResult));
        }


    }

    @FXML
    void initialize() {
        calculation = new Calculation();
    }


}