package tempral;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayStoreScreen extends JPanel implements ScreenApp{
    JPanel panel;
    public PlayStoreScreen(){
        panel=this;
        PlayStore playStore = new PlayStore();
        JButton button = new JButton("Volver");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.changeWindowContent("OK",panel);
            }
        });
        JLabel title = new JLabel("APP LIST");
        title.setHorizontalAlignment(JLabel.CENTER);
        setLayout(new BorderLayout());
        Youtube app = new Youtube(playStore);
        add(title,BorderLayout.PAGE_START);
        add(app.getAppComponent(),BorderLayout.CENTER);
        add(button,BorderLayout.PAGE_END);
    }

}
