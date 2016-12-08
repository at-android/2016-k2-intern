package vn.asiantech.training.Bean;

/**
 * Created by HoangDuy on 01/12/2016.
 */
public class Day {
    private String dayOfweek;
    private boolean isCheck;

    public Day(String dayOfweek, boolean isCheck) {
        this.dayOfweek = dayOfweek;
        this.isCheck = isCheck;
    }

    public String getDayOfweek() {
        return dayOfweek;
    }

    public void setDayOfweek(String dayOfweek) {
        this.dayOfweek = dayOfweek;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}