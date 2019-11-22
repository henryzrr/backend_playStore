package phone.storeApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class StoreScreenContent {
    private JPanel content;
    private JMenuBar menuBar;
    private StoreProcess storeProcess;
    private StoreScreenContentController controller;
    public StoreScreenContent(StoreProcess process){
        storeProcess = process;
        content = createContentPanel();
        menuBar = createMenuBar();
        controller = new StoreScreenContentController(process);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem  menuItem= new  JMenuItem("back");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storeProcess.getDefaultPhoneScreen();
            }
        });
        menu.add(menuItem);
        menuBar.add(menu);
        return menuBar;
    }

    private JPanel createContentPanel(){
        Map<String,String>installedApp= storeProcess.getInstalledApps();
        List<String[]>availableApps = storeProcess.getAvailableApps();

        JPanel content = new JPanel();
        JLabel title = new JLabel("My Store");
        title.setHorizontalAlignment(JLabel.CENTER);
        content.setLayout(new GridLayout(10,1));
        content.add(title);
        int i=0;
        for (String [] apps: availableApps
             ) {
            JPanel appPanel = new JPanel();
            JLabel appName = new JLabel(apps[0] + " v:"+apps[1]);
            appPanel.add(appName);
            JButton button = new JButton();
            if(!installedApp.containsKey(apps[0])){
                button.setText("Install");
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        storeProcess.installApp(apps[0]);
                    }
                });
            }else {
                if(Integer.parseInt(installedApp.get(apps[0])) != Integer.parseInt(apps[1]) ){
                    JButton buttonUpdate = new JButton("Update");
                    buttonUpdate.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            storeProcess.updateApp(apps[0]);
                        }
                    });
                    appPanel.add(buttonUpdate);
                }
                button.setText("Uninstall");
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        storeProcess.uninstallApp(apps[0]);
                    }
                });
            }

            appPanel.add(button);
            if(++i%2!=0){
                appPanel.setBackground(Color.cyan);
            }
            content.add(appPanel);
        }
        return content;
    }
    public JMenuBar getMenu(){
        return menuBar;
    }
    public JPanel getContent(){
        return content;
    }
}
