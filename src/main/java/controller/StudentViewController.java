package controller;

import DBCommunication.DatabaseCommunicator;
import enums.*;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import javafx.util.StringConverter;
import model.*;
import org.controlsfx.control.Notifications;
import utility.DateUtil;
import utility.FormEditizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Student View Controller
 **/
public class StudentViewController extends TitledPane {
    public ImageView avatarImageView;
    public Button addEditPhotoBtn;
    public Button removePhotoBtn;
    public TableView<FamilyHistory> familyHistoryTableView;
    public GridPane famHistoryFormGridPane;
    public SplitMenuButton famHistoryClearDeleteSplitMenuBtn;
    public Button famHistorySaveBtn;
    public DatePicker famHistoryFromDatePicker;
    public DatePicker famHistoryToDatePicker;
    public ChoiceBox<String> famHistoryBlockChoiceBox;
    public ComboBox<String> famHistoryRelationshipComboBox;
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
    public ChoiceBox<Country> countryChoiceBox;
    public ChoiceBox willParticipateChoiceBox;
    public TextField padreFirstNameInput;
    public TextField padreLastNameInput;
    public TextField padrePhoneNumberInput;
    public TextField madreFirstNameInput;
    public TextField madreLastNameInput;
    public TextField madrePhoneNumberInput;
    public ChoiceBox<String> roomNumberChoiceBox;
    public ChoiceBox<String> blockChoiceBox;
    public ChoiceBox<String> academicStatusChoiceBox;
    public ChoiceBox<String> facultyChoiceBox;
    public DatePicker dobDatePicker;
    public GridPane tertiaryLvlLabelsGridPane;
    public GridPane tertiaryLvlFieldsGridPane;
    public TextField tertiaryInstNameInput;
    public TextField tertiaryLvlOfInvolvementInput;
    public ChoiceBox<String> tertiaryYrofGradChoiceBox;
    public TableView<HallHistory> hallHistoryTableView;
    public GridPane hallHistoryFormGridPane;
    public Button hallHistorySaveBtn;
    public SplitMenuButton hallHistoryClearDeleteSplitMenuBtn;
    public ChoiceBox<String> hallHistoryHallChoiceBox;
    public CheckBox hallHistoryCheckBox;
    public CheckBox familyHistoryCheckBox;
    public CheckBox bevHistoryCheckBox;
    public TableView<BehaviourHistory> bevHistoryTableView;
    public GridPane bevHistoryFormGridPane;
    public ChoiceBox<String> bevHistoryHallChoiceBox;
    public ComboBox<Infraction> bevHistoryInfracComboBox;
    public TextArea bevHistoryReasonTextArea;
    public DatePicker bevHistoryDatePicker;
    public Button bevHistorySaveBtn;
    public SplitMenuButton bevHistoryClearDeleteBtn;
    public TextField secondarySchoolInput;
    public TableView<Achievement> achievementsTable;
    public TextField achievementNameInput;
    public ComboBox<String> achievementAreaComboBox;
    public Button achievementSaveBtn;
    public SplitMenuButton achievementClearDeleteSplitMenuBtn;
    public ComboBox<String> commGroupNameComboBox;
    public TableView<CommunityGroup> commGroupTableView;
    public GridPane commGroupFormGridPane;
    public TextField commGroupProjectInput;
    public TextArea commGroupResponsibilityTextArea;
    public Button commGroupSaveBtn;
    public SplitMenuButton commGroupClearDeleteSplitMenuBtn;
    public TableView<CoCurricular> coCurricularTableView;
    public TextArea reasonResidingTextArea;
    public Button coCurricularSaveBtn;
    public SplitMenuButton coCurricularClearDeleteBtn;
    public ChoiceBox<String> coCurricularTypeChoiceBox;
    public TextField coCurricularActivityInput;
    public ComboBox<String> nationalityComboBox;
    public ComboBox<String> participationLevelComboBox;
    public ComboBox<String> tertiaryLvlComboBox;
    public Button closeBtn;
    public MenuItem famHistoryDeleteMenuItem;
    public MenuItem hallHistoryDeleteMenuItem;
    public MenuItem bevHistoryDeleteMenuItem;
    public MenuItem achievementDeleteMenuItem;
    public MenuItem commGroupDeleteMenuItem;
    public MenuItem coCurricularDeleteMenuItem;
    public DatePicker hallHistoryPeriodFrom;
    public DatePicker hallHistoryPeriodTo;
    public GridPane achievementFormGrid;
    public GridPane coCurricularFormGrid;
    public SplitPane mainSplitPane;
    public Button studentSaveBtn;
    public Button mainClearBtn;
    public Button windowCloseBtn;
    public GridPane mainGridPane;
    protected Student student;
    protected Operation operation;
    LocalDate current_date;
    protected User user;
    DatabaseCommunicator databaseCommunicator;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    ObservableList<FamilyHistory> familyHistories = FXCollections.<FamilyHistory>observableArrayList();
    ObservableList<HallHistory> hallHistories = FXCollections.<HallHistory>observableArrayList();
    ObservableList<CommunityGroup> communityGroups = FXCollections.<CommunityGroup>observableArrayList();
    ObservableList<BehaviourHistory> behaviourHistories = FXCollections.<BehaviourHistory>observableArrayList();
    ObservableList<Achievement> achievements = FXCollections.<Achievement>observableArrayList();
    ObservableList<CoCurricular> coCurriculars = FXCollections.<CoCurricular>observableArrayList();

