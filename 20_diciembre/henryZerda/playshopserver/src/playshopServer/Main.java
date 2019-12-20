package playshopServer;

public class Main {
    public static void main(String [] args) throws Exception {
        try{
            System.out.println("Iniciando el servidor...");
            PlayShopServer playShopServer = new PlayShopServer("db_server");
            playShopServer.initServer();
        }catch (Exception e){

        }

    }
}
