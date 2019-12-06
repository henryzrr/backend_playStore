
import java.io.IOException;
import java.util.List;

public class RecursosPlayShopManager {
    private ServiciosPlayShopServer serviciosPlayShopServer;

    public RecursosPlayShopManager(ServiciosPlayShopServer serviciosPlayShopServer) {
        this.serviciosPlayShopServer = serviciosPlayShopServer;
    }

    public String getAllAvailableApps(){

        String data =  listToJson(serviciosPlayShopServer.getAllAvailableApps());
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
    public String newApp(String name)  {
        try {
            App aux = serviciosPlayShopServer.getAppByName(name);
            if(aux!=null){
                return "204";
            }
            serviciosPlayShopServer.addApp(new App(name,"1.0","true"));
            return "201";
        } catch (IOException e) {
            return "500";
        }

    }
    public String updateApp(App app)  {
        App aux = serviciosPlayShopServer.getAppByName(app.getName());
        if(aux==null){
            try {
                serviciosPlayShopServer.addApp(app);
            } catch (IOException e) {
                return "500";
            }
            return "200";
        }else{
            return "404";
        }
    }


    private boolean isValidUpdate(String oldAppVersion,String newAppVersion){
        if(Double.parseDouble(newAppVersion)>Double.parseDouble(oldAppVersion)){
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
 }
