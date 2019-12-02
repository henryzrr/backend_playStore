public class Main {
    public static void main(String [] args) throws Exception {
        PlayShopServer playShopServer = new PlayShopServer("src/apps");
        playShopServer.initServer();
        /*RecursosPlayShopManager recursosPlayShopManager = playShopServer.getRecursosPlayShopManager();
        RecursosPlayShopCliente recursosPlayShopCliente = playShopServer.getRecursosPlayShopCliente();

        System.out.println(recursosPlayShopManager.getAllAvailableApps());
        System.out.println(recursosPlayShopCliente.getApps());
        System.out.println(recursosPlayShopCliente.getAppByName("youtube"));
        System.out.println(recursosPlayShopManager.getAppByName("youtube"));
        System.out.println(recursosPlayShopCliente.getAppByName("youtubes"));
        System.out.println(recursosPlayShopManager.getAppByName("youtubes"));
        System.out.println(recursosPlayShopManager.addApp("{\"nombre\":\"youtubes2\",\"version\":\"1.0\",\"estado\":\"activa\"}"));
        System.out.println(recursosPlayShopManager.addApp("{\"nombre\":\"youtube\",\"version\":\"2.0\",\"estado\":\"activa\"}"));
        */
    }
}
