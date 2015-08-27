package enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Contract Management System - org.mtp.cms
 *
 * @author Alex Stewart <p>ID: 620076613</p>
 * @version 1.0
 */
public enum Decision {
    YES("Yes"),
    NO("No");

    private String label;

    Decision(String label) {
        setLabel(label);
    }

    public static List<String> labels() {
        List<String> strings = new ArrayList<>();
        for (Decision decision : Decision.values()) {
            strings.add(decision.getLabel());
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
