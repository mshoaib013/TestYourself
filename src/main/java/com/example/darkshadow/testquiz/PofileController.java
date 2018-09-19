package com.example.darkshadow.testquiz;

public class PofileController {
    private String um;//username
    private String ep;//email or phone
    private String b;//bio
    private String s;//score
    private String r;//rank

    public PofileController(String um, String ep, String b, String s, String r) {
        this.um = um;
        this.ep = ep;
        this.b = b;
        this.s = s;
        this.r = r;
    }

    public PofileController() {
    }

    public String getUm() {
        return um;
    }

    public void setUm(String um) {
        this.um = um;
    }

    public String getEp() {
        return ep;
    }

    public void setEp(String ep) {
        this.ep = ep;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }
}
