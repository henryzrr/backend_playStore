package playshopserver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class AppORMforPlainTextDB implements AppManager {

    private  PlainTextDBManager dbManager;
    public AppORMforPlainTextDB(String DB_LOCATION) throws Exception {
        dbManager=new PlainTextDBManager(DB_LOCATION);
    }

    @Override
    public List<App> getAll(){
        return convertToAppList(dbManager.selectAll());
    }

    @Override
    public App findOne(String name) {
        String [] aux= splitLine(dbManager.findOne(name));
        return new App(aux[0],aux[1],aux[2]);
    }

    @Override
    public App add(App app) throws IOException {
        dbManager.insert(app.getNombre()+","+app.getVersion()+","+app.getActivo());
        return app;
    }

    @Override
    public App update(App app) throws IOException {
        dbManager.update(app.getNombre()+","+app.getVersion()+","+app.getActivo(),app.getNombre());
        return app;
    }

    @Override
    public List<App> findByPattern(String pattern) {
        return convertToAppList(dbManager.find(pattern));
    }

    private List<App> convertToAppList(List<String> lines){
        List<App> apps=new LinkedList<>();
        for (String line:lines
        ) {
            String [] aux= splitLine(line);
            apps.add(new App(aux[0],aux[1],aux[2]));
        }
        return apps;
    }
    private String[]splitLine(String line){
        StringTokenizer tokenizer = new StringTokenizer(line,",");
        return new String[]{tokenizer.nextToken(),tokenizer.nextToken(),tokenizer.nextToken()};
    }

}
