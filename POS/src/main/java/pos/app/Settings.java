package pos.app;

import pos.utils.SettingsInterface;

import java.util.Properties;

public class Settings implements SettingsInterface {
    private Properties properties;
    public Settings() {
        properties = new Properties();
    }

    @Override
    public String getCurrentSettings() {
        return null;
    }

    @Override
    public void setOrderFileLocation(String fileName) {

    }

    @Override
    public void setProductFileLocation(String fileName) {

    }

    @Override
    public String getOrderFileLocation() {
        return null;
    }

    @Override
    public String getProductFileLocation() {
        return null;
    }

    @Override
    public void setNameOfCompany(String name) {

    }

    @Override
    public void setReceiptInfo(String info) {

    }

    @Override
    public void setStandardVat(int vatValue) {

    }
}
