import phone.PhoneServices;
import phone.ScreenManager;
import storeService.StoreAppServices;

public class Main {
    public static void main(String args[]){
        PhoneServices phoneServices = new PhoneServices();
        phoneServices.setDefaultPhoneScreen();
        StoreAppServices storeAppServices = new StoreAppServices();
    }
}
