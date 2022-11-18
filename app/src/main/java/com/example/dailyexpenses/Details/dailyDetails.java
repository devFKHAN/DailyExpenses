package com.example.dailyexpenses.Details;

public class dailyDetails {
    public int _ID1;
    public String Date;
    public String Categories;
    public String description;
    public int Price;
    public int day;
    public int month;
    public int year;
    public dailyDetails(){
    }
    public dailyDetails(String Date, String Categories, String Description, int Price,int id){
        this.Date=Date;
        this.Categories=Categories;
        this.description=Description;
        this.Price=Price;
        this._ID1=id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int get_ID1() {
        return _ID1;
    }

    public void set_ID1(int _ID1) {
        this._ID1 = _ID1;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getCategories() {
        return Categories;
    }

    public void setCategories(String categories) {
        Categories = categories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }
}
