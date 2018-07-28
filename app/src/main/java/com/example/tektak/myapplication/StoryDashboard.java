package com.example.tektak.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class StoryDashboard extends AppCompatActivity {

    public ViewPager viewPager;
    ImageButton imgButton;
    ImageButton img;
    ImageButton img3;
    MediaPlayer mysound;
    MediaPlayer mysound1;
    MediaPlayer mysound2;
    private DBRegistLogin db;
    private CircleImageView childphoto;
    private TextView childName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_story);
        Bundle bundle = getIntent().getExtras();
        final String stuff = bundle.getString("color");
        final String info  = bundle.getString("info");
        LinearLayout back = (LinearLayout) findViewById(R.id.storyDash);
        back.setBackgroundColor(Color.parseColor(stuff));

        // get child photo
        childphoto = (CircleImageView) findViewById(R.id.childphoto_story);
        childName = (TextView) findViewById(R.id.childname_story);
        db = new DBRegistLogin(this);
        List<String> pathsToPhoto = new ArrayList<>();
        pathsToPhoto = db.getPathToPhoto(info);
        Uri selectedImageUriChild = Uri.parse(pathsToPhoto.get(1));
        childphoto.setImageURI(selectedImageUriChild);


        // Log.v("Info",info);
//        //to get Child Name
        String child_Name = db.getChildName(info);
        //Log.v("Child Name",child_Name);
        childName.setText("Hello " + child_Name);


        mysound = MediaPlayer.create(this, R.raw.fox);
        mysound1 = MediaPlayer.create(this, R.raw.lion);
        mysound2 = MediaPlayer.create(this, R.raw.monkey);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        addButtonListener();
        addButtonListener1();
        addButtonListener2();
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

    private void addButtonListener() {
        imgButton = (ImageButton) findViewById(R.id.imageButton4);

        imgButton.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                mysound.start();
                Thread timerThread = new Thread() {
                    public void run() {
                        try {

                            sleep(1800);
                            mysound.stop();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            Intent intent = new Intent(StoryDashboard.this, story1.class);
                            startActivity(intent);
                        }

                    }


                };
                timerThread.start();

            }
        });
    }
    private void addButtonListener1() {
        img = (ImageButton) findViewById(R.id.imageButton5);

        img.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                mysound1.start();
                Thread timerThread = new Thread() {
                    public void run() {
                        try {
                            sleep(1800);
                            mysound1.stop();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            Intent intent = new Intent(StoryDashboard.this, story2.class);
                            startActivity(intent);
                        }
                    }


                };
                timerThread.start();

            }
        });
    }

    private void addButtonListener2() {
        img3 = (ImageButton) findViewById(R.id.imageButton6);

        img3.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                mysound2.start();
                Thread timerThread = new Thread() {
                    public void run() {
                        try {

                            sleep(2500);
                            mysound2.stop();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            Intent intent = new Intent(StoryDashboard.this, story3.class);
                            startActivity(intent);
                        }

                    }


                };
                timerThread.start();

            }
        });
    }

   /* public void buttonOnClick(View view) {

        String button_text;
        button_text = ((Button) view).getText().toString();
        {
            if (button_text.equals("FOX AND CROW")) {
                mysound.start();
                Thread timerThread = new Thread(){
                    public void run(){
                        try{
                            sleep(1800);
                            mysound.stop();
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }finally{
                            Intent intent = new Intent(StoryDashboard.this,story1.class);
                            startActivity(intent);
                        }
                    }
                };
                timerThread.start();
            }
            if (button_text.equals("LION AND MOUSE")) {
                mysound1.start();
                Thread timerThread = new Thread(){
                    public void run(){
                        try{
                            sleep(1800);
                            mysound1.stop();
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }finally{
                            Intent intent = new Intent(StoryDashboard.this,story2.class);
                            startActivity(intent);
                        }
                    }
                };
                timerThread.start();
            }


        }

    }*/


}
