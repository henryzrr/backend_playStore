package storeService.apps;

import storeService.MyAppInterface;

public class Youtube implements MyAppInterface {
    @Override
    public boolean install() {
        return false;
    }

    @Override
    public boolean uninstall() {
        return false;
    }

    @Override
    public String getAppName() {
        return null;
    }
}
