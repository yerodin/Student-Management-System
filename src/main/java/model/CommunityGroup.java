package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Yerodin on 8/22/2015.
 */
public class CommunityGroup {


    private StringProperty group = new SimpleStringProperty(this, "group");
    private StringProperty project = new SimpleStringProperty(this, "project");
    private StringProperty responsibility = new SimpleStringProperty(this, "responsibility");

    public CommunityGroup() {
        super();
    }

    public CommunityGroup(String group, String project, String responsibility)
    {
        setGroup(group);
        setProject(project);
        setResponsibility(responsibility);
    }

    public String getGroup() {
        return group.get();
    }

    public StringProperty groupProperty() {
        return group;
    }

    public void setGroup(String group) {
        this.group.set(group);
    }

    public String getProject() {
        return project.get();
    }

    public StringProperty projectProperty() {
        return project;
    }

    public void setProject(String project) {
        this.project.set(project);
    }

    public String getResponsibility() {
        return responsibility.get();
    }

    public StringProperty reasoinProperty() {
        return responsibility;
    }

    public void setResponsibility(String responsibility) {
        this.responsibility.set(responsibility);
    }


    public static CommunityGroup unwrap(String wrapped)
    {
        String group = wrapped.substring(1,wrapped.indexOf("}"));
        wrapped.replace("{","");
        wrapped.replace("}","");
        String project = wrapped.substring(wrapped.indexOf("}")+1,wrapped.indexOf("}"));
        wrapped.replace("{","");
        wrapped.replace("}","");
        String responsibility = wrapped.substring(wrapped.indexOf("}")+1,wrapped.indexOf("}"));
        return new CommunityGroup(group,project,responsibility);
    }

    public static String wrap(CommunityGroup communityGroup)
    {
        return "{"+communityGroup.getGroup()+"}{"+communityGroup.getProject()+"}{"+communityGroup.getResponsibility()+"}";
    }
}
