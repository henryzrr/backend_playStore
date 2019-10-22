package storeApp;

import phone.PhoneKernel;
import phone.PhoneProcess;
import store.StoreServices;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatitoStoreProcess extends PhoneProcess {
    private StoreServices storeServices;
    public PatitoStoreProcess(PhoneKernel phoneKernel) {
        super(phoneKernel);
        storeServices = StoreServices.getInstance();
    }

    @Override
    public void showScreen() {
        PatitoStoreScreenContent content = new PatitoStoreScreenContent(this);
        phoneKernel.drawNewContent(content.getContent(),content.getMenu());
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
        return storeServices.getAvailableServices();
    }

    public void getDefaultPhoneScreen(){
        phoneKernel.setDefaultScreen();
    }


}
