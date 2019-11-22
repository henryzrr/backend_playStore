package playshopserver;

public class InstallerAppServices {
    private AppManager appManager;
    public InstallerAppServices(){
        appManager=AppManager.getInstance();
    }
    public boolean setApp(String appName, String version){
        if(!appName.equals("") && !version.equals("")){
            appManager.setApp(appName,version);
            return true;
        }
        return false;
    }
}
