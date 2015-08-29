package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Yerodin on 8/22/2015.
 */
public class HallHistory {
    private StringProperty hall;
    private Period period;

    public HallHistory() {
        this("", null);
    }

    public HallHistory(String hall, Period period)
    {
        this.hall = new SimpleStringProperty(hall);
        setPeriod(period);
    }

    public String getHall() {
        return hall.get();
    }

    public StringProperty hallProperty() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall.set(hall);
    }

    public Period getPeriod()
    {
        return period;
    }

    public void setPeriod(Period period)
    {
        this.period = period;
    }


    public static HallHistory unwrap(String wrapped)
    {
        if (wrapped == null || wrapped.length() < 4) return new HallHistory();
        String achievemnt = wrapped.substring(1, wrapped.indexOf("}"));
        wrapped = wrapped.substring(wrapped.indexOf("}{") + 1);
        String periodStr = wrapped.substring(1, wrapped.indexOf("}"));
        return new HallHistory(achievemnt, Period.parse(periodStr));
    }

    public static String wrap(HallHistory hallHistory)
    {
        return "{" + hallHistory.getHall() + "}{" + hallHistory.getPeriod() + "}";
    }
}
