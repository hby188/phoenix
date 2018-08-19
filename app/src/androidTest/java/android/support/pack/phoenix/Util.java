package android.support.pack.phoenix;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Util {
    public static String getFile(String fileName) throws Exception {
        String str="";
        InputStreamReader isr = new InputStreamReader(new FileInputStream(fileName), "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        //BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line = br.readLine();
        while(line!=null) {
            line = line.replace("\uFEFF", "");
            line = line.replace("\uFFFE", "");

            if (!line.trim().startsWith("#")) {
                str += line + "\n";
            }
            line = br.readLine();
        }
        br.close();
        return str;
    }

    public static boolean isIn(String[] ss, String s) {

        for (int i=0;i<ss.length;i++) {
            if (s.equals(ss[i])) {
                return true;
            }
        }
        return false;
    }


    public static String getJsonValue(JSONObject stepJsonObject, String name) throws Exception {
        String value = null;
        if(!stepJsonObject.isNull(name)) {
            value = stepJsonObject.getString(name);
        }
        return value;
    }
    public static int getJsonValueInt(JSONObject stepJsonObject, String name, int defaultValue) throws Exception {
        int value = defaultValue ;
        if(!stepJsonObject.isNull(name)) {
            value = stepJsonObject.getInt(name);
        }
        return value;
    }
    public static String getJsonValueStr(JSONObject stepJsonObject, String name, String defaultValue) throws Exception {
        String value = defaultValue ;
        if(!stepJsonObject.isNull(name)) {
            value = stepJsonObject.getString(name);
        }
        return value;
    }

    public static void copyFile(String name, String sourceFolder, String targetFolder) {
        File source = new File(sourceFolder+"/" + name);
        File target = new File(targetFolder+"/" + name);
        if(target.exists()) {
            target.delete();
        }
        copyFile(source, target);
    }

    public static void copyFiles(String sourceFolder, String targetFolder) {
        File source = new File(sourceFolder);
        File[] files = source.listFiles();
        for (int i=0;i<files.length;i++) {
            File inFile = new File(sourceFolder + "/" + files[i].getName());
            File outFile = new File(targetFolder + "/" + files[i].getName());
            copyFile (inFile, outFile);
        }
    }

    public static void copyFile(File f1, File f2) {
        byte[] buf = new byte[1024];
        try {
            FileInputStream inFile = new FileInputStream(f1);
            FileOutputStream toFile = new FileOutputStream(f2);
            int len = inFile.read(buf);
            while(len>0) {
                toFile.write(buf, 0, len);
                len = inFile.read(buf);
            }
            inFile.close();
            toFile.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static String getCurrentTimeString() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        return formatter.format(new Date());

    }

    public static String getCurrentTimeLogging() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return formatter.format(new Date());
    }

    public static String getCurrentTimeLogging(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return formatter.format(new Date(time));
    }

    public static ArrayList<String> getFiles(String path) {
        ArrayList<String> fs = new ArrayList<String>();
        File f = new File(path);
        File[] sfs = f.listFiles();
        for (int i=0;i<sfs.length;i++) {
            fs.add(sfs[i].getName());
        }
        return fs;
    }

    public static int calRounds(int qTimeSec, int eTimeSec, int minIntervals) {
        return (qTimeSec - minIntervals)/eTimeSec;
    }

    public static int getRounds (int all, int speed) {
        int rounds = all/speed;
        if (all%speed!=0) {
            rounds ++;
        }
        return rounds;
    }

    public static String getNoneNullString(String str) {
        if (str==null) {
            return "";
        }
        return str;
    }

    public static String stackStrings(Exception e) {
        StringWriter sw = new StringWriter();
        BufferedWriter bw = new BufferedWriter(sw);
        PrintWriter pw = new PrintWriter(bw);
        e.printStackTrace(pw);
        return sw.toString();
    }

    public static void main(String[]args) {
        System.out.println(getRounds(44,5));
    }

}
