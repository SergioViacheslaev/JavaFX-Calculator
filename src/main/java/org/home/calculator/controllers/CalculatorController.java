package org.home.calculator.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.home.calculator.api.Observable;
import org.home.calculator.api.Observer;
import org.home.calculator.config.AppConfig;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.home.calculator.model.CalculationOperations.*;

@Getter
@Setter
public class CalculatorController implements Observable {
    private List<Observer> observers = new ArrayList<>();
    private AppConfig appConfig;
    private boolean startDigitInput = false;
    private BigDecimal number1;
    private BigDecimal number2;
    private String calculatedResult = "";
    private String calculateOperator = "";

    @FXML
    private GridPane calculatorControlsMenu;
    @FXML
    private TextField resultField;


    @Override
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers(Object arg) {
        observers.forEach(observer -> observer.update(this, arg));
    }

    @FXML
    void languageMenuEnglishAction(ActionEvent event) {
        notifyObservers("en");
    }

    @FXML
    void languageMenuRussianAction(ActionEvent event) {
        notifyObservers("ru");
    }

    @FXML
    void showMenuAbout(ActionEvent event) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/view/menuAbout.fxml"));
            stage.setTitle(appConfig.getBundle().getString("menu.about"));
            stage.setMinHeight(250);
            stage.setMinWidth(300);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
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
     * Clears all memory.
     * Removes focus from button.
     *
     * @param event
     */
    @FXML
    void handleClearAllDataAction(ActionEvent event) {
        resultField.setText("0");
        calculateOperator = "";
        calculatedResult = "";
        number1 = new BigDecimal("0");
        number2 = new BigDecimal("0");
        startDigitInput = true;
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
        if (startDigitInput) {
            resultField.setText("");
            startDigitInput = false;
        }

        Button buttonNumber = (Button) event.getSource();
        resultField.setText(resultField.getText() + buttonNumber.getText());
    }

    /**
     * Handles operations ('*','/','+','-','=') actions
     * Every user's new input digit determines by 'startDigitInput' flag.
     *
     * @param event
     */
    @FXML
    void handleOperationsAction(ActionEvent event) {
        String currentOperator = ((Button) event.getSource()).getText();

        //Saving number1, when user chooses operator
        if (!"=".equals(currentOperator)) {
            calculateOperator = currentOperator;
            number1 = new BigDecimal(resultField.getText());
            startDigitInput = true;
            calculatedResult = "";
        } else {
            //Saving number2 and operation result
            if (!calculateOperator.isEmpty() && calculatedResult.isEmpty()) {
                number2 = new BigDecimal(resultField.getText());

                if ("÷".equals(calculateOperator) && number2.intValue() == 0) {
                    startDigitInput = true;
                    number1 = BigDecimal.ZERO;
                    showErrorMessage(appConfig.getDivideByZeroErrorMessage());
                    blockControlsPane(calculatorControlsMenu);
                    return;
                }

                calculatedResult = executeCalculation(number1, number2).stripTrailingZeros().toPlainString();
            }
            //If we have result
            else if (!calculatedResult.isEmpty()) {
                BigDecimal previousResult = new BigDecimal(calculatedResult);
                calculatedResult = executeCalculation(previousResult, number2).stripTrailingZeros().toPlainString();
            } else {
                return;
            }

            resultField.setText(calculatedResult);
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






