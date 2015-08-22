package enums;

import javafx.collections.FXCollections;

import java.util.List;

/**
 * HallComplaintManager -
 **/
public enum Block {
    EXCELLENCE("Excellence"),
    AYE("Aye"),
    RUNCI("Runci"),
    CHE("Che"),
    DYNAMITE("Dynamite");

    private String label;

    Block(String label) {
        setLabel(label);
    }

    public static List<String> labels() {
        List<String> strings = FXCollections.observableArrayList();
        for (Block block : Block.values()) {
            strings.add(block.getLabel());
        }
        return strings;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
