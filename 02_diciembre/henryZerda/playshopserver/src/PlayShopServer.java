import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class PlayShopServer {
    private RecursosPlayShopCliente recursosPlayShopCliente;
    private RecursosPlayShopManager recursosPlayShopManager;
    private ServiciosPlayShopServer serviciosPlayShopServer;

    private final int PUERTO = 5000;
    private final ServerSocket serverSocket;
    private Socket clientSocket;
    private DataOutputStream salida;
    private DataInputStream entrada;

    public PlayShopServer(String DB_LOCATION) throws Exception{
        serverSocket = new ServerSocket(PUERTO);

        AppManager appManager = new AppORMforPlainTextDB(DB_LOCATION);
        serviciosPlayShopServer=new ServiciosPlayShopServer(appManager);
        recursosPlayShopCliente=new RecursosPlayShopCliente(serviciosPlayShopServer);
        recursosPlayShopManager = new RecursosPlayShopManager(serviciosPlayShopServer);
    }

    public void initServer() throws IOException {
        String request="";
        String response="";
        while(true){
            clientSocket = serverSocket.accept();
            entrada = new DataInputStream(clientSocket.getInputStream());
            salida = new DataOutputStream(clientSocket.getOutputStream());
            request = entrada.readUTF();
            response=procesarPeticion(request);
            salida.writeUTF(response);
            clientSocket.close();
        }
    }

    private String procesarPeticion(String request) {
        String [] route = request.split("/");
        if(route[0].equals("phone")){
            return getRecursosPhone(route);
        }else{
            return getRecursosManager(route);
        }
    }

    private String getRecursosManager(String  [] route) {
        if (route.length==2){
            return recursosPlayShopManager.getAllAvailableApps();
        }else if(route[2].charAt(0)=='{'){
            return recursosPlayShopManager.addApp(route[2]);
        }else{
            return recursosPlayShopManager.getAppByName(route[2]);
        }
    }

    private String getRecursosPhone(String [] route) {
        if(route.length==2){
            return recursosPlayShopCliente.getApps();
        }else{
            return recursosPlayShopCliente.getAppByName(route[2]);
        }
    }

    public RecursosPlayShopCliente getRecursosPlayShopCliente() {
        return recursosPlayShopCliente;
    }

    public RecursosPlayShopManager getRecursosPlayShopManager() {
        return recursosPlayShopManager;
    }
}
