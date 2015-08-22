package DBCommunication;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Yerodin on 8/21/2015.
 */
public class DatabaseCommunicator {
    final private String SERVER_IP = "localhost";
    final private String COMPLAINT_URL = "http://" + SERVER_IP + "//sms//dataprovider//hcmanager//login.php";

    public final static int SUCCESS = 1;
    public final static int FAILURE = 0;

    private JSONParser jParser;

    public DatabaseCommunicator()
    {
        jParser = new JSONParser();
    }


}
