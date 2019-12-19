package phone.storeApp;

import com.google.gson.Gson;
import phone.PhoneKernel;
import phone.PhoneProcess;

import javax.swing.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StoreProcess extends PhoneProcess{
    private  Map <String,App> serverApps;
    public StoreProcess(PhoneKernel phoneKernel) {
        super(phoneKernel);
        serverApps = new HashMap<>();
    }

    @Override
    public void showScreen() {
        StoreScreenContent content = new StoreScreenContent(this);
        phoneKernel.drawNewContent(content.getContent(),content.getMenu());
    }


    public void updateApp(String appName) {
        Request request = new Request();
        String response = request.doRequest("GET");
        Response r = (new Gson()).fromJson(response,Response.class);
        for (App app:r.getData()
        ) {
            if(app.getName().equals(appName)){
                phoneKernel.updateApp(app.getName(),app.getVersion());
                showScreen();
                break;
            }
        }
    }

    public void uninstallApp(String appName) {
        phoneKernel.uninstallApp(appName);
        showScreen();
    }

    public void installApp(String appName){
        Request request = new Request();
        String response = request.doRequest("GET");
        Response r = (new Gson()).fromJson(response,Response.class);
        for (App app:r.getData()
        ) {
            if(app.getName().equals(appName)){
                phoneKernel.installApp(app.getName(),app.getVersion());
                showScreen();
                break;
            }

        }
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
        Request request = new Request();
        String response = request.doRequest("GET");
        Response r = (new Gson()).fromJson(response,Response.class);

        List<String [] > formatedApps = new LinkedList<>();

        for (App app: r.getData()
        ) {
            if(app.getStatus().equals("false")){
                continue;
            }
            serverApps.put(app.getName(),app);
            String [] a = new String[]{app.getName(),app.getVersion()};
            formatedApps.add(a);
        }
        return formatedApps;
    }

    public void getDefaultPhoneScreen(){
        phoneKernel.setDefaultScreen();
    }

    private boolean isthereNewApps(){
        Request request = new Request();
        String response = request.doRequest("GET");
        Response r = (new Gson()).fromJson(response,Response.class);
        for (App app: r.getData()
        ) {
            if(app.getStatus().equals("false")){
                continue;
            }
            App aux = serverApps.get(app.getName());
            if(aux==null || aux.getVersion().equals( app.getVersion())){
                serverApps.put(app.getName(),app);
                return true;
            }
            serverApps.put(app.getName(),app);
        }
        return false;
    }

    private void sendNotification(){
        JOptionPane.showMessageDialog(phoneKernel.getContainer(),"Hay una Nueva actualizacion");
    }
}
