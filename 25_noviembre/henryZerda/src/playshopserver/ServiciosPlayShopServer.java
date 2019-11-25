package playshopserver;

import java.io.IOException;
import java.util.List;

public class ServiciosPlayShopServer {
    private PlainTextDBManager dbManager;

    public ServiciosPlayShopServer(PlainTextDBManager dbManager) {
        this.dbManager = dbManager;
    }

    public String getAppByName(String name){
        return dbManager.findOne(name);
    }
    public List<String> getAllAvailableApps(){
        return dbManager.find("activa");
    }
    public void addApp(String app) throws IOException {
        dbManager.insert(app);
    }
    public void updateApp(String newApp,String name) throws IOException {
        dbManager.update(newApp,name);
    }

    public void installApp(String app) throws IOException {
        dbManager.insert(app);
    }
}
