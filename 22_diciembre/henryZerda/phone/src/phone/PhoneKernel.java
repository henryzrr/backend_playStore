package phone;

import phone.storeApp.StoreProcess;

import javax.swing.*;
import java.util.*;

public class PhoneKernel {
    private ScreenManager screenManager;
    private AppManager appManager;
    private Map<String,PhoneProcess> processes;
    private NotificationManager notificationManager;
    public PhoneKernel(){
        screenManager = new ScreenManager();
        appManager = new AppManager();
        processes = getProcesses(appManager.getInstalledApps());
        notificationManager = new NotificationManager();
        setDefaultScreen();
    }

    private Map<String, PhoneProcess> getProcesses(List<String[]> apps) {
        Map<String,PhoneProcess> processes = new HashMap<>();
        processes.put("storeApp",new StoreProcess(this));
        return  processes;
    }
    public void installApp(String appName,String version){
        appManager.installApp(appName,version);
    }
    public void uninstallApp(String appName){
        appManager.uninstallApp(appName);
    }
    public void updateApp(String appName, String version){
        appManager.updateApp(appName,version);
    }
    public List<String[]> getInstalledApps(){
        return appManager.getInstalledApps();
    }
    public void setDefaultScreen(){
        Set<String> notifications = getAppNotifications();
        notificationManager.saveNotifications(notifications);
        notifications = notificationManager.getNotifications();
        DefaultPhoneScreenContent content = new DefaultPhoneScreenContent(appManager.getInstalledApps(),processes,notifications);
        screenManager.setNewContent(content.getScreenContent(),content.getMenuBar());
    }

    private Set<String> getAppNotifications() {
        Set<String> notifications = new HashSet<>();
        Iterator<PhoneProcess> it = processes.values().iterator();
        while (it.hasNext()){
            PhoneProcess p = it.next();
            notifications.addAll(p.getNotifications());
        }
        return notifications;
    }

    public void drawNewContent(JPanel content, JMenuBar menu){
        screenManager.setNewContent(content,menu);
    }
    public JFrame getContainer(){
        return screenManager.getContainer();
    }
    public void deleteNotification(String notification){
        notificationManager.deleteNotification(notification);
        setDefaultScreen();
    }
}
