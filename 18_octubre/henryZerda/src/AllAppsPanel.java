import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AllAppsPanel extends JPanel {
    JPanel panel;
    public AllAppsPanel(){
        panel = this;
        JButton button = new JButton("PLAY STORE");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.changeWindowContent("PLAY STORE",panel);
            }
        });
        add(button);
    }
}
