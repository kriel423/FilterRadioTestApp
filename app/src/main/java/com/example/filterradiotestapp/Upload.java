package com.example.filterradiotestapp;

public class Upload {
    private String mTitle;
    private String mDescription;
    private String mFilter;
    private String mQuantity;

    public Upload(){}

    public Upload(String title, String description, String filter, String quantity)
    {
        if(title.trim().equals(""))
        {
            title = "No Title";
        }

        mTitle = title;
        mDescription = description;
        mFilter = filter;
        mQuantity = quantity;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmFilter() {
        return mFilter;
    }

    public void setmFilter(String mFilter) {
        this.mFilter = mFilter;
    }

    public String getmQuantity() {
        return mQuantity;
    }

    public void setmQuantity(String mQuantity) {
        this.mQuantity = mQuantity;
    }
}
