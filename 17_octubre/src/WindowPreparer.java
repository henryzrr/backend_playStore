import javax.swing.*;
import java.awt.*;

public class WindowPreparer {
    private JFrame window;
    private JPanel defaultPanel;
    public WindowPreparer(){
        AllAppsPanel defaultPanel = new AllAppsPanel();
        window = getDefaultWindow();
        window.add(defaultPanel);
    }



    public JFrame getPreparedWindow(){
        return window;
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
