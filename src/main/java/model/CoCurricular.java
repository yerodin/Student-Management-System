package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Yerodin on 8/22/2015.
 */
public class CoCurricular {
    private StringProperty activity = new SimpleStringProperty(this, "activity");
    private StringProperty type = new SimpleStringProperty(this, "type");

    public CoCurricular() {
        super();
    }

    public CoCurricular(String activity, String type)
    {
        setActivity(activity);
        setType(type);
    }

    public String getActivity() {
        return activity.get();
    }

    public StringProperty activityProperty() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity.set(activity);
    }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public static CoCurricular unwrap(String wrapped)
    {
        String activity = wrapped.substring(1,wrapped.indexOf("}"));
        wrapped.replace("{","");
        wrapped.replace("}","");
        String type = wrapped.substring(wrapped.indexOf("}")+1,wrapped.indexOf("}"));
        return new CoCurricular(activity,type);
    }

    public static String wrap(CoCurricular coCurricular)
    {
        return "{"+coCurricular.getActivity()+"}{"+coCurricular.getType()+"}";
    }
}
