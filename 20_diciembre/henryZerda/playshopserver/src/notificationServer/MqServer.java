package notificationServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class MqServer {


    private QuequeManager manager;
    private DBManager dbManager;

    public MqServer(String DB_LOCATION) throws Exception{
        dbManager = new DBManager();
        QuequeManager manager = new QuequeManager(dbManager);
        System.out.println("Servidor de notificaciones iniciado en el puerto:500000");
    }

}
