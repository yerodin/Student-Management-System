package utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created: May 19, 2015 @ 10:52 AM
 *
 * @author Alex Stewart
 **/
public final class DateUtil {
    // Set up atomic time server
    public static final String ATOMICTIME_SERVER = "129.6.15.30";
    public static final int ATOMICTIME_PORT = 13;

    public static LocalDateTime getJamaicaDateTime() throws IOException {
        BufferedReader bufferedReader = null;
        Socket socketConnection = null;

        try {
            socketConnection = new Socket(ATOMICTIME_SERVER, ATOMICTIME_PORT);
            bufferedReader = new BufferedReader
                    (new InputStreamReader(socketConnection.getInputStream()));

            String atomicTime;
            while (true) {
                if ((atomicTime = bufferedReader.readLine()).contains("*")) {
                    break;
                }
            }
            String[] fields = atomicTime.split(" ");
            GregorianCalendar calendar = new GregorianCalendar();

            String[] date = fields[1].split("-");
            calendar.set(Calendar.YEAR, 2000 + Integer.parseInt(date[0]));
            calendar.set(Calendar.MONTH, Integer.parseInt(date[1]) - 1);
            calendar.set(Calendar.DATE, Integer.parseInt(date[2]));


            // Deal with timezone and daylight-saving-time
            TimeZone tz = TimeZone.getTimeZone("America/Jamaica"); // or .getDefault()
            int gmt = (tz.getRawOffset() + tz.getDSTSavings()) / 3600000;

            String[] time = fields[2].split(":");
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time[0]) + gmt);
            calendar.set(Calendar.MINUTE, Integer.parseInt(time[1]));
            calendar.set(Calendar.SECOND, Integer.parseInt(time[2]));

            return LocalDateTime.ofInstant(calendar.toInstant(), ZoneId.of(tz.getID()));
        } catch (IOException e) {
            throw e;
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (socketConnection != null) {
                socketConnection.close();
            }
        }
    }
}
