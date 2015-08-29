package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Yerodin on 8/22/2015.
 */
public class Country {
    private IntegerProperty countryID = new SimpleIntegerProperty(this, "countryID");
    private StringProperty country = new SimpleStringProperty(this, "country"),
            nationality = new SimpleStringProperty(this, "nationality");

    public Country(int countryID, String country, String nationality)
    {
        setCountryID(countryID);
        setCountry(country);
        setNationality(nationality);
    }

    public int getCountryID() {
        return countryID.get();
    }

    public IntegerProperty countryIDProperty() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID.set(countryID);
    }

    public String getCountry() {
        return country.get();
    }

    public StringProperty countryProperty() {
        return country;
    }

    public void setCountry(String country) {
        this.country.set(country);
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
}