    public StudentViewController(Student student, Operation operation,
                                 DatabaseCommunicator databaseCommunicator, User user) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/fxml/StudentView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        Object o = (operation == Operation.NEW && student == null) ? setStudent(new Student()) : setStudent(student);
        setOperation(operation);
        this.databaseCommunicator = databaseCommunicator;
        this.user = AuthController.user;
        AuthController.sMSSessioncount++;

        mainGridPane.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
            if (oldScene == null && newScene != null) {
                // scene is set for the first time. Now its the time to listen stage changes.
                newScene.windowProperty().addListener((observableWindow, oldWindow, newWindow) -> {
                    if (oldWindow == null && newWindow != null) {
                        // stage is set. now is the right time to do whatever we need to the stage in the controller.
                        ((Stage) newWindow).maximizedProperty().addListener((a, b, c) -> {
                            if (c) {
                                // TODO: Something needs to be done when maximized
                            }
                        });
                        newWindow.setOnCloseRequest(new EventHandler<WindowEvent>() {
                            @Override
                            public void handle(WindowEvent event) {
                                ((Stage) newWindow).close();
                            }
                        });
                    }
                });
            }
        });

        idInput.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) {
                byte[] bytes = newValue.getBytes();
                if (bytes.length > 9) {
                    idInput.setText(oldValue);
                }
            } else {
                idInput.setText(oldValue);
            }
        });

        countryChoiceBox.setConverter(new StringConverter<Country>() {
            @Override
            public String toString(Country object) {
                return object.getCountry();
            }

            @Override
            public Country fromString(String string) {
                Country thisCountry = null;
                for (Country country : DatabaseCommunicator.getCountries()) {
                    if (country.getCountry().equals(string)) {
                        thisCountry = new Country(country.getCountryID(), country.getCountry(), country.getNationality());
                        break;
                    }
                }
                return thisCountry;
            }
        });

        facultyChoiceBox.setItems(FXCollections.observableArrayList(Faculty.labels()));
        blockChoiceBox.setItems(FXCollections.observableArrayList(Block.labels()));
        famHistoryBlockChoiceBox.setItems(FXCollections.observableArrayList(Block.labels()));
        bevHistoryHallChoiceBox.setItems(FXCollections.observableArrayList(Hall.labels()));
        coCurricularTypeChoiceBox.setItems(FXCollections.observableArrayList(CoCurActivityType.labels()));
        countryChoiceBox.setItems(FXCollections.observableArrayList(DatabaseCommunicator.getCountries()));
        facultyChoiceBox.setItems(FXCollections.observableArrayList(DatabaseCommunicator.getFaculties()));
        roomNumberChoiceBox.setItems(FXCollections.observableArrayList(DatabaseCommunicator.getRooms()));
