package vn.asiantech.training;

import java.io.Serializable;

/**
 * Created by Administrator on 18/11/2016.
 */

public class FoodList implements Serializable {
    private int image;
    private String name;
    private String type;
    private String price;

    public FoodList() {

    }

    public FoodList(int image, String name, String type, String price) {
        this.image = image;
        this.name = name;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
