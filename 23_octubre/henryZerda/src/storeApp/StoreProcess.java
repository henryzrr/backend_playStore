package storeApp;

import phone.PhoneKernel;
import phone.PhoneProcess;
import store.StoreAppServices;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoreProcess extends PhoneProcess implements Runnable{
    private StoreAppServices storeAppServices;
    private  Thread thread;
    private int keyAvailableApps;
    public StoreProcess(PhoneKernel phoneKernel) {
        super(phoneKernel);
        storeAppServices = StoreAppServices.getInstance();
        thread = new Thread(this);
        thread.start();
        thread.suspend();
    }

    @Override
    public void showScreen() {
        StoreScreenContent content = new StoreScreenContent(this);
        phoneKernel.drawNewContent(content.getContent(),content.getMenu());
        keyAvailableApps = storeAppServices.getKeyAvailableApps();
        thread.resume();
    }


    public void updateApp(String appName) {
        String[] app = storeAppServices.getApp(appName);
        phoneKernel.updateApp(app[0],app[1]);
        showScreen();
    }

    public void uninstallApp(String appName) {
        phoneKernel.uninstallApp(appName);
        showScreen();
    }

    public void installApp(String appName){
        String[]app = storeAppServices.getApp(appName);
        phoneKernel.installApp(app[0],app[1]);
        showScreen();
    }

    public Map<String,String> getInstalledApps(){
        List<String[]>kernel = phoneKernel.getInstalledApps();
        Map<String,String> apps = new HashMap<>();
        for (String[]app:kernel
             ) {
            apps.put(app[0],app[1]);
        }
        return apps;
    }
    public List<String[]>getAvailableApps(){
        return storeAppServices.getAvailableServices();
    }

    public void getDefaultPhoneScreen(){
        thread.suspend();
        phoneKernel.setDefaultScreen();
    }


    @Override
    public void run() {
        int key;
        while (true){
            try {
                Thread.sleep(5000);
                key=storeAppServices.getKeyAvailableApps();
                if(key!=keyAvailableApps){
                    JOptionPane.showMessageDialog(phoneKernel.getContainer(),"Nuevas aplicaciones disponibles");
                    showScreen();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
