package org.home.calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.home.calculator.config.AppConfig;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * JavaFX App
 */
public class CalculatorApp extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/calculator.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("bundles.locale", new Locale("ru")));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setScene(scene);


        AppConfig appConfig = new AppConfig(fxmlLoader.getResources());
        appConfig.initStageParamsAndListeners(primaryStage);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}