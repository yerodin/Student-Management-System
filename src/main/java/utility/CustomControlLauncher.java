package utility;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Contract Management System - org.mtp.cms.utilities
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

    public void launch() {
        this.stage.show();
    }

}
