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
        try{
            String [] aux= splitLine(dbManager.findOne(name));
            return new App(aux[0],aux[1],aux[2]);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public App add(App app) throws IOException {
        dbManager.insert(app.getName()+","+app.getVersion()+","+app.getStatus());
        return app;
    }

    @Override
    public App update(App app) throws IOException {
        dbManager.update(app.getName()+","+app.getVersion()+","+app.getStatus(),app.getName());
        return app;
    }

    @Override
    public List<App> findPatternByColumn(String pattern,int column) {
        return convertToAppList(dbManager.findByColumn(pattern,column));
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
