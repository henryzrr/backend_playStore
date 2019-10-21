package phone;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainScreenContent extends JPanel {
    private MainScreenContentController mainScreenContentController;
    public MainScreenContent(List<String> installedApps){
        mainScreenContentController = new MainScreenContentController();
        setLayout(new FlowLayout());
        setButtons(installedApps);
    }

    private void setButtons(List<String> installedApps) {
        for (String app:installedApps
             ) {
            JButton button = new JButton(app);
            button.addActionListener(mainScreenContentController);
            add(button);
        }
    }

}
