package phone.storeApp;

import phone.PhoneKernel;
import phone.PhoneProcess;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StoreProcess extends PhoneProcess{

    public StoreProcess(PhoneKernel phoneKernel) {
        super(phoneKernel);
    }

    @Override
    public void showScreen() {
        StoreScreenContent content = new StoreScreenContent(this);
        phoneKernel.drawNewContent(content.getContent(),content.getMenu());
    }


    public void updateApp(String appName) {
        try {
            Request request = new Request();
            String response = request.requestGet("phone/apps/"+appName);
            App app = new App(getDataFromResponse(response));
            phoneKernel.updateApp(app.getNombre(),app.getVersion());
            showScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void uninstallApp(String appName) {
        phoneKernel.uninstallApp(appName);
        showScreen();
    }

    public void installApp(String appName){
        try {
            Request request = new Request();
            String response = request.requestGet("phone/apps/"+appName);
            App app = new App(getDataFromResponse(response));
            phoneKernel.installApp(app.getNombre(),app.getVersion());
            showScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String getDataFromResponse(String response) {
        int indexData=response.indexOf("data")+8;
        return response.substring(indexData,response.length()-2);
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
        try {
            Request request = new Request();
            String response = request.requestGet("phone/apps");
            System.out.println(response);
            String data = getDataFromResponse(response);
            String [] apps = data.split("}");

            List<String [] > formatedApps = new LinkedList<>();

            for (String s: apps
            ) {
                App app= new App(s);
                String [] a = new String[]{app.getNombre(),app.getVersion()};
                formatedApps.add(a);
            }
            return formatedApps;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void getDefaultPhoneScreen(){
        phoneKernel.setDefaultScreen();
    }

}
