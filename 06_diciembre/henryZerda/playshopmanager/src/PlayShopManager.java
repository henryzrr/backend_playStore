

import java.io.IOException;
import java.util.*;

public class PlayShopManager {
    private ScreenManager screenManager;
    private ContentManager contentManager;
    public PlayShopManager() throws IOException {
        screenManager = new ScreenManager();
        contentManager = new ContentManager(this);
        screenManager.setNewContent(contentManager.generateMainContent(getAllApps()),contentManager.generateMainMenu());
    }

    public List<App> getAllApps() throws IOException {
        List<App> apps=new LinkedList<>();
        Request request = new Request();
        String response = request.doRequest("GET");
        Response r = new Response(response);
        String [] lines  = r.getData().split("},");
        for (String s: lines
             ) {
            apps.add(new App(s));
        }
        return  apps;
    }

    public String actualizarApp(App app) throws IOException {
        Request request = new Request();
        String response = request.doRequest("PUT:apps/"+app.getName()+"/"+app.toPutJson());
        return  response;

    }

    public String instalarApp(String nombreApp) {
        Request request = null;
        try {
            request = new Request();
            String response = request.doRequest("POST:apps/"+nombreApp);
            return  response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public App getApp(String nombreApp)  {
        Request request = null;
        try {
            request = new Request();
            /*String response = request.doRequest("GET:apps/"+nombreApp);
            return  new App(response);*/
            return null;
        } catch (IOException e) {
            return null;
        }

    }

    public ScreenManager getScreenManager(){
        return screenManager;
    }
}
