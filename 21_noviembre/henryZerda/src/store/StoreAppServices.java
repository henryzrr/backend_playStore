package store;

import java.util.List;

public class StoreAppServices {
    private static StoreAppServices storeAppServices = new StoreAppServices();
    private  AppManager appManager;
    private StoreAppServices(){
        appManager = AppManager.getInstance();
    }
    public static StoreAppServices getInstance(){
        return storeAppServices;
    }
    public List<String[]> getAvailableServices(){
        return appManager.getAvailableApps();
    }
    public String[] getApp(String appName){
        return appManager.getApp(appName);
    }
    public int getKeyAvailableApps(){
        return appManager.getKeyAvailableApps();
    }
}
