import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Connection {
    private final  String HOST = "localhost";
    private  final int PUERTO = 50000;
    private Socket cliente;
    private DataOutputStream salida;
    private DataInputStream entrada;

    public Connection() {

    }
    public String doRequest(String url) throws IOException {
        cliente = new Socket(HOST, PUERTO);
        salida = new DataOutputStream(cliente.getOutputStream());
        entrada = new DataInputStream(cliente.getInputStream());
        String response = "";
        salida.writeUTF(url);
        response = entrada.readUTF();
        cliente.close();
        return response;
    }


}
