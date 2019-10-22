package storeApp;

import phone.PhoneKernel;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class PatitoStoreScreenContent {
    private JPanel content;
    private JMenuBar menuBar;
    private PatitoStoreProcess patitoStoreProcess;
    private PatitoStocreScreenContentController controller;
    public PatitoStoreScreenContent(PatitoStoreProcess process){
        patitoStoreProcess = process;
        content = getContentPanel();
        menuBar = getMenuBar();
        controller = new PatitoStocreScreenContentController(process);
    }

    private JMenuBar getMenuBar() {
        return new JMenuBar();
    }

    private JPanel getContentPanel(){
        Map<String,String>installedApp=patitoStoreProcess.getInstalledApps();
        List<String[]>availableApps = patitoStoreProcess.getAvailableApps();
        JPanel content = new JPanel();
        JLabel title = new JLabel("My Store");
        content.setLayout(new FlowLayout());
        content.add(title);

        for (String [] apps: availableApps
             ) {
            JPanel appPanel = new JPanel();
            JLabel appName = new JLabel(apps[0]);
            appPanel.add(appName);
            JButton button = new JButton();
            if(!installedApp.containsKey(apps[0])){
                button.setText("Install");
            }else {
                if(Integer.parseInt(installedApp.get(apps[0])) < Integer.parseInt(apps[0]) ){
                    JButton buttonUpdate = new JButton("Update");
                    buttonUpdate.addActionListener(controller);
                }
                button.setText("Uninstall");
            }
            button.addActionListener(controller);
            appPanel.add(button);
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
