package org.home.calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import org.home.calculator.api.Observable;
import org.home.calculator.api.Observer;
import org.home.calculator.config.AppConfig;
import org.home.calculator.controllers.CalculatorController;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * JavaFX App
 */
public class CalculatorApp extends Application implements Observer {
    private AppConfig appConfig;
    private VBox currentRootContainer;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/calculator.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("bundles.locale", new Locale("en")));
        currentRootContainer = fxmlLoader.load();
        Scene scene = new Scene(currentRootContainer);
        primaryStage.setScene(scene);
        this.primaryStage = primaryStage;

        appConfig = new AppConfig(fxmlLoader.getResources());
        appConfig.applyConfig(primaryStage);

        CalculatorController controller = fxmlLoader.getController();
        controller.setAppConfig(appConfig);
        controller.addObserver(this);


        primaryStage.show();
    }

    @SneakyThrows
    @Override
    public void update(Observable o, Object arg) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/calculator.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("bundles.locale", new Locale((String) arg)));
        VBox newNode = fxmlLoader.load();
        CalculatorController controller = fxmlLoader.getController();
        currentRootContainer.getChildren().setAll(newNode.getChildren());

        appConfig.setBundle(fxmlLoader.getResources());
        appConfig.initStageParams(primaryStage);

        controller.setAppConfig(appConfig);
        controller.addObserver(this);
    }

    public static void main(String[] args) {
        launch();
    }


}