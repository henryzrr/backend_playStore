package storeService;

import java.util.List;

public class StoreAppServices {
    private AvailableStoreAppManager appsManager;
    public StoreAppServices(){
         appsManager=new AvailableStoreAppManager();
    }
    public List<String> getAvailableAppsName(){
        return appsManager.getAllAvailableAppNames();
    }
    public MyAppInterface getMyAppManager(String appName){
        return appsManager.getMyAppInterface(appName);
    }
}
