package enums;

import javafx.collections.FXCollections;

import java.util.List;

/**
 * Created by alex on 8/25/15 at 5:02 AM.
 */
public enum Faculty {
    HUM_ED("Humanities & Education"),
    LAW("Law"),
    MED_SCI("Medical Sciences"),
    SCI_TECH("Science & Technology"),
    SOC_SCI("Social Sciences"),
    NONE("None");

    private String label;

    Faculty(String label) {
        setLabel(label);
    }

    public static List<String> labels() {
        List<String> strings = FXCollections.observableArrayList();
        for (Faculty faculty : Faculty.values()) {
            strings.add(faculty.getLabel());
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
