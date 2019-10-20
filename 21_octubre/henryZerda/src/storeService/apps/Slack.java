package storeService.apps;

import storeService.MyAppInterface;

public class Slack implements MyAppInterface {
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
