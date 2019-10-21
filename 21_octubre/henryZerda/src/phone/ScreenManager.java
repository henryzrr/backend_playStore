package phone;

import javax.swing.*;

public class ScreenManager {
    private  JFrame screen;
    private  JPanel content;
    private  JMenuBar menuBar;

    public ScreenManager(){
        screen = new JFrame();
        screen.setResizable(false);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setBounds(50,50,400,500);
        content=null;
        menuBar=null;
    }

    public void setContent(JPanel content, JMenuBar menuBar){
        if(this.content!=null){
            screen.remove(this.content);
        }
        this.content=content;
        screen.setJMenuBar(menuBar);
        screen.add(content);
        screen.setVisible(true);
    }
}
