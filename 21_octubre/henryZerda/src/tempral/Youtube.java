package tempral;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Youtube implements AppInstaller {
    private PlayStore playStore;
    public Youtube(PlayStore playStore){
        this.playStore=playStore;
    }

    @Override
    public JComponent getAppComponent() {
        JComponent component=new JPanel();
        JButton installButton = new JButton("Install");
        installButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playStore.install();
            }
        });
        JLabel name= new JLabel("YOUTUBE");
        component.add(name);
        component.add(installButton);
        return component;
    }

    @Override
    public AppInstaller getAppInstaller() {
        return this;
    }
}
