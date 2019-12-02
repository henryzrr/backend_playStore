package phone.storeApp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Request{
    private final  String HOST = "localhost";
    private  final int PUERTO = 5000;
    private Socket cliente;
    private DataOutputStream salida;
    private DataInputStream entrada;

    public Request() throws IOException {
        cliente = new Socket(HOST, PUERTO);
    }
    public String requestGet(String url) throws IOException {
        salida = new DataOutputStream(cliente.getOutputStream());
        entrada = new DataInputStream(cliente.getInputStream());
        String response = "";
        salida.writeUTF(url);
        response = entrada.readUTF();
        cliente.close();
        return response;
    }
}
