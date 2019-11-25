package playshopserver;

import java.io.IOException;
import java.util.List;

public class RecursosPlayShopManager {
    private ServiciosPlayShopServer serviciosPlayShopServer;

    public RecursosPlayShopManager(ServiciosPlayShopServer serviciosPlayShopServer) {
        this.serviciosPlayShopServer = serviciosPlayShopServer;
    }

    public List<String> getAllAvailableApps(){
        return serviciosPlayShopServer.getAllAvailableApps();
    }

    public String addApp(String app) throws IOException {
        String [] appAux = app.split(",");
        String oldApp =serviciosPlayShopServer.getAppByName(appAux[0]);
        if(oldApp.equals("")){
            if(Double.parseDouble(appAux[1])==1.0){
                serviciosPlayShopServer.addApp(app);
                return "RECURSO CREADO";
            }else{
                return "TODO RECURSO NUEVO DEBE EMPEZAR CON LA VERSION 1.0";
            }
        }
        if(isValidUpdate(oldApp,app)){
            try {
                serviciosPlayShopServer.updateApp(app,appAux[0]);
                return "RECURSO_CREADO";
            }catch (Exception e){
                return "ERROR"+e.getMessage();
            }
        }
        return "ERROR RECURSO_NO_ENCONTRADO";
    }

    private boolean isValidUpdate(String oldApp,String newApp){
        String[]old=oldApp.split(",");
        String[]nuevo = newApp.split(",");
        if(Double.parseDouble(nuevo[1])>Double.parseDouble(old[1])){
            return true;
        }
        return false;
    }
 }
