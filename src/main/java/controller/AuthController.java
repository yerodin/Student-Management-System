package controller;

import DBCommunication.DatabaseCommunicator;
import enums.Status;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.User;
import org.controlsfx.control.Notifications;
import utility.CustomControlLauncher;

import java.io.IOException;
import java.util.Objects;

/**
 * Created: May 19, 2015 @ 12:08 PM
 *
 * @author Alex Stewart
 **/
public class AuthController extends StackPane {
    public SplitPane afterLoginPane;
    public SplitPane beforeLoginPane;
    public Label loggedInAs;
    public ChoiceBox<String> statusChoiceBox;
    public SplitMenuButton logout;
    public MenuItem switchUser;
    protected static User user;
    public Circle statusColorCircle;
    public Button launchSMSBtn;
    DatabaseCommunicator databaseCommunicator = new DatabaseCommunicator();
    ObservableList<String> statuses = FXCollections.<String>observableArrayList();

    @FXML
    public TextField usernameField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public Button loginBtn;
    @FXML
    public Button closeBtn;
    @FXML
    public Label infoTitle;
    @FXML
    public Label infoMessage;
    @FXML
    public ProgressBar progressBar;

    Stage primaryStage;

    public AuthController(Stage primaryStage) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/fxml/Auth.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }


        this.primaryStage = primaryStage;

        statuses.setAll(Status.labels());
        statusChoiceBox.setItems(statuses);

        statusChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("Online")) {
                statusColorCircle.fillProperty().set(Paint.valueOf("#008287"));
            } else if (newValue.equals("Away")) {
                statusColorCircle.fillProperty().set(Paint.valueOf("darkslateblue"));
            }
        });

    }

    public boolean isFeldsFilled() {
        return !(usernameField.getText().isEmpty() || passwordField.getText().isEmpty());
    }

    public void loadApp(ActionEvent event) {
        if (isFeldsFilled()) {
            Task<User> task = new Task<User>() {
                @Override
                protected User call() throws Exception {
                    return databaseCommunicator.login(usernameField.getText(), passwordField.getText(), 1);
                }
            };
            task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                @Override
                public void handle(WorkerStateEvent event) {
                    user = task.getValue();
                    if (user != null) {
                        Platform.runLater(() -> {
                            loggedInAs.setText("\t".concat(user.getUsername())
                                    .concat("\n").concat("(".concat(user.getFirstName())
                                            .concat(" "))
                                    .concat(user.getLastName().concat(")")));
                            statusChoiceBox.getSelectionModel().select(0);
                            afterLoginPane.setVisible(true);
                            beforeLoginPane.setVisible(false);
                            afterLoginPane.requestFocus();
                        });
                    } else {
                        Platform.runLater(() -> Notifications.create()
                                .title("Error!").text("Unable to login at this time. Check your credentials and try again later")
                                .showError());
                        if (databaseCommunicator.getStatusId() == 1)
                            infoMessage.setText(databaseCommunicator.getStatus());
                        else
                            infoMessage.setText("Error: 1");
                    }

                }
            });
            new Thread(task).start();

//        Stage thisStage = (Stage) closeBtn.getScene().getWindow();
//        thisStage.close();
        } else {
            Platform.runLater(() -> Notifications.create()
                    .title("Error!").text("Please check to ensure all fields are filled in correctly.")
                    .showError());
        }
    }

    public void sessionEventHandler(ActionEvent event) {
        Object eventSource = event.getSource();
        if (Objects.deepEquals(eventSource, logout)) {
            Task<Boolean> logoutTask = new Task<Boolean>() {
                @Override
                protected Boolean call() throws Exception {
                    return databaseCommunicator.logout(user, 0);
                }
            };
            logoutTask.setOnSucceeded(event1 -> {
                if (logoutTask.getValue()) {
                    Stage thisStage = (Stage) closeBtn.getScene().getWindow();
                    thisStage.close();
                }
            });
            new Thread(logoutTask).start();
        }
        if (Objects.deepEquals(eventSource, launchSMSBtn)) {
            try {
                CustomControlLauncher.create().setTitle("Student Management System").setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxml/Main.fxml")), 1280, 640)).launch();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void closeApp(ActionEvent event) {
        if (!user.getStatus().equals("Offline")) {
            //
        }
        Stage thisStage = (Stage) closeBtn.getScene().getWindow();
        thisStage.close();
    }

}
