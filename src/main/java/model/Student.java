package model;

import javafx.beans.property.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Yerodin on 8/21/2015.
 */
public class Student {
    private BooleanProperty academicStatus, willParticipate, picture;
    private ObservableList<Achievement> achievements;
    private ObservableList<BehaviourHistory> behaviourHistories;
    private ObservableList<FamilyHistory> familyHistories;
    private ObservableList<HallHistory> hallHistories;
    private ObservableList<CommunityGroup> communityGroups;
    private ObservableList<CoCurricular> coCurriculars;
    private Image image;
    private ObservableList<File> attachedDocuments;

    private StringProperty cellPhone, dayJoined, dob, fatherFirstName, fatherLastName, fatherPhone,
            homeAddress1, homeAddress2, homeCity, homeProvince, motherFirstName, faculty,
            motherLastName, motherPhone, previousSecondary, reasonResiding, email;

    // Proof of concept

    StringProperty idNumber = new SimpleStringProperty(this, "idNumber");
    StringProperty firstName = new SimpleStringProperty(this, "firstName");
    StringProperty middleName = new SimpleStringProperty(this, "middleName");
    StringProperty lastName = new SimpleStringProperty(this, "lastName");
    StringProperty block = new SimpleStringProperty(this, "block");
    StringProperty room = new SimpleStringProperty(this, "room");

    private IntegerProperty particpationLevel;

    private Country nationalityCountry, residentCountry;
    private TertiaryLevel tertiaryLevel;

    public Student(boolean academicStatus, boolean willParticipate, String achievements, String behaviourHistories, String familyHistories, String hallHistories, String communityGroups, String
            coCurriculars, String cellPhone, String dayJoined, String dob, String block, String faculty, String fatherFirstName, String fatherLastName, String fatherPhone, String firstName, String
            homeAddress1, String homeAddress2, String homeCity, String homeProvince, String idNumber, String lastName, String middleName, String motherFirstName, String motherLastName, String
            motherPhone, String previousSecondary, String reasonResiding, String room, String tertiaryLevel, String email, Country nationalityCountry, int participationLevel, boolean picture,
                   Country residentCountry, ObservableList<File> attachedDocuments)
    {
        this();
        this.academicStatus = new SimpleBooleanProperty(academicStatus);
        unwraoAndSetAchievements(achievements);
        unwraoAndSetBehaviourHistories(behaviourHistories);
        this.block = new SimpleStringProperty(block);
        this.cellPhone = new SimpleStringProperty(cellPhone);
        unwraoAndSetFamilyHistories(familyHistories);
        unwraoAndSetCommunityGroups(communityGroups);
        unwraoAndSetCoCurriculars(coCurriculars);
        this.dayJoined = new SimpleStringProperty(dayJoined);
        this.dob = new SimpleStringProperty(dob);
        this.faculty = new SimpleStringProperty(faculty);
        this.fatherFirstName = new SimpleStringProperty(fatherFirstName);
        this.fatherLastName = new SimpleStringProperty(fatherLastName);
        this.fatherPhone = new SimpleStringProperty(fatherPhone);
        this.firstName = new SimpleStringProperty(firstName);
        unwraoAndSetBHallHistories(hallHistories);
        this.homeAddress1 = new SimpleStringProperty(homeAddress1);
        this.homeAddress2 = new SimpleStringProperty(homeAddress2);
        this.homeCity = new SimpleStringProperty(homeCity);
        this.homeProvince = new SimpleStringProperty(homeProvince);
        this.idNumber = new SimpleStringProperty(idNumber);
        this.lastName = new SimpleStringProperty(lastName);
        this.middleName = new SimpleStringProperty(middleName);
        this.motherFirstName = new SimpleStringProperty(motherFirstName);
        this.motherLastName = new SimpleStringProperty(motherLastName);
        this.motherPhone = new SimpleStringProperty(motherPhone);
        this.nationalityCountry = nationalityCountry;
        this.particpationLevel = new SimpleIntegerProperty(participationLevel);
        this.picture = new SimpleBooleanProperty(picture);
        this.previousSecondary = new SimpleStringProperty(previousSecondary);
        this.reasonResiding = new SimpleStringProperty(reasonResiding);
        this.residentCountry = residentCountry;
        this.room = new SimpleStringProperty(room);
        this.tertiaryLevel = TertiaryLevel.unwrap(tertiaryLevel);
        this.willParticipate = new SimpleBooleanProperty(willParticipate);
        this.email = new SimpleStringProperty(email);
        this.attachedDocuments = FXCollections.observableArrayList();
        this.attachedDocuments.addAll(attachedDocuments);
    }

