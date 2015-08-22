package controller;

import enums.Operation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TitledPane;
import model.Student;

import java.io.IOException;

/**
 * Student View Controller
 **/
public class StudentViewController extends TitledPane {
    protected Student student;
    protected Operation operation;

    public StudentViewController(Student student, Operation operation) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "fxml/StudentView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        setStudent(student);
        setOperation(operation);
    }

    public Student getStudent() {
        return student;
    }

    public StudentViewController setStudent(Student student) {
        this.student = student;
        return this;
    }

    public Operation getOperation() {
        return operation;
    }

    public StudentViewController setOperation(Operation operation) {
        this.operation = operation;
        return this;
    }

    @FXML
    public void photoEventHandler(ActionEvent event) {

    }

    @FXML
    public void famHistoryHandler(ActionEvent event) {

    }

    @FXML
    public void commGroupHandler(ActionEvent event) {

    }

    @FXML
    public void studentLookup(ActionEvent event) {

    }

    @FXML
    public void hallHistoryHandler(ActionEvent event) {

    }

    @FXML
    public void bevHistoryHandler(ActionEvent event) {

    }

    @FXML
    public void achievementHandler(ActionEvent event) {

    }

    @FXML
    public void coCurricularHandler(ActionEvent event) {

    }

    @FXML
    public void studentHandler(ActionEvent event) {

    }

    @FXML
    public void studentViewHandler(ActionEvent event) {

    }

}
