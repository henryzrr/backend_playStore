

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
        String response = request.requestGet("manager/apps");
        Response r = new Response(response);
        String [] lines  = r.getData().split("}");
        for (String s: lines
             ) {
            apps.add(new App(s));
        }
        return  apps;
    }

    public Response actualizarApp(App app) throws IOException {
        Request request = new Request();
        String response = request.requestGet("manager/apps/"+app.toJson());
        Response r = new Response(response);
        return  r;

    }

    public Response instalarApp(App app) throws IOException {
        return  actualizarApp( app);
    }
    public Response deshabilitarApp(App app) throws IOException {
        return actualizarApp(app);
    }
    public Response buscarApps(String patron) throws IOException {
        Request request = new Request();
        String response = request.requestGet("manager/apps/"+patron);
        Response r = new Response(response);
        return  r;
    }


}
