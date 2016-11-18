package vn.asiantech.training.application;


import android.app.Application;
import android.util.Log;

import java.util.ArrayList;

import vn.asiantech.training.model.Food;

/**
 * Created by phuong on 18/11/2016.
 */

public class App extends Application {
    ArrayList<Food> mFoods = new ArrayList<>();

    public ArrayList<Food> getListFood() {
        return mFoods;
    }

    public void addFood(Food food) {
        mFoods.add(food);
    }

    public ArrayList<String> initDataForSpinner() {
        ArrayList<String> types = new ArrayList<>();
        types.add("food");
        types.add("drink");
        types.add("other");
        return types;
    }

    public void editFood(Food food, int position) {
        mFoods.set(position, food);
        Log.d("po", String.valueOf(position));
    }

    public void initListFood() {
        mFoods.add(new Food("bread", "food", "2000VNĐ"));
        mFoods.add(new Food("noodle", "food", "10000VNĐ"));
        mFoods.add(new Food("cheese", "other", "8000VNĐ"));
        mFoods.add(new Food("coca", "drink", "6000VNĐ"));
        mFoods.add(new Food("meal", "food", "12000VNĐ"));
        mFoods.add(new Food("milk", "drink", "9000VNĐ"));
    }
}
