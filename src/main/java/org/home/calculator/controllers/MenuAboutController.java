package org.home.calculator.controllers;

/**
 * @author Sergei Viacheslaev
 */

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class MenuAboutController {

    @FXML
    private TextArea system_info;

    @FXML
    void initialize() {
        String java_runtime = System.getProperties().getProperty("java.runtime.name");
        String java_version = System.getProperties().getProperty("java.version");
        String os_name = System.getProperties().getProperty("os.name");
        String os_version = System.getProperties().getProperty("os.version");

        system_info.setText(String.format("OS: %s %s%n%s",
                os_name, os_version, String.format("Java: %s %s%n", java_runtime, java_version)));

    }
}
