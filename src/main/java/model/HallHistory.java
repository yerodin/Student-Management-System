package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Yerodin on 8/22/2015.
 */
public class HallHistory {
    private StringProperty hall = new SimpleStringProperty(this, "hall");
    private Peroid peroid;
    private StringProperty period = new SimpleStringProperty(this, "period");

    public HallHistory() {
        super();
    }

    public HallHistory(String achievemnt, Peroid peroid)
    {
        setHall(achievemnt);
        setPeroid(peroid);
        setPeriod();
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

    public Peroid getPeroid() {
        return peroid;
    }

    public void setPeroid(Peroid peroid) {
        this.peroid = peroid;
    }

    public StringProperty periodProperty() {
        return period;
    }

    public void setPeriod() {
        this.period.set(String.valueOf(getPeroid().fromYear).concat("-" + String.valueOf(getPeroid().toYear)));
    }

    public String getPeriod() {
        return period.get();
    }

    public static HallHistory unwrap(String wrapped)
    {
        String achievemnt = wrapped.substring(1,wrapped.indexOf("}"));
        wrapped.replace("{","");
        wrapped.replace("}","");
        String periodStr = wrapped.substring(wrapped.indexOf("}")+1,wrapped.indexOf("}"));
        return new HallHistory(achievemnt, Peroid.parse(periodStr));
    }

    public static String wrap(HallHistory hallHistory)
    {
        return "{" + hallHistory.getHall() + "}{" + hallHistory.getPeriod() + "}";
    }
}
