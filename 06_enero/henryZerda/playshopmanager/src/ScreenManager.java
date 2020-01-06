

import javax.swing.*;

public class ScreenManager{
    private JFrame screen;
    private JPanel actualContent;
    public ScreenManager(){
        screen = new JFrame();
        screen.setResizable(false);
        screen.setBounds(200,100,400,500);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setTitle("APP MANAGER");
    }
    public void setNewContent(JPanel newContent, JMenuBar newMenu){
        if(actualContent!=null){
            screen.remove(actualContent);
        }
        screen.add(newContent);
        screen.setJMenuBar(newMenu);
        actualContent = newContent;
        screen.setVisible(true);

    }
    public JFrame getContainer(){
        return screen;
    }

}
