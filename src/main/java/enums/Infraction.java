package enums;

import javafx.collections.FXCollections;

import java.util.List;

/**
 * Created by alex on 8/25/15 at 12:17 PM.
 */
public enum Infraction {
    SUSPENSION("Suspension"),
    EXPULSION("Expulsion");

    private String label;

    Infraction(String label) {
        setLabel(label);
    }

    public static List<String> labels() {
        List<String> strings = FXCollections.observableArrayList();
        for (Infraction infraction : Infraction.values()) {
            strings.add(infraction.getLabel());
        }
        return strings;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
