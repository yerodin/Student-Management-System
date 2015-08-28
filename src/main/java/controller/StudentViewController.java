package controller;

import DBCommunication.DatabaseCommunicator;
import enums.*;
import enums.TertiaryLevel;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
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
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.util.StringConverter;
import model.*;
import org.controlsfx.control.Notifications;
import utility.CustomControlLauncher;
import utility.DateUtil;
import utility.FormEditizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * Student View Controller
 **/
public class StudentViewController extends TitledPane {
    public TitledPane mainTitledPane;
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
    public ChoiceBox<String> willParticipateChoiceBox;
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
    public ComboBox<String> bevHistoryInfracComboBox;
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
    public TextField nationalityField;
    public DatePicker dateJoinedDatePicker;
    public Button uploadAttachBtn;
    public Button removeAttachmentBtn;
    public ListView<File> attachmentsListView;
    protected Student student = new Student();
    protected Operation operation;
    protected User user;
    LocalDate current_date;
    private DatabaseCommunicator databaseCommunicator;
    private MainController mainController;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    ObservableList<File> attachments = FXCollections.observableArrayList();

    ObservableList<FamilyHistory> familyHistories = FXCollections.<FamilyHistory>observableArrayList();
    ObservableList<HallHistory> hallHistories = FXCollections.<HallHistory>observableArrayList();
    ObservableList<CommunityGroup> communityGroups = FXCollections.<CommunityGroup>observableArrayList();
    ObservableList<BehaviourHistory> behaviourHistories = FXCollections.<BehaviourHistory>observableArrayList();
    ObservableList<Achievement> achievements = FXCollections.<Achievement>observableArrayList();
    ObservableList<CoCurricular> coCurriculars = FXCollections.<CoCurricular>observableArrayList();

