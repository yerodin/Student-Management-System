package utility;

import enums.NotifierType;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * Created: May 18, 2015 @ 11:33 AM
 *
 * @author Alex Stewart
 **/
public class CustomControlLauncher {
    private String title;
    private Stage stage = new Stage();
    private Scene scene;

    private CustomControlLauncher() {
        super();
    }

    public static CustomControlLauncher create() {
        return new CustomControlLauncher();
    }

    public CustomControlLauncher setTitle(String title) {
        this.stage.setTitle(title);
        return this;
    }

    public CustomControlLauncher setScene(Scene scene) {
        this.scene = scene;
        this.stage.setScene(scene);
        return this;
    }

    public CustomControlLauncher setStage(Stage stage) {
        this.stage = stage;
        return this;
    }

    public CustomControlLauncher setResizable(Boolean resizable) {
        this.stage.setResizable(resizable);
        return this;
    }

    public void launch() {
        this.stage.show();
    }

    /**
     * Notifier to pleasantly update User
     *
     * @param title   Title of notification
     * @param message Message of notification
     */
    public static void notifier(String title, String message, NotifierType notifierType) {
        Platform.runLater(() -> {
                    Notifications notif = Notifications.create()
                            .title(title)
                            .text(message)
                            .hideAfter(new Duration(3000));
                    if (notifierType == NotifierType.CONFIRM) {
                        notif.showConfirm();
                    } else if (notifierType == NotifierType.ERROR) {
                        notif.showError();
                    } else if (notifierType == NotifierType.INFORATION) {
                        notif.showInformation();
                    } else if (notifierType == NotifierType.WARNING) {
                        notif.showWarning();
                    }

                }
        );
    }

}
