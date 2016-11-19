package vn.asiantech.training;

import java.io.Serializable;

/**
 * Created by Administrator on 17/11/2016.
 */

public class Question implements Serializable {
    private String question;
    private String keyA;
    private String keyB;
    private String keyC;
    private String keyD;
    private String result;


    public Question(){

    }
    public Question(String question,String keyA,String keyB,String keyC,String keyD,String result){
        this.question = question;
        this.keyA = keyA;
        this.keyB = keyB;
        this.keyC = keyC;
        this.keyD = keyD;
        this.result = result;
    }
    public String getKeyA() {
        return keyA;
    }

    public void setKeyA(String keyA) {
        this.keyA = keyA;
    }

    public String getKeyB() {
        return keyB;
    }

    public void setKeyB(String keyB) {
        this.keyB = keyB;
    }

    public String getKeyC() {
        return keyC;
    }

    public void setKeyC(String keyC) {
        this.keyC = keyC;
    }

    public String getKeyD() {
        return keyD;
    }

    public void setKeyD(String keyD) {
        this.keyD = keyD;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

}
