package org.home.calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.home.calculator.controllers.CalculatorController;

import java.io.IOException;

/**
 * JavaFX App
 */
public class CalculatorApp extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/calculator.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setScene(scene);

        CalculatorController controller = fxmlLoader.getController();
        controller.initStageParams(primaryStage);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}