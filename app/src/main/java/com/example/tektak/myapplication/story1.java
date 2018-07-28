package com.example.tektak.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class story1 extends AppCompatActivity {

    MediaPlayer mysound;


    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story1);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        mysound = MediaPlayer.create(this, R.raw.music);
        mysound.start();
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        PagerAdapter adapter = new customactivity(this);
        viewPager.setAdapter(adapter);
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

    @Override
    protected void onPause() {
        super.onPause();
        mysound.release();
    }
    public void buttonOnClick(View view) {
        String button_text;
        button_text = ((Button) view).getText().toString();
        {
            if (button_text.equals("DASHBOARD")) {
                Intent intent = new Intent(this,StoryDashboard.class);
                startActivity(intent);


            }
        }

    }
}
