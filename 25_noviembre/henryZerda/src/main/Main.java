package main;

import phone.PhoneKernel;
import playshopmanager.PlayShopManager;
import playshopserver.PlayShopServer;
import playshopserver.RecursosPlayShopCliente;
import playshopserver.RecursosPlayShopManager;


public class Main {

    private static RecursosPlayShopManager recursosPlayShopManager;
    private static  RecursosPlayShopCliente recursosPlayShopCliente;

    public static void main(String [] args) throws Exception{
        PlayShopServer server = new PlayShopServer("src/playshopserver/apps");
        recursosPlayShopCliente = server.getRecursosPlayShopCliente();
        recursosPlayShopManager=server.getRecursosPlayShopManager();
        PhoneKernel kernel = new PhoneKernel();
        PlayShopManager playShopManager = new PlayShopManager();
    }
    public static RecursosPlayShopCliente getRecursosCliente(){
        return  recursosPlayShopCliente;
    }
    public static RecursosPlayShopManager getRecursosPlayShopManager(){
        return recursosPlayShopManager;
    }
}


