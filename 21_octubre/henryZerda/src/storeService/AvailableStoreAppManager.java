package storeService;

import storeService.apps.Facebook;
import storeService.apps.Slack;
import storeService.apps.Websis;
import storeService.apps.Youtube;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AvailableStoreAppManager {
    private Map<String, MyAppInterface> availableApps;
    private List<String> nameAvailableApps;
    public AvailableStoreAppManager(){
        availableApps = new HashMap<>();
        availableApps.put("Youtube", new Youtube());
        availableApps.put("Slack",new Slack());
        availableApps.put("Facebook", new Facebook());
        availableApps.put("WebSis",new Websis());
        nameAvailableApps = getAllAppNames();
    }

    private List<String> getAllAppNames() {
        List<String> appNames = new LinkedList<>(availableApps.keySet());
        return  appNames;
    }

    public List<String> getAllAvailableAppNames(){
        return nameAvailableApps;
    }
    public MyAppInterface getMyAppInterface(String name){
        return availableApps.get(name);
    }
}
