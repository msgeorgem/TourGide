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


public class CzestochowaFragment extends Fragment {
    public static final String EXTRA_PICTURE = "EXTRA_PICTURE";
    public static final String EXTRA_TITLE = "EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "EXTRA_DESCRIPTION";
    private static final int REQUEST_RESPONSE = 1;

    public CzestochowaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list, container, false);

        // Create an array of items
        final ArrayList<Item> items = new ArrayList<Item>();

        items.add(new Item(R.drawable.church, "Church", "Gothic church of xxxx"));
        items.add(new Item(R.drawable.jasnagora, "Jasna Góra", "Famous Catherdal of Saint Mary"));
        items.add(new Item(R.drawable.jasnagora01, "Jasna Góra", "Famous Catherdal of Saint Mary"));
        items.add(new Item(R.drawable.maxresdefault, "YA Church", "Yet another church"));
        items.add(new Item(R.drawable.speedway, "Speedway Stadium", "Speedway Stadium is a favourite stadium for locals who enjoy watching their local speedway team"));

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
                Bitmap b = BitmapFactory.decodeResource(getResources(), adapter.getItem(position).getPicture());
                b.compress(Bitmap.CompressFormat.JPEG, 25, bs);
                intent1.putExtra(EXTRA_PICTURE, bs.toByteArray());
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
