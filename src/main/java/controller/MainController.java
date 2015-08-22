package controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import model.Student;
import org.controlsfx.control.Notifications;
import org.fxmisc.easybind.EasyBind;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    TextField filterField;
    @FXML
    TableView studentTableView;
    @FXML
    Button addStudentBtn, editStudentBtn, deleteStudentBtn;
    private ObservableList<Student> students = FXCollections.observableArrayList();

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EasyBind.listBind(studentTableView.getItems(), students);

    }

    public void populateTestStudents() {

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
        Object eventSource = event.getSource();
        if (Objects.deepEquals(eventSource, addStudentBtn)) {
            // Code to add student
        } else if (Objects.deepEquals(eventSource, editStudentBtn)) {
            // Code to edit student
        } else if (Objects.deepEquals(eventSource, deleteStudentBtn)) {
            // Code to delete student
        }
    }
}
