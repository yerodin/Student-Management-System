package controller;

import enums.NotifierType;
import enums.Operation;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Student;
import utility.CustomControlLauncher;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    final Text[] tablePlaceholders = {
            new Text("Start by adding a student with the Add Student button to the right."),
            new Text("No results found.")};
    public VBox mainVBox;
    @FXML
    TextField filterField;
    @FXML
    TableView<Student> studentTableView;
    @FXML
    Button addStudentBtn, editStudentBtn, viewStudentBtn, deleteStudentBtn;
    protected static ObservableList<Student> students = FXCollections.<Student>observableArrayList();
    ObjectProperty<Student> currentStudent = new SimpleObjectProperty<>();
    ObservableList<ObjectProperty<Student>> selectedStudents;
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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
        AuthController.sMSSessioncount++;
        getNewStudents();
        Platform.runLater(() -> {
            addStudentBtn.requestFocus();
            filterStudentTable();
        });

        mainVBox.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
            if (oldScene == null && newScene != null) {
                // scene is set for the first time. Now its the time to listen stage changes.
                newScene.windowProperty().addListener((observableWindow, oldWindow, newWindow) -> {
                    if (oldWindow == null && newWindow != null) {
                        // stage is set. now is the right time to do whatever we need to the stage in the controller.
                        ((Stage) newWindow).maximizedProperty().addListener((a, b, c) -> {
                            if (c) {
                                // TODO: Something needs to be done when maximized
                            }
                        });
                        newWindow.setOnCloseRequest(event -> {
                            event.consume();
                            AuthController.sMSSessioncount--;
                            ((Stage) newWindow).close();
                        });
                    }
                });
            }
        });

        // TODO: Enable multi-selection of list items
//        studentTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Listen for table selection and update UI
        studentTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
        {
            if (newValue != null) {
                Platform.runLater(() -> {
                    editStudentBtn.setDisable(false);
                    viewStudentBtn.setDisable(false);
                    deleteStudentBtn.setDisable(false);
                    this.currentStudent.setValue(newValue);
                });
            } else {
                this.currentStudent.setValue(null);
            }
        });

        // Listen for student list and update UI
        students.addListener((ListChangeListener<Student>) c -> {
            if (students.isEmpty() || students.size() == 0) {
                currentStudent.setValue(null);
                studentTableView.setPlaceholder(tablePlaceholders[0]);
            }
        });

        // Listen currently selected student and update UI
        currentStudent.addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                Platform.runLater(() -> {
                    editStudentBtn.setDisable(true);
                    viewStudentBtn.setDisable(true);
                    deleteStudentBtn.setDisable(true);
                });
            }
        });
    }

    public void getNewStudents() {
        Task<Student[]> newStudentsTask = new Task<Student[]>() {
            @Override
            protected Student[] call() throws Exception {
                return AuthController.databaseCommunicator.getNewStudents(AuthController.user, 0);
            }
        };
        newStudentsTask.setOnSucceeded(event -> {
            Student[] stds = newStudentsTask.getValue();
            if (stds != null) {
                ObservableList<Student> studentList = FXCollections.observableArrayList(stds);
                FilteredList<Student> studentFilteredList = new FilteredList<>(
                        studentList, student -> !students.contains(student)
                );
                students.addAll(studentFilteredList);
                Platform.runLater(() -> studentTableView.setItems(students));
            } else {
                CustomControlLauncher.notifier("Error", "There was a problem fetching students from database.", NotifierType.ERROR);
            }
        });
        new Thread(newStudentsTask).start();
        students.addAll(generateFakeStudents());
    }


    private ObservableList<Student> generateFakeStudents() {
        return FXCollections.observableArrayList(
                new Student("827632829", "Damion", "Marlon", "Richardson", "Excellence", "12", "Science & Technology"),
                new Student("236251971", "Ramone", "Davere", "Wright", "Aye", "8", "Law"),
                new Student("131273681", "Richard", "Ben", "Hickler", "Runci", "11", "Social Sciences"),
                new Student("974535780", "Easton", "Hamesh", "Ricketts", "Che", "3", "Humanities & Education"),
                new Student("688325630", "John", "Wilder", "Scott", "Dynamite", "19", "Medical Sciences")
        );
    }

    public static void launchStudentViewerWindow(Student student, Operation operation) {
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
        if (operation != Operation.VIEW) {
            Platform.runLater(() -> {
                StudentAddEditController studentAddEditController = new StudentAddEditController(student, operation);
                CustomControlLauncher.create()
                        .setTitle(title.toString())
                        .setScene(new Scene(studentAddEditController, 1024, 640))
                        .setResizable(false).launch();
            });
        } else {
            Platform.runLater(() -> {
                StudentViewController studentViewController = new StudentViewController(Integer.valueOf(student.getIdNumber()));
                CustomControlLauncher.create()
                        .setTitle(title.toString())
                        .setScene(new Scene(studentViewController, 1024, 640))
                        .setResizable(false).launch();
            });
        }
    }

    public void optionsHandler(ActionEvent event) {
        Object eventSource = event.getSource();
        if (Objects.deepEquals(eventSource, addStudentBtn)) {
            Platform.runLater(() -> launchStudentViewerWindow(null, Operation.NEW));
        } else if (Objects.deepEquals(eventSource, editStudentBtn)) {
            Platform.runLater(() -> launchStudentViewerWindow(currentStudent.getValue(), Operation.EDIT));
        } else if (Objects.deepEquals(eventSource, viewStudentBtn)) {
            Platform.runLater(() -> launchStudentViewerWindow(currentStudent.getValue(), Operation.VIEW));
        } else if (Objects.deepEquals(eventSource, deleteStudentBtn)) {
            // TODO: Dialog to confirm exit needs implementation
            Task<Boolean> deleteTask = new Task<Boolean>() {
                @Override
                protected Boolean call() throws Exception {
                    return AuthController.databaseCommunicator.deleteStudent(AuthController.user, currentStudent.getValue(), 1);
                }
            };
            deleteTask.setOnSucceeded(event1 -> {
                if (deleteTask.getValue()) {

                    Platform.runLater(() -> students.remove(currentStudent.getValue()));
                    CustomControlLauncher.notifier("Success", currentStudent.getValue().getFirstName().concat(" has been deleted!"), NotifierType.INFORATION);
                } else
                    CustomControlLauncher.notifier("Error", currentStudent.getValue().getFirstName().concat(" has not been deleted!"), NotifierType.ERROR);
            });
            new Thread(deleteTask).start();
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
                    } else if (student.getEmail().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (student.getBlock().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (student.getFaculty().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (student.getFatherFirstName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (student.getFatherLastName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (student.getFatherPhone().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (student.getMotherFirstName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (student.getMotherLastName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (student.getMotherPhone().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (student.getCellPhone().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (student.getHomeAddress1().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (student.getHomeAddress2().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (student.getHomeCity().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (student.getHomeProvince().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (student.getResidentCountry().getCountry().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (student.getNationalityCountry().getNationality().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (student.getPreviousSecondary().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
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
