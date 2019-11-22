package playshopserver;

import java.util.List;

public interface DBAppManager {
    List<App> getAll();
    App save();
    App findByName(String appName);
    App update(App app);
    App delete(App app);
}
