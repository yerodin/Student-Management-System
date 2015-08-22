package model;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student {
    private IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private StringProperty firstName = new SimpleStringProperty(this, "firstName");
    private StringProperty middleName = new SimpleStringProperty(this, "middleName");
    private StringProperty lastName = new SimpleStringProperty(this, "lastName");
    private StringProperty block = new SimpleStringProperty(this, "block");
    private StringProperty room = new SimpleStringProperty(this, "lastName");
    private StringProperty faculty = new SimpleStringProperty(this, "lastName");
}
