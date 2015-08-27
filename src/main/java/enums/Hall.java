package enums;

import javafx.collections.FXCollections;

import java.util.List;

/**
 * Created by alex on 8/26/15 at 11:47 PM.
 */
public enum Hall {
    AZPRESTONHALL("AZ Preston Hall"),
    ABZHALL("ABC Hall"),
    CHANCELLORHALL("Chancellor Hall");

    private String label;

    Hall(String label) {
        setLabel(label);
    }

    public static List<String> labels() {
        List<String> strings = FXCollections.observableArrayList();
        for (Hall hall : Hall.values()) {
            strings.add(hall.getLabel());
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
