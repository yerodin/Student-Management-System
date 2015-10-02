import DBCommunication.DatabaseCommunicator;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import model.Student;
import model.User;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by Yerodin on 9/22/2015.
 */
public class Test001
{
    private DatabaseCommunicator databaseCommunicator;
    private User currentUser;
    Student currentStudent;

    public static void main(String[] args)
    {
        new Test001();
    }

    public Test001()
    {
        databaseCommunicator = new DatabaseCommunicator();
        currentStudent =  new Student("827632829", "Damion", "Marlon", "Richardson", "Excellence", "12", "Science & Technology");
        System.out.println("Testing Started..");
        testLogin();
        testImageUpload();


    }

    public void testLogin()
    {
        System.out.println("Testing Login..");

        currentUser = databaseCommunicator.login("smsadmin", "passworD", 123);
            if (currentUser == null)
                System.out.println("Login Failed" + databaseCommunicator.getStatus(123));
            else
                System.out.println("Login Successful" + databaseCommunicator.getStatus(123));
    }

    public void testImageUpload()
    {
        System.out.println("Testing Student Image Upload..");
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.showDialog(null, "Select");
        File file = jFileChooser.getSelectedFile();
        try
        {
            currentStudent.setImage(new Image(new FileInputStream(file)));
            currentStudent.setPicture(true);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error with selected image");
            return;
        }
        Task<Boolean> dbTask = new Task<Boolean>()
        {
            @Override
            protected Boolean call() throws Exception
            {
                return databaseCommunicator.uploadStudentImage(currentUser,currentStudent,489);
            }
        };
        dbTask.setOnSucceeded(event -> {
            if (!dbTask.getValue()) System.out.println("Upload Failed" + databaseCommunicator.getStatus(489));
            else System.out.println("Upload Successful" + databaseCommunicator.getStatus(489));
        });
        new Thread(dbTask).start();
    }
}
