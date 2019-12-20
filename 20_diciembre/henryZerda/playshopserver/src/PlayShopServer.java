import notificationServer.NotificacionResources;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class PlayShopServer {
    private RecursosPlayShopServer recursosPlayShopServer;
    private ServiciosPlayShopServer serviciosPlayShopServer;
    private NotificacionResources notificacionResources;

    private final int PUERTO = 50000;
    private final ServerSocket serverSocket;
    private Socket clientSocket;
    private DataOutputStream salida;
    private DataInputStream entrada;

    public PlayShopServer(String DB_LOCATION) throws Exception{

        serverSocket = new ServerSocket(PUERTO);
        AppManager appManager = new AppORMforPlainTextDB(DB_LOCATION);
        serviciosPlayShopServer=new ServiciosPlayShopServer(appManager);
        recursosPlayShopServer = new RecursosPlayShopServer(serviciosPlayShopServer);
        System.out.println("Servidor iniciado en el puerto:50000");
        notificacionResources = new NotificacionResources();
        System.out.println("Servidor de notificaciones iniciado");
    }

    public void initServer() throws IOException {
        String request="";
        String response="";
        while(true){
            System.out.println("Esperando conexiones...");
            clientSocket = serverSocket.accept();
            entrada = new DataInputStream(clientSocket.getInputStream());
            salida = new DataOutputStream(clientSocket.getOutputStream());
            request = entrada.readUTF();
            response=procesarPeticion(request);
            System.out.println("Peticion: "+request);
            System.out.println("Respuesta: "+response);
            salida.writeUTF(response);
            clientSocket.close();
        }
    }

    private String procesarPeticion(String request) {
        StringTokenizer st = new StringTokenizer(request,"/");
        String method = st.nextToken();
        System.out.println(request);
        switch (method){
            case "GET":
                if(!st.hasMoreTokens()){
                    return recursosPlayShopServer.getAllAvailableApps();
                }else{
                    return notificacionResources.getNotification();
                }
            case "POST":
                String jsonApp = st.nextToken();
                return recursosPlayShopServer.addApp(jsonApp);
            default:
                return recursosPlayShopServer.errorResponse();

        }
    }

}
