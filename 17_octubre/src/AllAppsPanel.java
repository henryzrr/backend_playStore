import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AllAppsPanel extends JPanel {
    JPanel panel;
    public AllAppsPanel(){
        panel = this;
        JMenuBar menuBar = getDefaultMenuBar();
        JButton button = new JButton("PLAY STORE");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.changeWindowContent("PLAY STORE",panel);
            }
        });
        setLayout(new FlowLayout());
        add(menuBar);
        add(button);

    }
    private JMenuBar getDefaultMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("file");
        JMenuItem quitOption = new JMenuItem("quit");
        quitOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.finalizeProgram();
            }
        });
        menu.add(quitOption);
        menuBar.add(menu);
        return menuBar;
    }
}
