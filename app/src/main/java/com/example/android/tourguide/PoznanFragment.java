package com.example.android.tourguide;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class PoznanFragment extends Fragment {private MediaPlayer mMediaPlayer;
    private Context mContext;
    private String LOG = "Colors";


    public PoznanFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list, container, false);

        // Create an array of items
        final ArrayList<Item> items = new ArrayList<Item>();

        items.add(new Item(R.drawable.church01,"Church", "Gothic church of Saint Josephs"));
        items.add(new Item(R.drawable.church02,"Church", "Gothic church of Saint Mary"));
        items.add(new Item(R.drawable.fountain,"Fountain", "Fountain at the market square"));
        items.add(new Item(R.drawable.medieval_walls,"Wals", "Rebuild medieval walls"));
        items.add(new Item(R.drawable.museum,"Museum", "Museum"));
        items.add(new Item(R.drawable.townhall,"Townhall", "Rebuild medieval town hall"));

        ItemAdapter adapter = new ItemAdapter(getActivity(), items, R.color.tan_background);
        final ListView listview = (ListView) rootView.findViewById(R.id.list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                getView();

            }
        });
        return rootView;
    }
    //    public View getView(int position, View convertView, ViewGroup parent) {
//        ImageView imageView = (ImageView)convertView;
//        if (imageView == null) {
//            imageView = new ImageView(mContext);
//        }
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inSampleSize = 0;
//        Bitmap bm = BitmapFactory.decodeFile(thumbnails.get(i), options);
//        imageView.setImageBitmap(bm);
//        imageView.setLayoutParams(new Gallery.LayoutParams(300, 3000));
//        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//        return imageView;
//    }
    @Override
    public void onStop() {
        super.onStop();
    }


}

