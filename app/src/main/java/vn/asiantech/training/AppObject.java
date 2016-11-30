package vn.asiantech.training;

/**
 * Created by MaiManhDuy on 11/30/2016.
 */

public class AppObject {
    private String name;
    private String appPackage;

    public AppObject(String name, String appPackage) {
        this.name = name;
        this.appPackage = appPackage;
    }

    public AppObject() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }
}
