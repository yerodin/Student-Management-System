package model;

import javafx.beans.property.StringProperty;

/**
 * Created by Yerodin on 8/22/2015.
 */
public class Achievement {
    private StringProperty achievemnt, area;

    public Achievement(String achievemnt, String area)
    {
        setAchievemnt(achievemnt);
        setArea(area);
    }

    public String getAchievemnt() {
        return achievemnt.get();
    }

    public StringProperty achievemntProperty() {
        return achievemnt;
    }

    public void setAchievemnt(String achievemnt) {
        this.achievemnt.set(achievemnt);
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
        String achievemnt = wrapped.substring(1,wrapped.indexOf("}"));
        wrapped.replace("{","");
        wrapped.replace("}","");
        String area = wrapped.substring(wrapped.indexOf("}")+1,wrapped.indexOf("}"));
        return new Achievement(achievemnt,area);
    }

    public static String wrap(Achievement achievement)
    {
        return "{"+achievement.getAchievemnt()+"}{"+achievement.getArea()+"}";
    }
}
