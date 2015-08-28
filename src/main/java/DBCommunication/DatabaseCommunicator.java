package DBCommunication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import model.Country;
import model.Student;
import model.User;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yerodin on 8/21/2015.
 */
public class DatabaseCommunicator {
    public final static int SUCCESS = 1;
    public final static int FAILURE = 0;
    final private String SERVER_IP = "localhost";
    final private String LOGIN_URL = "http://" + SERVER_IP + "//sms//dataprovider//login.php";
    final private String BLOCKS_URL = "http://" + SERVER_IP + "//sms//dataprovider//get_blocks.php";
    final private String FACULTIES_URL = "http://" + SERVER_IP + "//sms//dataprovider//get_faculties.php";
    final private String ROOMS_URL = "http://" + SERVER_IP + "//sms//dataprovider//get_rooms.php";
    final private String COUNTRIES_URL = "http://" + SERVER_IP + "//sms//dataprovider//get_countries.php";
    final private String ADD_STUDENT_URL = "http://" + SERVER_IP + "//sms//dataprovider//add_student.php";
    final private String EDIT_STUDENT_URL = "http://" + SERVER_IP + "//sms//dataprovider//edit_student.php";
    final private String DELETE_STUDENT_URL = "http://" + SERVER_IP + "//sms//dataprovider//delete_student.php";
    final private String GET_STUDENTS_URL = "http://" + SERVER_IP + "//sms//dataprovider//get_new_students.php";
    final private String UPLOAD_STUDENT_IMAGE_URL = "http://" + SERVER_IP + "//sms//dataprovider//upload_student_image.php";
    final private String DELETE_STUDENT_IMAGE_URL = "http://" + SERVER_IP + "//sms//dataprovider//delete_student_image.php";
    final private String UPLOAD_ATTACHMENT_URL = "http://" + SERVER_IP + "//sms//dataprovider//upload_attachment.php";
    final private String DELETE_ATTACHMENT_URL = "http://" + SERVER_IP + "//sms//dataprovider//delete_attachment.php";
    final private String LOGOUT_URL = "http://" + SERVER_IP + "//sms//dataprovider//logout.php";
    final private String GET_HALLS_URL = "http://" + SERVER_IP + "//sms//dataprovider//get_halls.php";
    final private String GET_ATTACHMENT_URL = "http://" + SERVER_IP + "//sms//dataprovider//get_student_attachment.php";

    private JSONParser jParser;
    private static String[] blocks, faculties, rooms, halls;
    private static ArrayList<Country> countries;

    private int statusId;
    private String status;

    private int studentVersion;

    public DatabaseCommunicator() {

        jParser = new JSONParser();
        studentVersion = 0;
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
                fillRooms(u);
                fillHalls(u);
                return u;
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

    public boolean logout(User user, int taskID)
    {
        statusId = taskID;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("sid", user.getSID()));
        JSONObject jObj = jParser.makeHttpRequest(LOGOUT_URL, "POST", params);
        System.out.println(jObj);
        try
        {
            if (jObj.getInt("success") == SUCCESS)
            {
               return true;
            }
            else
                status = jObj.getString("data");
        } catch (Exception e)
        {
            e.printStackTrace();
            status = "Error, could not connect to server.";
        }
        return false;
    }

