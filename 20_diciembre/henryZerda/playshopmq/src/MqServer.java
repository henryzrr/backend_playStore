import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class MqServer {

    private final int PUERTO = 50001;
    private final ServerSocket serverSocket;
    private Socket clientSocket;
    private DataOutputStream salida;
    private DataInputStream entrada;

    private QuequeManager manager;
    private DBManager dbManager;

    public MqServer(String DB_LOCATION) throws Exception{
        serverSocket = new ServerSocket(PUERTO);
        dbManager = new DBManager();
        QuequeManager manager = new QuequeManager(dbManager);

        System.out.println("Servidor de notificaciones iniciado en el puerto:50000");
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
        int user = Integer.parseInt(st.nextToken());
        switch (method){
            case "GET":
                return manager.dequeueNotificacion(user).toJson();
            case "POST":
                String message = st.nextToken();
                return manager.enqueueNotification(message,user).toJson();
            default:
                return new Response(400,"").toJson();

        }
    }
}
