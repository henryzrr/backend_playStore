package phone;

public  abstract class PhoneProcess {
    protected PhoneKernel phoneKernel;
    public PhoneProcess(PhoneKernel phoneKernel){
        this.phoneKernel = phoneKernel;
    }
    public  abstract void showScreen();
}
