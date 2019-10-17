import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayStorePanel extends JPanel {
    JPanel panel;
    public PlayStorePanel(){
        panel=this;
        JMenuBar menuBar = getDefaultMenuBar();
        JButton button = new JButton("OK");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlayStoreController playStoreController = new PlayStoreController();
                playStoreController.buyApp();
                Main.changeWindowContent("OK",panel);
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
