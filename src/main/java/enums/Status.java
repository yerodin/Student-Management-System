package enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 */
public enum Status {
    ONLINE("Online"),
    AVAILABLE("Available"),
    AWAY("Away"),
    DONOTDISTRURB("Do Not Disturb"),
    INVISIBLE("Invisible"),
    OFFLINE("Offline");

    private String label;

    Status(String label) {
        setLabel(label);
    }

    public static List<String> labels() {
        List<String> strings = new ArrayList<>();
        for (Status status : Status.values()) {
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
