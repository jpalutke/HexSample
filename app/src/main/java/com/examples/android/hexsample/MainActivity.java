package com.examples.android.hexsample;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private View mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // the view associated with the map
        mapView = findViewById(R.id.map);

    }

    private void map_onDraw(Canvas canvas) {
        int w = canvas.getWidth();
        int h = canvas.getHeight();
        int tileSize = 0;
        if (w < h) {
            tileSize = w/20;
        } else {
            tileSize = h/20;
        }
        int i = 0;
        for (i=0;i<24;i++) {

        }
    }
}
