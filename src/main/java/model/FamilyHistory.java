package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * StudentManagementSystem -
 **/
public class FamilyHistory {
    private StringProperty from = new SimpleStringProperty(this, "from");
    private StringProperty to = new SimpleStringProperty(this, "to");
    private StringProperty relationship = new SimpleStringProperty(this, "relationship");
    private StringProperty block = new SimpleStringProperty(this, "block");


    public FamilyHistory() {
        super();
    }

    public FamilyHistory(String from, String to, String relationship, String block) {
        setFrom(from);
        setTo(to);
        setRelationship(relationship);
        setBlock(block);
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

    public StringProperty getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block.set(block);
    }

    public static FamilyHistory unwrap(String wrapped)
    {
        String from = wrapped.substring(1,wrapped.indexOf("}"));
        wrapped.replace("{","");
        wrapped.replace("}","");
        String to = wrapped.substring(wrapped.indexOf("}")+1,wrapped.indexOf("}"));
        wrapped.replace("{","");
        wrapped.replace("}","");
        String relationship = wrapped.substring(wrapped.indexOf("}")+1,wrapped.indexOf("}"));
        wrapped.replace("{","");
        wrapped.replace("}", "");
        String block = wrapped.substring(wrapped.indexOf("}")+1,wrapped.indexOf("}"));

        return new FamilyHistory(from,to,relationship,block);
    }

    public static String wrap(FamilyHistory familyHistory)
    {
        return "{"+familyHistory.getFrom()+"}{"+familyHistory.getTo()+"}{"+familyHistory.getRelationship()+"}{"+familyHistory.getBlock()+"}";
    }

    @Override
    public String toString() {
        return "FamilyHistory{" +
                "from=" + from.get() +
                ", to=" + to.get() +
                ", relationship=" + relationship.get() +
                ", block=" + block.get() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FamilyHistory)) return false;

        FamilyHistory that = (FamilyHistory) o;

        if (!getFrom().equals(that.getFrom())) return false;
        if (!getTo().equals(that.getTo())) return false;
        if (!getRelationship().equals(that.getRelationship())) return false;
        return getBlock().equals(that.getBlock());

    }

    @Override
    public int hashCode() {
        int result = getFrom().hashCode();
        result = 31 * result + getTo().hashCode();
        result = 31 * result + getRelationship().hashCode();
        result = 31 * result + getBlock().hashCode();
        return result;
    }
}
