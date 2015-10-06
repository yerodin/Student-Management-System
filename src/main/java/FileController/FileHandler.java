package FileController;

import enums.NotifierType;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import model.Student;
import utility.CustomControlLauncher;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.channels.FileChannel;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by Yerodin on 8/27/2015.
 */
public class FileHandler
{
    private String workingDirectory;
    private File updatedDataFile;
    private RandomAccessFile updatedData;

    public FileHandler()
    {
        if (System.getProperty("os.name").toUpperCase().contains("WIN")) workingDirectory = System.getenv("AppData");
        else
        {
            workingDirectory = System.getProperty("user.home");
            if (System.getProperty("os.name").toUpperCase().contains("MAC")) workingDirectory += "/Library/Application Support";
        }
        File imagesDir = new File(workingDirectory += "/images");
        File attachDir = new File(workingDirectory += "/attached");
        if (!imagesDir.exists())
        {
            if (!imagesDir.mkdir()) CustomControlLauncher.notifier("Error", "Error handling files..", NotifierType.ERROR);
            System.exit(1);
        }
        if (!attachDir.exists())
        {
            if (!attachDir.mkdir()) CustomControlLauncher.notifier("Error", "Error handling files..", NotifierType.ERROR);
            System.exit(1);
        }
        try
        {
            updatedDataFile = new File(workingDirectory += "/updat.kw");
            if (!updatedDataFile.exists())
            {
                if (!updatedDataFile.createNewFile()) CustomControlLauncher.notifier("Error", "Error handling files..", NotifierType.ERROR);
                System.exit(1);
            }
            updatedData = new RandomAccessFile(updatedDataFile, "rw");
        }
        catch (IOException e)
        {
            e.printStackTrace();
            CustomControlLauncher.notifier("Error", "Error handling files..", NotifierType.ERROR);
            System.exit(1);
        }

    }

    public boolean saveStudentImage(Student student)
    {
        if (!student.getPicture()) return true;
        File image = new File(workingDirectory += "/images/" + student.getIdNumber() + ".png");
        try
        {
            if (!image.exists())
            {
                if (!image.createNewFile()) CustomControlLauncher.notifier("Error", "Error handling files..", NotifierType.ERROR);
                System.exit(1);
            }
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(image));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(SwingFXUtils.fromFXImage(student.getImage(), null), "png", baos);
            bufferedOutputStream.write(baos.toByteArray());
            bufferedOutputStream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean saveStudentAttachment(byte[] attachmentBytes, Student student, String name)
    {
        File attachment = new File(workingDirectory += "/attached/" + student.getIdNumber() + "/" + name);
        try
        {
            if (!attachment.exists())
            {
                if (!attachment.createNewFile()) CustomControlLauncher.notifier("Error", "Error handling files..", NotifierType.ERROR);
                System.exit(1);
            }
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(attachment));
            bufferedOutputStream.write(attachmentBytes);
            bufferedOutputStream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public File getStudentAttachment(Student student, String name)
    {
        File file = new File(workingDirectory + "/attached/" + student.getIdNumber() + "/" + name);
        if (file.exists()) return file;
        else return null;

    }

    public javafx.scene.image.Image getStudentImage(Student student)
    {
        File file = new File(workingDirectory + "/images/" + student.getIdNumber() + ".png");
        if (file.exists()) try { return new Image(new FileInputStream(file));}
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;

    }

    public long getLastImageUpdatedTime(Student s)
    {
        String id = s.getIdNumber();
        try
        {
//            BufferedReader bufferedReader = new BufferedReader(new FileReader(updatedData));

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }
        return 0;
    }

    private void saveLastImageUpdatedTime(Student s)
    {
        String id = s.getIdNumber();
        long time = Timestamp.valueOf(LocalDateTime.now()).getTime();
        try
        {
            Scanner fileScan = new Scanner(updatedDataFile);
            int currentLine = 0;
            int line = -1;
            while (fileScan.hasNextLine())
            {
                ++currentLine;
                String[] strs = fileScan.nextLine().split(",");
                String currentId = strs[0];
                if (currentId.equalsIgnoreCase(id))
                {
                    break;
                }
            }
            FileChannel fileChannel = updatedData.getChannel();
//            filechannel.

        }
        catch (Exception e)
        {
            e.printStackTrace();
            CustomControlLauncher.notifier("Error", "Error handling files..", NotifierType.ERROR);
        }


    }
}
