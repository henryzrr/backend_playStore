import store.InstallerAppServices;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main2 {
    public static void main(String [] args){
        InstallerAppServices installerAppServices = new InstallerAppServices();
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
                verificar(installerAppServices.setApp(appName.getText(),version.getText()),frame);
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
    private static void verificar(boolean res,JFrame frame){
        if(!res){
            JOptionPane.showMessageDialog(frame,"Debe Llenar todos los campos");
        }
    }
}
