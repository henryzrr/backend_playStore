package storeApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StoreScreenContentController implements ActionListener {
    private String clickedButtonName;
    private String appName;
    private StoreProcess storeProcess;
    public StoreScreenContentController(StoreProcess process){
        clickedButtonName=null;
        appName=null;
        storeProcess=process;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
