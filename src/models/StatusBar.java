/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.io.IOException;

/**
 * @author Alex
 */
public class StatusBar extends HBox {
    @FXML
    private TextField textField;

    /**
     * Create a new status bar.
     */
    public StatusBar() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/views/StatusBar.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * @return The text displayed by the status bar.
     */
    public String getText() {
        return textProperty().get();
    }

    /**
     * Set the text displayed by the status bar.
     *
     * @param value The new text to display.
     */
    public void setText(String value) {
        textProperty().set(value);
    }

    /**
     * @return A StringProperty representing the text on the status bar.
     */
    public StringProperty textProperty() {
        return textField.textProperty();
    }

    @FXML
    private void doSomething() {
        System.out.println("The button was clicked!");
    }
}
