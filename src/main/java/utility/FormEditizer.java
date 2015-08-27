package utility;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.fxmisc.easybind.EasyBind;

/**
 * Created by alex on 8/22/15 at 5:39 PM.
 */
public class FormEditizer {
    private ObservableList<Node> nodes = FXCollections.observableArrayList();

    public enum Action {
        ENABLE_EDIT, DISABLE_EDIT,
        ENABLE_NODE, DISABLE_NODE, SHOW_NODE, HIDE_NODE, CLEAR
    }

    private Pane pane;
    private Action action;

    public FormEditizer(Pane pane, Action action) {
        nodes.setAll(pane.getChildren());
        Task<Void> deepClearingTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                for (Node node : nodes) {
                    new FormEditizer(((node instanceof Pane) ? (((Pane) node)) : (new Pane())), Action.CLEAR)
                            .choiceBoxes().comboBoxes().datePickers().passwordFields().textAreas().textFields();
                }
                return getValue();
            }
        };
        new Thread(deepClearingTask).start();
        EasyBind.listBind(pane.getChildren(), nodes);
        setPane(pane);
        setAction(action);
    }

    public Pane getPane() {
        return pane;
    }

    public FormEditizer setPane(Pane pane) {
        this.pane = pane;
        return this;
    }

    public void deepClearing(Pane pane) {
        try {
            for (Node node : pane.getChildren()) {
                if (node instanceof GridPane) {
                    nodes.addAll(((GridPane) node).getChildren());
                }
            }
        } catch (Exception ex) {
            // Do something
        }
    }

    public Action getAction() {
        return action;
    }

    public FormEditizer setAction(Action action) {
        this.action = action;
        return this;
    }

    public FormEditizer passwordFields() {
        for (Node node : nodes) {
            if (node instanceof PasswordField) {
                PasswordField thisNode = (PasswordField) node;
                switch (getAction()) {
                    case ENABLE_EDIT:
                        thisNode.setEditable(true);
                    case DISABLE_EDIT:
                        thisNode.setEditable(false);
                    case ENABLE_NODE:
                        thisNode.setDisable(false);
                    case DISABLE_NODE:
                        thisNode.setDisable(true);
                    case SHOW_NODE:
                        thisNode.setVisible(true);
                    case HIDE_NODE:
                        thisNode.setVisible(false);
                    case CLEAR:
                        thisNode.clear();
                }
            }
        }
        return this;
    }

    public FormEditizer textFields() {
        for (Node node : nodes) {
            if (node instanceof TextField) {
                TextField thisNode = (TextField) node;
                switch (getAction()) {
                    case ENABLE_EDIT:
                        thisNode.setEditable(true);
                    case DISABLE_EDIT:
                        thisNode.setEditable(false);
                    case ENABLE_NODE:
                        thisNode.setDisable(false);
                    case DISABLE_NODE:
                        thisNode.setDisable(true);
                    case SHOW_NODE:
                        thisNode.setVisible(true);
                    case HIDE_NODE:
                        thisNode.setVisible(false);
                    case CLEAR:
                        thisNode.clear();
                }
            }
        }
        return this;
    }

    public FormEditizer textAreas() {
        for (Node node : nodes) {
            if (node instanceof TextArea) {
                TextArea thisNode = (TextArea) node;
                switch (getAction()) {
                    case ENABLE_EDIT:
                        thisNode.setEditable(true);
                    case DISABLE_EDIT:
                        thisNode.setEditable(false);
                    case ENABLE_NODE:
                        thisNode.setDisable(false);
                    case DISABLE_NODE:
                        thisNode.setDisable(true);
                    case SHOW_NODE:
                        thisNode.setVisible(true);
                    case HIDE_NODE:
                        thisNode.setVisible(false);
                    case CLEAR:
                        thisNode.clear();
                }
            }
        }
        return this;
    }

    public FormEditizer datePickers() {
        for (Node node : nodes) {
            if (node instanceof DatePicker) {
                DatePicker thisNode = (DatePicker) node;
                switch (getAction()) {
                    case ENABLE_EDIT:
                        thisNode.setEditable(true);
                    case DISABLE_EDIT:
                        thisNode.setEditable(false);
                    case ENABLE_NODE:
                        thisNode.setDisable(false);
                    case DISABLE_NODE:
                        thisNode.setDisable(true);
                    case SHOW_NODE:
                        thisNode.setVisible(true);
                    case HIDE_NODE:
                        thisNode.setVisible(false);
                    case CLEAR:
                        thisNode.getEditor().clear();
                }
            }
        }
        return this;
    }

    public FormEditizer comboBoxes() {
        for (Node node : nodes) {
            if (node instanceof ComboBox) {
                ComboBox thisNode = (ComboBox) node;
                switch (getAction()) {
                    case ENABLE_EDIT:
                        thisNode.setEditable(true);
                    case DISABLE_EDIT:
                        thisNode.setEditable(false);
                    case ENABLE_NODE:
                        thisNode.setDisable(false);
                    case DISABLE_NODE:
                        thisNode.setDisable(true);
                    case SHOW_NODE:
                        thisNode.setVisible(true);
                    case HIDE_NODE:
                        thisNode.setVisible(false);
                    case CLEAR:
                        thisNode.getEditor().clear();
                        thisNode.getSelectionModel().clearSelection();
                }
            }
        }
        return this;
    }

    public FormEditizer choiceBoxes() {
        for (Node node : nodes) {
            if (node instanceof ChoiceBox) {
                ChoiceBox thisNode = (ChoiceBox) node;
                switch (getAction()) {
                    case ENABLE_NODE:
                        thisNode.setDisable(false);
                    case DISABLE_NODE:
                        thisNode.setDisable(true);
                    case SHOW_NODE:
                        thisNode.setVisible(true);
                    case HIDE_NODE:
                        thisNode.setVisible(false);
                    case CLEAR:
                        thisNode.getSelectionModel().clearSelection();
                }
            }
        }
        return this;
    }
}

