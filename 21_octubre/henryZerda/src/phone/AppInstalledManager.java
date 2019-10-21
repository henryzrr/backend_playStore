package phone;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class AppInstalledManager {
    private File installedAppDB;
    public AppInstalledManager(){
        installedAppDB = new File("src/phone/bd.installedApps");
    }
    public List<String> getInstalledApps(){
        List<String> installedApps=null;
        try {
            installedApps= new LinkedList<>();
            Scanner reader = new Scanner(installedAppDB);
            while(reader.hasNextLine()){
                installedApps.add(reader.nextLine());
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

    }

}
