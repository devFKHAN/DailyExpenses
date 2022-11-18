package com.example.dailyexpenses.Details;

public class logindetails {
    public  String COLUMN_NAME;
    public  String COLUMN_USERNAME;
    public  String COLUMN_GENDER;
    public  int COLUMN_BUDGET;
    public  String COLUMN_TIME;
    public int id;

    public logindetails() {
    }

    public logindetails(String COLUMN_NAME, String COLUMN_USERNAME, String COLUMN_GENDER,
                        int COLUMN_BUDGET, String COLUMN_TIME) {
        COLUMN_NAME=this.COLUMN_NAME;
        COLUMN_USERNAME=this.COLUMN_USERNAME;
        COLUMN_GENDER=this.COLUMN_GENDER;
        COLUMN_BUDGET=this.COLUMN_BUDGET;
        COLUMN_TIME=this.COLUMN_TIME;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCOLUMN_NAME() {
        return COLUMN_NAME;
    }

    public String getCOLUMN_USERNAME() {
        return COLUMN_USERNAME;
    }

    public String getCOLUMN_GENDER() {
        return COLUMN_GENDER;
    }

    public int getCOLUMN_BUDGET() {
        return COLUMN_BUDGET;
    }

    public String getCOLUMN_TIME() {
        return COLUMN_TIME;
    }

    public void setCOLUMN_NAME(String COLUMN_NAME) {
        this.COLUMN_NAME = COLUMN_NAME;
    }

    public void setCOLUMN_USERNAME(String COLUMN_USERNAME) {
        this.COLUMN_USERNAME = COLUMN_USERNAME;
    }

    public void setCOLUMN_GENDER(String COLUMN_GENDER) {
        this.COLUMN_GENDER = COLUMN_GENDER;
    }

    public void setCOLUMN_BUDGET(int COLUMN_BUDGET) {
        this.COLUMN_BUDGET = COLUMN_BUDGET;
    }

    public void setCOLUMN_TIME(String COLUMN_TIME) {
        this.COLUMN_TIME = COLUMN_TIME;
    }
}
