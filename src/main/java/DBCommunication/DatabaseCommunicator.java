package DBCommunication;

import java.util.ArrayList;

/**
 * Created by Yerodin on 8/21/2015.
 */
public class DatabaseCommunicator {
    public final static int SUCCESS = 1;
    public final static int FAILURE = 0;
    final private String SERVER_IP = "localhost";
    final private String COMPLAINT_URL = "http://" + SERVER_IP + "//sms//dataprovider//hcmanager//login.php";
    private JSONParser jParser;
    public static ArrayList<String> blocks;

    public DatabaseCommunicator() {
        jParser = new JSONParser();
    }


}
