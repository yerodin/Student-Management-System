package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Yerodin on 8/22/2015.
 */
public class BehaviourHistory {


    private StringProperty hall = new SimpleStringProperty(this, "hall"),
            infraction = new SimpleStringProperty(this, "infraction"),
            reason = new SimpleStringProperty(this, "reason");

    public BehaviourHistory() {
        super();
    }

    public BehaviourHistory(String hall, String infraction, String reason)
    {
        setHall(hall);
        setInfraction(infraction);
        setReason(reason);
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

    public String getInfraction() {
        return infraction.get();
    }

    public StringProperty infractionProperty() {
        return infraction;
    }

    public void setInfraction(String infraction) {
        this.infraction.set(infraction);
    }

    public String getReason() {
        return reason.get();
    }

    public StringProperty reasoinProperty() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason.set(reason);
    }


    public static BehaviourHistory unwrap(String wrapped)
    {
        if (wrapped == null || wrapped.length() < 6) return new BehaviourHistory();
        String hall = wrapped.substring(1, wrapped.indexOf("}"));
        wrapped = wrapped.substring(wrapped.indexOf("}{") + 1);
        String infraction = wrapped.substring(1, wrapped.indexOf("}"));
        wrapped = wrapped.substring(wrapped.indexOf("}{") + 1);
        String reason = wrapped.substring(1, wrapped.indexOf("}"));
        return new BehaviourHistory(hall, infraction, reason);
    }

    public static String wrap(BehaviourHistory behaviourHistory)
    {
        return "{" + behaviourHistory.getHall() + "}{" + behaviourHistory.getInfraction() + "}{" + behaviourHistory.getReason() + "}";
    }
}
