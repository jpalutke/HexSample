package com.examples.android.hexsample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;

class MapViewPort extends android.support.v7.widget.AppCompatImageView {

    private static Paint paint;
    private int mColWidth = 0;
    private int mRowHeight = 0;
    private int mTileSize = 0;
    private Bitmap emptyHex;
    private Size mapSize = new Size(5, 10);

    public MapViewPort(Context context, AttributeSet attrs) {
        super(context, attrs, 1);

        // Create the blank Hex for map drawing
        emptyHex = ((BitmapDrawable) getResources().getDrawable(R.drawable.hex_blank_64x54_transparency)).getBitmap();
        mColWidth = emptyHex.getWidth() - 2;
        mRowHeight = emptyHex.getHeight() - 2;

        paint = new Paint();
        paint.setStrokeWidth(3);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
    }

    public boolean isOdd(int val) {
        return (val & 0x01) != 0;
    }

    public boolean isEven(int val) {
        return (val & 0x01) != 1;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int cX, cY;
        double dX, dY;

        for (int x = 0; x < mapSize.X; x++) {
            for (int y = 0; y < mapSize.Y; y++) {
                if (isOdd(y)) {
                    cX = (int) (x * mColWidth * 1.5 + mColWidth * 0.75);
                    cY = y * mRowHeight / 2;
                } else {
                    cX = (int) (x * mColWidth * 1.5);
                    cY = y * mRowHeight / 2;
                }
                canvas.drawBitmap(emptyHex, cX, cY, paint);

            }
        }
    }
}
