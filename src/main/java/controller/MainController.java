package controller;

import DBCommunication.DatabaseCommunicator;
import enums.Operation;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.Student;
import model.User;
import org.controlsfx.control.Notifications;
import utility.CustomControlLauncher;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    final Text[] tablePlaceholders = {
            new Text("Start by creating a student with the Add Student button to the right."),
            new Text("No results found.")};
    @FXML
    TextField filterField = new TextField();
    @FXML
    TableView<Student> studentTableView = new TableView<Student>();
    @FXML
    Button addStudentBtn, editStudentBtn, viewStudentBtn, deleteStudentBtn;
    private ObservableList<Student> students = FXCollections.observableArrayList();
    ObjectProperty<Student> currentStudent = new SimpleObjectProperty<>();
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
        getNewStudents();
        Platform.runLater(() -> {
            addStudentBtn.requestFocus();
            filterStudentTable();
        });

        // Listen for table selection and update CurrentStudent
        studentTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
        {
            if (newValue != null) {
                Platform.runLater(() -> {
                    editStudentBtn.setDisable(false);
                    viewStudentBtn.setDisable(false);
                    deleteStudentBtn.setDisable(false);
                    this.currentStudent.setValue(newValue);
                });
            }
        });

        students.addListener((ListChangeListener<Student>) c -> {
            if (students.isEmpty() || students.size() == 0) {
                studentTableView.setPlaceholder(tablePlaceholders[0]);
            }
        });
    }

    public void getNewStudents() {

        Platform.runLater(() -> {
            DatabaseCommunicator databaseCommunicator = new DatabaseCommunicator();
            User user = databaseCommunicator.login("asap", "password", 0);
            Student[] students1 = databaseCommunicator.getNewStudents(user, 1);
            students.addAll(students1);
            studentTableView.setItems(students);
        });
    }


    private ObservableList<Student> generateFakeStudents() {
        return FXCollections.observableArrayList(
                new Student("827632829", "Damion", "Marlon", "Richardson", "Excellence", "12", "Sci. Tech"),
                new Student("236251971", "Ramone", "Davere", "Wright", "Aye", "8", "Law"),
                new Student("131273681", "Richard", "Ben", "Hickler", "Runci", "11", "Social Sciences"),
                new Student("974535780", "Easton", "Hamesh", "Ricketts", "Che", "3", "Humanities"),
                new Student("688325630", "John", "Wilder", "Scott", "Dynamite", "19", "Med")
        );
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
                    .setScene(new Scene(studentViewController, 1280, 640))
                    .launch();
        });
    }

    public void optionsHandler(ActionEvent event) {
        Object eventSource = event.getSource();

        if (Objects.deepEquals(eventSource, addStudentBtn)) {
            Platform.runLater(() -> launchStudentViewWindow(null, Operation.NEW));
        } else if (Objects.deepEquals(eventSource, editStudentBtn)) {
            Platform.runLater(() -> launchStudentViewWindow(currentStudent.getValue(), Operation.EDIT));
        } else if (Objects.deepEquals(eventSource, viewStudentBtn)) {
            Platform.runLater(() -> launchStudentViewWindow(currentStudent.getValue(), Operation.VIEW));
        } else if (Objects.deepEquals(eventSource, deleteStudentBtn)) {
            Platform.runLater(() -> students.remove(currentStudent.getValue()));
        }
    }

    // Quickly search for students
    public void filterStudentTable() {
        // 1. Wrap the ObservableList in a FilteredList (initially display all students).
        FilteredList<Student> filteredData = new FilteredList<>(students, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(student -> {
                // If filter text is empty, display all students.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare every student's property with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                try {
                    if (student.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (student.getLastName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (student.getMiddleName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (String.valueOf(student.getIdNumber()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
//                    } else if (student.getEmail().toLowerCase().contains(lowerCaseFilter)) {
//                        return true;
                    } else if (student.getBlock().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (student.getFaculty().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
//                    } else if (student.getFatherFirstName().toLowerCase().contains(lowerCaseFilter)) {
//                        return true;
//                    } else if (student.getFatherLastName().toLowerCase().contains(lowerCaseFilter)) {
//                        return true;
//                    } else if (student.getFatherPhone().toLowerCase().contains(lowerCaseFilter)) {
//                        return true;
//                    } else if (student.getMotherFirstName().toLowerCase().contains(lowerCaseFilter)) {
//                        return true;
//                    } else if (student.getMotherLastName().toLowerCase().contains(lowerCaseFilter)) {
//                        return true;
//                    } else if (student.getMotherPhone().toLowerCase().contains(lowerCaseFilter)) {
//                        return true;
//                    } else if (student.getCellPhone().toLowerCase().contains(lowerCaseFilter)) {
//                        return true;
//                    } else if (student.getHomeAddress1().toLowerCase().contains(lowerCaseFilter)) {
//                        return true;
//                    } else if (student.getHomeAddress2().toLowerCase().contains(lowerCaseFilter)) {
//                        return true;
//                    } else if (student.getHomeCity().toLowerCase().contains(lowerCaseFilter)) {
//                        return true;
//                    } else if (student.getHomeProvince().toLowerCase().contains(lowerCaseFilter)) {
//                        return true;
//                    } else if (student.getResidentCountry().getCountry().toLowerCase().contains(lowerCaseFilter)) {
//                        return true;
//                    } else if (student.getNationality().getNationality().toLowerCase().contains(lowerCaseFilter)) {
//                        return true;
//                    } else if (student.getPreviousSecondary().toLowerCase().contains(lowerCaseFilter)) {
//                        return true;
                    }

                    studentTableView.setPlaceholder(tablePlaceholders[1]);
                    return false; // Does not match.
                } catch (NullPointerException nullP) {
                    // Do nothing... For now
                }
                studentTableView.setPlaceholder(tablePlaceholders[1]);
                return false;
            });
        });

//        studentTableView.setPlaceholder(tablePlaceholders[0]);

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Student> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(studentTableView.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        studentTableView.setItems(sortedData);
    }
}
