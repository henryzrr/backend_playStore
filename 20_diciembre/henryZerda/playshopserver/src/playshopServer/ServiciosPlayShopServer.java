package playshopServer;

import java.io.IOException;
import java.util.List;

public class ServiciosPlayShopServer {
    private AppManager appManager;

    public ServiciosPlayShopServer(AppManager appManager) {
        this.appManager = appManager;
    }

    public App getAppByName(String name){
        return appManager.findOne(name);
    }
    public List<App> getAllAvailableApps(){
        return appManager.findPatternByColumn("true",2);
    }
    public void newApp(App app) throws IOException {
        appManager.add(app);
    }
    public void updateApp(App newApp) throws IOException {
        appManager.update(newApp);
    }
    public List<App> getAllApps(){
        return appManager.getAll();
    }
}
