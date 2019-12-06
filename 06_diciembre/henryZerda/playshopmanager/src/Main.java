import java.io.IOException;

public class Main {
    public static void main(String [] args) throws IOException {
        try {
            System.out.println("Conectado al servidor...");
            PlayShopManager playShopManager = new PlayShopManager();
            System.out.println("Conectado!");
        }catch(Exception e){
            System.out.println("No se pudo establecer conexion con el servidor");
        }

    }
}

