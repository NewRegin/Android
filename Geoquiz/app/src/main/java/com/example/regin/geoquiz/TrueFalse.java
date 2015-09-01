package com.example.regin.geoquiz;

/**
 * Created by Regin on 15/5/4.
 */
public class TrueFalse {
    private int mQuestion;
    private boolean mTrueQuestion;

    public int getmQuestion() {
        return mQuestion;
    }

    public void setmQuestion(int mQuestion) {
        this.mQuestion = mQuestion;
    }

    public boolean ismTrueQuestion() {
        return mTrueQuestion;
    }

    public void setmTrueQuestion(boolean mTrueQuestion) {
        this.mTrueQuestion = mTrueQuestion;
    }
    public TrueFalse(int question,boolean truequestion){
        mQuestion = question;
        mTrueQuestion = truequestion;
    }
}
