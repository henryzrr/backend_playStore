package phone;

import java.util.Set;

public  abstract class PhoneProcess {
    protected PhoneKernel phoneKernel;
    public PhoneProcess(PhoneKernel phoneKernel){
        this.phoneKernel = phoneKernel;
    }
    public  abstract void showScreen();
    public abstract Set<String> getNotifications();
    public abstract void deleteNotifications(String notifications);
}
