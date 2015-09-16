package controller;

import enums.AcademicStatus;
import enums.Decision;
import enums.Operation;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.*;
import utility.DateUtil;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * Created by alex on 8/31/15 at 5:54 PM.
 */
public class StudentViewController extends TitledPane {
    public GridPane mainGridPane;
    public ImageView avatarImageView;
    public TableView<FamilyHistory> familyHistoryTableView;
    public GridPane famHistoryFormGridPane;
    public TextField famHistoryFromInput;
    public TextField famHistoryToInput;
    public TextField famHistoryBlockInput;
    public TextField famHistoryRelInput;
    public TextField idInput;
    public TextField firstNameInput;
    public TextField middleNameInput;
    public TextField lastNameInput;
    public TextField cellPhoneInput;
    public TextField emailInput;
    public TextField presentAgeInput;
    public TextField address1Input;
    public TextField address2Input;
    public TextField cityInput;
    public TextField stateProvinceInput;
    public TextField padreFirstNameInput;
    public TextField padreLastNameInput;
    public TextField padrePhoneNumberInput;
    public TextField madreFirstNameInput;
    public TextField madreLastNameInput;
    public TextField madrePhoneNumberInput;
    public GridPane tertiaryLvlLabelsGridPane;
    public TextField tertiaryInstNameInput;
    public TextField tertiaryLvlOfInvolvementInput;
    public TableView<HallHistory> hallHistoryTableView;
    public GridPane hallHistoryFormGridPane;
    public TextField hallHistoryFromInput;
    public TextField hallHistoryToInput;
    public TextField hallHistoryHallInput;
    public CheckBox hallHistoryCheckBox;
    public CheckBox familyHistoryCheckBox;
    public CheckBox bevHistoryCheckBox;
    public TableView<BehaviourHistory> bevHistoryTableView;
    public GridPane bevHistoryFormGridPane;
    public TextArea bevHistoryReasonTextArea;
    public TableView<Achievement> achievementsTable;
    public GridPane achievementFormGrid;
    public TextField achievementNameInput;
    public CheckBox secondarySchoolCheckBox;
    public TextField secondarySchoolInput;
    public TableView<CommunityGroup> commGroupTableView;
    public GridPane commGroupFormGridPane;
    public TextField commGroupProjectInput;
    public TextArea commGroupResponsibilityTextArea;
    public TextField commGroupGroupInput;
    public TableView<CoCurricular> coCurricularTableView;
    public TextArea reasonResidingTextArea;
    public CheckBox coCurCheckBox;
    public GridPane coCurricularFormGrid;
    public TextField coCurricularActivityInput;
    public TextField nationalityField;
    public ListView<File> attachmentsListView;
    public TextField dobInput;
    public TextField roomInput;
    public TextField blockInput;
    public TextField academicStatusInput;
    public TextField facultyInput;
    public TextField dateJoinedInput;
    public TextField countryInput;
    public TextField participationInput;
    public CheckBox commGroupCheckbox;
    public Button studentEditBtn;
    public Button windowCloseBtn;
    public TextField tertiaryLevelInput;
    public TextField coCurType;
    public TextField achievementAreaInput;
    public TextField bevHistoryHallInput;
    public TextField bevHistoryDateInput;
    public TextField bevHistoryInfractionInput;
    public TextField yearOfGradInput;
    private Integer sid;
    private Stage stage;

    public StudentViewController(Integer sid) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/fxml/StudentView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.sid = sid;

        showStudent();

