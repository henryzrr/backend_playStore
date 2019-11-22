package phone;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class DefaultPhoneScreenContent {
    private JPanel screenContent;
    private JMenuBar menuBar;
    public DefaultPhoneScreenContent(List<String[]> installedApps, Map<String,PhoneProcess> processes){
        screenContent = setDefaultContent(installedApps,processes);
        menuBar = setDefaultMenuBar();
    }

    private JMenuBar setDefaultMenuBar() {
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

    private JPanel setDefaultContent(List<String[]> installedApps,Map<String,PhoneProcess> processes) {
        JPanel content = new JPanel();
        content.setLayout(new GridLayout(10,1));
        for (String[] apps: installedApps
             ) {
            JButton button = new JButton(apps[0] + " Version:"+apps[1]);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    manageEvent(apps[0],processes);
                }
            });
            content.add(button);
        }
        return content;
    }

    private void manageEvent(String app,Map<String,PhoneProcess> processes) {
        if(app.contains("storeApp")){
            processes.get(app).showScreen();
        }else{
            JOptionPane.showMessageDialog(null,"OK!");
        }
    }
    public JPanel getScreenContent(){
        return screenContent;
    }
    public JMenuBar getMenuBar(){
        return menuBar;
    }
}
