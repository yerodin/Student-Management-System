package utility;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import org.fxmisc.easybind.EasyBind;

/**
 *
 **/
public class FormEraser {
    private ObservableList<Node> nodes = FXCollections.observableArrayList();

    public FormEraser(Pane pane) {
        nodes.setAll(pane.getChildren());
        EasyBind.listBind(pane.getChildren(), nodes);
    }

    public static FormEraser erase(Pane pane) {
        return new FormEraser(pane);
    }

    public FormEraser passwordFields() {
        for (Node node : nodes) {
            if (node instanceof PasswordField) {
                ((PasswordField) node).clear();
            }
        }
        return this;
    }

    public FormEraser textFields() {
        for (Node node : nodes) {
            if (node instanceof TextField) {
                ((TextField) node).clear();
            }
        }
        return this;
    }

    public FormEraser textAreas() {
        for (Node node : nodes) {
            if (node instanceof TextArea) {
                ((TextArea) node).clear();
            }
        }
        return this;
    }

    public FormEraser datePickers() {
        for (Node node : nodes) {
            if (node instanceof DatePicker) {
                ((DatePicker) node).getEditor().clear();
            }
        }
        return this;
    }

    public FormEraser comboBoxes() {
        for (Node node : nodes) {
            if (node instanceof ComboBox) {
                ((ComboBox) node).getSelectionModel().clearSelection();
            }
        }
        return this;
    }

    public FormEraser choiceBoxes() {
        for (Node node : nodes) {
            if (node instanceof ChoiceBox) {
                ((ChoiceBox) node).getSelectionModel().clearSelection();
            }
        }
        return this;
    }
}