    public Student() {
        this.academicStatus = new SimpleBooleanProperty();
        unwraoAndSetAchievements("");
        unwraoAndSetBehaviourHistories("");
        this.block = new SimpleStringProperty("");
        this.cellPhone = new SimpleStringProperty("");
        unwraoAndSetFamilyHistories("");
        unwraoAndSetCommunityGroups("");
        unwraoAndSetCoCurriculars("");
        this.dayJoined = new SimpleStringProperty("");
        this.dob = new SimpleStringProperty("");
        this.faculty = new SimpleStringProperty("none");
        this.fatherFirstName = new SimpleStringProperty("");
        this.fatherLastName = new SimpleStringProperty("");
        this.fatherPhone = new SimpleStringProperty("");
        this.firstName = new SimpleStringProperty("");
        unwraoAndSetBHallHistories("");
        this.homeAddress1 = new SimpleStringProperty("");
        this.homeAddress2 = new SimpleStringProperty("");
        this.homeCity = new SimpleStringProperty("");
        this.homeProvince = new SimpleStringProperty("");
        this.idNumber = new SimpleStringProperty("");
        this.lastName = new SimpleStringProperty("");
        this.middleName = new SimpleStringProperty("");
        this.motherFirstName = new SimpleStringProperty("");
        this.motherLastName = new SimpleStringProperty("");
        this.motherPhone = new SimpleStringProperty("");
        this.particpationLevel = new SimpleIntegerProperty();
        this.picture = new SimpleBooleanProperty();
        this.previousSecondary = new SimpleStringProperty("");
        this.reasonResiding = new SimpleStringProperty("");
        this.room = new SimpleStringProperty("");
        this.tertiaryLevel = new TertiaryLevel();
        this.willParticipate = new SimpleBooleanProperty();
        this.email = new SimpleStringProperty("");
        this.attachedDocuments = FXCollections.observableArrayList();
    }

    // Constructor for testing purposes only
    public Student(String idNumber, String firstName, String middleName,
                   String lastName, String block, String room,
                   String faculty) {
        setIdNumber(idNumber);
        setFirstName(firstName);
        setMiddleName(middleName);
        setLastName(lastName);
        setBlock(block);
        setRoom(room);
        setFaculty(faculty);
    }

    public boolean getPicture() {
        return picture.get();
    }

    public BooleanProperty pictureProperty() {
        return picture;
    }

    public void setPicture(boolean picture) {
        this.picture.set(picture);
    }

    public int getParticpationLevel() {
        return particpationLevel.get();
    }

    public IntegerProperty particpationLevelProperty() {
        return particpationLevel;
    }

    public void setParticpationLevel(int particpationLevel) {
        this.particpationLevel.set(particpationLevel);
    }

    public boolean getAcademicStatus() {
        return academicStatus.get();
    }

    public BooleanProperty academicStatusProperty() {
        return academicStatus;
    }

    public void setAcademicStatus(boolean academicStatus) {
        this.academicStatus.set(academicStatus);
    }

    public boolean getWillParticipate() {
        return willParticipate.get();
    }

    public BooleanProperty willParticipateProperty() {
        return willParticipate;
    }

    public void setWillParticipate(boolean willParticipate) {
        this.willParticipate.set(willParticipate);
    }

    public TertiaryLevel getTertiaryLevel()
    {
        return tertiaryLevel;
    }

