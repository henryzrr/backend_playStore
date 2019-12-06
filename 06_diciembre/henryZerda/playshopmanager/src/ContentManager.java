
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ContentManager {
    private PlayShopManager playShopManager;

    public ContentManager(PlayShopManager playShopManager) {
        this.playShopManager = playShopManager;
    }

    public JPanel generateMainContent(List<App>apps){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(10,1));


        JPanel cabecera = new JPanel();
        JTextField appBuscador = new JTextField();
        mainPanel.add(appBuscador);
        JButton nuevaApp = new JButton("Nueva App");
        nuevaApp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playShopManager.getScreenManager().setNewContent(generateNewAppView(),generateBackMenu());
            }
        });
        JButton buscarApp = new JButton("Buscar");
        buscarApp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                App a = playShopManager.getApp(appBuscador.getText());
                if (a==null){
                    JOptionPane.showMessageDialog(mainPanel,"No existe la Aplicacion");
                }else{
                    List <App> l = new LinkedList<>();
                    l.add(a);
                    playShopManager.getScreenManager().setNewContent(generateMainContent(l),generateMainMenu());
                }
            }
        });
        cabecera.add(buscarApp);
        cabecera.add(nuevaApp);
        mainPanel.add(cabecera);

        JPanel panelTitulo = new JPanel();

        panelTitulo.add( new JLabel("Nombre App"));
        panelTitulo.add(new Label("Version"));
        panelTitulo.add(new Label("Estado"));
        panelTitulo.add(new Label("Opciones"));
        mainPanel.add(panelTitulo);


        for (App app: apps)
        {
            JPanel p = new JPanel();
            JLabel nombreApp = new JLabel(app.getName());
            JLabel versionApp = new JLabel(app.getVersion());
            JLabel activo = new JLabel("activa:"+app.getStatus());
            JButton button = new JButton("Editar");
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null,"Ok");
                }
            });
            p.add(nombreApp);
            p.add(versionApp);
            p.add(activo);
            p.add(button);
            mainPanel.add(p);
        }
        return mainPanel;
    }

    private JPanel generateNewAppView() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(10,1));
        JTextField nombreApp = new JTextField();
        JButton botton = new JButton("Nueva App");
        botton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    String res = playShopManager.instalarApp(nombreApp.getText());
                    if(res==null){
                        JOptionPane.showMessageDialog(mainPanel,"ERROR 500");
                    }else{
                        JOptionPane.showMessageDialog(mainPanel,res);
                    }

            }
        });
        mainPanel.add(nombreApp);
        mainPanel.add(botton);
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

    public JMenuBar generateBackMenu(){
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem  menuItem= new  JMenuItem("back");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    playShopManager.getScreenManager().setNewContent(generateMainContent(playShopManager.getAllApps())
                            ,generateMainMenu());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        menu.add(menuItem);
        menuBar.add(menu);
        return menuBar;
    }

}
