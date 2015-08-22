import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utility.CustomControlLauncher;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        CustomControlLauncher.create().setTitle("Student Management System").setScene(new Scene(FXMLLoader.load(getClass().getResource("fxml/Main.fxml")), 800, 630)).launch();
    }
}
