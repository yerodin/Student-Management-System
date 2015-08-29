package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Yerodin on 8/27/2015.
 */
public class TertiaryLevel
{
    private StringProperty tertiaryLevel, institutionName, gradYear, involvment;

    public TertiaryLevel(String tertiaryLevel, String institutionName, String gradYear, String involment)
    {
        this.tertiaryLevel = new SimpleStringProperty(tertiaryLevel);
        this.institutionName = new SimpleStringProperty(institutionName);
        this.gradYear = new SimpleStringProperty(gradYear);
        this.involvment = new SimpleStringProperty(involment);
    }

    public TertiaryLevel()
    {
        this("", "", "", "");
    }

    public String getTertiaryLevel()
    {
        return tertiaryLevel.get();
    }

    public StringProperty tertiaryLevelProperty()
    {
        return tertiaryLevel;
    }

    public void setTertiaryLevel(String tertiaryLevel)
    {
        this.tertiaryLevel.set(tertiaryLevel);
    }

    public String getInstitutionName()
    {
        return institutionName.get();
    }

    public StringProperty institutionNameProperty()
    {
        return institutionName;
    }

    public void setInstitutionName(String institutionName)
    {
        this.institutionName.set(institutionName);
    }

    public String getGradYear()
    {
        return gradYear.get();
    }

    public StringProperty gradYearProperty()
    {
        return gradYear;
    }

    public void setGradYear(String gradYear)
    {
        this.gradYear.set(gradYear);
    }

    public String getInvolvment()
    {
        return involvment.get();
    }

    public StringProperty involvmentProperty()
    {
        return involvment;
    }

    public void setInvolvment(String involvment)
    {
        this.involvment.set(involvment);
    }


    public static TertiaryLevel unwrap(String wrapped)
    {
        System.out.println("wrapped:" + wrapped);
        if (wrapped == null || wrapped.length() < 8) return new TertiaryLevel();
        String tertiaryLevel = wrapped.substring(1, wrapped.indexOf("}"));
        wrapped = wrapped.substring(wrapped.indexOf("}{") + 1);
        String institutionName = wrapped.substring(1, wrapped.indexOf("}"));
        wrapped = wrapped.substring(wrapped.indexOf("}{") + 1);
        String gradYear = wrapped.substring(1, wrapped.indexOf("}"));
        wrapped = wrapped.substring(wrapped.indexOf("}{") + 1);
        String involvment = wrapped.substring(1, wrapped.indexOf("}"));
        return new TertiaryLevel(tertiaryLevel, institutionName, gradYear, involvment);
    }

    public static String wrap(TertiaryLevel tertiaryLevel)
    {
        return "{" + tertiaryLevel.getTertiaryLevel() + "}{" + tertiaryLevel.getInstitutionName() + "}{" + tertiaryLevel.getGradYear() + "}{" + tertiaryLevel.getInvolvment() + "}";
    }
}
