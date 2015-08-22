package model;

import javafx.beans.property.StringProperty;

import java.time.Period;
import java.util.IntSummaryStatistics;

/**
 * Created by Yerodin on 8/22/2015.
 */
public class HallHistory {
    private StringProperty hall;
    private Period peried;

    public HallHistory(String achievemnt, Period peried)
    {
        setHall(achievemnt);
        setPeried(peried);
    }

    public String getHall() {
        return hall.get();
    }

    public StringProperty hallProperty() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall.set(hall);
    }

    public Period getPeried() {
        return peried;
    }

    public void setPeried(Period peried) {
        this.peried = peried;
    }

    public static HallHistory unwrap(String wrapped)
    {
        String achievemnt = wrapped.substring(1,wrapped.indexOf("}"));
        wrapped.replace("{","");
        wrapped.replace("}","");
        String periodStr = wrapped.substring(wrapped.indexOf("}")+1,wrapped.indexOf("}"));
        return new HallHistory(achievemnt,Period.parse(periodStr));
    }

    public static String wrap(HallHistory hallHistory)
    {
        return "{"+hallHistory.getHall()+"}{"+hallHistory.getPeried()+"}";
    }

    public class Peroid
    {
        int fromYear, toYear;
        int fromSemester, toSemester;

        public Peroid()
        {
            this(0, 0, 0, 0);
        }

        public Peroid(int fromYear, int toYear, int fromSemester, int toSemester)
        {
            this.fromYear = fromYear;
            this.toYear = toYear;
            this.fromSemester = fromSemester;
            this.toSemester = toSemester;
        }

        public String toString()
        {
            return fromYear+""+toYear+""+fromSemester+toSemester;
        }

        public Period Parse(String coded)
        {
            Peroid period = new Peroid();

            period.fromYear = Integer.valueOf(coded.substring(0,4));
            period.toYear = Integer.valueOf(coded.substring(4,8));
            period.fromSemester = Integer.valueOf(coded.substring(8,9));
            period.fromSemester = Integer.valueOf(coded.substring(9,10));
            return peried;
        }

    }
}
