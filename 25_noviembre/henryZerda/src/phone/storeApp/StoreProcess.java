package phone.storeApp;

import main.Main;
import phone.PhoneKernel;
import phone.PhoneProcess;
import playshopserver.RecursosPlayShopCliente;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StoreProcess extends PhoneProcess{
    private RecursosPlayShopCliente recursosPlayShopCliente;

    public StoreProcess(PhoneKernel phoneKernel) {
        super(phoneKernel);
        recursosPlayShopCliente= Main.getRecursosCliente();
    }

    @Override
    public void showScreen() {
        StoreScreenContent content = new StoreScreenContent(this);
        phoneKernel.drawNewContent(content.getContent(),content.getMenu());
    }


    public void updateApp(String appName) {
        String [] app = recursosPlayShopCliente.getAppByName(appName).split(",");
        phoneKernel.updateApp(app[0],app[1]);
        showScreen();
    }

    public void uninstallApp(String appName) {
        phoneKernel.uninstallApp(appName);
        showScreen();
    }

    public void installApp(String appName){
        String[]app = recursosPlayShopCliente.getAppByName(appName).split(",");
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
        List<String> apps = recursosPlayShopCliente.getApps();
        List<String [] > formatedApps = new LinkedList<>();

        for (String s: apps
             ) {
            formatedApps.add(s.split(","));
        }
        return formatedApps;
    }

    public void getDefaultPhoneScreen(){
        phoneKernel.setDefaultScreen();
    }

}
