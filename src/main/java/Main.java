import controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utility.CustomControlLauncher;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Controller root = new Controller();
        CustomControlLauncher.create().setTitle("Student Management System").setScene(new Scene(root, 800, 630)).launch();
    }
}
