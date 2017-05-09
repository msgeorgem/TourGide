package com.example.android.tourguide;

/**
 * Created by Marcin on 2017-05-05.
 */

public class Item {

    private static final int NO_IMAGE_PROVIDED = -1;
    private int mPicture = NO_IMAGE_PROVIDED;
    private String mTitle;
    private String mDescription;


    public Item(int picture, String title, String description) {
        mPicture = picture;
        mTitle = title;
        mDescription = description;
    }

    public int getPicture() { return mPicture; }
    public String getTitle() {
        return mTitle;
    }
    public String getDescription() {
        return mDescription;
    }
    /**
     * Returns whether or not there is an image for this item.
     */
    public boolean hasImage() {
        return mPicture != NO_IMAGE_PROVIDED;
    }

    @Override
    public String toString() {
        return "Item{" +
                "mPicture='" + mPicture + '\'' +
                ", mTitle='" + mTitle + '\'' +
                ", mDescription=" + mDescription +
                '}';
    }
}
