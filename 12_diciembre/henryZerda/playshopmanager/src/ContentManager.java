
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


        JButton nuevaApp = new JButton("Agregar Nueva Aplicacion");
        nuevaApp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playShopManager.getScreenManager().setNewContent(generateNewAppView(),generateBackMenu());
            }
        });

        mainPanel.add(nuevaApp);
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
                    playShopManager.getScreenManager().setNewContent(generateEditAppView(app),generateBackMenu());
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
                boolean res = playShopManager.instalarApp(nombreApp.getText());
                if(!res){
                    JOptionPane.showMessageDialog(mainPanel,"La aplicacion no pudo agregarse al servidor");
                }else{
                    JOptionPane.showMessageDialog(mainPanel,"Aplicacion agregada con éxito");
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

    private JPanel generateEditAppView(App app) {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(10,1));
        JLabel nombre = new JLabel("Nombre App: "+app.getName());
        mainPanel.add(nombre);
        JLabel versionLabel = new JLabel("Version:");
        mainPanel.add(versionLabel);
        JTextField version = new JTextField();
        version.setText(app.getVersion());
        mainPanel.add(version);
        JCheckBox activar = new JCheckBox("Activar");
        JCheckBox desactivar = new JCheckBox("Desactivar");
        if(app.getStatus().equals("true")){
            activar.setSelected(true);
        }else{
            desactivar.setSelected(true);
        }
        activar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desactivar.setSelected(false);
            }
        });
        desactivar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                activar.setSelected(false);
            }
        });
        mainPanel.add(activar);
        mainPanel.add(desactivar);
        JButton botton = new JButton("Actualizar App");

        botton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                App app1 = new App(app.getName(),version.getText(), activar.isSelected()?"true":"false");
                boolean res = playShopManager.actualizarApp(app1);
                if(!res){
                    JOptionPane.showMessageDialog(mainPanel,"La aplicacion no pudo actualizarse");
                }else{
                    JOptionPane.showMessageDialog(mainPanel,"Aplicacion actualizada con éxito");
                }

            }
        });
        mainPanel.add(botton);
        return mainPanel;
    }
}
