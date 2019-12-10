package com.chinamobile.smartgateway.apitest.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Debug {
    static String logPath = "/var/felix-temp/";
    static{
        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith("win")){
            logPath = "E:"+logPath;
        }
        try {
            File f = new File(logPath);
            if(!f.exists()){
                f.mkdirs();
            }
        } catch (Exception e) {
            Debug.log(e);
        }
    }
    public static void log(String logMessage)
    {
        if (logMessage != null) {
            writeLogMsg("log:", logMessage, logPath+"apitestDebug.log");
        }
    }

    public static void log(Throwable exception)
    {
        if (exception != null) {
            writeLogMsg("log:", exception, logPath+"apitestDebug.log");
        }
    }

    public static void logMsg(String logMessage)
    {
        if (logMessage != null) {
            writeLogMsg("log:", logMessage, logPath+"noticeDebug.log");
        }
    }

    private static void writeLogMsg(String level, String msg, String path)
    {
        System.out.println(msg);
        File file = new File(path);
        BufferedWriter bw = null;
        boolean flag = true;
        try
        {
            if (!file.exists()) {
                flag = file.createNewFile();
            }
            if (flag)
            {
                FileOutputStream fos = new FileOutputStream(file, true);
                OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
                bw = new BufferedWriter(osw);
                bw.newLine();
                bw.write(level + msg + "\n");
                bw.flush();
            }
        }
        catch (FileNotFoundException localFileNotFoundException)
        {
            System.out.println("invalid file");
            try
            {
                if (bw != null) {
                    bw.close();
                }
            }
            catch (IOException localIOException1) {}
            try
            {
                if (bw != null) {
                    bw.close();
                }
            }
            catch (IOException localIOException2) {}
        }
        catch (Exception e)
        {
//            System.out.println("Connection error " + e.getMessage());
            Debug.log(e);
            try
            {
                if (bw != null) {
                    bw.close();
                }
            }
            catch (IOException localIOException3) {}
            try
            {
                if (bw != null) {
                    bw.close();
                }
            }
            catch (IOException localIOException4) {}
        }
        finally
        {
            try
            {
                if (bw != null) {
                    bw.close();
                }
            }
            catch (IOException localIOException5) {}
        }
    }

    private static void writeLogMsg(String level, Throwable exception, String path)
    {
        System.out.println(exception);
        File file = new File(path);
        BufferedWriter bw = null;
        boolean flag = true;
        try
        {
            if (!file.exists()) {
                flag = file.createNewFile();
            }
            if (flag)
            {
                FileOutputStream fos = new FileOutputStream(file, true);
                OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
                bw = new BufferedWriter(osw);
//                bw.newLine();
                bw.write(level + exception.getMessage() + "\n");
                for (StackTraceElement element : exception.getStackTrace()) {
                    bw.write("\t" + element.toString() + "\n");
                }
                bw.flush();
            }
        }
        catch (FileNotFoundException localFileNotFoundException)
        {
            System.out.println("invalid file");
            try
            {
                if (bw != null) {
                    bw.close();
                }
            }
            catch (IOException localIOException1) {}
            try
            {
                if (bw != null) {
                    bw.close();
                }
            }
            catch (IOException localIOException2) {}
        }
        catch (Exception e)
        {
            //            System.out.println("Connection error " + e.getMessage());
            Debug.log(e);
            try
            {
                if (bw != null) {
                    bw.close();
                }
            }
            catch (IOException localIOException3) {}
            try
            {
                if (bw != null) {
                    bw.close();
                }
            }
            catch (IOException localIOException4) {}
        }
        finally
        {
            try
            {
                if (bw != null) {
                    bw.close();
                }
            }
            catch (IOException localIOException5) {}
        }
    }

    public static String getCurrentDate()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        return sdf.format(date);
    }

    public static String buildLogMessage(String[] msg)
    {
        String massage = null;
        if (msg.length > 0)
        {
            StringBuffer msgBuffer = new StringBuffer();
            String[] arrayOfString = msg;
            int j = msg.length;
            for (int i = 0; i < j; i++)
            {
                String message = arrayOfString[i];

                msgBuffer.append(message);
            }
            massage = msgBuffer.toString();
        }
        else
        {
            massage = "";
        }
        return massage;
    }

    public static boolean isEnablelog()
    {
        return true;
    }
}