//        tertiaryYrofGradChoiceBox.setItems(FXCollections.observableArrayList(
//                genReasonableYearRange().getValue()
//        ));


        countryChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Country>() {
            @Override
            public void changed(ObservableValue<? extends Country> observable, Country oldValue, Country newValue) {

            }
        });

        familyHistoryCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals(true)) {
                famHistoryFormGridPane.setDisable(false);
                familyHistoryTableView.setDisable(false);
            } else {
                famHistoryFormGridPane.setDisable(true);
                familyHistoryTableView.setDisable(true);
            }
        });

        hallHistoryCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals(true)) {
                hallHistoryFormGridPane.setDisable(false);
                hallHistoryTableView.setDisable(false);
            } else {
                hallHistoryFormGridPane.setDisable(true);
                hallHistoryTableView.setDisable(true);
            }
        });

        bevHistoryCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals(true)) {
                bevHistoryFormGridPane.setDisable(false);
                bevHistoryTableView.setDisable(false);
            } else {
                bevHistoryFormGridPane.setDisable(true);
                bevHistoryTableView.setDisable(true);
            }
        });

        familyHistoryTableView.setItems(familyHistories);
        hallHistoryTableView.setItems(hallHistories);
        commGroupTableView.setItems(communityGroups);
        bevHistoryTableView.setItems(behaviourHistories);
        achievementsTable.setItems(achievements);
        coCurricularTableView.setItems(coCurriculars);

        this.getChildren().stream()
                .filter(node -> node instanceof DatePicker)
                .forEach(node -> ((DatePicker) node)
                        .setConverter(new StringConverter<LocalDate>() {
                            @Override
                            public String toString(LocalDate object) {
                                return object.format(formatter);
                            }

                            @Override
                            public LocalDate fromString(String string) {
                                return LocalDate.parse(string, formatter);
                            }
                        }));
    }

    /**
     * Notifier to pleasantly update User
     *
     * @param title   Title of notification
     * @param message Message of notification
     */
    private static void notifier(String title, String message) {
        Platform.runLater(() -> Notifications.create()
                        .title(title)
                        .text(message)
                        .hideAfter(new Duration(2000))
                        .showInformation()
        );
    }

    private Task<String[]> genReasonableYearRange() {
        Task<String[]> reasonableYears = new Task<String[]>() {
            @Override
            protected String[] call() throws Exception {
                String[] years = new String[100];
                int thisYear;
                try {
                    thisYear = DateUtil.getJamaicaDateTime().getYear();
                } catch (Exception ex) {
                    thisYear = 2020;
                }
                for (int i = 0; i >= 99; i++) {
                    years[i] = String.valueOf(thisYear - i);
                }
                return years;
            }
        };
        return reasonableYears;
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

    public void attachToStudent(Student student) {
        this.idInput.textProperty().bindBidirectional(student.idNumberProperty());
        this.firstNameInput.textProperty().bindBidirectional(student.firstNameProperty());
        this.middleNameInput.textProperty().bindBidirectional(student.middleNameProperty());
        this.lastNameInput.textProperty().bindBidirectional(student.lastNameProperty());
//        this.dobDatePicker.editorProperty().getValue().textProperty().bindBidirectional(student.dobProperty());
        this.cellPhoneInput.textProperty().bindBidirectional(student.cellPhoneProperty());
        this.emailInput.textProperty().bindBidirectional(student.emailProperty());
        this.padreFirstNameInput.textProperty().bindBidirectional(student.fatherFirstNameProperty());
        this.padreLastNameInput.textProperty().bindBidirectional(student.fatherLastNameProperty());
        this.padrePhoneNumberInput.textProperty().bindBidirectional(student.fatherPhoneProperty());
        this.madreFirstNameInput.textProperty().bindBidirectional(student.motherFirstNameProperty());
        this.madreLastNameInput.textProperty().bindBidirectional(student.motherLastNameProperty());
        this.madrePhoneNumberInput.textProperty().bindBidirectional(student.motherPhoneProperty());
//        this.facultyChoiceBox.valueProperty().bindBidirectional(student.facultyProperty());
//        this.academicStatusChoiceBox.valueProperty().bindBidirectional(student.academicStatusProperty());
//        this.blockChoiceBox.valueProperty().bindBidirectional(student.blockProperty());
        this.address1Input.textProperty().bindBidirectional(student.homeAddress1Property());
        this.address2Input.textProperty().bindBidirectional(student.homeAddress2Property());
        this.cityInput.textProperty().bindBidirectional(student.homeCityProperty());
        this.stateProvinceInput.textProperty().bindBidirectional(student.homeProvinceProperty());
//        this.countryChoiceBox.valueProperty().bindBidirectional(student.getResidentCountry().countryProperty());
//        this.nationalityComboBox.getEditor().textProperty().bindBidirectional(student.getResidentCountry().nationalityProperty());
//        this.willParticipateChoiceBox.valueProperty().bindBidirectional(student.willParticipateProperty());
//        this.participationLevelComboBox.getEditor().textProperty().bindBidirectional(student.particpationLevelProperty());
    }

    // TODO: All choice boxes will get their own listeners on initialization
    public void grabStudent() {
        student.setIdNumber(this.idInput.getText());
        student.setFirstName(this.firstNameInput.getText());
        student.setMiddleName(this.middleNameInput.getText());
        student.setLastName(this.lastNameInput.getText());
        student.setDob(this.dobDatePicker.editorProperty().get().getText());
        student.setCellPhone(this.cellPhoneInput.getText());
        student.setEmail(this.emailInput.getText());
        student.setFatherFirstName(this.padreFirstNameInput.getText());
        student.setFatherLastName(this.padreLastNameInput.getText());
        student.setFatherPhone(this.padrePhoneNumberInput.getText());
        student.setMotherFirstName(this.madreFirstNameInput.getText());
        student.setMotherLastName(this.madreLastNameInput.getText());
        student.setMotherPhone(this.madrePhoneNumberInput.getText());
        student.setFaculty(this.facultyChoiceBox.getValue());
        student.setAcademicStatus((this.academicStatusChoiceBox.getValue().equals("Full time")));
        student.setBlock(this.blockChoiceBox.getValue());
        student.setRoom(this.roomNumberChoiceBox.getValue());
        student.setHomeAddress1(this.address1Input.getText());
        student.setHomeAddress2(this.address2Input.getText());
        student.setHomeCity(this.cityInput.getText());
        student.setHomeProvince(this.stateProvinceInput.getText());
        student.setNationality(DatabaseCommunicator.getCountryFromID(this.countryChoiceBox.getValue().getCountryID()));
        student.setWillParticipate(this.willParticipateChoiceBox.getValue().equals("Yes"));
        student.setParticpationLevel(0);
    }

    @FXML
    public void photoEventHandler(ActionEvent event) {
        Object eventSource = event.getSource();
        if (Objects.deepEquals(eventSource, addEditPhotoBtn)) {
            Platform.runLater(() -> {
                FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter jpgFilter =
                        new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.jpg");
                FileChooser.ExtensionFilter pngFilter =
                        new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
                FileChooser.ExtensionFilter bmpFilter =
                        new FileChooser.ExtensionFilter("BMP files (*.bmp)", "*.bmp");
                fileChooser.getExtensionFilters().addAll(jpgFilter, pngFilter, bmpFilter);
                File file = fileChooser.showOpenDialog(null);
                try {
                    BufferedImage bufferedImage = ImageIO.read(file);
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    avatarImageView.setImage(image);
                    getStudent().setImage(image);
                    getStudent().setPicture(true);
                } catch (IOException ex) {
                    throw new RuntimeException("Error setting image");
                }
            });
        } else {
            Platform.runLater(() -> {
                try {
                    avatarImageView.setImage(SwingFXUtils.toFXImage(ImageIO.read(new File("resources/img/xlarge.jpg")), null));
                    getStudent().setImage(null);
                    getStudent().setPicture(false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public boolean isFieldsFilled(Pane pane) {
        for (Node node : pane.getChildren()) {
            if (node instanceof PasswordField && ((PasswordField) node).getText().isEmpty()) {
                return false;
            }
            if (node instanceof TextField && ((TextField) node).getText().isEmpty()) {
                return false;
            }
            if (node instanceof TextArea && ((TextArea) node).getText().isEmpty()) {
                return false;
            }
            if (node instanceof DatePicker && ((DatePicker) node).getEditor().getText().isEmpty()) {
                return false;
            }
            if (node instanceof ComboBox && ((ComboBox) node).getSelectionModel().getSelectedIndex() == -1) {
                return false;
            }
            if (node instanceof ChoiceBox && ((ChoiceBox) node).getSelectionModel().getSelectedIndex() == -1) {
                return false;
            }
        }
        return true;
    }

    @FXML
    public void famHistoryHandler(ActionEvent event) {
        Object eventSource = event.getSource();
        if (Objects.deepEquals(eventSource, famHistorySaveBtn)) {
            FamilyHistory famHis = new FamilyHistory();
            Platform.runLater(() -> {
                try {
                    famHis.setFrom(famHistoryFromDatePicker.getValue().format(formatter));
                    famHis.setTo(famHistoryToDatePicker.getValue().format(formatter));
                    famHis.setBlock(famHistoryBlockChoiceBox.getSelectionModel().selectedItemProperty().getValue());
                    famHis.setRelationship(famHistoryRelationshipComboBox.getEditor().getText());
                    familyHistories.add(famHis);
                    student.setFamilyHistories(familyHistories);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        }
        if (Objects.deepEquals(eventSource, famHistoryClearDeleteSplitMenuBtn)) {
            new FormEditizer(famHistoryFormGridPane, FormEditizer.Action.CLEAR)
                    .datePickers().choiceBoxes().comboBoxes();
        }
        if (Objects.deepEquals(eventSource, famHistoryDeleteMenuItem)) {
            Platform.runLater(() -> {
                try {
                    familyHistories.remove(familyHistoryTableView.getSelectionModel().getSelectedItem());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        }

    }

    @FXML
    public void commGroupHandler(ActionEvent event) {
        Object eventSource = event.getSource();
        if (Objects.deepEquals(eventSource, commGroupSaveBtn)) {
            CommunityGroup communityGroup = new CommunityGroup();
            Platform.runLater(() -> {
                communityGroup.setGroup(commGroupNameComboBox.getEditor().getText());
                communityGroup.setProject(commGroupProjectInput.getText());
                communityGroup.setResponsibility(commGroupResponsibilityTextArea.getText());
                communityGroups.add(communityGroup);
                student.setCommunityGroups(communityGroups);
            });
        }
        if (Objects.deepEquals(eventSource, commGroupClearDeleteSplitMenuBtn)) {
            Platform.runLater(() -> {
                new FormEditizer(commGroupFormGridPane, FormEditizer.Action.CLEAR)
                        .textFields().textAreas();
                commGroupNameComboBox.getEditor().clear();
                commGroupNameComboBox.getSelectionModel().clearSelection();
            });
        }
        if (Objects.deepEquals(eventSource, commGroupDeleteMenuItem)) {
            Platform.runLater(() -> {
                try {
                    communityGroups.remove(commGroupTableView.getSelectionModel().getSelectedItem());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        }

    }

    @FXML
    // TODO - Drop this for right now. Come back to it later
    public void studentLookup(ActionEvent event) {
        Object eventSource = event.getSource();
    }

    @FXML
    public void hallHistoryHandler(ActionEvent event) {
        Object eventSource = event.getSource();
        if (Objects.deepEquals(eventSource, hallHistorySaveBtn)) {
            HallHistory hallHistory = new HallHistory();
            Platform.runLater(() -> {
                hallHistory.setHall(hallHistoryHallChoiceBox.getItems()
                        .get(hallHistoryHallChoiceBox.getSelectionModel().getSelectedIndex()));
                hallHistory.setPeriod(new Peroid(
                                hallHistoryPeriodFrom.getValue().getYear(),
                                hallHistoryPeriodTo.getValue().getYear(), 1, 3)
                );
                hallHistories.add(hallHistory);
                student.setHallHistories(hallHistories);
            });
        }
        if (Objects.deepEquals(eventSource, hallHistoryClearDeleteSplitMenuBtn)) {
            Platform.runLater(() -> new FormEditizer(hallHistoryFormGridPane, FormEditizer.Action.CLEAR)
                    .choiceBoxes().datePickers());
        }
        if (Objects.deepEquals(eventSource, hallHistoryDeleteMenuItem)) {
            Platform.runLater(() -> {
                try {
                    hallHistories.remove(hallHistoryTableView.getSelectionModel().getSelectedItem());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        }

    }

    @FXML
    public void bevHistoryHandler(ActionEvent event) {
        Object eventSource = event.getSource();
        if (Objects.deepEquals(eventSource, bevHistorySaveBtn)) {
            BehaviourHistory behaviourHistory = new BehaviourHistory();
            Platform.runLater(() -> {
                behaviourHistory.setHall(bevHistoryHallChoiceBox.getItems()
                        .get(bevHistoryHallChoiceBox.getSelectionModel().getSelectedIndex()));
                behaviourHistory.setInfraction(bevHistoryInfracComboBox.getEditor().getText());
                behaviourHistories.add(behaviourHistory);
            });
        }
        if (Objects.deepEquals(eventSource, bevHistoryClearDeleteBtn)) {
            Platform.runLater(() -> new FormEditizer(hallHistoryFormGridPane, FormEditizer.Action.CLEAR)
                    .comboBoxes().choiceBoxes().textAreas().datePickers());
        }
        if (Objects.deepEquals(eventSource, bevHistoryDeleteMenuItem)) {
            Platform.runLater(() -> {
                try {
                    behaviourHistories.remove(bevHistoryTableView.getSelectionModel().getSelectedItem());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        }
        student.setBehaviourHistories(behaviourHistories);
    }

    @FXML
    public void achievementHandler(ActionEvent event) {
        Object eventSource = event.getSource();
        if (Objects.deepEquals(eventSource, achievementSaveBtn)) {
            Achievement achievement = new Achievement();
            Platform.runLater(() -> {
                achievement.setAchievement(achievementNameInput.getText());
                achievement.setArea(achievementAreaComboBox.getSelectionModel().getSelectedItem());
                achievements.add(achievement);
            });
        }
        if (Objects.deepEquals(eventSource, achievementClearDeleteSplitMenuBtn)) {
            Platform.runLater(() -> new FormEditizer(achievementFormGrid, FormEditizer.Action.CLEAR)
                    .textFields().choiceBoxes());
        }
        if (Objects.deepEquals(eventSource, achievementDeleteMenuItem)) {
            Platform.runLater(() -> achievements.remove(achievementsTable.getSelectionModel().getSelectedItem()));
        }
        student.setAchievements(achievements);

    }

    @FXML
    public void coCurricularHandler(ActionEvent event) {
        Object eventSource = event.getSource();
        if (Objects.deepEquals(eventSource, coCurricularSaveBtn)) {
            CoCurricular coCurricular = new CoCurricular();
            Platform.runLater(() -> {
                coCurricular.setActivity(coCurricularActivityInput.getText());
                coCurricular.setType(coCurricularTypeChoiceBox.getItems()
                        .get(coCurricularTypeChoiceBox.getSelectionModel().getSelectedIndex()));
                coCurriculars.add(coCurricular);
                student.setCoCurriculars(coCurriculars);
            });
        }
        if (Objects.deepEquals(eventSource, coCurricularClearDeleteBtn)) {
            Platform.runLater(() -> new FormEditizer(coCurricularFormGrid, FormEditizer.Action.CLEAR)
                    .textFields().choiceBoxes());
        }
        if (Objects.deepEquals(eventSource, coCurricularDeleteMenuItem)) {
            Platform.runLater(() -> coCurriculars.remove(coCurricularTableView.getSelectionModel().getSelectedItem()));

        }

    }

//    public void populateStudent()

    @FXML
    public void windowBtnHandler(ActionEvent event) {
        Object eventSource = event.getSource();
        if (Objects.deepEquals(eventSource, studentSaveBtn)) {
            databaseCommunicator.addStudent(user, student, 1);
        }
        if (Objects.deepEquals(eventSource, mainClearBtn)) {
            new FormEditizer(mainGridPane, FormEditizer.Action.CLEAR)
                    .choiceBoxes().comboBoxes().datePickers()
                    .passwordFields().textAreas().textFields();
        }
        if (Objects.deepEquals(eventSource, closeBtn)) {
            Stage thisStage = (Stage) closeBtn.getScene().getWindow();
            thisStage.close();
        }

    }

}
