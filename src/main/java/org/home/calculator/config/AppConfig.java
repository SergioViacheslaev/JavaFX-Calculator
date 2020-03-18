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
    private final ResourceBundle bundle;
    private String appTitle;

    public AppConfig(ResourceBundle bundle) {
        this.bundle = bundle;
        this.appTitle = bundle.getString("appTitle");
    }


    public void initStageParamsAndListeners(Stage primaryStage) {
        Scene scene = primaryStage.getScene();
        primaryStage.getIcons().add(new Image("static/images/logo.png"));
        primaryStage.setMinWidth(420);
        primaryStage.setMinHeight(450);
        primaryStage.setMaxWidth(768);
        primaryStage.setMaxHeight(1024);
        primaryStage.setTitle(appTitle);

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
