package pos.utils;

public interface SettingsInterface {

    String getCurrentSettings();

    void setOrderFileLocation(String fileName);

    void setProductFileLocation(String fileName);

    void setNameOfCompany(String name);

    void setReceiptInfo(String info);

    void setStandardVat(int vatValue);
}
