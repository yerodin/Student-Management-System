package DBCommunication;

import model.Country;
import model.Student;
import model.User;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yerodin on 8/21/2015.
 */
public class DatabaseCommunicator {
    public final static int SUCCESS = 1;
    public final static int FAILURE = 0;
    final private String SERVER_IP = "localhost";
    final private String LOGIN_URL = "http://" + SERVER_IP + "//sms//dataprovider//hcmanager//login.php";
    final private String BLOCKS_URL = "http://" + SERVER_IP + "//cms//dataprovider//hcmanager//get_blocks.php";
    final private String FACULTIES_URL = "http://" + SERVER_IP + "//cms//dataprovider//hcmanager//get_faculties.php";
    final private String ROOMS_URL = "http://" + SERVER_IP + "//cms//dataprovider//hcmanager//get_rooms.php";
    final private String COUNTRIES_URL = "http://" + SERVER_IP + "//cms//dataprovider//hcmanager//get_countries.php";
    final private String ADD_STUDENT_URL = "http://" + SERVER_IP + "//cms//dataprovider//hcmanager//add_student.php";
    final private String EDIT_STUDENT_COUNTRIES_URL = "http://" + SERVER_IP + "//cms//dataprovider//hcmanager//edit_student.php";
    final private String DELETE_STUDENT_COUNTRIES_URL = "http://" + SERVER_IP + "//cms//dataprovider//hcmanager//delete_student.php";
    final private String GET_STUDENTS_URL = "http://" + SERVER_IP + "//cms//dataprovider//hcmanager//get_all_students.php";

    private JSONParser jParser;
    private static String[] blocks, faculties, rooms;
    private static ArrayList<Country> countries;

    private int statusId;
    private String status;

    private int studentVersion;

    public DatabaseCommunicator() {

        jParser = new JSONParser();
    }

