package enums;

import javafx.collections.FXCollections;

import java.util.List;

/**
 * Created by alex on 8/25/15 at 4:04 PM.
 */
public enum CoCurActivityType {
    UNIVERSITY("University"),
    HALL("Hall"),
    BLOCK("Block");

    private String label;

    CoCurActivityType(String label) {
        setLabel(label);
    }

    public static List<String> labels() {
        List<String> strings = FXCollections.observableArrayList();
        for (CoCurActivityType coCurActivityType : CoCurActivityType.values()) {
            strings.add(coCurActivityType.getLabel());
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
