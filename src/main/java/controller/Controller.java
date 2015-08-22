package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import javax.swing.text.TableView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class Controller extends VBox {
    @FXML
    TextField filterField;
    @FXML
    TableView studentTableView;
    @FXML
    Button optionsSaveBtn, optionsEditBtn, optionsDeleteBtn;

    public Controller() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "../view/Main.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }

    private static void notifier(String title, String message) {
        Platform.runLater(() -> Notifications.create()
                        .title(title)
                        .text(message)
                        .hideAfter(new Duration(2000))
                        .showInformation()
        );
    }

    public void optionsHandler(ActionEvent event) {

    }
}
