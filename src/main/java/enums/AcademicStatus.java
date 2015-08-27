package enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 8/27/15 at 12:25 AM.
 */
public enum AcademicStatus {
    FULLTIME("Full time"),
    PARTTIME("Part time");

    private String label;

    AcademicStatus(String label) {
        setLabel(label);
    }

    public static List<String> labels() {
        List<String> strings = new ArrayList<>();
        for (AcademicStatus status : AcademicStatus.values()) {
            strings.add(status.getLabel());
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
