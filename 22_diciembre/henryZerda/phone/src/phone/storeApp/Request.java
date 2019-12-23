package phone.storeApp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Request{
    private final  String HOST = "localhost";
    private  final int PUERTO = 50000;
    private Socket cliente;
    private DataOutputStream salida;
    private DataInputStream entrada;

    public Request() {
        try {
            cliente = new Socket(HOST, PUERTO);
        } catch (IOException e) {
            System.err.println("Error de comunicacion con el servidor");
        }
    }
    String doRequest(String url)  {
        try{
            salida = new DataOutputStream(cliente.getOutputStream());
            entrada = new DataInputStream(cliente.getInputStream());
            String response = "";
            salida.writeUTF(url);
            response = entrada.readUTF();
            cliente.close();
            return response;
        }catch (Exception e){
            System.err.println("Error de comunicacion con el servidor");
        }
        return null;
    }


}
