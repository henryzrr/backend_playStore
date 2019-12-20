package playshopServer;

import com.google.gson.Gson;

import java.util.LinkedList;
import java.util.List;

public class RecursosPlayShopServer {
    private ServiciosPlayShopServer serviciosPlayShopServer;

    public RecursosPlayShopServer(ServiciosPlayShopServer serviciosPlayShopServer) {
        this.serviciosPlayShopServer = serviciosPlayShopServer;
    }

    public String getAllAvailableApps(){
        List<App> apps;
        apps = serviciosPlayShopServer.getAllApps();
        Response response = new Response(200,apps);
        return (new Gson()).toJson(response);
    }

    public String addApp(String jsonApp) {
        App app = (new Gson()).fromJson(jsonApp,App.class);
        App auxApp = serviciosPlayShopServer.getAppByName(app.getName());
        try {
            if(auxApp==null){
                serviciosPlayShopServer.newApp(app);
            }else{
                if(!isValidUpdate(auxApp.getVersion(),app.getVersion()))
                    return errorResponse();
                serviciosPlayShopServer.updateApp(app);
            }
            Response response=new Response(200,new LinkedList<>());
            return (new Gson()).toJson(response);
        }catch (Exception e){
            return  errorResponse();
        }

    }

    private boolean isValidUpdate(String oldAppVersion,String newAppVersion){
        if(Double.parseDouble(newAppVersion)>=Double.parseDouble(oldAppVersion)){
            return true;
        }
        return false;
    }

    public String errorResponse() {
        Response response = new Response(400,new LinkedList<>());
        return (new Gson()).toJson(response);
    }


}
