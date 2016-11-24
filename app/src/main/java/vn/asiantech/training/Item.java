package vn.asiantech.training;

/**
 * Created by HoangDuy on 21/11/2016.
 */
public class Item {
    private String nameItem;
    private int icon;

    public Item() {

    }

    public Item(String nameItem, int icon) {
        this.nameItem = nameItem;
        this.icon = icon;
    }

    public String getNameItem() {
        return nameItem;
    }

    public void setNameItem(String nameItem) {
        this.nameItem = nameItem;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
