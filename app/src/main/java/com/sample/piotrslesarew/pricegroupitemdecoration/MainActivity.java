package com.sample.piotrslesarew.pricegroupitemdecoration;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import static android.R.color.black;
import static android.R.color.darker_gray;

public class MainActivity extends AppCompatActivity {

    private RecyclerView swatches;

    @SuppressWarnings("ResourceAsColor")
    private Color[] data = new Color[]{
            new Color(darker_gray, 20.0),
            new Color(black, 20.0),
            new Color(darker_gray, 20.0),
            new Color(black, 20.0),
            new Color(darker_gray, 20.0),
            new Color(black, 20.0),
            new Color(darker_gray, 50.0),
            new Color(black, 50.0),
            new Color(darker_gray, 50.0),
            new Color(black, 50.0),
            new Color(darker_gray, 50.0),
            new Color(black, 50.0),
            new Color(darker_gray, 50.0),
            new Color(black, 50.0),
            new Color(darker_gray, 50.0),
            new Color(black, 50.0),
            new Color(darker_gray, 60.0),
            new Color(black, 60.0),
            new Color(darker_gray, 60.0),
            new Color(black, 60.0),
            new Color(darker_gray, 60.0),
            new Color(black, 60.0),
            new Color(darker_gray, 60.0),
            new Color(black, 60.0),
            new Color(darker_gray, 60.0),
            new Color(black, 60.0)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swatches = ((RecyclerView) findViewById(R.id.recycler));
        swatches.setHasFixedSize(true);
        swatches.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        MyAdapter adapter = new MyAdapter(data);
        swatches.setAdapter(adapter);
        swatches.addItemDecoration(new PriceGroupItemDecoration(adapter));
    }
}
