
import java.io.IOException;
import java.util.List;

public class RecursosPlayShopManager {
    private ServiciosPlayShopServer serviciosPlayShopServer;

    public RecursosPlayShopManager(ServiciosPlayShopServer serviciosPlayShopServer) {
        this.serviciosPlayShopServer = serviciosPlayShopServer;
    }

    public String getAllAvailableApps(){
        String data =  listToJson(serviciosPlayShopServer.getAllAvailableApps());
        JsonResponse response = new JsonResponse(true,"RECURSOS OBTENIDOS CON ÉXITO",data);
        return response.toJson();
    }
    public String getAppByName(String name){
        App app = serviciosPlayShopServer.getAppByName(name);
        JsonResponse response;
        if(app==null){
            response = new JsonResponse(false,"LA APP SOLICITADA NO EXISTE","");
        }else{
            response = new JsonResponse(true,"RECURSO OBTENIDO CON EXITO",app.toJson());
        }
        return response.toJson();
    }

    public String addApp(String appJson)  {
        System.out.println(appJson);
        App app = new App(appJson);
        JsonResponse response;
        try {
            App oldApp = serviciosPlayShopServer.getAppByName(app.getNombre());
            if (oldApp == null) {
                if (Double.parseDouble(app.getVersion()) == 1.0) {
                    serviciosPlayShopServer.addApp(app);
                    response = new JsonResponse(true,"RECURSO CREADO","");
                } else {
                    response = new JsonResponse(false,"TODA VERSION INICIAL DE CADA APP DEBE INICIAR EN 1.0","");
                }
            } else{
                if (isValidUpdate(oldApp.getVersion(), app.getVersion())) {
                    serviciosPlayShopServer.updateApp(app);
                    response = new JsonResponse(true,"RECURSO ACTUALIZADO","");
                }else {
                    response = new JsonResponse(false,"VERSION DE RECURSO INVALIDA","");
                }
            }
        }catch (Exception e){
            response = new JsonResponse(false,"ERROR EN BASE DE DATOS","");
        }
        return response.toJson();
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
