package vn.asiantech.training;

/**
 * Created by MaiManhDuy on 11/23/2016.
 */

public class NotesObj {
    private String titleNote;
    private String contentNote;
    private String timesNote;

    public NotesObj() {
    }

    public NotesObj(String titleNote, String contentNote, String timesNote) {
        this.titleNote = titleNote;
        this.contentNote = contentNote;
        this.timesNote = timesNote;
    }

    public String getTitleNote() {
        return titleNote;
    }

    public void setTitleNote(String titleNote) {
        this.titleNote = titleNote;
    }

    public String getContentNote() {
        return contentNote;
    }

    public void setContentNote(String contentNote) {
        this.contentNote = contentNote;
    }

    public String getTimesNote() {
        return timesNote;
    }

    public void setTimesNote(String timesNote) {
        this.timesNote = timesNote;
    }
}
