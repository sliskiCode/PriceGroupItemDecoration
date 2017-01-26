package com.sample.piotrslesarew.pricegroupitemdecoration;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import static android.R.color.black;
import static android.R.color.darker_gray;

public class MainActivity extends AppCompatActivity {

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
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView swatches = ((RecyclerView) findViewById(R.id.recycler));
        MyAdapter adapter = new MyAdapter(data);

        swatches.setHasFixedSize(true);
        swatches.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        swatches.setAdapter(adapter);
        swatches.addItemDecoration(new PriceGroupItemDecoration(adapter));
    }
}
