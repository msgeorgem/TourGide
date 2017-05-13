package com.example.android.tourguide;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Marcin on 2017-05-08.
 */

public class ItemAdapter extends ArrayAdapter<Item> {
    /**
     * Resource ID for the background color for this list of words
     */
    private int mColorResourceId;


    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context   The current context. Used to inflate the layout file.
     * @param listItems A List of AndroidFlavor objects to display in a list
     */
    public ItemAdapter(Activity context, ArrayList<Item> listItems, int colorResourceId) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, listItems);
        mColorResourceId = colorResourceId;
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The position in the list of data that should be displayed in the
     *                    list item view.
     * @param convertView The recycled view to populate.
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Get the {@link WItem} object located at this position in the list
        Item currentItem = getItem(position);


        viewHolder.titleTextView.setText(currentItem.getTitle());
        viewHolder.descriptionTextView.setText(currentItem.getDescription());

        // Get the image resource ID from the current AndroidFlavor object and
        // set the image to iconView
        // Check if an image is provided for this word or not
        if (currentItem.hasImage()) {
            // If an image is available, display the provided image based on the resource ID
            viewHolder.iconView.setImageResource(currentItem.getPicture());
            // Make sure the view is visible
            viewHolder.iconView.setVisibility(View.VISIBLE);
        } else {
            // Otherwise hide the ImageView (set visibility to GONE)
            viewHolder.iconView.setVisibility(View.GONE);
        }
        // Set the theme color for the list item
        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        // Set the background color of the text container View
        viewHolder.textContainer.setBackgroundColor(color);

        return convertView;
    }

    class ViewHolder {
        private TextView titleTextView;
        private TextView descriptionTextView;
        private ImageView iconView;
        private View textContainer;

        public ViewHolder(@NonNull View view) {
            this.titleTextView = (TextView) view
                    .findViewById(R.id.title_text_view);
            this.descriptionTextView = (TextView) view
                    .findViewById(R.id.description_text_view);
            this.iconView = (ImageView) view
                    .findViewById(R.id.icon);
            this.textContainer = view.findViewById(R.id.text_container);
        }
    }
}
