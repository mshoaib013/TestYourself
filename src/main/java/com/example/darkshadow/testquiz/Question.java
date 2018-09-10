package com.example.darkshadow.testquiz;
/*
a = choice1;
b = choice2;
c = choice3;
d = choice4;
q = question;
an = answer;
qh = question history;
c = choice;
s = subject;
tn = test number;
q1 = question1;
*/


public class Question {
    private String a;
    private String b;
    private String c;
    private String d;
    private String q;
    private String an;
    private String qh;

    public Question(String a, String b, String c, String d, String q, String an, String qh) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.q = q;
        this.an = an;
        this.qh = qh;
    }
    public Question() {
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getAn() {
        return an;
    }

    public void setAn(String an) {
        this.an = an;
    }

    public String getQh() {
        return qh;
    }

    public void setQh(String qh) {
        this.qh = qh;
    }
}