    private void fillBlocks(User currentUser) throws Exception
    {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("sid", currentUser.getSID()));
        JSONObject jObj = jParser.makeHttpRequest(BLOCKS_URL, "POST", params);
        System.out.println(jObj);
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
        System.out.println(jObj);
        System.out.println(jObj);
        try
        {
            if (jObj.getInt("success") == SUCCESS)
            {
                JSONArray dfaculties = jObj.getJSONArray("data");
                faculties = new String[dfaculties.length()+1];
                faculties[0] = "None";
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
        System.out.println(jObj);
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
        System.out.println(jObj);
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

    private void fillHalls(User currentUser) throws Exception
    {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("sid", currentUser.getSID()));
        JSONObject jObj = jParser.makeHttpRequest(GET_HALLS_URL, "POST", params);
        System.out.println(jObj);
        try
        {
            if (jObj.getInt("success") == SUCCESS)
            {
                JSONArray drooms = jObj.getJSONArray("data");
                halls = new String[drooms.length()+1];
                for(int i = 0; i < drooms.length();++i)
                {
                    JSONObject droom = drooms.getJSONObject(i);
                    int id = droom.getInt("id");
                    String number = droom.getString("name");
                    halls[id] = number;
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


    public boolean addStudent(User currentUser, Student student,int taskID)
    {
        statusId = taskID;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("sid", currentUser.getSID()));
        params.add(new BasicNameValuePair("academic_status", student.getAcademicStatus()? "1":"0"));
        params.add(new BasicNameValuePair("achievements", student.wrapAchievements()));
        params.add(new BasicNameValuePair("behaviour_history", student.wrapBehaviourHistories()));
        params.add(new BasicNameValuePair("block", String.valueOf(indexOf(student.getBlock(), blocks))));
        params.add(new BasicNameValuePair("cell_phone", student.getCellPhone()));
        params.add(new BasicNameValuePair("family_history", student.wrapFamilyHistories()));
        params.add(new BasicNameValuePair("community_group", student.wrapCommunityCommunityGroups()));
        params.add(new BasicNameValuePair("co_curricular", student.wrapCoCurriculars()));
        params.add(new BasicNameValuePair("day_joined", student.getDayJoined()));
        params.add(new BasicNameValuePair("dob", student.getDob()));
        params.add(new BasicNameValuePair("faculty", student.getFaculty()));
        params.add(new BasicNameValuePair("father_first_name", student.getFirstName()));
        params.add(new BasicNameValuePair("father_last_name", student.getFatherLastName()));
        params.add(new BasicNameValuePair("father_phone", student.getFatherPhone()));
        params.add(new BasicNameValuePair("first_name", student.getFirstName()));
        params.add(new BasicNameValuePair("hall_history", student.wrapHallHistories()));
        params.add(new BasicNameValuePair("home_address1", student.getHomeAddress1()));
        params.add(new BasicNameValuePair("home_address2", student.getHomeAddress2()));
        params.add(new BasicNameValuePair("home_city", student.getHomeCity()));
        params.add(new BasicNameValuePair("home_province", student.getHomeProvince()));
        params.add(new BasicNameValuePair("id_number", student.getIdNumber()));
        params.add(new BasicNameValuePair("last_name", student.getLastName()));
        params.add(new BasicNameValuePair("middle_name", student.getMiddleName()));
        params.add(new BasicNameValuePair("mother_first_name", student.getMotherFirstName()));
        params.add(new BasicNameValuePair("mother_last_name", student.getMotherLastName()));
        params.add(new BasicNameValuePair("mother_phone", student.getMotherPhone()));
        params.add(new BasicNameValuePair("nationality", String.valueOf(student.getNationality())));
        params.add(new BasicNameValuePair("previous_secondary_school", student.getPreviousSecondary()));
        params.add(new BasicNameValuePair("reason_residing", student.getReasonResiding()));
        params.add(new BasicNameValuePair("resident_country", String.valueOf(student.getResidentCountry())));
        params.add(new BasicNameValuePair("room", student.getRoom()));
        params.add(new BasicNameValuePair("tertiary_level", student.getTertiaryLevel()? "1":"0"));
        params.add(new BasicNameValuePair("will_participate", (student.getWillParticipate())? "1":"0"));
        params.add(new BasicNameValuePair("participation_level", String.valueOf(student.getParticpationLevel())));
        params.add(new BasicNameValuePair("email", student.getEmail()));
        params.add(new BasicNameValuePair("picture", student.getPicture()? "1":"0"));
        JSONObject jObj = jParser.makeHttpRequest(ADD_STUDENT_URL, "POST", params);
        System.out.println(jObj);
        try
        {
            if (jObj.getInt("success") == SUCCESS)
               return true;
            else
                status = "Incorrect username/password combination.";
        } catch (Exception e)
        {
            e.printStackTrace();
            status = "Error, could not connect to server.";
        }
        return false;
    }

    public boolean editStudent(User currentUser, Student student,int taskID)
    {
        statusId = taskID;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("sid", currentUser.getSID()));
        params.add(new BasicNameValuePair("academic_status", student.getAcademicStatus()? "1":"0"));
        params.add(new BasicNameValuePair("achievements", student.wrapAchievements()));
        params.add(new BasicNameValuePair("behaviour_history", student.wrapBehaviourHistories()));
        params.add(new BasicNameValuePair("block", String.valueOf(indexOf(student.getBlock(), blocks))));
        params.add(new BasicNameValuePair("cell_phone", student.getCellPhone()));
        params.add(new BasicNameValuePair("family_history", student.wrapFamilyHistories()));
        params.add(new BasicNameValuePair("community_group", student.wrapCommunityCommunityGroups()));
        params.add(new BasicNameValuePair("co_curricular", student.wrapCoCurriculars()));
        params.add(new BasicNameValuePair("day_joined", student.getDayJoined()));
        params.add(new BasicNameValuePair("dob", student.getDob()));
        params.add(new BasicNameValuePair("faculty", student.getFaculty()));
        params.add(new BasicNameValuePair("father_first_name", student.getFirstName()));
        params.add(new BasicNameValuePair("father_last_name", student.getFatherLastName()));
        params.add(new BasicNameValuePair("father_phone", student.getFatherPhone()));
        params.add(new BasicNameValuePair("first_name", student.getFirstName()));
        params.add(new BasicNameValuePair("hall_history", student.wrapHallHistories()));
        params.add(new BasicNameValuePair("home_address1", student.getHomeAddress1()));
        params.add(new BasicNameValuePair("home_address2", student.getHomeAddress2()));
        params.add(new BasicNameValuePair("home_city", student.getHomeCity()));
        params.add(new BasicNameValuePair("home_province", student.getHomeProvince()));
        params.add(new BasicNameValuePair("id_number", student.getIdNumber()));
        params.add(new BasicNameValuePair("last_name", student.getLastName()));
        params.add(new BasicNameValuePair("middle_name", student.getMiddleName()));
        params.add(new BasicNameValuePair("mother_first_name", student.getMotherFirstName()));
        params.add(new BasicNameValuePair("mother_last_name", student.getMotherLastName()));
        params.add(new BasicNameValuePair("mother_phone", student.getMotherPhone()));
        params.add(new BasicNameValuePair("nationality", String.valueOf(student.getNationality().getCountryID())));
        params.add(new BasicNameValuePair("previous_secondary_school", student.getPreviousSecondary()));
        params.add(new BasicNameValuePair("reason_residing", student.getReasonResiding()));
        params.add(new BasicNameValuePair("resident_country", String.valueOf(student.getResidentCountry().getCountryID())));
        params.add(new BasicNameValuePair("room", student.getRoom()));
        params.add(new BasicNameValuePair("tertiary_level", student.getTertiaryLevel()? "1":"0"));
        params.add(new BasicNameValuePair("will_participate", (student.getWillParticipate())? "1":"0"));
        params.add(new BasicNameValuePair("participation_level", String.valueOf(student.getParticpationLevel())));
        params.add(new BasicNameValuePair("email", student.getEmail()));
        params.add(new BasicNameValuePair("picture", student.getPicture()? "1":"0"));
        JSONObject jObj = jParser.makeHttpRequest(EDIT_STUDENT_URL, "POST", params);
        System.out.println(jObj);
        try
        {
            if (jObj.getInt("success") == SUCCESS)
                return true;
            else
                status = jObj.getString("data");
        } catch (Exception e)
        {
            e.printStackTrace();
            status = "Error, could not connect to server.";
        }
        return false;
    }

    public boolean deleteStudent(User currentUser, Student studentChanges,int taskID)
    {
        statusId = taskID;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("sid", currentUser.getSID()));
        params.add(new BasicNameValuePair("id_number", studentChanges.getIdNumber()));
        JSONObject jObj = jParser.makeHttpRequest(DELETE_STUDENT_URL, "POST", params);
        System.out.println(jObj);
        try
        {
            if (jObj.getInt("success") == SUCCESS)
                return true;
            else
                status = jObj.getString("data");
        } catch (Exception e)
        {
            e.printStackTrace();
            status = "Error, could not connect to server.";
        }
        return false;
    }

    public boolean uploadStudentAttachment(User currentUser, Student student,String name, int taskID)
    {
        try {
            HttpURLConnection httpUrlConnection = (HttpURLConnection)new URL(UPLOAD_ATTACHMENT_URL+"?sid="+currentUser.getSID()+"&id="+student.getIdNumber()+"&name="+name).openConnection();
            httpUrlConnection.setDoOutput(true);

            httpUrlConnection.setRequestMethod("GET");
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(SwingFXUtils.fromFXImage(student.getImage(), null), "png", os);
            byte[] totalBytes = os.toByteArray();
            int totalByte = totalBytes.length;
            InputStream is = new ByteArrayInputStream(os.toByteArray());
            int byteTrasferred = 0;
            for (int i = 0; i < totalByte; i++) {
                os.write(is.read());
                byteTrasferred = i + 1;
            }

            os.close();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            httpUrlConnection.getInputStream()));

            String s = null;
            while ((s = in.readLine()) != null) {
                System.out.println(s);
            }
            in.close();
            is.close();
            return true;
        }catch(Exception e)
        {
            e.printStackTrace();
            return false;

        }
    }

