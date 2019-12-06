import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class PlayShopServer {
    private RecursosPlayShopManager recursosPlayShopManager;
    private ServiciosPlayShopServer serviciosPlayShopServer;

    private final int PUERTO = 50000;
    private final ServerSocket serverSocket;
    private Socket clientSocket;
    private DataOutputStream salida;
    private DataInputStream entrada;

    public PlayShopServer(String DB_LOCATION) throws Exception{

        serverSocket = new ServerSocket(PUERTO);
        AppManager appManager = new AppORMforPlainTextDB(DB_LOCATION);
        serviciosPlayShopServer=new ServiciosPlayShopServer(appManager);
        recursosPlayShopManager = new RecursosPlayShopManager(serviciosPlayShopServer);
        System.out.println("Servidor iniciado en el puerto:50000");
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
        StringTokenizer st = new StringTokenizer(request,":");
        String method = st.nextToken();
        switch (method){
            case "GET":
                if(!st.hasMoreTokens()){
                    return recursosPlayShopManager.getAllAvailableApps();
                }else{
                    String[]app= request.split("/");
                    String aux = app[1].replaceAll(" ","");
                    return recursosPlayShopManager.getAppByName(aux);
                }

            case "POST":
                String[]app= request.split("/");
                String aux = app[1].replaceAll(" ","");
                return recursosPlayShopManager.newApp(aux);

            case "PUT":
                String[]apps = request.split("/");
                return recursosPlayShopManager.updateApp(new App(apps[2]));
            default:
                return "500";

        }
    }

}
