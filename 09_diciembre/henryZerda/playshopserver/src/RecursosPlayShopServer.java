
import java.io.IOException;
import java.util.List;

public class RecursosPlayShopServer {
    private ServiciosPlayShopServer serviciosPlayShopServer;

    public RecursosPlayShopServer(ServiciosPlayShopServer serviciosPlayShopServer) {
        this.serviciosPlayShopServer = serviciosPlayShopServer;
    }

    public String getAllAvailableApps(){

        String data =  listToJson(serviciosPlayShopServer.getAllApps());
        Response response = new Response(200,data);
        return response.toJson();
    }
    public String getAppByName(String name){
        App app = serviciosPlayShopServer.getAppByName(name);
        if(app!=null)
            return app.toJson();
        else
            return  "404";
    }

    public String addApp(String jsonApp) {
        App app = new App(jsonApp);
        App auxApp = serviciosPlayShopServer.getAppByName(app.getName());
        try {
            if(auxApp==null){
                serviciosPlayShopServer.newApp(app);
            }else{
                if(!isValidUpdate(auxApp.getVersion(),app.getVersion()))
                    return errorResponse();
                serviciosPlayShopServer.updateApp(app);
            }
            return (new Response(200,"[]")).toJson();
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
    private String listToJson(List<App> apps){
        String json="[";
        boolean primero=true;
        for (App app:apps
             ) {
            if(!primero){
                json+=",";
            }else{
                primero=false;
            }
            json+=app.toJson();
        }
        json+="]";
        return json;
    }

    public String errorResponse() {
        return (new Response(400,"[]").toJson());
    }


}
