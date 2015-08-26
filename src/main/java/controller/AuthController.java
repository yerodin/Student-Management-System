package controller;

import DBCommunication.DatabaseCommunicator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.User;
import utility.CustomControlLauncher;

import java.io.IOException;

/**
 * Created: May 19, 2015 @ 12:08 PM
 *
 * @author Alex Stewart
 **/
public class AuthController extends StackPane {
    private User user;
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
    }

    public void loadApp(ActionEvent event) throws IOException {
//        Object eventSource = event.getSource();
//        if (Objects.deepEquals(eventSource, loginBtn)) {
//                Task<User> task = new Task<User>() {
//                    @Override
//                    protected User call() throws Exception {
//                        return user = databaseCommunicator.login(usernameField.getText(), passwordField.getText(), 1);
//                    }
//                };
//                task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
//                    @Override
//                    public void handle(WorkerStateEvent event) {
//                        user = task.getValue();
//                        if (user != null) {
//                            getLoginMessageLabel().setText("Login successful!");
//                            );
//                            getLoginProgressIndicator().setVisible(true);
//                            startUpdateService();
//                            try {
//                                Thread.sleep(1000);
//                            } catch (Exception e) {
//                            }
//                            getPane_complaints().setVisible(true);
//                            getPane_login().setVisible(false);
//                        } else {
//                            getLoginMessageLabel().setTextFill(Paint.valueOf("RED"));
//                            getLoginMessageLabel().setVisible(true);
//                            if (databaseConnector.getStatusId() == 1)
//                                getLoginMessageLabel().setText(databaseConnector.getStatus());
//                            else
//                                getLoginMessageLabel().setText("Error: 1");
//                        }
//
//                    }
//                });
//            }
//        }
//        try {
//            new HibernateUtil();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        CustomControlLauncher.create().setTitle("Student Management System").setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxml/Main.fxml")), 1280, 640)).launch();

//        Stage thisStage = (Stage) closeBtn.getScene().getWindow();
//        thisStage.close();
    }

    public void closeApp(ActionEvent event) {
        Stage thisStage = (Stage) closeBtn.getScene().getWindow();
        thisStage.close();
    }

}