    public StudentViewController(Student student, Operation operation, MainController mainController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/fxml/StudentView.fxml"));
//        if (operation == Operation.VIEW) {
//            fxmlLoader = new FXMLLoader(getClass().getResource(
//                    "/fxml/StudentViewReadOnly.fxml"));
//        }
        this.mainController = mainController;
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        if (operation == Operation.NEW && student == null) {
            setStudent(new Student());
        }
        if (operation.equals(Operation.EDIT) && student != null) {
            cleanAttachToStudent(student);
            dirtyAttachToStudent(student);
        }
        setOperation(operation);
        this.databaseCommunicator = AuthController.databaseCommunicator;
        this.user = AuthController.user;
        setStudent(student);

        // TODO: Keep this for later
        // Keep date current
//        ScheduledService<Void> updatedDate = new ScheduledService<Void>() {
//            @Override
//            protected Task<Void> createTask() {
//                return new Task<Void>() {
//                    @Override
//                    protected Void call() throws Exception {
//                        current_date = DateUtil.getJamaicaDateTime().toLocalDate();
//                        return null;
//                    }
//                };
//            }
//        };
//        updatedDate.setPeriod(Duration.seconds(5));
//        updatedDate.start();

        mainTitledPane.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
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
                        newWindow.setOnCloseRequest(event -> ((Stage) newWindow).close());
                    }
                });
            }
        });

        idInput.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) {
                byte[] bytes = newValue.getBytes();
            } else {
                idInput.setText(oldValue);
            }
        });

        cellPhoneInput.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) {
                byte[] bytes = newValue.getBytes();
            } else {
                cellPhoneInput.setText(oldValue);
            }
        });

        padrePhoneNumberInput.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) {
                byte[] bytes = newValue.getBytes();
            } else {
                padrePhoneNumberInput.setText(oldValue);
            }
        });

        madrePhoneNumberInput.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) {
                byte[] bytes = newValue.getBytes();
            } else {
                madrePhoneNumberInput.setText(oldValue);
            }
        });

        // Set converters
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

        dobDatePicker.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate object) {
                return object.format(formatter);
            }

            @Override
            public LocalDate fromString(String string) {
                return LocalDate.parse(string, formatter);
            }
        });

        dateJoinedDatePicker.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate object) {
                return object.format(formatter);
            }

            @Override
            public LocalDate fromString(String string) {
                return LocalDate.parse(string, formatter);
            }
        });

        hallHistoryPeriodFrom.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate object) {
                return object.format(formatter);
            }

            @Override
            public LocalDate fromString(String string) {
                return LocalDate.parse(string, formatter);
            }
        });

        hallHistoryPeriodTo.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate object) {
                return object.format(formatter);
            }

            @Override
            public LocalDate fromString(String string) {
                return LocalDate.parse(string, formatter);
            }
        });

        famHistoryFromDatePicker.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate object) {
                return object.format(formatter);
            }

            @Override
            public LocalDate fromString(String string) {
                return LocalDate.parse(string, formatter);
            }
        });

        famHistoryToDatePicker.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate object) {
                return object.format(formatter);
            }

            @Override
            public LocalDate fromString(String string) {
                return LocalDate.parse(string, formatter);
            }
        });

        bevHistoryDatePicker.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate object) {
                return object.format(formatter);
            }

            @Override
            public LocalDate fromString(String string) {
                return LocalDate.parse(string, formatter);
            }
        });

        setGradYears();
        facultyChoiceBox.setItems(FXCollections.observableArrayList(DatabaseCommunicator.getFaculties()));
        academicStatusChoiceBox.setItems(FXCollections.observableArrayList(AcademicStatus.labels()));
        achievementAreaComboBox.setItems(FXCollections.observableArrayList(AchievementArea.labels()));
        blockChoiceBox.setItems(FXCollections.observableArrayList(Block.labels()));
        famHistoryBlockChoiceBox.setItems(FXCollections.observableArrayList(Block.labels()));
        bevHistoryHallChoiceBox.setItems(FXCollections.observableArrayList(DatabaseCommunicator.getHalls()));
        hallHistoryHallChoiceBox.setItems(FXCollections.observableArrayList(DatabaseCommunicator.getHalls()));
        coCurricularTypeChoiceBox.setItems(FXCollections.observableArrayList(CoCurActivityType.labels()));
        countryChoiceBox.setItems(FXCollections.observableArrayList(DatabaseCommunicator.getCountries()));
        roomNumberChoiceBox.setItems(FXCollections.observableArrayList(DatabaseCommunicator.getRooms()));
        bevHistoryInfracComboBox.setItems(FXCollections.observableArrayList(Infraction.labels()));
        famHistoryRelationshipComboBox.setItems(FXCollections.observableArrayList(Relationship.labels()));
        tertiaryLvlComboBox.setItems(FXCollections.observableArrayList(TertiaryLevel.labels()));
        willParticipateChoiceBox.setItems(FXCollections.observableArrayList(Decision.labels()));

        facultyChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (student != null) {
                    student.setFaculty(newValue);
                }
            }
        });

        tertiaryLvlComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (student != null) {
                    //                    student.setTertiaryLevel(!Objects.equals(newValue, "None"));
                }
            }
        });

        blockChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (student != null) {
                    student.setBlock(newValue);
                }
            }
        });

        roomNumberChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (student != null) {
                    student.setRoom(newValue);
                }
            }
        });

        countryChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            nationalityField.setText(newValue.getNationality());
            if (student != null) {
                student.setResidentCountry(newValue);
                student.setNationalityCountry(DatabaseCommunicator.getCountryFromID(this.countryChoiceBox.getValue().getCountryID()));
            }
        });

        academicStatusChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (student != null) {
                    student.setAcademicStatus(newValue.equals("Full time"));
                }
            }
        });

        willParticipateChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (student != null) {
                    student.setWillParticipate(newValue.equals("Yes"));
                }
            }
        });


        dobDatePicker.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                LocalDate end = LocalDate.now();
                try {
                    end = DateUtil.getJamaicaDateTime().toLocalDate();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // Days
                presentAgeInput.setText(String.valueOf(ChronoUnit.YEARS.between(newValue, end)).concat(" year%s old".replace("%s", ((ChronoUnit.YEARS.between(newValue, end) > 1) ? "s" : ""))));
            }
        });


        familyHistoryCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                famHistoryFormGridPane.setDisable(false);
                familyHistoryTableView.setDisable(false);
            } else {
                famHistoryFormGridPane.setDisable(true);
                familyHistoryTableView.setDisable(true);
            }
        });

        hallHistoryCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                hallHistoryFormGridPane.setDisable(false);
                hallHistoryTableView.setDisable(false);
            } else {
                hallHistoryFormGridPane.setDisable(true);
                hallHistoryTableView.setDisable(true);
            }
        });

        bevHistoryCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                bevHistoryFormGridPane.setDisable(false);
                bevHistoryTableView.setDisable(false);
            } else {
                bevHistoryFormGridPane.setDisable(true);
                bevHistoryTableView.setDisable(true);
            }
        });

        attachmentsListView.setItems(attachments);
        familyHistoryTableView.setItems(familyHistories);
        hallHistoryTableView.setItems(hallHistories);
        commGroupTableView.setItems(communityGroups);
        bevHistoryTableView.setItems(behaviourHistories);
        achievementsTable.setItems(achievements);
        coCurricularTableView.setItems(coCurriculars);

        // Display user-friendly attachment info
        attachmentsListView.setCellFactory(new Callback<ListView<File>, ListCell<File>>() {
            @Override
            public ListCell<File> call(ListView<File> param) {
                return new ListCell<File>() {
                    @Override
                    public void updateItem(File item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getName());
                        }
                    }
                }; // ListCell
            }
        }); // setCellFactory

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
        Platform.runLater(() -> Notifications.create().title(title).text(message).hideAfter(new Duration(2000)).showInformation());
    }

    private void setGradYears() {
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
                for (int i = 0; i <= 99; i++) {
                    years[i] = String.valueOf(thisYear - i);
                }
                return years;
            }
        };
        reasonableYears.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                if (reasonableYears.getValue().length != 0) {
                    tertiaryYrofGradChoiceBox.setItems(FXCollections.observableArrayList(reasonableYears.getValue()));
                }
            }
        });
        new Thread(reasonableYears).start();
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

    public void cleanAttachToStudent(Student student) {
        this.idInput.textProperty().bindBidirectional(student.idNumberProperty());
        this.firstNameInput.textProperty().bindBidirectional(student.firstNameProperty());
        this.middleNameInput.textProperty().bindBidirectional(student.middleNameProperty());
        this.lastNameInput.textProperty().bindBidirectional(student.lastNameProperty());
        this.cellPhoneInput.textProperty().bindBidirectional(student.cellPhoneProperty());
        this.emailInput.textProperty().bindBidirectional(student.emailProperty());
        this.padreFirstNameInput.textProperty().bindBidirectional(student.fatherFirstNameProperty());
        this.padreLastNameInput.textProperty().bindBidirectional(student.fatherLastNameProperty());
        this.padrePhoneNumberInput.textProperty().bindBidirectional(student.fatherPhoneProperty());
        this.madreFirstNameInput.textProperty().bindBidirectional(student.motherFirstNameProperty());
        this.madreLastNameInput.textProperty().bindBidirectional(student.motherLastNameProperty());
        this.madrePhoneNumberInput.textProperty().bindBidirectional(student.motherPhoneProperty());
        this.address1Input.textProperty().bindBidirectional(student.homeAddress1Property());
        this.address2Input.textProperty().bindBidirectional(student.homeAddress2Property());
        this.cityInput.textProperty().bindBidirectional(student.homeCityProperty());
        this.stateProvinceInput.textProperty().bindBidirectional(student.homeProvinceProperty());
        this.reasonResidingTextArea.textProperty().bindBidirectional(student.reasonResidingProperty());
    }

    public void dirtyAttachToStudent(Student student) {
        familyHistories.setAll(student.getFamilyHistories());
        hallHistories.setAll(student.getHallHistories());
        communityGroups.setAll(student.getCommunityGroups());
        behaviourHistories.setAll(student.getBehaviourHistories());
        achievements.setAll(student.getAchievements());
        coCurriculars.setAll(student.getCoCurriculars());
        blockChoiceBox.getSelectionModel().select(student.getBlock());
        facultyChoiceBox.getSelectionModel().select(student.getFaculty());
        academicStatusChoiceBox.getSelectionModel().select((student.getAcademicStatus()) ? "Full time" : "Part time");
        countryChoiceBox.getSelectionModel().select(student.getResidentCountry());
        roomNumberChoiceBox.getSelectionModel().select(student.getRoom());
        //this.dateJoinedDatePicker.setValue(LocalDate.parse(student.getDayJoined(), formatter));
        this.secondarySchoolInput.setText(student.getPreviousSecondary());
        this.willParticipateChoiceBox.getSelectionModel().select(student.getWillParticipate() ? "Yes" : "No");
        this.countryChoiceBox.getSelectionModel().select(student.getResidentCountry());
        this.nationalityField.setText(student.getNationalityCountry().getNationality());
        // TODO: Tertiary level ComboBox needs logic
    }

    public void grabStudent() {
        student = new Student();
        student.setAcademicStatus(this.academicStatusChoiceBox.getSelectionModel().getSelectedItem().equals(AcademicStatus.FULLTIME.getLabel()));
        student.setIdNumber(this.idInput.getText());
        student.setFirstName(this.firstNameInput.getText());
        student.setMiddleName(this.middleNameInput.getText());
        student.setLastName(this.lastNameInput.getText());
        student.setCellPhone(this.cellPhoneInput.getText());
        student.setEmail(this.emailInput.getText());
        student.setFatherFirstName(this.padreFirstNameInput.getText());
        student.setFatherLastName(this.padreLastNameInput.getText());
        student.setFatherPhone(this.padrePhoneNumberInput.getText());
        student.setMotherFirstName(this.madreFirstNameInput.getText());
        student.setMotherLastName(this.madreLastNameInput.getText());
        student.setMotherPhone(this.madrePhoneNumberInput.getText());
        student.setHomeAddress1(this.address1Input.getText());
        student.setHomeAddress2(this.address2Input.getText());
        student.setHomeCity(this.cityInput.getText());
        student.setBlock(this.blockChoiceBox.getSelectionModel().getSelectedItem());
        student.setFaculty(this.facultyChoiceBox.getSelectionModel().getSelectedItem());
        student.setRoom(this.roomNumberChoiceBox.getSelectionModel().getSelectedItem());
        student.setAchievements(achievements);
        student.setCoCurriculars(coCurriculars);
        student.setBehaviourHistories(behaviourHistories);
        student.setFamilyHistories(familyHistories);
        student.setCommunityGroups(communityGroups);
        if (this.dobDatePicker.getValue() != null) student.setDob(this.dobDatePicker.getValue().format(formatter));
        if (this.dateJoinedDatePicker.getValue() != null)
            student.setDayJoined(this.dateJoinedDatePicker.getValue().format(formatter));
        // TODO: Add code to set Tertiary meta info
        student.setWillParticipate(this.willParticipateChoiceBox.getValue() != null && this.willParticipateChoiceBox.getValue().equals(Decision.YES.getLabel()));
        student.setHomeProvince(this.stateProvinceInput.getText());
        student.setReasonResiding(this.reasonResidingTextArea.getText());
        student.setParticpationLevel(0);
    }

    @FXML
    public void photoEventHandler(ActionEvent event) {
        Object eventSource = event.getSource();
        if (Objects.deepEquals(eventSource, addEditPhotoBtn)) {
            Platform.runLater(() -> {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Upload Image");
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
                    Task<Boolean> uploadTask = new Task<Boolean>() {
                        @Override
                        protected Boolean call() throws Exception {
                            return databaseCommunicator.uploadStudentImage(user, student, 1);
                        }
                    };
                    uploadTask.setOnSucceeded(event1 -> student.setPicture(uploadTask.getValue()));
                    new Thread(uploadTask).start();
                } catch (IOException ex) {
                    throw new RuntimeException("Error setting image");
                }
            });
        } else {
            Platform.runLater(() -> {
                try {
                    Task<Boolean> deleteImageTask = new Task<Boolean>() {
                        @Override
                        protected Boolean call() throws Exception {
                            return databaseCommunicator.deleteSudentImage(user, student, 1);
                        }
                    };
                    deleteImageTask.setOnSucceeded(event1 -> student.setPicture(deleteImageTask.getValue()));
                    new Thread(deleteImageTask).start();
                    avatarImageView.setImage(SwingFXUtils.toFXImage(ImageIO.read(new File("resources/img/xlarge.jpg")), null));
                    getStudent().setImage(null);
                    getStudent().setPicture(false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public void attachmentEventHandler(ActionEvent event) {
        Object eventSource = event.getSource();
        if (Objects.deepEquals(eventSource, uploadAttachBtn)) {
            Platform.runLater(() -> {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Upload Attachment");
                File file = fileChooser.showOpenDialog(null);
                student.getAttachedDocuments().add(file);
                Task<Boolean> uploadTask = new Task<Boolean>() {
                    @Override
                    protected Boolean call() throws Exception {
                        return databaseCommunicator.uploadStudentAttachment(user, student, file.getName(), 1);
                    }
                };
                uploadTask.setOnSucceeded(event1 -> {
                    if (uploadTask.getValue()) {
                        attachments.add(file);
                        student.setAttachedDocuments(attachments);
                    }
                });
                new Thread(uploadTask).start();
            });
        } else {
            Platform.runLater(() -> {
                Task<Boolean> removeAttachmentTask = new Task<Boolean>() {
                    @Override
                    protected Boolean call() throws Exception {
                        return databaseCommunicator.deleteSudentAttachment(user, student, attachmentsListView.getSelectionModel().getSelectedItem().getName(), 1);
                    }
                };
                removeAttachmentTask.setOnSucceeded(event1 ->
                {
                    attachments.remove(attachmentsListView.getSelectionModel().getSelectedItem());
                    student.setAttachedDocuments(attachments);
                });
                new Thread(removeAttachmentTask).start();
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
                    famHistoryClearDeleteSplitMenuBtn.fire();
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
                try {
                    communityGroup.setGroup(commGroupNameComboBox.getEditor().getText());
                    communityGroup.setProject(commGroupProjectInput.getText());
                    communityGroup.setResponsibility(commGroupResponsibilityTextArea.getText());
                    communityGroups.add(communityGroup);
                    student.setCommunityGroups(communityGroups);
                    commGroupClearDeleteSplitMenuBtn.fire();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
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
                hallHistory.setPeriod(new Period(hallHistoryPeriodFrom.getValue().getYear(), hallHistoryPeriodTo.getValue().getYear(), 1, 3));
                hallHistories.add(hallHistory);
                student.setHallHistories(hallHistories);
                hallHistoryClearDeleteSplitMenuBtn.fire();
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
                student.setBehaviourHistories(behaviourHistories);
                bevHistoryClearDeleteBtn.fire();
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
                achievementClearDeleteSplitMenuBtn.fire();
            });
        }
        if (Objects.deepEquals(eventSource, achievementClearDeleteSplitMenuBtn)) {
            Platform.runLater(() -> new FormEditizer(achievementFormGrid, FormEditizer.Action.CLEAR)
                    .textFields().comboBoxes());
        }
        if (Objects.deepEquals(eventSource, achievementDeleteMenuItem)) {
            Platform.runLater(() -> {
                try {
                    achievements.remove(achievementsTable.getSelectionModel().getSelectedItem());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        }
    }

    @FXML
    public void coCurricularHandler(ActionEvent event) {
        Object eventSource = event.getSource();
        if (Objects.deepEquals(eventSource, coCurricularSaveBtn)) {
            CoCurricular coCurricular = new CoCurricular();
            Platform.runLater(() -> {
                coCurricular.setActivity(coCurricularActivityInput.getText());
                coCurricular.setType(coCurricularTypeChoiceBox.getSelectionModel().getSelectedItem());
                coCurriculars.add(coCurricular);
                student.setCoCurriculars(coCurriculars);
                coCurricularClearDeleteBtn.fire();
            });
        }
        if (Objects.deepEquals(eventSource, coCurricularClearDeleteBtn)) {
            Platform.runLater(() -> new FormEditizer(coCurricularFormGrid, FormEditizer.Action.CLEAR)
                    .textFields().choiceBoxes());
        }
        if (Objects.deepEquals(eventSource, coCurricularDeleteMenuItem)) {
            Platform.runLater(() -> {
                try {
                    coCurriculars.remove(coCurricularTableView.getSelectionModel().getSelectedItem());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        }
    }

    @FXML
    public void windowBtnHandler(ActionEvent event) {
        Object eventSource = event.getSource();
        if (Objects.deepEquals(eventSource, studentSaveBtn)) {
            grabStudent();
            Task<Boolean> saveSudentTask;
            if (operation.equals(Operation.NEW)) saveSudentTask = new Task<Boolean>()
            {
                    @Override
                    protected Boolean call() throws Exception {
                        return databaseCommunicator.addStudent(user, student, 1);
                    }
                };
            else
                saveSudentTask = new Task<Boolean>() {
                    @Override
                    protected Boolean call() throws Exception {
                        return databaseCommunicator.editStudent(user, student, 1);
                    }
                };
            saveSudentTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                @Override
                public void handle(WorkerStateEvent event) {
                    if (saveSudentTask.getValue()) {
                        if (operation.equals(Operation.NEW))
                            mainController.getNewStudents();
                        else CustomControlLauncher.notifier("Success", "Student has been saved!", NotifierType.INFORATION);

                    } else
                        CustomControlLauncher.notifier("Error", databaseCommunicator.getStatus(), NotifierType.ERROR);
                }
            });

            new Thread(saveSudentTask).start();

        }


        if (Objects.deepEquals(eventSource, mainClearBtn)) {
            Platform.runLater(() -> {
                new FormEditizer(mainGridPane, FormEditizer.Action.CLEAR)
                        .choiceBoxes().comboBoxes().datePickers()
                        .passwordFields().textAreas().textFields();
                removePhotoBtn.fire();
                famHistoryClearDeleteSplitMenuBtn.fire();
                hallHistoryClearDeleteSplitMenuBtn.fire();
                commGroupClearDeleteSplitMenuBtn.fire();
                bevHistoryClearDeleteBtn.fire();
                achievementClearDeleteSplitMenuBtn.fire();
                coCurricularClearDeleteBtn.fire();

            });

        }
        if (Objects.deepEquals(eventSource, closeBtn)) {
            Stage thisStage = (Stage) closeBtn.getScene().getWindow();
            thisStage.close();
        }

    }
}