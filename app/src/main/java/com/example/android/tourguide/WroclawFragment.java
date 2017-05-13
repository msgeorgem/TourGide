package com.example.android.tourguide;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;


public class WroclawFragment extends Fragment {
    public static final String EXTRA_PICTURE = "EXTRA_PICTURE";
    public static final String EXTRA_TITLE = "EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "EXTRA_DESCRIPTION";
    public static final String TAG = "Wroclaw_Fragment";
    private static final int REQUEST_RESPONSE = 1;
    public ListView listview;
    public ItemAdapter adapter;
    Parcelable state;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

    public WroclawFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list, container, false);

        // Create an array of items
        final ArrayList<Item> items = new ArrayList<Item>();

        items.add(new Item(R.drawable.church_tower_view_wro, "Tower view", "Tower view"));
        items.add(new Item(R.drawable.flight_wro, "Sky view", "Sky view"));
        items.add(new Item(R.drawable.market_square_wro, "Market Square", "Market Square"));
        items.add(new Item(R.drawable.ostrow_wro, "Island on river", "Island on river"));
        items.add(new Item(R.drawable.sky_tower_wro, "Museum", "Museum"));
        items.add(new Item(R.drawable.sky_tower_wro, "Museum", "Museum"));
        items.add(new Item(R.drawable.sky_tower_wro, "Museum", "Museum"));
        items.add(new Item(R.drawable.sky_tower_wro, "Museum", "Museum"));
        items.add(new Item(R.drawable.flight_wro, "Sky view", "Sky view"));

        adapter = new ItemAdapter(getActivity(), items, R.color.tan_background);
        listview = (ListView) rootView.findViewById(R.id.list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                view.startAnimation(buttonClick);

                Intent intent1 = new Intent(view.getContext(), DisplayZoom.class);
                intent1.putExtra(EXTRA_TITLE, adapter.getItem(position).getTitle());
                intent1.putExtra(EXTRA_DESCRIPTION, adapter.getItem(position).getDescription());

                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                Bitmap b = BitmapFactory.decodeResource(getResources(), adapter.getItem(position).getPicture());
                b.compress(Bitmap.CompressFormat.JPEG, 25, bs);
                intent1.putExtra(EXTRA_PICTURE, bs.toByteArray());
                startActivityForResult(intent1, REQUEST_RESPONSE);

            }
        });
        return rootView;
    }

    @Override
    public void onPause() {
        // Save ListView state @ onPause
        Log.d(TAG, "saving listview state @ onPause");
        state = listview.onSaveInstanceState();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

        if (getAdapter() != null) {
            listview.setAdapter(getAdapter());
            if (state != null) {
                listview.requestFocus();
                listview.onRestoreInstanceState(state);
            }
        }
    }

    public ItemAdapter getAdapter() {
        return adapter;
    }

    @Override
    public void onStop() {
        super.onStop();
    }

}
