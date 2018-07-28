package com.example.tektak.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;


/**
 * Created by RADHA KARKI on 10/14/2015.
 */
public class DrawingView extends View {


    // drawing Path
    private Path drawPath;
    //drawing and canvas paint
    private Paint drawPaint,canvasPaint;
    //initial color
    private int paintColor = 0xFFFF0000;
    //canvas
    private Canvas drawCanvas;
    //canvas bitmap
    private Bitmap canvasBitmap;
    private Bitmap mBitmap;

    // brush sizes
    private float brushSize,lastBrushSize;

    // eraser
    private  boolean erase = false;


    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupDrawing();
    }

    private void setupDrawing() {
        //instantiate the drawing path and paint objects
        drawPath = new Path();
        drawPaint = new Paint();

        // initial color
        drawPaint.setColor(paintColor);

        // instantiate brush sizes
        //brushSize = getResources().getInteger(R.integer.medium_size);
        brushSize = 20;
        lastBrushSize = brushSize;

        // set initial path properties
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(brushSize);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);

        // instantiate canvas Paint object
        canvasPaint = new Paint(Paint.DITHER_FLAG);

        //load image
        //mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image).copy(Bitmap.Config.ARGB_8888,true);

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasBitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
        //drawCanvas = new Canvas(mBitmap);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        //Bitmap mbitmap = BitmapFactory.decodeResource(getResources(),R.drawable.image);
        //canvas.drawBitmap(canvasBitmap,canvas.getWidth(),canvas.getHeight(),null);
        canvas.drawBitmap(canvasBitmap,0,0,canvasPaint);
        canvas.drawPath(drawPath, drawPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //retrieve X and Y positions

        float touchX = event.getX();
        float touchY = event.getY();

        // add switch statement to respond to each movements
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                drawCanvas.drawPath(drawPath, drawPaint);
                drawPath.reset();
                break;
            default:
                return false;

        }
        invalidate();
        return true;
    }

    public void setColor (String newColor){
        invalidate();
        paintColor = Color.parseColor(newColor);
        drawPaint.setColor(paintColor);
    }

    public void setBrushSize(float newSize){
        // update size
        float pixelAmount = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                newSize,getResources().getDisplayMetrics());

        brushSize = pixelAmount;
        drawPaint.setStrokeWidth(brushSize);

    }

    public void setLastBrushSize(float lastSize){
        lastBrushSize = lastSize;
    }
    public float getLastBrushSize(){
        return lastBrushSize;
    }

    public void setErase (boolean isErase){
        erase = isErase;
        if (erase) {
            drawPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

        } else {
            drawPaint.setXfermode(null);
        }
    }

    public  void startNew (){
        drawCanvas.drawColor(0,PorterDuff.Mode.CLEAR);
        invalidate();
    }
}
