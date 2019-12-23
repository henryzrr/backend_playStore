package phone;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class NotificationManager {
    private File dbNotification;
    public NotificationManager(){
        dbNotification = new File("db_notifications");
    }

    public Set<String>getNotifications(){
        return getSavedNotifications();
    }
    public void deleteNotification(String notification){
        List<String> n = new LinkedList<>();
        try {
            Scanner reader = new Scanner(dbNotification);
            while(reader.hasNextLine()){
                String line = reader.nextLine();
                if(!notification.equals(line)){
                    n.add(line);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        writeDB(n);
    }
    private Set<String> getSavedNotifications(){
        Set<String> n = new HashSet<>();
        try {
            Scanner reader = new Scanner(dbNotification);
            while(reader.hasNextLine()){
                String line = reader.nextLine();
                n.add(line);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return  n;
    }
    private void saveNotification(String notification){
        try {
            FileWriter fr = new FileWriter(dbNotification,true);
            BufferedWriter writer = new BufferedWriter(fr);
            writer.write(notification);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void writeDB(List<String>apps){
        FileWriter fr = null;
        try {
            fr = new FileWriter(dbNotification);
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
    public void saveNotifications(Set<String> notifications){
        notifications.addAll(getSavedNotifications());
        writeDB(notifications.stream().collect(Collectors.toList()));
    }
}
