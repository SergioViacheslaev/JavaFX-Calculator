package org.home.calculator.config;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.home.calculator.button.CalculatorButton;
import org.home.calculator.button.KeyboardShortcut;

import java.util.ResourceBundle;

/**
 * @author Sergei Viacheslaev
 */
@Getter
@Setter
public class AppConfig {
    private ResourceBundle bundle;

    public AppConfig(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public void applyConfig(Stage primaryStage) {
        initStageParams(primaryStage);
        initStageListeners(primaryStage);
    }


    public void initStageParams(Stage primaryStage) {
        primaryStage.getIcons().add(new Image("static/images/logo.png"));
        primaryStage.setMinWidth(420);
        primaryStage.setMinHeight(450);
        primaryStage.setMaxWidth(768);
        primaryStage.setMaxHeight(1024);
        primaryStage.setTitle(bundle.getString("appTitle"));

    }

    public String getDivideByZeroErrorMessage() {
        return bundle.getString("divideByZeroError");
    }

    private void initStageListeners(Stage primaryStage) {
        Scene scene = primaryStage.getScene();
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


}
