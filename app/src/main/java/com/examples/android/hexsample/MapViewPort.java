package com.examples.android.hexsample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import java.lang.Math;

class MapViewPort extends android.support.v7.widget.AppCompatImageView {

    private static Paint paint;
    private int mWidth = 0;
    private int mHeight = 0;
    private int mTileSize = 0;
    private Bitmap hexImage;
    private int mapSize = 5;
    private Point[] mVertices = new Point[6];

    public MapViewPort(Context context, AttributeSet attrs) {
        super(context, attrs,1);

        paint = new Paint();
        paint.setStrokeWidth(3);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);

        mWidth = this.getMeasuredWidth();
        mHeight = this.getMeasuredHeight();
        // FIXME: due to the interlocking of the hexagons, this is incorrect
        if (mWidth > mHeight) {
            mTileSize = mHeight / mapSize;
        } else {
            mTileSize = mWidth / mapSize;
        }
        for (int i=0;i<6;i++) {
            mVertices[i] = new Point();
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        // determine tile size
        // FIXME: due to the interlocking of the hexagons, this is incorrect
        // FIXME: due to the interlocking of the hexagons, this is incorrect
        if (mWidth > mHeight) {
            mTileSize = mHeight / mapSize;
        } else {
            mTileSize = mWidth / mapSize;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int cX, cY;
        double dX, dY;

        for (int x=0;x<mapSize;x++) {
            for (int y=0;y<mapSize;y++) {
                // draw one hex at (x + x/2, y + y/2)
                // Note x/2, y/2 gives us the center point of the hex
                int mRadius = mTileSize/2;
                cX = x*mTileSize + mRadius;
                cY = (int) (y*(mTileSize*0.86) + mRadius);
                //////
                // Given:
                //   hyp = mTileSize/2
                //   Sin = opp/hyp
                //   Cos = adj/hyp
                // Therefore:
                   dX = Math.abs(Math.cos(60*Math.PI/180)*mRadius);
                   dY = Math.abs(Math.sin(60*Math.PI/180)*mRadius);



                   //lets convert this to add images instead of drawing?



                //
                // calc the vertices beginning with the right most vertex
                // and working clockwise
                //////
                mVertices[0].x = cX + mRadius; mVertices[0].y = cY;
                mVertices[1].x = (int) (cX + dX); mVertices[1].y = (int) (cY-dY);
                mVertices[2].x = (int) (cX - dX); mVertices[2].y = (int) (cY-dY);
                mVertices[3].x = cX -mRadius; mVertices[3].y = cY;
                mVertices[4].x = (int) (cX - dX); mVertices[4].y = (int) (cY+dY);
                mVertices[5].x = (int) (cX + dX); mVertices[5].y = (int) (cY+dY);
                canvas.drawPoint(cX,cY,paint);
                canvas.drawLine(mVertices[0].x,mVertices[0].y,mVertices[1].x,mVertices[1].y,paint);
                canvas.drawLine(mVertices[1].x,mVertices[1].y,mVertices[2].x,mVertices[2].y,paint);
                canvas.drawLine(mVertices[2].x,mVertices[2].y,mVertices[3].x,mVertices[3].y,paint);
                canvas.drawLine(mVertices[3].x,mVertices[3].y,mVertices[4].x,mVertices[4].y,paint);
                canvas.drawLine(mVertices[4].x,mVertices[4].y,mVertices[5].x,mVertices[5].y,paint);
                canvas.drawLine(mVertices[5].x,mVertices[5].y,mVertices[0].x,mVertices[0].y,paint);
            }
        }
    }
}
