package controller;

import DBCommunication.DatabaseCommunicator;
import javafx.application.Platform;
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
import javafx.stage.Stage;
import model.User;
import org.controlsfx.control.Notifications;
import utility.CustomControlLauncher;

import java.io.IOException;

/**
 * Created: May 19, 2015 @ 12:08 PM
 *
 * @author Alex Stewart
 **/
public class AuthController extends StackPane {
    public SplitPane afterLoginPane;
    public SplitPane beforeLoginPane;
    public Label loggedInAs;
    public ChoiceBox statusChoiceBox;
    public SplitMenuButton logout;
    public MenuItem switchUser;
    public static User user;
    DatabaseCommunicator databaseCommunicator = new DatabaseCommunicator();

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

        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    CustomControlLauncher.create().setTitle("Student Management System").setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxml/Main.fxml")), 1280, 640)).launch();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
                    return  databaseCommunicator.login(usernameField.getText(), passwordField.getText(), 1);
                }
            };
            task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                @Override
                public void handle(WorkerStateEvent event) {
                    user = task.getValue();
                    System.out.println(user.getSID());
                    if (user != null) {
                        infoTitle.setText("Welcome");
                        infoTitle.setTextFill(Paint.valueOf("GREEN"));
                        infoMessage.setText("Login successful!");
                        infoMessage.setTextFill(Paint.valueOf("GREEN"));
                        infoTitle.setVisible(true);
                        infoMessage.setVisible(true);
//                            startUpdateService();
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                        }
                        Platform.runLater(() -> afterLoginPane.setVisible(true));
                        try {
                            CustomControlLauncher.create().setTitle("Student Management System").setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxml/Main.fxml")), 1280, 640)).launch();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Platform.runLater(() -> Notifications.create()
                                .title("Error!").text("Unable to login at this time. Try again later")
                                .showError());
                        Platform.runLater(() -> {
                            infoTitle.setTextFill(Paint.valueOf("RED"));
                            infoMessage.setTextFill(Paint.valueOf("RED"));
                            infoTitle.setVisible(true);
                            infoMessage.setVisible(true);
                        });
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

    }

    public void closeApp(ActionEvent event) {
        Stage thisStage = (Stage) closeBtn.getScene().getWindow();
        thisStage.close();
    }

}
