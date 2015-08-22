package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * StudentManagementSystem -
 **/
public class FamilyHistory {
    private IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private StringProperty from = new SimpleStringProperty(this, "from");
    private StringProperty to = new SimpleStringProperty(this, "to");
    private StringProperty relationship = new SimpleStringProperty(this, "relationship");
    private Student student;

    public FamilyHistory() {
        super();
    }

    public FamilyHistory(String from, String to, String relationship) {
        setFrom(from);
        setTo(to);
        setRelationship(relationship);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getFrom() {
        return from.get();
    }

    public void setFrom(String from) {
        this.from.set(from);
    }

    public StringProperty fromProperty() {
        return from;
    }

    public String getTo() {
        return to.get();
    }

    public void setTo(String to) {
        this.to.set(to);
    }

    public StringProperty toProperty() {
        return to;
    }

    public String getRelationship() {
        return relationship.get();
    }

    public void setRelationship(String relationship) {
        this.relationship.set(relationship);
    }

    public StringProperty relationshipProperty() {
        return relationship;
    }

    @Override
    public String toString() {
        return "FamilyHistory{" +
                "id=" + id +
                ", from=" + from +
                ", to=" + to +
                ", relationship=" + relationship +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FamilyHistory)) return false;

        FamilyHistory that = (FamilyHistory) o;

        if (!getFrom().equals(that.getFrom())) return false;
        if (!getTo().equals(that.getTo())) return false;
        return getRelationship().equals(that.getRelationship());

    }

    @Override
    public int hashCode() {
        int result = getFrom().hashCode();
        result = 31 * result + getTo().hashCode();
        result = 31 * result + getRelationship().hashCode();
        return result;
    }
}
