package com.example.tienbi.ailatrieuphu.mode;

/**
 * Created by TienBi on 09/10/2016.
 */
public class Question {
    private int id;
    private String question;
    private String caseA;
    private String caseB;
    private String caseC;
    private String caseD;
    private int trueCase;

    public Question() {
    }

    public Question(int id, String question, String caseA, String caseB, String caseC, String caseD, int trueCase) {
        this.id = id;
        this.question = question;
        this.caseA = caseA;
        this.caseB = caseB;
        this.caseC = caseC;
        this.caseD = caseD;
        this.trueCase = trueCase;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getCaseA() {
        return caseA;
    }

    public String getCaseB() {
        return caseB;
    }

    public String getCaseC() {
        return caseC;
    }

    public String getCaseD() {
        return caseD;
    }

    public int getTrueCase() {
        return trueCase;
    }
}
