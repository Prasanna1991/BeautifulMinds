package com.example.tektak.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

public class colorPage extends AppCompatActivity {

    private ColorBookColor colorBookColor;
    private ImageButton currColor;
    private boolean erase = false;
    public ProgressBar pbFloodFill;
    private int resID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the activity to full screen mode.
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //        WindowManager.LayoutParams.FLAG_FULLSCREEN);


        Intent iin = getIntent();
        Bundle bundle = iin.getExtras();
        if (bundle != null){
            resID = bundle.getInt("bookSource");
        }
        else{
            resID = R.drawable.colorbookpic1;
        }
        setContentView(R.layout.colorpage);
        FrameLayout colorBody = (FrameLayout) findViewById(R.id.colorBody);
        LinearLayout paintLayout = (LinearLayout) findViewById(R.id.paint_colors_colorbook);
        currColor = (ImageButton) paintLayout.getChildAt(0);
        currColor.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));

        //Log.v("ColorBody", Integer.toString(colorBody.getHeight()));


        // Create the progressbar view
        pbFloodFill = new ProgressBar(this);
        // Give the progressbar some parameters.
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.RIGHT;

        pbFloodFill.setLayoutParams(params);
        pbFloodFill.setIndeterminate(true);
        pbFloodFill.setVisibility(View.GONE);

        colorBody.requestFocus();
        //colorBookColor = (ColorBookColor) findViewById(R.id.colorBook);
        loadImage();

        FrameLayout.LayoutParams paramsImage = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.CENTER;
        colorBookColor.setLayoutParams(paramsImage);
        colorBody.addView(colorBookColor);
        colorBody.addView(pbFloodFill);
        //colorBookColor = (ColorBookColor) findViewById(R.id.colorBook);
        loadBrushes();



    }

    private void loadImage() {
        Bitmap picture = decodeImage(resID);
       if (colorBookColor == null) {
            colorBookColor = new ColorBookColor(this, picture.getWidth(), picture.getHeight(),resID);
            //colorBookColor = new ColorBookColor(this, width, height);
        //    colorBookColor = new ColorBookColor(this);
        }
        if (colorBookColor.pathCanvas !=null){
            colorBookColor.clear();
        }
        colorBookColor.getPictureBitmapBuffer = picture;
        colorBookColor.paintBitmapName = "coloring_book_1_image_1";


    }

    /**
     * Resizes the image if it is too big for the screen. This should almost
     * never really be needed if the proper images are supplied to the drawable
     * folders. However, in practice this may not be the case and therefore,
     * this is used as a protection against these bad cases.
     */
    private Bitmap decodeImage(int resId) {
        // Get the screen width and height.
        /*
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float screenWidth = dm.widthPixels;
        float screenHeight = dm.heightPixels;

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(),
                resId, options);

        int inSampleSize = 1;
        int imageWidth = options.outWidth;
        int imageHeight = options.outHeight;

       // Log.v("imageWidth", Integer.toString(imageWidth));
        //Log.v("imageHeight", Integer.toString(imageHeight));
        //Log.v("Width", Float.toString(screenWidth));
        //Log.v("Height", Float.toString(screenHeight));

        // If the scale fails, we will need to use more memory to perform
        // scaling for the layout to work on all size screens.
        boolean scaleFailed = false;
        Bitmap scaledBitmap = null;
        float resizeRatioHeight = 1;

        // THIS IS DESIGNED FOR FITTING ON THE SCREEN WITH NO SCROLLBAR

        // Scale down if the image width exceeds the screen width.
        if (imageWidth > screenWidth || imageHeight > screenHeight) {

            // If we need to resize the image because the width or height is too
            // big, get the resize ratios for width and height.
            resizeRatioHeight = (float) imageHeight / (float) screenHeight;

            // Get the smaller ratio.
            inSampleSize = (int) resizeRatioHeight;

            if (inSampleSize <= 1) {
                scaleFailed = true;
            }
        }

        // Decode Bitmap with inSampleSize set
        options.inSampleSize = inSampleSize;
        options.inJustDecodeBounds = false;

        Bitmap picture = BitmapFactory.decodeResource(getResources(), resId, options);

        // If the scale failed, that means a scale was needed but didn't happen.
        // We need to create a scaled copy of the image by allocating more
        // memory.
        if (scaleFailed) {
            int newWidth = (int) (picture.getWidth() / resizeRatioHeight);
            int newHeight = (int) (picture.getHeight() / resizeRatioHeight);

            scaledBitmap = Bitmap.createScaledBitmap(picture, newWidth, newHeight, true);

            // Recycle the picture bitmap.
            picture.recycle();
        }
        else {
            // No scaling was needed in the first place!
            scaledBitmap = picture;
        }*/
        //BitmapFactory.Options options = new BitmapFactory.Options();
        //options.inJustDecodeBounds = true;
        //BitmapFactory.decodeResource(getResources(),
        //        resId, options);

        Bitmap picture = BitmapFactory.decodeResource(getResources(), resId);
        Bitmap scaledBitmap;
        scaledBitmap = Bitmap.createScaledBitmap(picture,1080,528,true);
        return scaledBitmap;
    }

    private MaskFilter mBlur;

    /**
     * Loads the brush and it's stylings.
     */
    public void loadBrushes() {
        colorBookColor.paint = new Paint();
        colorBookColor.paint.setAntiAlias(true);
        colorBookColor.paint.setDither(true);
        colorBookColor.paint.setColor(colorBookColor.selectedColor);
        colorBookColor.paint.setStyle(Paint.Style.STROKE);
        colorBookColor.paint.setStrokeJoin(Paint.Join.ROUND);
        colorBookColor.paint.setStrokeCap(Paint.Cap.ROUND);
        colorBookColor.paint.setStrokeWidth(12);
        mBlur = new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL);
        colorBookColor.paint.setMaskFilter(mBlur);
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
        if (view != currColor) {
            colorBookColor.isEraseModeEnabled = false;
            //drawingView.setBrushSize(drawingView.getLastBrushSize());
            ImageButton imgView = (ImageButton) view;
            String color = view.getTag().toString();
            colorBookColor.paint.setColor(Color.parseColor(color));
            colorBookColor.selectedColor = Color.parseColor(color);
            imgView.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
            currColor.setImageDrawable(getResources().getDrawable(R.drawable.paint));
            currColor = (ImageButton) view;
        }

    }

    public void onBrushClicked(View view) {
        colorBookColor.isFillEnabled = false;
        colorBookColor.isFillModeEnabled = false;
    }

    public void onFillClicked(View view) {
        colorBookColor.isFillEnabled = true;
        colorBookColor.isFillModeEnabled = true;
        colorBookColor.isEraseModeEnabled = false;
        colorBookColor.paint.setMaskFilter(mBlur);
        colorBookColor.paint.setXfermode(null);

    }

    public void onEraseClicked(View view) {
        colorBookColor.isFillEnabled = false;
        colorBookColor.isFillModeEnabled = false;
        colorBookColor.paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        colorBookColor.paint.setMaskFilter(null);
        //colorBookColor.paint.setColor(Color.WHITE);

    }

    @Override
    protected void onResume() {
        super.onResume();
        colorBookColor.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        colorBookColor.pause();
    }
}
