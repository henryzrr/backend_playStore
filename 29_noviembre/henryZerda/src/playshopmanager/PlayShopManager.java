package playshopmanager;

import main.Main;
import playshopserver.RecursosPlayShopManager;

import java.io.IOException;
import java.util.*;

public class PlayShopManager {
    private ScreenManager screenManager;
    private RecursosPlayShopManager recursosPlayShopManager;
    private Map<String,App> apps;
    private ContentManager contentManager;
    public PlayShopManager() {
        recursosPlayShopManager= Main.getRecursosPlayShopManager();
        apps = getAppsFromServer();
        screenManager = new ScreenManager();
        contentManager = new ContentManager(this);
        screenManager.setNewContent(contentManager.generateMainContent(getAllApps()),contentManager.generateMainMenu());
    }

    private Map<String, App> getAppsFromServer() {
        List<String> recursos = recursosPlayShopManager.getAllAvailableApps();
        return stringToApp(recursos);
    }

    private Map<String, App> stringToApp(List<String> recursos) {
        Map<String, App> apps = new HashMap<>();
        for (String recurso: recursos
             ) {
            String [] params = recurso.split(",");
            apps.put(params[0],new App(params[0],params[1],params[2]));
        }
        return apps;
    }

    public boolean actualizarApp(App app){
        App a = apps.get(app.getNombre());
        double v1=Double.parseDouble(a.getVersion());
        double v2=Double.parseDouble(app.getVersion());
        if(v2>v1){
            apps.put(app.getNombre(),app);
            String data = appToString(app);
            try {
                recursosPlayShopManager.addApp(data);
            } catch (IOException e) {
                return false;
            }
            return true;
        }else{
            return false;
        }
    }

    private String appToString(App app) {
        return app.getNombre()+app.getVersion()+app.getActiva();
    }

    public boolean instalarApp(App app){
        if(apps.containsKey(app.getNombre())){
            return false;
        }else{
            apps.put(app.getNombre(),app);
            String data = appToString(app);
            try {
                recursosPlayShopManager.addApp(data);
                return true;
            } catch (IOException e) {
                return false;
            }
        }
    }
    public boolean deshabilitarApp(App app){
        try {
            String data = appToString(app);
            recursosPlayShopManager.addApp(data);
            apps.put(app.getNombre(),app);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    public List<App> buscarApps(String patron){
        List<App> lsapps= new LinkedList<>();
        for (App app: this.apps.values()
             ) {
            if(app.getNombre().contains(patron)){
                lsapps.add(app);
            }
        }
        return  lsapps;
    }

    private List<App> getAllApps(){
        List<App> aps=new LinkedList<>();
        for (App app:apps.values()
             ) {
            aps.add(app);
        }
        return aps;
    }

}
