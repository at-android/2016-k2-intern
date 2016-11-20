package vn.asiantech.training;

/**
 * Created by MaiManhDuy on 11/17/2016.
 */

public class QuizzObj {
    private String mQuestion;
    private String mResultA;
    private String mResultB;
    private String mResultC;
    private String mResultD;
    private String mResultCorrect;

    public QuizzObj(String mQuestion, String mResultA, String mResultB, String mResultC, String mResultD, String mResultCorrect) {
        this.mQuestion = mQuestion;
        this.mResultA = mResultA;
        this.mResultB = mResultB;
        this.mResultC = mResultC;
        this.mResultD = mResultD;
        this.mResultCorrect = mResultCorrect;
    }

    public QuizzObj() {
    }

    public String getmQuestion() {
        return mQuestion;
    }

    public void setmQuestion(String mQuestion) {
        this.mQuestion = mQuestion;
    }

    public String getmResultA() {
        return mResultA;
    }

    public void setmResultA(String mResultA) {
        this.mResultA = mResultA;
    }

    public String getmResultB() {
        return mResultB;
    }

    public void setmResultB(String mResultB) {
        this.mResultB = mResultB;
    }

    public String getmResultC() {
        return mResultC;
    }

    public void setmResultC(String mResultC) {
        this.mResultC = mResultC;
    }

    public String getmResultD() {
        return mResultD;
    }

    public void setmResultD(String mResultD) {
        this.mResultD = mResultD;
    }

    public String getmResultCorrect() {
        return mResultCorrect;
    }

    public void setmResultCorrect(String mResultCorrect) {
        this.mResultCorrect = mResultCorrect;
    }
}
