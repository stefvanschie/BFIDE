package com.gmail.stefvanschiedev.bfide.application.util;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.util.Optional;

/**
 * A builder for JavaFX dialogs
 */
public class DialogBuilder<R> {
    private final Dialog<R> dialog = new Dialog<>();

    public Optional<R> showAndWait() {
        return dialog.showAndWait();
    }

    public Dialog<R> build() {
        return dialog;
    }

    public DialogBuilder<R> setTitle(String title) {
        dialog.setTitle(title);
        return this;
    }

    public DialogBuilder<R> setHeaderText(String headerText) {
        dialog.setHeaderText(headerText);
        return this;
    }

    public DialogBuilder<R> setContextText(String contextText) {
        dialog.setContentText(contextText);
        return this;
    }

    public DialogBuilder<R> setDialogPane(DialogPane value) {
        dialog.setDialogPane(value);
        return this;
    }

    public DialogBuilder<R> setGraphic(Node graphic) {
        dialog.setGraphic(graphic);
        return this;
    }

    public DialogBuilder<R> setHeight(double height) {
        dialog.setHeight(height);
        return this;
    }

    public DialogBuilder<R> setWidth(double width) {
        dialog.setWidth(width);
        return this;
    }

    public DialogBuilder<R> setX(double x) {
        dialog.setX(x);
        return this;
    }

    public DialogBuilder<R> setY(double y) {
        dialog.setY(y);
        return this;
    }

    public DialogBuilder<R> setResult(R value) {
        dialog.setResult(value);
        return this;
    }

    public DialogBuilder<R> setResultConverter(Callback<ButtonType, R> value) {
        dialog.setResultConverter(value);
        return this;
    }

    public DialogBuilder<R> setResizable(boolean resizable) {
        dialog.setResizable(resizable);
        return this;
    }

    public DialogBuilder<R> setOnShown(EventHandler<DialogEvent> value) {
        dialog.setOnShown(value);
        return this;
    }

    public DialogBuilder<R> setOnShowing(EventHandler<DialogEvent> value) {
        dialog.setOnShowing(value);
        return this;
    }

    public DialogBuilder<R> setOnHiding(EventHandler<DialogEvent> value) {
        dialog.setOnHiding(value);
        return this;
    }

    public DialogBuilder<R> setOnHidden(EventHandler<DialogEvent> value) {
        dialog.setOnHidden(value);
        return this;
    }

    public DialogBuilder<R> setOnCloseRequest(EventHandler<DialogEvent> value) {
        dialog.setOnCloseRequest(value);
        return this;
    }
}
