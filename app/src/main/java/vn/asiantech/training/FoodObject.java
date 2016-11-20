package vn.asiantech.training;

/**
 * Created by MaiManhDuy on 11/18/2016.
 */

public class FoodObject {
    private String name;
    private int type;
    private int cost;

    public FoodObject(String name, int type, int cost) {
        this.name = name;
        this.type = type;
        this.cost = cost;
    }

    public FoodObject() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
