package com.gmail.stefvanschiedev.bfide.application.util;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.DialogPane;
import javafx.util.Callback;

import java.util.Optional;

/**
 * A builder for JavaFX alerts. The default type is {@link Alert.AlertType#NONE}
 */
public class AlertBuilder {
    private final Alert alert;

    public AlertBuilder() {
        alert = new Alert(Alert.AlertType.NONE);
    }

    public AlertBuilder(Alert.AlertType alertType, String contentText, ButtonType... buttons) {
        alert = new Alert(alertType, contentText, buttons);
    }

    public Optional<ButtonType> showAndWait() {
        return alert.showAndWait();
    }

    public Alert build() {
        return alert;
    }

    public AlertBuilder setType(Alert.AlertType alertType) {
        alert.setAlertType(alertType);
        return this;
    }

    public AlertBuilder setTitle(String title) {
        alert.setTitle(title);
        return this;
    }

    public AlertBuilder setHeaderText(String headerText) {
        alert.setHeaderText(headerText);
        return this;
    }

    public AlertBuilder setContextText(String contextText) {
        alert.setContentText(contextText);
        return this;
    }

    public AlertBuilder setDialogPane(DialogPane value) {
        alert.setDialogPane(value);
        return this;
    }

    public AlertBuilder setGraphic(Node graphic) {
        alert.setGraphic(graphic);
        return this;
    }

    public AlertBuilder setHeight(double height) {
        alert.setHeight(height);
        return this;
    }

    public AlertBuilder setWidth(double width) {
        alert.setWidth(width);
        return this;
    }

    public AlertBuilder setX(double x) {
        alert.setX(x);
        return this;
    }

    public AlertBuilder setY(double y) {
        alert.setY(y);
        return this;
    }

    public AlertBuilder setResult(ButtonType value) {
        alert.setResult(value);
        return this;
    }

    public AlertBuilder setResultConverter(Callback<ButtonType, ButtonType> value) {
        alert.setResultConverter(value);
        return this;
    }

    public AlertBuilder setResizable(boolean resizable) {
        alert.setResizable(resizable);
        return this;
    }

    public AlertBuilder setOnShown(EventHandler<DialogEvent> value) {
        alert.setOnShown(value);
        return this;
    }

    public AlertBuilder setOnShowing(EventHandler<DialogEvent> value) {
        alert.setOnShowing(value);
        return this;
    }

    public AlertBuilder setOnHiding(EventHandler<DialogEvent> value) {
        alert.setOnHiding(value);
        return this;
    }

    public AlertBuilder setOnHidden(EventHandler<DialogEvent> value) {
        alert.setOnHidden(value);
        return this;
    }

    public AlertBuilder setOnCloseRequest(EventHandler<DialogEvent> value) {
        alert.setOnCloseRequest(value);
        return this;
    }
}