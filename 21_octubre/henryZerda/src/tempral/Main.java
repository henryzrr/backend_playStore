package tempral;

import javax.swing.*;

public class Main {
    private static JFrame frame = getDefaultWindow();
    public static void main(String [] args){
        frame.setVisible(true);
    }
    private static JFrame getDefaultWindow(){
        WindowPreparer preparer = new WindowPreparer();
        return preparer.getPreparedWindow();
    }
    public static void changeWindowContent(String actualWindow,JPanel panel){
        frame.remove(panel);
        if(actualWindow.equals("PLAY STORE")){
            PlayStoreScreen playStoreScreen = new PlayStoreScreen();
            frame.add(playStoreScreen);
        }else{
            AllAppsPanel allAppsPanel = new AllAppsPanel();
            frame.add(allAppsPanel);
        }
        frame.invalidate();
        frame.validate();
    }
    public static void finalizeProgram(){
        frame.dispose();
    }
}
