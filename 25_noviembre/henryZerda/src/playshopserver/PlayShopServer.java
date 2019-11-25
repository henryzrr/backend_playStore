package playshopserver;

public class PlayShopServer {
    private PlainTextDBManager dbManager;
    private RecursosPlayShopCliente recursosPlayShopCliente;
    private RecursosPlayShopManager recursosPlayShopManager;
    private ServiciosPlayShopServer serviciosPlayShopServer;

    public PlayShopServer(String DB_LOCATION) throws Exception{
        this.dbManager = new PlainTextDBManager(DB_LOCATION);
        serviciosPlayShopServer=new ServiciosPlayShopServer(dbManager);
        recursosPlayShopCliente=new RecursosPlayShopCliente(serviciosPlayShopServer);
        recursosPlayShopManager = new RecursosPlayShopManager(serviciosPlayShopServer);
    }

    public RecursosPlayShopCliente getRecursosPlayShopCliente() {
        return recursosPlayShopCliente;
    }

    public RecursosPlayShopManager getRecursosPlayShopManager() {
        return recursosPlayShopManager;
    }
}
