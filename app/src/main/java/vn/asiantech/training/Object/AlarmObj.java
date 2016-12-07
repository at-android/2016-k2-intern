package vn.asiantech.training.Object;

import java.io.Serializable;

/**
 * Created by MaiManhDuy on 12/2/2016.
 */

public class AlarmObj implements Serializable {
    private String title;
    private int hour;
    private int minute;
    private String dayofweek;

    public AlarmObj(String title, int hour, int minute, String dayofweek) {
        this.title = title;
        this.hour = hour;
        this.minute = minute;
        this.dayofweek = dayofweek;
    }

    public AlarmObj() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getDayofweek() {
        return dayofweek;
    }

    public void setDayofweek(String dayofweek) {
        this.dayofweek = dayofweek;
    }
}
