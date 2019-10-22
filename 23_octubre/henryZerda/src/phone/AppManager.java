package phone;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class AppManager {
    private File installedAppDB;
    public AppManager(){
        installedAppDB = new File("src/phone/dbInstalledApp");
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
    public void installApp(String appName){
        try {
            FileWriter fr = new FileWriter(installedAppDB);
            BufferedWriter writer = new BufferedWriter(fr);
            writer.append(appName);
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
            FileWriter fr = new FileWriter(installedAppDB);
            BufferedWriter writer = new BufferedWriter(fr);
            writer.write("");
            for (String line:apps
                 ) {
                writer.append(line);
            }
            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