    public void setTertiaryLevel(TertiaryLevel tertiaryLevel)
    {
        this.tertiaryLevel = tertiaryLevel;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public ObservableList<Achievement> getAchievements() {
        return achievements;
    }

    public void setAchievements(ObservableList<Achievement> achievements) {
        this.achievements = achievements;
    }

    public ObservableList<BehaviourHistory> getBehaviourHistories() {
        return behaviourHistories;
    }

    public void setBehaviourHistories(ObservableList<BehaviourHistory> behaviourHistories) {
        this.behaviourHistories = behaviourHistories;
    }

    public ObservableList<FamilyHistory> getFamilyHistories() {
        return familyHistories;
    }

    public void setFamilyHistories(ObservableList<FamilyHistory> familyHistories) {
        this.familyHistories = familyHistories;
    }

    public ObservableList<HallHistory> getHallHistories() {
        return hallHistories;
    }

    public void setHallHistories(ObservableList<HallHistory> hallHistories) {
        this.hallHistories = hallHistories;
    }

    public ObservableList<CommunityGroup> getCommunityGroups() {
        return communityGroups;
    }

    public void setCommunityGroups(ObservableList<CommunityGroup> communityGroups) {
        this.communityGroups = communityGroups;
    }

    public ObservableList<CoCurricular> getCoCurriculars() {
        return coCurriculars;
    }

    public void setCoCurriculars(ObservableList<CoCurricular> coCurriculars) {
        this.coCurriculars = coCurriculars;
    }

    public ObservableList<File> getAttachedDocuments() {
        return attachedDocuments;
    }

    public void setAttachedDocuments(ObservableList<File> attachedDocuments) {
        this.attachedDocuments = attachedDocuments;
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getRoom() {
        return room.get();
    }

    public StringProperty roomProperty() {
        return room;
    }

    public void setRoom(String room) {
        this.room.set(room);
    }


    public String getReasonResiding() {
        return reasonResiding.get();
    }

    public StringProperty reasonResidingProperty() {
        return reasonResiding;
    }

    public void setReasonResiding(String reasonResiding) {
        this.reasonResiding.set(reasonResiding);
    }

    public String getPreviousSecondary() {
        return previousSecondary.get();
    }

    public StringProperty previousSecondaryProperty() {
        return previousSecondary;
    }

    public void setPreviousSecondary(String previousSecondary) {
        this.previousSecondary.set(previousSecondary);
    }

    public String getMotherPhone() {
        return motherPhone.get();
    }

    public StringProperty motherPhoneProperty() {
        return motherPhone;
    }

    public void setMotherPhone(String motherPhone) {
        this.motherPhone.set(motherPhone);
    }

    public String getMotherLastName() {
        return motherLastName.get();
    }

    public StringProperty motherLastNameProperty() {
        return motherLastName;
    }

    public void setMotherLastName(String motherLastName) {
        this.motherLastName.set(motherLastName);
    }

    public String getMotherFirstName() {
        return motherFirstName.get();
    }

    public StringProperty motherFirstNameProperty() {
        return motherFirstName;
    }

    public void setMotherFirstName(String motherFirstName) {
        this.motherFirstName.set(motherFirstName);
    }

    public String getMiddleName() {
        return middleName.get();
    }

    public StringProperty middleNameProperty() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName.set(middleName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getIdNumber() {
        return idNumber.get();
    }

    public StringProperty idNumberProperty() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber.set(idNumber);
    }

    public String getHomeProvince() {
        return homeProvince.get();
    }

    public StringProperty homeProvinceProperty() {
        return homeProvince;
    }

    public void setHomeProvince(String homeProvince) {
        this.homeProvince.set(homeProvince);
    }

    public String getHomeCity() {
        return homeCity.get();
    }

    public StringProperty homeCityProperty() {
        return homeCity;
    }

    public void setHomeCity(String homeCity) {
        this.homeCity.set(homeCity);
    }

    public String getHomeAddress2() {
        return homeAddress2.get();
    }

    public StringProperty homeAddress2Property() {
        return homeAddress2;
    }

    public void setHomeAddress2(String homeAddress2) {
        this.homeAddress2.set(homeAddress2);
    }

    public String getHomeAddress1() {
        return homeAddress1.get();
    }

    public StringProperty homeAddress1Property() {
        return homeAddress1;
    }

    public void setHomeAddress1(String homeAddress1) {
        this.homeAddress1.set(homeAddress1);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getFatherPhone() {
        return fatherPhone.get();
    }

    public StringProperty fatherPhoneProperty() {
        return fatherPhone;
    }

    public void setFatherPhone(String fatherPhone) {
        this.fatherPhone.set(fatherPhone);
    }

    public String getFatherLastName() {
        return fatherLastName.get();
    }

    public StringProperty fatherLastNameProperty() {
        return fatherLastName;
    }

    public void setFatherLastName(String fatherLastName) {
        this.fatherLastName.set(fatherLastName);
    }

    public String getFatherFirstName() {
        return fatherFirstName.get();
    }

    public StringProperty fatherFirstNameProperty() {
        return fatherFirstName;
    }

    public void setFatherFirstName(String fatherFirstName) {
        this.fatherFirstName.set(fatherFirstName);
    }

    public String getFaculty() {
        return faculty.get();
    }

    public StringProperty facultyProperty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty.set(faculty);
    }

    public String getBlock() {
        return block.get();
    }

    public StringProperty blockProperty() {
        return block;
    }

    public void setBlock(String block) {
        this.block.set(block);
    }

    public String getDob() {
        return dob.get();
    }

    public StringProperty dobProperty() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob.set(dob);
    }

    public String getDayJoined() {
        return dayJoined.get();
    }

    public StringProperty dayJoinedProperty() {
        return dayJoined;
    }

    public void setDayJoined(String dayJoined) {
        this.dayJoined.set(dayJoined);
    }

    public String getCellPhone() {
        return cellPhone.get();
    }

    public StringProperty cellPhoneProperty() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone.set(cellPhone);
    }

