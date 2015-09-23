package model;

public class Period
        {

        int fromYear, toYear;
        int fromSemester, toSemester;

public Period()
        {
        this(0, 0, 0, 0);
        }

public Period(int fromYear, int toYear, int fromSemester, int toSemester)
        {
        this.fromYear = fromYear;
        this.toYear = toYear;
        this.fromSemester = fromSemester;
        this.toSemester = toSemester;
        }

public String toString()
        {
        return fromYear + "" + toYear + "" + fromSemester + toSemester;
        }

public static Period parse(String coded)
        {
        Period period = new Period();
        period.fromYear = Integer.valueOf(coded.substring(0, 4));
        period.toYear = Integer.valueOf(coded.substring(4, 8));
        period.fromSemester = Integer.valueOf(coded.substring(8, 9));
        period.fromSemester = Integer.valueOf(coded.substring(9, 10));
        return period;
        }
        }
