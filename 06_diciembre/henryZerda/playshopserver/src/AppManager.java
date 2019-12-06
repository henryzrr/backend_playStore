

import java.io.IOException;
import java.util.List;

public interface AppManager {
    List<App> getAll();
    List<App> findByPattern(String pattern);
    App findOne(String name);
    App add(App app) throws IOException;
    App update(App app) throws IOException;

}
