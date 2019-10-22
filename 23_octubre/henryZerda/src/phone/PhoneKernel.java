package phone;

import storeApp.PatitoStoreProcess;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneKernel {
    private ScreenManager screenManager;
    private AppManager appManager;
    private Map<String,PhoneProcess> processes;

    public PhoneKernel(){
        screenManager = new ScreenManager();
        appManager = new AppManager();
        processes = getProcesses(appManager.getInstalledApps());
        setDefaultScreen();
    }

    private Map<String, PhoneProcess> getProcesses(List<String[]> apps) {
        Map<String,PhoneProcess> processes = new HashMap<>();
        processes.put("storeApp",new PatitoStoreProcess(this));
        return  processes;
    }
    public void installApp(String appName){
        appManager.installApp(appName);
    }
    public void uninstallApp(String appName){
        appManager.uninstallApp(appName);
    }
    public List<String[]> getInstalledApps(){
        return appManager.getInstalledApps();
    }
    public void setDefaultScreen(){
        DefaultPhoneScreenContent content = new DefaultPhoneScreenContent(appManager.getInstalledApps(),processes);
        screenManager.setNewContent(content.getScreenContent(),content.getMenuBar());
    }
    public void drawNewContent(JPanel content, JMenuBar menu){
        screenManager.setNewContent(content,menu);
    }
    public JFrame getContainer(){
        return screenManager.getContainer();
    }

}
