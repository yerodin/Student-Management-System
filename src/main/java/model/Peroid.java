package model;

/**
 * Created by alex on 8/25/15 at 11:11 AM.
 */
public class Peroid {

    int fromYear, toYear;
    int fromSemester, toSemester;

    public Peroid() {
        this(0, 0, 0, 0);
    }

    public Peroid(int fromYear, int toYear, int fromSemester, int toSemester) {
        this.fromYear = fromYear;
        this.toYear = toYear;
        this.fromSemester = fromSemester;
        this.toSemester = toSemester;
    }

    public String toString() {
        return fromYear + "" + toYear + "" + fromSemester + toSemester;
    }

    public static Peroid parse(String coded) {
        Peroid peroid = new Peroid();

        peroid.fromYear = Integer.valueOf(coded.substring(0, 4));
        peroid.toYear = Integer.valueOf(coded.substring(4, 8));
        peroid.fromSemester = Integer.valueOf(coded.substring(8, 9));
        peroid.fromSemester = Integer.valueOf(coded.substring(9, 10));
        return peroid;
    }
}
