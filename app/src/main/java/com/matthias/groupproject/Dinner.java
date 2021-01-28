package com.matthias.groupproject;

public class Dinner {
    //declare private member variable
    private final int dinnerImage;
    private String dinnerTitle;
    private String dinnerPrice;
    private String dinnerDescription;

    /* create constructor for recipe data model and pass data for recipeImage, title and description
     */
    Dinner(int dinnerImage, String dinnerTitle, String dinnerPrice, String dinnerDescription){
        this.dinnerImage = dinnerImage;
        this.dinnerTitle = dinnerTitle;
        this.dinnerPrice = dinnerPrice;
        this.dinnerDescription = dinnerDescription;

    }
    //create getters amd return specific object
    public int getDinnerImage(){ return dinnerImage; }
    public String getDinnerTitle(){ return dinnerTitle; }
    public String getDinnerPrice(){
        return dinnerPrice;
    }
    public String getDinnerDescription(){
        return dinnerDescription;
    }
}