    public boolean uploadStudentImage(User currentUser, Student student, int taskID)
    {
        try {
            HttpURLConnection httpUrlConnection = (HttpURLConnection)new URL(UPLOAD_STUDENT_IMAGE_URL+"?sid="+currentUser.getSID()+"&id="+student.getIdNumber()).openConnection();
            httpUrlConnection.setDoOutput(true);

                httpUrlConnection.setRequestMethod("GET");
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                ImageIO.write(SwingFXUtils.fromFXImage(student.getImage(), null), "png", os);
                byte[] totalBytes = os.toByteArray();
                int totalByte = totalBytes.length;
                InputStream is = new ByteArrayInputStream(os.toByteArray());
                int byteTrasferred = 0;
                for (int i = 0; i < totalByte; i++) {
                    os.write(is.read());
                    byteTrasferred = i + 1;
                }

                os.close();
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                httpUrlConnection.getInputStream()));

                String s = null;
                while ((s = in.readLine()) != null) {
                    System.out.println(s);
                }
                in.close();
                is.close();
                return true;
        }catch(Exception e)
        {
            e.printStackTrace();
            return false;

        }
    }

    public boolean deleteSudentImage(User currentUser, Student student, int taskID)
    {
        statusId = taskID;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("sid", currentUser.getSID()));
        params.add(new BasicNameValuePair("id", student.getIdNumber()));
        JSONObject jObj = jParser.makeHttpRequest(DELETE_STUDENT_IMAGE_URL, "POST", params);
        System.out.println(jObj);
        try
        {
            if (jObj.getInt("success") == SUCCESS)
                return true;
            else
                status = jObj.getString("data");
        } catch (Exception e)
        {
            e.printStackTrace();
            status = "Error, could not connect to server.";
        }
        return false;
    }

    public boolean deleteSudentAttachment(User currentUser, Student student,String fileName, int taskID)
    {
        statusId = taskID;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("sid", currentUser.getSID()));
        params.add(new BasicNameValuePair("id", student.getIdNumber()));
        params.add(new BasicNameValuePair("name", fileName));
        JSONObject jObj = jParser.makeHttpRequest(DELETE_ATTACHMENT_URL, "POST", params);
        System.out.println(jObj);
        try
        {
            if (jObj.getInt("success") == SUCCESS)
                return true;
            else
                status = jObj.getString("data");
        } catch (Exception e)
        {
            e.printStackTrace();
            status = "Error, could not connect to server.";
        }
        return false;
    }

    private File getStudentAttachment(User currentUser, String studentID, String fileName, int taskID)
        {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("sid", currentUser.getSID()));
            params.add(new BasicNameValuePair("id", studentID));
            params.add(new BasicNameValuePair("name", fileName));
            JSONObject jObj = jParser.makeHttpRequest(GET_ATTACHMENT_URL, "POST", params);
            System.out.println(jObj);
            try
            {
                if (jObj.getInt("success") == SUCCESS)
                {
                    JSONArray bytes = jObj.getJSONArray("data");
                    byte[] fileBytes = new byte[bytes.length()];
                    for(int i = 0; i < bytes.length();++i)
                    {
                        fileBytes[i] = (byte) bytes.getInt(i);
                    }
//                    File file = new File();
//                    FileOutputStream fos = new FileOutputStream(file);
//                    fos.write(fileBytes);
                return null;
                }
                else
                    status = "error";
            } catch (Exception e)
            {
                e.printStackTrace();
                status = "Error, could not connect to server.";
            }
            return null;
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
                    boolean academicStatus = student.getInt("academic_status") == 1;
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
                    boolean picture = student.getInt("picture") == 1;
                    String previousSecondary = student.getString("previous_secondary_school");
                    String reasonResiding = student.getString("reason_residing");
                    int residentCountry = student.getInt("resident_country");
                    int room = student.getInt("room");
                    boolean tertiaryLevel = student.getInt("tertiary_level") == 1;
                    boolean willParticipate = student.getInt("will_participate") == 1;
                    String email = student.getString("email");
                    String attachedWrapped = student.getString("attached_documents");
                    ArrayList<String> attachedNames = Student.unwrapAndGetAttachedFilesNames(attachedWrapped);
                    ObservableList<File> attachedDocuments = FXCollections.observableArrayList();
                    for(String name:attachedNames)
                        attachedDocuments.add(getStudentAttachment(currentUser,idNumber,name,taskID));

                    returnArray[i]= new Student(academicStatus,willParticipate,achievements,behaviour_history,familyHistory,hallHistory,communityGroup,coCurricular,
                            cellPhone,day_joined,dob,getBlockFromID(block),getFacultyFromID(faculty),fatherFirstName,fatherLastName,fatherPhone,firstName,homeAddress1,
                            homeAddress2,homeCity,homeProvince,idNumber, lastName,middleName,motherFirstName,motherLastName,motherPhone,previousSecondary,reasonResiding,
                            getRoomFromID(room),tertiaryLevel,email,getCountryFromID(nationality),participationLevel,picture,getCountryFromID(residentCountry),attachedDocuments);

                }
                studentVersion = jObj.getInt("sversion");
                status = "Successful";
                return returnArray;
            }
            else
                status = jObj.getString("data");
        } catch (Exception e)
        {
            e.printStackTrace();
            status = "Error, could not connect to server.";
        }
        return null;
    }


    public static String getBlockFromID(int ID)
    {
        return ((blocks.length > ID)? blocks[ID]:null);
    }

    public static String getFacultyFromID(int ID)
    {
        return ((faculties.length > ID)? faculties[ID]:null);
    }

    public static Country getCountryFromID(int ID)
    {
        return ((countries.size() > ID)? countries.get(ID):null);
    }

    public static String getRoomFromID(int ID)
    {
        return ((rooms.length > ID)? rooms[ID]:"");
    }

    public static String getHallFromID(int ID)
    {
        return ((halls.length > ID)? halls[ID]:"");
    }

    public int getStatusId()
    {
        return statusId;
    }
    public String getStatus()
    {
        return status;
    }

    private int indexOf(String s, String[] array)
    {
        for(int i = 0; i < array.length; ++i)
            if(array[i].equals(s))
                return i;
        return -1;
    }

    public static ArrayList<Country> getCountries() {
        return countries;
    }

    public static String[] getRooms() {
        return rooms;
    }

    public static String[] getFaculties() {
        return faculties;
    }

    public static String[] getBlocks() {
        return blocks;
    }

    public static String[] getHalls(){return halls;}
}
