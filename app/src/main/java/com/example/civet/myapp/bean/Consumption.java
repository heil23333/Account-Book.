package com.example.civet.myapp.bean;

public class Consumption {
    private String tag;
    private float money;
    private long time;
    private String classification;

    public Consumption(String tag, float money, long time, String classification) {
        this.tag = tag;
        this.money = money;
        this.time = time;
        this.classification = classification;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }
}