    public Country getNationalityCountry() {
        return nationalityCountry;
    }

    public void setNationalityCountry(Country nationalityCountry) {
        this.nationalityCountry = nationalityCountry;
    }

    public Country getResidentCountry() {
        return residentCountry;
    }

    public void setResidentCountry(Country residentCountry) {
        this.residentCountry = residentCountry;
    }

    public String wrapAchievements()
    {
        String retString = "";
        for (Achievement achievement : achievements)
            retString += Achievement.wrap(achievement) + ",";
        if (retString.length() > 1) retString = retString.substring(0, retString.length() - 1);
        return retString;
    }

    public void unwraoAndSetAchievements(String wrapped)
    {
        achievements = FXCollections.observableArrayList();
        while (wrapped.length() > 1)
        {
            int index = wrapped.indexOf(",");
            if (index == -1) index = wrapped.length() - 1;
            Achievement achievement = Achievement.unwrap(wrapped.substring(0, index + 1));
            wrapped = wrapped.substring(index + 1);
            achievements.add(achievement);
        }
    }

    public String wrapBehaviourHistories()
    {
        String retString = "";
        for (BehaviourHistory behaviourHistory : behaviourHistories)
            retString += BehaviourHistory.wrap(behaviourHistory) + ",";
        if (retString.length() > 1) retString = retString.substring(0, retString.length() - 1);
        return retString;
    }

    public void unwraoAndSetFamilyHistories(String wrapped)
    {
        familyHistories = FXCollections.observableArrayList();
        while (wrapped.length() > 1)
        {
            int index = wrapped.indexOf(",");
            if (index == -1) index = wrapped.length() - 1;
            FamilyHistory familyHistory = FamilyHistory.unwrap(wrapped.substring(0, index + 1));
            wrapped = wrapped.substring(index + 1);
            familyHistories.add(familyHistory);
        }
    }

    public String wrapFamilyHistories()
    {
        String retString = "";
        for (FamilyHistory familyHistory : familyHistories)
            retString += FamilyHistory.wrap(familyHistory) + ",";
        if (retString.length() > 1) retString = retString.substring(0, retString.length() - 1);
        return retString;
    }

    public void unwraoAndSetBehaviourHistories(String wrapped)
    {
        behaviourHistories = FXCollections.observableArrayList();
        while (wrapped.length() > 1)
        {
            int index = wrapped.indexOf(",");
            if (index == -1) index = wrapped.length() - 1;
            BehaviourHistory behaviourHistory = BehaviourHistory.unwrap(wrapped.substring(0, index + 1));
            wrapped = wrapped.substring(index + 1);
            behaviourHistories.add(behaviourHistory);
        }
    }

    public String wrapHallHistories()
    {
        String retString = "";
        for (HallHistory hallHistory : hallHistories)
            retString += HallHistory.wrap(hallHistory) + ",";
        if (retString.length() > 1) retString = retString.substring(0, retString.length() - 1);
        return retString;
    }

