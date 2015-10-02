import controller.AuthController;
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
        CustomControlLauncher.create()
                .setTitle("SMS - Login")
                .setScene(new Scene(new AuthController(), 400, 380))
                .setResizable(false).launch();
    }
}
