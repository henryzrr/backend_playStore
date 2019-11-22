package playshopserver;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class AppManager {
    private static  AppManager appManager = new AppManager();
    private File db;
    private File key;
    private AppManager(){
        db = new File("src/playshopserver/apps");
        key = new File("src/playshopserver/key");
        createKey();
    }

    private void createKey() {
        try {
            FileWriter fr = new FileWriter(key);
            fr.write("1");
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static AppManager getInstance(){
        return appManager;
    }
    public List<String[]> getAvailableApps(){
        List<String[]> apps=new LinkedList<>();
        try {
            Scanner sc = new Scanner(db);
            while (sc.hasNextLine()){
                StringTokenizer tokenizer = new StringTokenizer(sc.nextLine(),",");
                apps.add(new String[]{tokenizer.nextToken(),tokenizer.nextToken()});
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return apps;
    }
    public void setApp(String appName,String version){
        List<String> ls = new LinkedList<>();
        boolean added=false;
        try {
            Scanner sc = new Scanner(db);
            String line,stname,stversion;
            while (sc.hasNextLine()){
                line = sc.nextLine();
                StringTokenizer st = new StringTokenizer(line,",");
                stname=st.nextToken();
                stversion=st.nextToken();
                if(!stname.equals(appName)){
                    ls.add(line);
                }else{
                    ls.add(appName+","+version);
                    if(Integer.parseInt(stversion)!=Integer.parseInt(version)){
                        updateKey();
                    }
                    added=true;
                }
            }
            sc.close();
            if(!added){
                ls.add(appName+","+version);
                updateKey();
            }
            FileWriter fileWriter = new FileWriter(db);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (String app:ls
                 ) {
                bufferedWriter.write(app+"\n");
            }
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void updateKey() {
        try {
            Scanner sc = new Scanner(key);
            int n = sc.nextInt()+1;
            sc.close();
            FileWriter fr = new FileWriter(key);
            fr.write(n+"");
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public int getKeyAvailableApps() {
        try {
            Scanner sc = new Scanner(key);
            return sc.nextInt();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String[] getApp(String appName){
        try {
            Scanner sc = new Scanner(db);
            String line;
            while (sc.hasNextLine()){
                line =sc.nextLine();
                if (line.contains(appName)){
                    StringTokenizer tokenizer = new StringTokenizer(line,",");
                    sc.close();
                    return new String[]{tokenizer.nextToken(),tokenizer.nextToken()};
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
