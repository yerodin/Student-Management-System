package enums;

import javafx.collections.FXCollections;

import java.util.List;

/**
 * Created by alex on 8/27/15 at 7:14 AM.
 */
public enum Relationship {
    BROTHER("Brother"),
    FATHER("Father"),
    UNCLE("Uncle"),
    GRANDFATHER("Grandfather");

    private String label;

    Relationship(String label) {
        setLabel(label);
    }

    public static List<String> labels() {
        List<String> strings = FXCollections.observableArrayList();
        for (Relationship relationship : Relationship.values()) {
            strings.add(relationship.getLabel());
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
