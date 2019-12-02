import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class RecursosPlayShopCliente {
    private ServiciosPlayShopServer serviciosPlayShopServer;

    public RecursosPlayShopCliente(ServiciosPlayShopServer serviciosPlayShopServer) {
        this.serviciosPlayShopServer = serviciosPlayShopServer;
    }

    public String getAppByName(String name){
        App app = serviciosPlayShopServer.getAppByName(name);
        JsonResponse response;
        if(app==null){
            response = new JsonResponse(false,"LA APP SOLICITADA NO EXISTE","");
        }else{
            response = new JsonResponse(true,"RECURSO OBTENIDO CON EXITO",(new AppVO(app).toJson()));
        }
        return response.toJson();

    }

    public String getApps(){
        List<App>apps = serviciosPlayShopServer.getAllAvailableApps();
        JsonResponse response = new JsonResponse(true,"RECURSOS OBTENIDOS CON EXITO",listToJson(apps));
        return response.toJson();
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
