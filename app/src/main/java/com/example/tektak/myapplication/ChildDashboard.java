package com.example.tektak.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pkmmte.view.CircularImageView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChildDashboard extends AppCompatActivity {

    private String colorName;
    private String information;
    private DBRegistLogin db;
    private CircleImageView childphoto;
    private TextView childName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_dashboard);
        Bundle bundle = getIntent().getExtras();
        final String stuff = bundle.getString("color");
        final String info  = bundle.getString("info");
        this.colorName = stuff;
        this.information = info;
        LinearLayout back = (LinearLayout) findViewById(R.id.childDash);
        back.setBackgroundColor(Color.parseColor(stuff));

        // get child photo
        childphoto = (CircleImageView) findViewById(R.id.childphoto);
        childName = (TextView) findViewById(R.id.childname);
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_child_dashboard, menu);
        return true;
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

    public void goToStory(View view) {
        Intent intent = new Intent(ChildDashboard.this,StoryDashboard.class);
        intent.putExtra("color",colorName);
        intent.putExtra("info",information);
        startActivity(intent);
    }

    public void goToMusic(View view) {
        Intent intent = new Intent(ChildDashboard.this,MusicActivity.class);
        intent.putExtra("color",colorName);
        startActivity(intent);
    }

    public void goToDrawing(View view) {
        Intent myIntent = new Intent(ChildDashboard.this,DrawingActivity_2.class);
        startActivity(myIntent);
    }

    public void goToColor(View view) {
        Intent myIntent = new Intent(ChildDashboard.this,SelectBook.class);
        myIntent.putExtra("color",colorName);
        myIntent.putExtra("info",information);
        startActivity(myIntent);
    }
}
