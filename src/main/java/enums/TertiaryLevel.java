package enums;

import javafx.collections.FXCollections;

import java.util.List;

/**
 * Created by alex on 8/25/15 at 12:33 PM.
 */
public enum TertiaryLevel {
    DIPLOMA("Diploma"),
    BACHELORS("Bachelors"),
    MASTERS("Masters"),
    PHD("PhD"),
    OTHER("Other");

    private String label;

    TertiaryLevel(String label) {
        setLabel(label);
    }

    public static List<String> labels() {
        List<String> strings = FXCollections.observableArrayList();
        for (TertiaryLevel tertiaryLevel : TertiaryLevel.values()) {
            strings.add(tertiaryLevel.getLabel());
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
