package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Yerodin on 8/22/2015.
 */
public class Achievement {
    private StringProperty achievement = new SimpleStringProperty(this, "achievement");
    private StringProperty area = new SimpleStringProperty(this, "area");

    public Achievement() {
        super();
    }

    public Achievement(String achievement, String area)
    {
        setAchievement(achievement);
        setArea(area);
    }

    public String getAchievement() {
        return achievement.get();
    }

    public StringProperty achievementProperty() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement.set(achievement);
    }

    public String getArea() {
        return area.get();
    }

    public StringProperty areaProperty() {
        return area;
    }

    public void setArea(String area) {
        this.area.set(area);
    }

    public static Achievement unwrap(String wrapped)
    {
        if (wrapped == null || wrapped.length() < 4) return new Achievement();
        String achievement = wrapped.substring(1, wrapped.indexOf("}"));
        wrapped = wrapped.substring(wrapped.indexOf("}{") + 1);
        String area = wrapped.substring(1, wrapped.indexOf("}"));

        return new Achievement(achievement, area);
    }

    public static String wrap(Achievement achievement)
    {
        return "{" + achievement.getAchievement() + "}{" + achievement.getArea() + "}";
    }
}
