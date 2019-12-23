

import com.google.gson.Gson;

import java.io.IOException;
import java.util.*;

public class PlayShopManager {
    private ScreenManager screenManager;
    private ContentManager contentManager;
    private HashMap<String,App> availableApps;
    public PlayShopManager() throws IOException {
        screenManager = new ScreenManager();
        contentManager = new ContentManager(this);
        screenManager.setNewContent(contentManager.generateMainContent(getAllApps()),contentManager.generateMainMenu());
    }

    public List<App> getAllApps() throws IOException {

        Request request = new Request();
        String response = request.doRequest("GET");
        Response r = (new Gson()).fromJson(response,Response.class);
        availableApps = new HashMap<>();
        for (App a:r.getData()
             ) {
            availableApps.put(a.getName(),a);
        }
        return  r.getData();

    }

    public boolean actualizarApp(App app)  {
        App aux = availableApps.get(app.getName());
        Response response;
        if(isValidUpdate(aux.getVersion(),app.getVersion())) {
            Request request = new Request();
            String r = request.doRequest("POST/" + (new Gson()).toJson(app));
            response = (new Gson()).fromJson(r,Response.class);
            return response.getStatus()==200;
        }
        return false;
    }

    public boolean instalarApp(String nombreApp)  {
        App aux = availableApps.get(nombreApp);
        if(aux==null){
            App app = new App(nombreApp,"1.0","true");
            Request request = new Request();
            String r = request.doRequest("POST/" + (new Gson()).toJson(app));
            Response response = (new Gson()).fromJson(r,Response.class);
            return response.getStatus()==200;
        }
        return false;
    }


    private boolean isValidUpdate(String oldAppVersion,String newAppVersion){
        return (Double.parseDouble(newAppVersion)>=Double.parseDouble(oldAppVersion));
    }

    public ScreenManager getScreenManager(){
        return screenManager;
    }
}