    public User login(String username, String password, int taskID)
    {
        statusId = taskID;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("user", username));
        params.add(new BasicNameValuePair("password", password));
        JSONObject jObj = jParser.makeHttpRequest(LOGIN_URL, "POST", params);
        System.out.println(jObj);
        try
        {
            if (jObj.getInt("success") == SUCCESS)
            {
                JSONObject user = jObj.getJSONArray("data").getJSONObject(0);
                int id = user.getInt("id");
                String firstName = user.getString("first_name");
                String lastName = user.getString("last_name");
                username = user.getString("username");
                int permission = user.getInt("permission");
                String sid = jObj.getString("sid");
                status = "Successful Login.";
                User u = new User(id,firstName,lastName,username,password,permission,sid);
                fillFaculties(u);
                fillBlocks(u);
                fillCountries(u);
            }
            else
                status = "Incorrect username/password combination.";
        } catch (Exception e)
        {
            e.printStackTrace();
            status = "Error, could not connect to server.";
        }
        return null;
    }

    private void fillBlocks(User currentUser) throws Exception
    {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("sid", currentUser.getSID()));
        JSONObject jObj = jParser.makeHttpRequest(BLOCKS_URL, "POST", params);
        try
        {
            if (jObj.getInt("success") == SUCCESS)
            {
                JSONArray dblocks = jObj.getJSONArray("data");
                blocks = new String[dblocks.length()+1];
                for(int i = 0; i < dblocks.length();++i)
                {
                    JSONObject block= dblocks.getJSONObject(i);
                    int id = block.getInt("block_id");
                    String alias = block.getString("block_alias");
                    blocks[id] = alias;
                }
            }
            else
                status = "error";
        } catch (Exception e)
        {
            e.printStackTrace();
            status = "Error, could not connect to server.";
        }
    }

    private void fillFaculties(User currentUser) throws Exception
    {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("sid", currentUser.getSID()));
        JSONObject jObj = jParser.makeHttpRequest(FACULTIES_URL, "POST", params);
        try
        {
            if (jObj.getInt("success") == SUCCESS)
            {
                JSONArray dfaculties = jObj.getJSONArray("data");
                faculties = new String[dfaculties.length()+1];
                for(int i = 0; i < dfaculties.length();++i)
                {
                    JSONObject faculty= dfaculties.getJSONObject(i);
                    int id = faculty.getInt("faculty_id");
                    String alias = faculty.getString("name");
                    faculties[id] = alias;
                }
            }
            else
                status = "error";
        } catch (Exception e)
        {
            e.printStackTrace();
            status = "Error, could not connect to server.";
        }
    }

    private void fillCountries(User currentUser) throws Exception
    {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("sid", currentUser.getSID()));
        JSONObject jObj = jParser.makeHttpRequest(COUNTRIES_URL, "POST", params);
        try
        {
            if (jObj.getInt("success") == SUCCESS)
            {
                JSONArray dcountries = jObj.getJSONArray("data");
                countries = new ArrayList<>();
                for(int i = 0; i < dcountries.length();++i)
                {
                    JSONObject dcountry= dcountries.getJSONObject(i);
                    int id = dcountry.getInt("country_id");
                    String country = dcountry.getString("country");
                    String nationality = dcountry.getString("nationality");
                   countries.add(new Country(id,country,nationality));
                }
            }
            else
                status = "error";
        } catch (Exception e)
        {
            e.printStackTrace();
            status = "Error, could not connect to server.";
        }
    }

    private void fillRooms(User currentUser) throws Exception
    {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("sid", currentUser.getSID()));
        JSONObject jObj = jParser.makeHttpRequest(ROOMS_URL, "POST", params);
        try
        {
            if (jObj.getInt("success") == SUCCESS)
            {
                JSONArray drooms = jObj.getJSONArray("data");
                rooms = new String[drooms.length()+1];
                for(int i = 0; i < drooms.length();++i)
                {
                    JSONObject droom = drooms.getJSONObject(i);
                    int id = droom.getInt("room_id");
                    String number = droom.getString("number");
                    rooms[id] = number;
                }
            }
            else
                status = "error";
        } catch (Exception e)
        {
            e.printStackTrace();
            status = "Error, could not connect to server.";
        }
    }


    public void addStudent(User currentUser, Student student,int taskID)
    {

    }

    public void editStudent(User currentUser, Student studentChanges,int taskID)
    {

    }

    public void deleteStudent(User currentUser, Student studentChanges,int taskID)
    {

    }

    public Student[] getNewStudents(User currentUser,int taskID)
    {
        statusId = taskID;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("sid", currentUser.getSID()));
        params.add(new BasicNameValuePair("sversion", String.valueOf(studentVersion)));
        JSONObject jObj = jParser.makeHttpRequest(GET_STUDENTS_URL, "POST", params);
        System.out.println("students:" + jObj);
        Student[] returnArray=null;
        try
        {
            if (jObj.getInt("success") == SUCCESS)
            {
                if(jObj.getInt("flag") == 1)
                    return null;
                JSONArray students = jObj.getJSONArray("data").getJSONArray(0);
                if(students.length() > 0)
                    returnArray = new Student[students.length()];
                for(int i = 0; i < students.length(); ++i)
                {
                    JSONObject student = students.getJSONObject(i);
                    boolean academicStatus = student.getBoolean("academic_status");
                    String achievements = student.getString("achievements");
                    String behaviour_history = student.getString("achievements");
                    int block = student.getInt("block");
                    String cellPhone = student.getString("cell_phone");
                    String familyHistory = student.getString("family_history");
                    String communityGroup = student.getString("community_group");
                    String coCurricular = student.getString("co_curricular");
                    String day_joined = student.getString("day_joined");
                    String dob = student.getString("dob");
                    int faculty = student.getInt("faculty");
                    String fatherFirstName = student.getString("father_first_name");
                    String fatherLastName = student.getString("father_last_name");
                    String fatherPhone = student.getString("father_phone");
                    String firstName = student.getString("first_name");
                    String hallHistory = student.getString("hall_history");
                    String homeAddress1 = student.getString("home_address1");
                    String homeAddress2 = student.getString("home_address2");
                    String homeCity = student.getString("home_city");
                    String homeProvince = student.getString("home_province");
                    String idNumber = student.getString("id_number");
                    String lastName = student.getString("last_name");
                    String middleName = student.getString("middle_name");
                    String motherFirstName = student.getString("mother_first_name");
                    String motherLastName = student.getString("mother_last_name");
                    String motherPhone = student.getString("mother_phone");
                    int nationality = student.getInt("nationality");
                    int participationLevel = student.getInt("participation_level");
                    boolean picture = student.getBoolean("picture");
                    String previousSecondary = student.getString("previous_secondary_school");
                    String reasonResiding = student.getString("reason_residing");
                    int residentCountry = student.getInt("resident_country");
                    int room = student.getInt("room");
                    boolean tertiaryLevel = student.getBoolean("tertiary_level");
                    boolean willParticipate = student.getBoolean("will_participate");
                    String email = student.getString("email");


                    returnArray[i]= new Student(academicStatus,willParticipate,achievements,behaviour_history,familyHistory,hallHistory,communityGroup,coCurricular,
                            cellPhone,day_joined,dob,getBlockFromID(block),getFacultyFromID(faculty),fatherFirstName,fatherLastName,fatherPhone,firstName,homeAddress1,
                            homeAddress2,homeCity,homeProvince,idNumber, lastName,middleName,motherFirstName,motherLastName,motherPhone,previousSecondary,reasonResiding,
                            getRoomFromID(room),tertiaryLevel,email,getCountryFromID(nationality),participationLevel,picture,getCountryFromID(residentCountry));

                }
                studentVersion = jObj.getInt("sversion");
                status = "Successful";
                return returnArray;
            }
            else
                status = "Incorrect username/password combination.";
        } catch (Exception e)
        {
            e.printStackTrace();
            status = "Error, could not connect to server.";
        }
        return null;
    }


    public static String getBlockFromID(int ID)
    {
        return ((blocks.length >= ID)? blocks[ID-1]:null);
    }

    public static String getFacultyFromID(int ID)
    {
        return ((faculties.length >= ID)? faculties[ID-1]:null);
    }

    public static Country getCountryFromID(int ID)
    {
        return ((countries.size() >= ID)? countries.get(ID-1):null);
    }

    public static String getRoomFromID(int ID)
    {
        return ((rooms.length >= ID)? rooms[ID-1]:null);
    }

    public int getStatusId()
    {
        return statusId;
    }
    public String getStatus()
    {
        return status;
    }

}
