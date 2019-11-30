package playshopmanager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ContentManager {
    private PlayShopManager playShopManager;

    public ContentManager(PlayShopManager playShopManager) {
        this.playShopManager = playShopManager;
    }

    public JPanel generateMainContent(List<App>apps){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(10,4));
        mainPanel.add( new JLabel("Nombre App"));
        mainPanel.add(new Label("Version"));
        mainPanel.add(new Label("Estado"));
        mainPanel.add(new JLabel());

        for (App app: apps)
        {
            JLabel nombreApp = new JLabel(app.getNombre());
            JLabel versionApp = new JLabel(app.getVersion());
            JLabel activo = new JLabel("activa:"+app.getActiva());
            JButton button = new JButton("Editar");
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null,"Ok");
                }
            });
            mainPanel.add(nombreApp);
            mainPanel.add(versionApp);
            mainPanel.add(activo);
            mainPanel.add(button);
        }
        return mainPanel;
    }
    public JPanel generateAppView(App app){
        JPanel appPanel = new JPanel();
        return appPanel;
    }
    public JMenuBar generateMainMenu(){
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
    public JMenuBar generateAppMenu(){
        JMenuBar appMenu = new JMenuBar();
        return appMenu;
    }

}
