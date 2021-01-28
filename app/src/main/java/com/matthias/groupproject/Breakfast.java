package com.matthias.groupproject;

public class Breakfast {
    //declare private member variable
    private final int breakfastImage;
    private String breakfastTitle;
    private String breakfastPrice;
    private String breakfastDescription;

    /* create constructor for recipe data model and pass data for recipeImage, title and description
     */
    Breakfast(int breakfastImage, String breakfastTitle, String breakfastPrice, String breakfastDescription){
        this.breakfastImage = breakfastImage;
        this.breakfastTitle = breakfastTitle;
        this.breakfastPrice = breakfastPrice;
        this.breakfastDescription = breakfastDescription;

    }
    //create getters amd return specific object
    public int getBreakfastImage(){ return breakfastImage; }
    public String getBreakfastTitle(){ return breakfastTitle; }
    public String getBreakfastPrice(){
        return breakfastPrice;
    }
    public String getBreakfastDescription(){
        return breakfastDescription;
    }
}
