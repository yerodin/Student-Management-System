package model;

import javafx.beans.property.StringProperty;

/**
 * Created by Yerodin on 8/22/2015.
 */
public class HallHistory {
    private StringProperty hall;
    private Peroid peroid;

    public HallHistory() {
        super();
    }

    public HallHistory(String achievemnt, Peroid peroid)
    {
        setHall(achievemnt);
        setPeriod(peroid);
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

    public Peroid getPeriod() {
        return peroid;
    }

    public void setPeriod(Peroid peroid) {
        this.peroid = peroid;
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
