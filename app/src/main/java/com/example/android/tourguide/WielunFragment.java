package com.example.android.tourguide;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private static final int REQUEST_RESPONSE = 1;

    public WielunFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
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

        final ItemAdapter adapter = new ItemAdapter(getActivity(), items, R.color.tan_background);
        final ListView listview = (ListView) rootView.findViewById(R.id.list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Intent intent1 = new Intent(view.getContext(), DisplayZoom.class);
                intent1.putExtra(EXTRA_TITLE, adapter.getItem(position).getTitle());
                intent1.putExtra(EXTRA_DESCRIPTION, adapter.getItem(position).getDescription());

                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                Bitmap b = BitmapFactory.decodeResource(getResources(),adapter.getItem(position).getPicture());
                b.compress(Bitmap.CompressFormat.JPEG, 50, bs);
                intent1.putExtra(EXTRA_PICTURE,bs.toByteArray());
                startActivityForResult(intent1, REQUEST_RESPONSE);

            }
        });
        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
    }

}
