package com.example.tektak.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.UUID;

public class DrawingActivity_2 extends AppCompatActivity {

    private DrawingView drawingView;
    private ImageButton currPaint;
    //private float smallBrush,mediumBrush,largeBrush;
    private boolean erase = false;
    private ImageButton brush;
    private ImageButton eraserer;
    private String currentColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawingpage);
        drawingView = (DrawingView) findViewById(R.id.drawing);
        LinearLayout paintLayout = (LinearLayout) findViewById(R.id.paint_colors);
        currPaint = (ImageButton) paintLayout.getChildAt(0);
        currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
        brush = (ImageButton) findViewById(R.id.brush);
        eraserer = (ImageButton) findViewById(R.id.eraser);
        drawingView.setBrushSize(20);
        currentColor = "#FFFF0000";

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void colorClicked(View view) {

        if (view != currPaint) {
            drawingView.setErase(false);
            drawingView.setBrushSize(drawingView.getLastBrushSize());
            ImageButton imgView = (ImageButton) view;
            String color = view.getTag().toString();
            currentColor = color;
            drawingView.setColor(color);
            imgView.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
            currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint));
            currPaint = (ImageButton) view;
        }


    }
    public void brushSize(View view) {
        brush.setBackgroundColor(Color.GREEN);
        eraserer.setBackgroundColor(Color.BLUE);
        drawingView.setErase(false);
        drawingView.setColor(currentColor);
    }
    public void eraserAction(View view)
    {
        //drawingView.setErase(true);

        drawingView.setColor("#FFFFFFFF");
        brush.setBackgroundColor(Color.BLUE);
        eraserer.setBackgroundColor(Color.GREEN);
    }


    public void clearAll(View view) {
        drawingView.startNew();

        brush.setBackgroundColor(Color.GREEN);
        eraserer.setBackgroundColor(Color.BLUE);
        drawingView.setColor(currentColor);
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        drawingView.setDrawingCacheEnabled(true);
        String imgSaved = MediaStore.Images.Media.insertImage(
                getContentResolver(),drawingView.getDrawingCache(),
                UUID.randomUUID().toString()+".png","drawing");
        if (imgSaved != null) {
            Toast.makeText(getApplicationContext(), "Drawing saved to Gallery!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(),"Image Couldn't be saved",Toast.LENGTH_SHORT).show();
        }
        drawingView.destroyDrawingCache();

    }
}
