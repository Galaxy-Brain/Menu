package com.matthias.groupproject;

public class Lunch {
    //declare private member variable
    private final int lunchImage;
    private String lunchTitle;
    private String lunchPrice;
    private String lunchDescription;

    /* create constructor for recipe data model and pass data for recipeImage, title and description
     */
    Lunch(int lunchImage, String lunchTitle, String lunchPrice, String lunchDescription){
        this.lunchImage = lunchImage;
        this.lunchTitle = lunchTitle;
        this.lunchPrice = lunchPrice;
        this.lunchDescription = lunchDescription;

    }
    //create getters amd return specific object
    public int getLunchImage(){ return lunchImage; }
    public String getLunchTitle(){ return lunchTitle; }
    public String getLunchPrice(){
        return lunchPrice;
    }
    public String getLunchDescription(){
        return lunchDescription;
    }
}
