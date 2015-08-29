package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Yerodin on 8/27/2015.
 */
public class TertiaryLevel
{
    private StringProperty tertiaryLevel = new SimpleStringProperty(this, "tertiaryLevel"),
            institutionName = new SimpleStringProperty(this, "institutionName"),
            gradYear = new SimpleStringProperty(this, "gradYear"),
            involvement = new SimpleStringProperty(this, "involvement");

    public TertiaryLevel(String tertiaryLevel, String institutionName, String gradYear, String involvement)
    {
        setTertiaryLevel(tertiaryLevel);
        setInstitutionName(institutionName);
        setGradYear(gradYear);
        setInvolvement(involvement);
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

    public String getInvolvement()
    {
        return involvement.get();
    }

    public StringProperty involvementProperty()
    {
        return involvement;
    }

    public void setInvolvement(String involvement)
    {
        this.involvement.set(involvement);
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
        String involvement = wrapped.substring(1, wrapped.indexOf("}"));
        return new TertiaryLevel(tertiaryLevel, institutionName, gradYear, involvement);
    }

    public static String wrap(TertiaryLevel tertiaryLevel)
    {
        return "{" + tertiaryLevel.getTertiaryLevel() + "}{" + tertiaryLevel.getInstitutionName() + "}{" + tertiaryLevel.getGradYear() + "}{" + tertiaryLevel.getInvolvement() + "}";
    }
}
