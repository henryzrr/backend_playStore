package playshopmanager;

import playshopserver.RecursosPlayShopManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Screen {
    private RecursosPlayShopManager recursosPlayShopManager;
    public Screen(RecursosPlayShopManager recursosPlayShopManager){
        this.recursosPlayShopManager=recursosPlayShopManager;
        JFrame frame = new JFrame();
        frame.setBounds(100,100,300,150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Subir App");
        frame.setLayout(new GridLayout(3,2));
        JTextPane appName = new JTextPane();
        appName.setBorder(BorderFactory.createLineBorder(Color.black));
        JLabel lname = new JLabel("Nombre app");
        JTextPane version = new JTextPane();
        version.setBorder(BorderFactory.createLineBorder(Color.black));
        JLabel lversion = new JLabel("Version: ");
        JButton button = new JButton("Registrar");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String res = recursosPlayShopManager.addApp(appName.getText()+","+version.getText()+",activa");
                    JOptionPane.showConfirmDialog(frame,res);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                appName.setText("");
                version.setText("");
            }
        });
        JButton salir = new JButton("salir");
        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        frame.add(lname);
        frame.add(appName);
        frame.add(lversion);
        frame.add(version);
        frame.add(button);
        frame.add(salir);
        frame.setVisible(true);
    }

}
