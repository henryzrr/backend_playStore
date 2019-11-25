package playshopserver;

import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class RecursosPlayShopCliente {
    private ServiciosPlayShopServer serviciosPlayShopServer;

    public RecursosPlayShopCliente(ServiciosPlayShopServer serviciosPlayShopServer) {
        this.serviciosPlayShopServer = serviciosPlayShopServer;
    }

    public String getAppByName(String name){
        String app = serviciosPlayShopServer.getAppByName(name);
        return formatLineToSend(app);
    }

    public List<String> getApps(){
        List<String>apps = serviciosPlayShopServer.getAllAvailableApps();
        List<String> formattedApp = new LinkedList<>();
        for (String app:apps
             ) {
            formattedApp.add(formatLineToSend(app));
        }
        return formattedApp;
    }
    private String formatLineToSend (String line){
        String [] apps = line.split(",");
        return (apps[0]+","+apps[1]);
    }
}
