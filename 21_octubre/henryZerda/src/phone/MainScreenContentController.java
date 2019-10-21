package phone;

import storePhoneApp.StoreAppScreenContent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreenContentController implements ActionListener {
    private PhoneServices phoneServices;
    public MainScreenContentController(){
        this.phoneServices = new PhoneServices();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if(button.getText().equals("Collector App")){
            StoreAppScreenContent storeAppScreenContent= new StoreAppScreenContent();
            phoneServices.setNewScreen(storeAppScreenContent,getDefaultStoreMenuBar());
        }else{

        }
    }
    private JMenuBar getDefaultStoreMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem  menuItem= new  JMenuItem("back");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                phoneServices.setDefaultPhoneScreen();
            }
        });
        menu.add(menuItem);
        menuBar.add(menu);
        return menuBar;
    }
}
