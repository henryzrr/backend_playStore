package phone;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class AppManager {
    private File installedAppDB;
    public AppManager(){
        installedAppDB = new File("db_phone");
    }

    public List<String[]> getInstalledApps(){
        List<String[]> installedApps=null;
        try {
            installedApps= new LinkedList<>();
            Scanner reader = new Scanner(installedAppDB);
            while(reader.hasNextLine()){
                String line = reader.nextLine();
                StringTokenizer tokenizer = new StringTokenizer(line,",");
                installedApps.add(new String[]{tokenizer.nextToken(),tokenizer.nextToken()});
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return  installedApps;
    }
    public void installApp(String appName,String version){
        try {
            FileWriter fr = new FileWriter(installedAppDB,true);
            BufferedWriter writer = new BufferedWriter(fr);
            writer.write(appName+","+version+"\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void uninstallApp(String appName){
        try {
            Scanner reader = new Scanner(installedAppDB);
            List<String> apps = new LinkedList<>();
            while(reader.hasNextLine()){
                String app = reader.nextLine();
                if(!app.contains(appName)){
                    apps.add(app);
                }
            }
            writeDB(apps);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void updateApp(String appName,String version){
        try {
            Scanner reader = new Scanner(installedAppDB);
            List<String> apps = new LinkedList<>();
            while(reader.hasNextLine()){
                String app = reader.nextLine();
                if(app.contains(appName)){
                    apps.add(appName+","+version);
                }else {
                    apps.add(app);
                }
            }
            writeDB(apps);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void writeDB(List<String>apps){
        FileWriter fr = null;
        try {
            fr = new FileWriter(installedAppDB);
            BufferedWriter writer = new BufferedWriter(fr);
            for (String line:apps
            ) {
                writer.write(line+"\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