    public void unwraoAndSetBHallHistories(String wrapped)
    {
        hallHistories = FXCollections.observableArrayList();
        while (wrapped.length() > 1)
        {
            int index = wrapped.indexOf(",");
            if (index == -1) index = wrapped.length() - 1;
            HallHistory hallHistory = HallHistory.unwrap(wrapped.substring(0, index + 1));
            wrapped = wrapped.substring(index + 1);
            hallHistories.add(hallHistory);
        }
    }

    public String wrapCommunityCommunityGroups()
    {
        String retString = "";
        for (CommunityGroup communityGroup : communityGroups)
            retString += CommunityGroup.wrap(communityGroup) + ",";
        if (retString.length() > 1) retString = retString.substring(0, retString.length() - 1);
        return retString;
    }

    public void unwraoAndSetCommunityGroups(String wrapped)
    {
        communityGroups = FXCollections.observableArrayList();
        while (wrapped.length() > 1)
        {
            int index = wrapped.indexOf(",");
            if (index == -1) index = wrapped.length() - 1;
            CommunityGroup communityGroup = CommunityGroup.unwrap(wrapped.substring(0, index + 1));
            wrapped = wrapped.substring(index + 1);
            communityGroups.add(communityGroup);
        }
    }

    public String wrapCoCurriculars()
    {
        String retString = "";
        for (CoCurricular coCurricular : coCurriculars)
            retString += CoCurricular.wrap(coCurricular) + ",";
        if (retString.length() > 1) retString = retString.substring(0, retString.length() - 1);
        return retString;
    }

    public void unwraoAndSetCoCurriculars(String wrapped)
    {
        coCurriculars = FXCollections.observableArrayList();
        while (wrapped.length() > 1)
        {
            int index = wrapped.indexOf(",");
            if (index == -1) index = wrapped.length() - 1;
            CoCurricular coCurricular = CoCurricular.unwrap(wrapped.substring(0, index + 1));
            wrapped = wrapped.substring(index + 1);
            coCurriculars.add(coCurricular);
        }
    }

    public String wrapAttachedFiles()
    {
        String retString = "";
        for (File attachedDocument : attachedDocuments)
            retString += "{" + attachedDocument.getName() + "},";
        if (retString.length() > 1) retString = retString.substring(0, retString.length() - 1);
        return retString;
    }

    public static ArrayList<String> unwrapAndGetAttachedFilesNames(String wrapped)
    {
        ArrayList<String> returnArray = new ArrayList<String>();
        while (wrapped.length() > 1)
        {
            String name = wrapped.substring(0, wrapped.indexOf("},") + 1);
            name = name.substring(1, name.length() - 1);
            wrapped = wrapped.substring(wrapped.indexOf("},") + 2);
            returnArray.add(name);
        }
        return returnArray;
    }

    @Override
    public String toString() {
        return "Student{" +
                "academicStatus=" + academicStatus +
                ", willParticipate=" + willParticipate +
                ", tertiaryLevel=" + tertiaryLevel +
                ", picture=" + picture +
                ", achievements=" + achievements +
                ", behaviourHistories=" + behaviourHistories +
                ", familyHistories=" + familyHistories +
                ", hallHistories=" + hallHistories +
                ", communityGroups=" + communityGroups +
                ", coCurriculars=" + coCurriculars +
                ", image=" + image +
                ", cellPhone=" + cellPhone +
                ", dayJoined=" + dayJoined +
                ", dob=" + dob +
                ", block=" + block +
                ", faculty=" + faculty +
                ", fatherFirstName=" + fatherFirstName +
                ", fatherLastName=" + fatherLastName +
                ", fatherPhone=" + fatherPhone +
                ", firstName=" + firstName +
                ", homeAddress1=" + homeAddress1 +
                ", homeAddress2=" + homeAddress2 +
                ", homeCity=" + homeCity +
                ", homeProvince=" + homeProvince +
                ", idNumber=" + idNumber +
                ", lastName=" + lastName +
                ", middleName=" + middleName +
                ", motherFirstName=" + motherFirstName +
                ", motherLastName=" + motherLastName +
                ", motherPhone=" + motherPhone +
                ", previousSecondary=" + previousSecondary +
                ", reasonResiding=" + reasonResiding +
                ", room=" + room +
                ", email=" + email +
                ", particpationLevel=" + particpationLevel +
                ", nationalityCountry=" + nationalityCountry +
                ", residentCountry=" + residentCountry +
                '}';
    }
}
