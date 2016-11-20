package vn.asiantech.training;

import java.io.Serializable;

/**
 * Created by HoangDuy on 18/11/2016.
 */
public class Foody implements Serializable {
    private int image;
    private String name;
    private String category;
    private String price;

    public Foody(int image, String name, String category, String price) {
        this.image = image;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
