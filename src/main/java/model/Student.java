package model;

<<<<<<< HEAD
import javafx.beans.property.StringProperty;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Yerodin on 8/21/2015.
 */
public class Student {
    private boolean academicStatus, willParticipate;
    private ArrayList<Achievement> achievements;
    private ArrayList<BehaviourHistory> behaviourHistories;
    private ArrayList<FamilyHistory> familyHistories;
    private ArrayList<HallHistory> hallHistories;
    private ArrayList<CommunityGroup> communityGroups;
    private ArrayList<CoCurricular> coCurriculars;


    private StringProperty cellPhone, dayJoined, dob, block, faculty, fatherFirstName, fatherLastName,fatherPhone, firstName,
            homeAddress1,homeAddress2, homeCity, homeProvince,idNumber,lastName,middleName,motherFirstName,
            motherLastName, motherPhone, nationality, previousSecondary,reasonResiding, residentCountry, room, email;

    public boolean isAcademicStatus() {
        return academicStatus;
    }

    public void setAcademicStatus(boolean academicStatus) {
        this.academicStatus = academicStatus;
    }

    public boolean isWillParticipate() {
        return willParticipate;
    }

    public void setWillParticipate(boolean willParticipate) {
        this.willParticipate = willParticipate;
    }

    public ArrayList<Achievement> getAchievements() {
        return achievements;
    }

    public void setAchievements(ArrayList<Achievement> achievements) {
        this.achievements = achievements;
    }

    public ArrayList<BehaviourHistory> getBehaviourHistories() {
        return behaviourHistories;
    }

    public void setBehaviourHistories(ArrayList<BehaviourHistory> behaviourHistories) {
        this.behaviourHistories = behaviourHistories;
    }

    public ArrayList<FamilyHistory> getFamilyHistories() {
        return familyHistories;
    }

    public void setFamilyHistories(ArrayList<FamilyHistory> familyHistories) {
        this.familyHistories = familyHistories;
    }

    public ArrayList<HallHistory> getHallHistories() {
        return hallHistories;
    }

    public void setHallHistories(ArrayList<HallHistory> hallHistories) {
        this.hallHistories = hallHistories;
    }

    public ArrayList<CommunityGroup> getCommunityGroups() {
        return communityGroups;
    }

    public void setCommunityGroups(ArrayList<CommunityGroup> communityGroups) {
        this.communityGroups = communityGroups;
    }

    public ArrayList<CoCurricular> getCoCurriculars() {
        return coCurriculars;
    }

    public void setCoCurriculars(ArrayList<CoCurricular> coCurriculars) {
        this.coCurriculars = coCurriculars;
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

    public String getResidentCountry() {
        return residentCountry.get();
    }

    public StringProperty residentCountryProperty() {
        return residentCountry;
    }

    public void setResidentCountry(String residentCountry) {
        this.residentCountry.set(residentCountry);
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

    public String getNationality() {
        return nationality.get();
    }

    public StringProperty nationalityProperty() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality.set(nationality);
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


=======

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
>>>>>>> ec6248fe605486e90856d89b660f35291482e859
}
