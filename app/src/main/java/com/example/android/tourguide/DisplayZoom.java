package com.example.android.tourguide;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Marcin on 2017-05-08.
 */

public class DisplayZoom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_zoom);

        String title = getIntent().getStringExtra(WielunFragment.EXTRA_TITLE);
        TextView textViewTitle = (TextView) findViewById(R.id.title_text_view);
        textViewTitle.setText(title);

        String description = getIntent().getStringExtra(WielunFragment.EXTRA_DESCRIPTION);
        TextView textViewDescription = (TextView) findViewById(R.id.description_text_view);
        textViewDescription.setText(description);

        Bitmap picture = BitmapFactory.decodeByteArray(getIntent().getByteArrayExtra
                (WielunFragment.EXTRA_PICTURE),0,getIntent().getByteArrayExtra(WielunFragment.EXTRA_PICTURE).length);
        ImageView imageViewPicture = (ImageView) findViewById(R.id.image_view);
        imageViewPicture.setImageBitmap(picture);
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
