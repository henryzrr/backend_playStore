package store;

import java.util.List;

public class StoreServices {
    private static StoreServices storeServices = new StoreServices();

    private StoreServices(){

    }
    public static StoreServices getInstance(){
        return storeServices;
    }
    public List<String[]> getAvailableServices(){
        return null;
    }
}
