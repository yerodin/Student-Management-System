package controller;

import enums.Operation;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import model.Student;
import org.controlsfx.control.Notifications;
import utility.CustomControlLauncher;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    TextField filterField;
    @FXML
    TableView studentTableView;
    @FXML
    Button addStudentBtn, editStudentBtn, viewStudentBtn, deleteStudentBtn;
    private ObservableList<Student> students = FXCollections.observableArrayList();
    ObjectProperty<Student> currentStudent;
    ObservableList<ObjectProperty<Student>> selectedStudents;

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

        // Listen for table selection and update CurrentStudent
        studentTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
        {
            if (oldValue != null) {
                editStudentBtn.setDisable(false);
                deleteStudentBtn.setDisable(false);
            }
            Student student = (Student) newValue;
            this.currentStudent.set(student);
            this.currentStudent.setValue(student);
        });

        populateStudents();
    }

    public void populateStudents() {
        // Code to grab students and insert into ObservableList
    }


    /**
     * Notifier to pleasantly update User
     *
     * @param title   Title of notification
     * @param message Message of notification
     */
    private static void notifier(String title, String message) {
        Platform.runLater(() -> Notifications.create()
                        .title(title)
                        .text(message)
                        .hideAfter(new Duration(2000))
                        .showInformation()
        );
    }

    public void launchStudentViewWindow(Student student, Operation operation) {
        // Determine title
        String[] titles = {"Add ", "Edit ", "View ", " Student"};
        StringBuilder title = new StringBuilder();
        switch (operation) {
            case NEW:
                title.insert(0, titles[0] + titles[3]);
                break;
            case EDIT:
                title.insert(0, titles[1] + titles[3]);
                break;
            case VIEW:
                title.insert(0, titles[2] + titles[3]);
                break;
            default:
                title.insert(0, "Student Viewer");
        }

        // Launch Student Viewer with given Student and operation
        Platform.runLater(() -> {
            StudentViewController studentViewController = new StudentViewController(student, operation);
            CustomControlLauncher.create()
                    .setTitle(title.toString())
                    .setScene(new Scene(studentViewController, 1024, 600))
                    .launch();
        });
    }

    public void optionsHandler(ActionEvent event) {
        Object eventSource = event.getSource();

        if (Objects.deepEquals(eventSource, addStudentBtn)) {
            launchStudentViewWindow(null, Operation.NEW);
        } else if (Objects.deepEquals(eventSource, editStudentBtn)) {
            launchStudentViewWindow(null, Operation.EDIT);
        } else if (Objects.deepEquals(eventSource, viewStudentBtn)) {
            launchStudentViewWindow(null, Operation.VIEW);
        } else if (Objects.deepEquals(eventSource, deleteStudentBtn)) {
            students.remove(currentStudent.get());
        }
    }
}
