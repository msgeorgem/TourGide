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

/**
 * Created by Marcin on 2017-05-05.
 */


public class WielunFragment extends Fragment {
    public static final String EXTRA_PICTURE = "EXTRA_PICTURE";
    public static final String EXTRA_TITLE = "EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "EXTRA_DESCRIPTION";
    public static final String TAG = "Wielun_Fragment";
    private static final int REQUEST_RESPONSE = 1;
    public ListView listview;
    public ItemAdapter adapter;
    public int index;
    public int title;
    public int description;

    Parcelable state;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

    public WielunFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list, container, false);

        // Create an array of items
        final ArrayList<Item> items = new ArrayList<Item>();

        items.add(new Item(R.drawable.church01, R.string.church_Wielun_N, R.string.church01_Wielun));
        items.add(new Item(R.drawable.church02, R.string.church_Wielun_N, R.string.church02_Wielun));
        items.add(new Item(R.drawable.fountain, R.string.fountain_wielun_N, R.string.fountain_wielun));
        items.add(new Item(R.drawable.medieval_walls, R.string.walls_wielun_N, R.string.walls_wielun));
        items.add(new Item(R.drawable.museum, R.string.museum_wielun_N, R.string.museum_wielun));
        items.add(new Item(R.drawable.townhall, R.string.townhall_wielun_N, R.string.townhall_wielun));
        items.add(new Item(R.drawable.townhall, R.string.townhall_wielun_N, R.string.townhall_wielun));
        items.add(new Item(R.drawable.townhall, R.string.townhall_wielun_N, R.string.townhall_wielun));

        adapter = new ItemAdapter(getActivity(), items, R.color.tan_background);
        listview = (ListView) rootView.findViewById(R.id.list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                view.startAnimation(buttonClick);

                String currentItemTitleString = getString(adapter.getItem(position).getTitle());
                String currentItemDescString = getString(adapter.getItem(position).getDescription());

                Intent intent1 = new Intent(view.getContext(), DisplayZoom.class);
                intent1.putExtra(EXTRA_TITLE, currentItemTitleString);
                intent1.putExtra(EXTRA_DESCRIPTION, currentItemDescString);

                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                Bitmap b = BitmapFactory.decodeResource(getResources(), adapter.getItem(position).getPicture());
                b.compress(Bitmap.CompressFormat.JPEG, 50, bs);
                intent1.putExtra(EXTRA_PICTURE, bs.toByteArray());
                startActivityForResult(intent1, REQUEST_RESPONSE);

            }

            private Item getItem(int position) {
                return null;
            }
        });
        return rootView;
    }

    @Override
    public void onPause() {
        // Save ListView state @ onPause
        Log.d(TAG, "saving listview state @ onPause");
        state = listview.onSaveInstanceState();
        index = listview.getFirstVisiblePosition();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

        if (getAdapter() != null) {
            listview.setAdapter(getAdapter());
            listview.setSelectionFromTop(index, 0);
            if (state != null) {
                listview.requestFocus();
                listview.onRestoreInstanceState(state);
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public ItemAdapter getAdapter() {
        return adapter;
    }
}
