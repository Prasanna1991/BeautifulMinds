package com.example.tektak.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SelectBook extends AppCompatActivity {

    private DBRegistLogin db;
    private CircleImageView childphoto;
    private TextView childName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_book);
        Bundle bundle = getIntent().getExtras();
        final String stuff = bundle.getString("color");
        final String info  = bundle.getString("info");
        LinearLayout back = (LinearLayout) findViewById(R.id.colorDash);
        back.setBackgroundColor(Color.parseColor(stuff));

        // get child photo
        childphoto = (CircleImageView) findViewById(R.id.childphoto_color);
        childName = (TextView) findViewById(R.id.childname_color);
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

    public void colorOneClicked(View view) {
        Intent myIntent = new Intent(this,colorPage.class);
        myIntent.putExtra("bookSource",R.drawable.colorpic1);
        startActivity(myIntent);
    }

    public void colorTwoClicked(View view) {
        Intent myIntent = new Intent(this,colorPage.class);
        myIntent.putExtra("bookSource",R.drawable.colorpic2);
        startActivity(myIntent);

    }

    public void colorThreeClicked(View view) {
        Intent myIntent = new Intent(this,colorPage.class);
        myIntent.putExtra("bookSource",R.drawable.colorpic3);
        startActivity(myIntent);
    }
}
