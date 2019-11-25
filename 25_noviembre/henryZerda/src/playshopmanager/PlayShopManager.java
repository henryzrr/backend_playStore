package playshopmanager;

import main.Main;
import playshopserver.RecursosPlayShopManager;

public class PlayShopManager {
    private Screen screen;
    private RecursosPlayShopManager recursosPlayShopManager;
    public PlayShopManager() {
        recursosPlayShopManager= Main.getRecursosPlayShopManager();
        this.screen = new Screen(recursosPlayShopManager);

    }

}