        mainGridPane.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
            if (oldScene == null && newScene != null) {
                // scene is set for the first time. Now its the time to listen stage changes.
                newScene.windowProperty().addListener((observableWindow, oldWindow, newWindow) -> {
                    if (oldWindow == null && newWindow != null) {
                        // stage is set. now is the right time to do whatever we need to the stage in the controller.
                        this.stage = ((Stage) newWindow);
                        newWindow.setOnCloseRequest(event -> {
                            event.consume();
                            ((Stage) newWindow).close();
                        });
                    }
                });
            }
        });

        familyHistoryTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            famHistoryBlockInput.setText(newValue.getBlock().get());
            famHistoryFromInput.setText(newValue.getFrom());
            famHistoryToInput.setText(newValue.getTo());
            famHistoryRelInput.setText(newValue.getRelationship());
        });

        achievementsTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            achievementNameInput.setText(newValue.getAchievement());
            achievementAreaInput.setText(newValue.getArea());
        });

        bevHistoryTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            bevHistoryHallInput.setText(newValue.getHall());
            bevHistoryInfractionInput.setText(newValue.getInfraction());
            bevHistoryReasonTextArea.setText(newValue.getReason());
            //TODO: Implement date field
        });
    }

    public Student findStudentById(Integer sid) {
        return MainController.students
                .stream().filter(student -> Objects.equals(Integer.valueOf(student.getIdNumber()), sid))
                .findFirst().get();
    }

    public void showStudent() {
        Student student = findStudentById(sid);
        if (student.getPicture()) {
            avatarImageView.setImage(student.getImage());
        }
        if (!(student.getAttachedDocuments() == null || student.getAttachedDocuments().isEmpty())) {
            attachmentsListView.setItems(student.getAttachedDocuments());
        }
        idInput.setText(student.getIdNumber());
        firstNameInput.setText(student.getFirstName());
        middleNameInput.setText(student.getMiddleName());
        lastNameInput.setText(student.getLastName());
        dobInput.setText(student.getDob());
        cellPhoneInput.setText(student.getCellPhone());
        emailInput.setText(student.getEmail());
        padreFirstNameInput.setText(student.getFatherFirstName());
        padreLastNameInput.setText(student.getFatherLastName());
        padrePhoneNumberInput.setText(student.getFatherPhone());
        madreFirstNameInput.setText(student.getMotherFirstName());
        madreLastNameInput.setText(student.getMotherLastName());
        madrePhoneNumberInput.setText(student.getMotherPhone());
        facultyInput.setText(student.getFaculty());
        academicStatusInput.setText(student.getAcademicStatus() ?
                AcademicStatus.FULLTIME.getLabel() :
                AcademicStatus.PARTTIME.getLabel());
        blockInput.setText(student.getBlock());
        roomInput.setText(student.getRoom());
        LocalDate end;
        try {
            end = DateUtil.getJamaicaDateTime().toLocalDate();
        } catch (IOException io) {
            end = LocalDate.now();
        }
        presentAgeInput.setText(String.valueOf(ChronoUnit.YEARS.between(LocalDate.parse(student.getDob(), MainController.formatter), end))
                .concat(" year%s old".replace("%s", ((ChronoUnit.YEARS.between(LocalDate.parse(student.getDob(), MainController.formatter), end) > 1) ? "s" : ""))));
        dateJoinedInput.setText(student.getDayJoined());
        address1Input.setText(student.getHomeAddress1());
        address2Input.setText(student.getHomeAddress2());
        cityInput.setText(student.getHomeCity());
        stateProvinceInput.setText(student.getHomeProvince());
        countryInput.setText(student.getResidentCountry().getCountry());
        nationalityField.setText(student.getResidentCountry().getNationality());
        participationInput.setText(String.valueOf(student.getParticpationLevel()));
        reasonResidingTextArea.setText(student.getReasonResiding());
        if (student.getFamilyHistories() != null || !student.getFamilyHistories().isEmpty()) {
            familyHistoryTableView.setDisable(false);
            famHistoryFormGridPane.setDisable(false);
            familyHistoryCheckBox.setSelected(true);
            familyHistoryCheckBox.setText(Decision.YES.getLabel());
            familyHistoryTableView.setItems(student.getFamilyHistories());
        }
        if (student.getCommunityGroups() != null || !student.getCommunityGroups().isEmpty()) {
            commGroupTableView.setDisable(false);
            commGroupFormGridPane.setDisable(false);
            commGroupCheckbox.setSelected(true);
            commGroupCheckbox.setText(Decision.YES.getLabel());
            commGroupTableView.setItems(student.getCommunityGroups());
        }
        if (student.getHallHistories() != null || !student.getHallHistories().isEmpty()) {
            hallHistoryTableView.setDisable(false);
            hallHistoryFormGridPane.setDisable(false);
            hallHistoryCheckBox.setSelected(true);
            hallHistoryCheckBox.setText(Decision.YES.getLabel());
            hallHistoryTableView.setItems(student.getHallHistories());
        }
        if (!student.getPreviousSecondary().isEmpty() || !student.getAchievements().isEmpty()) {
            achievementsTable.setDisable(false);
            achievementFormGrid.setDisable(false);
            secondarySchoolCheckBox.setSelected(true);
            secondarySchoolCheckBox.setText(Decision.YES.getLabel());
            secondarySchoolInput.setText(student.getPreviousSecondary());
            achievementsTable.setItems(student.getAchievements());
        }
        if (student.getTertiaryLevel() != null) {
            tertiaryLvlLabelsGridPane.setDisable(false);
            tertiaryLevelInput.setText(student.getTertiaryLevel().getTertiaryLevel());
            tertiaryInstNameInput.setText(student.getTertiaryLevel().getInstitutionName());
            yearOfGradInput.setText(student.getTertiaryLevel().getGradYear());
            tertiaryLvlOfInvolvementInput.setText(student.getTertiaryLevel().getInvolvement());
        }
        if (student.getBehaviourHistories() != null || !student.getBehaviourHistories().isEmpty()) {
            bevHistoryTableView.setDisable(false);
            bevHistoryFormGridPane.setDisable(false);
            bevHistoryCheckBox.setSelected(true);
            bevHistoryCheckBox.setText(Decision.YES.getLabel());
            bevHistoryTableView.setItems(student.getBehaviourHistories());
        }
        if (student.getCoCurriculars() != null || !student.getCoCurriculars().isEmpty()) {
            coCurricularTableView.setDisable(false);
            coCurricularTableView.setDisable(false);
            coCurCheckBox.setSelected(true);
            coCurCheckBox.setText(Decision.YES.getLabel());
            coCurricularTableView.setItems(student.getCoCurriculars());
        }
    }

    public void windowBtnHandler(ActionEvent event) {
        Object eventSource = event.getSource();
        if (Objects.deepEquals(eventSource, studentEditBtn)) {
            Platform.runLater(() -> MainController.launchStudentViewerWindow(findStudentById(sid), Operation.EDIT));
        } else {
            //TODO: Implement logic to properly close the window
            Platform.runLater(this.stage::close);
        }
    }
}

