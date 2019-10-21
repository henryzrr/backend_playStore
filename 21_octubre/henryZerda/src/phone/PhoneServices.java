package phone;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PhoneServices {
    private AppInstalledManager appInstalledManager;
    private ScreenManager screenManager;

    public PhoneServices(){
        appInstalledManager = new AppInstalledManager();
        screenManager = new ScreenManager();
    }
    public void setDefaultPhoneScreen(){
        MainScreenContent screenContent = new MainScreenContent(appInstalledManager.getInstalledApps());
        screenManager.setContent(screenContent,getMenuBar());
    }
    public List<String> getInstalledApps(){
        return appInstalledManager.getInstalledApps();
    }
    public void installApp(String app){
        appInstalledManager.installApp(app);
    }
    public void uninstallApp(String app){
        appInstalledManager.uninstallApp(app);
    }
    public void setNewScreen(JPanel screenContent, JMenuBar menuBar){
        screenManager.setContent(screenContent,menuBar);
    }
    private JMenuBar getMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem  menuItem= new  JMenuItem("quit");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menu.add(menuItem);
        menuBar.add(menu);
        return menuBar;
    }
}
