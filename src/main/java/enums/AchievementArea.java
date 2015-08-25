package enums;

import javafx.collections.FXCollections;

import java.util.List;

/**
 * Created by alex on 8/25/15 at 2:49 PM.
 */
public enum AchievementArea {
    //academics, leadership, service, sports, any other
    ACADEMICS("Academics"),
    LEADERSHIP("Leadership"),
    SERVICE("Service"),
    SPORTS("Sports"),
    OTHER("Other");

    private String label;

    AchievementArea(String label) {
        setLabel(label);
    }

    public static List<String> labels() {
        List<String> strings = FXCollections.observableArrayList();
        for (AchievementArea achievementArea : AchievementArea.values()) {
            strings.add(achievementArea.getLabel());
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
