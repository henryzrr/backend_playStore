package tempral;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowPreparer {
    private JFrame window;
    public WindowPreparer(){
        AllAppsPanel defaultPanel = new AllAppsPanel();
        window = getDefaultWindow();
        JMenuBar menuBar = getDefaultMenuBar();
        window.setJMenuBar(menuBar);
        window.add(defaultPanel);
    }

    public JFrame getPreparedWindow(){
        return window;
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


    private JFrame getDefaultWindow() {
        JFrame frame = new JFrame();
        frame.setTitle("Apps Manager");
        frame.setBounds(50,50,500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        return frame;
    }


}
