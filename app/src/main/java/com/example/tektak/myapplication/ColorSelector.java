package com.example.tektak.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class ColorSelector extends AppCompatActivity {

    private ImageView Red, Blue, Green, Yellow, Cyan, Orange, Purple, Lemon, Grey, Brown;

//    //set Background Color
//    Bundle bundle = getIntent().getExtras();
//    final String stuff = bundle.getString("color");
//    RelativeLayout back = (RelativeLayout) findViewById(R.id.childDash);
//    back.setBackgroundColor(Color.parseColor(stuff));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_selector);

        Red = (ImageView) findViewById(R.id.makeRed);
        Green = (ImageView) findViewById(R.id.makeGreen);
        Yellow = (ImageView) findViewById(R.id.makeYellow);

        Blue = (ImageView) findViewById(R.id.makeBlue);
        Grey = (ImageView) findViewById(R.id.makeGrey);
        Purple = (ImageView) findViewById(R.id.makePurple);

        Brown = (ImageView) findViewById(R.id.makeBrown);
        Lemon = (ImageView) findViewById(R.id.makeLemon);
        Orange = (ImageView) findViewById(R.id.makeOrange);

        /// getting basic info
        Bundle bundle = getIntent().getExtras();
        final String username = bundle.getString("info");

        Red.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ColorSelector.this, ChildDashboard.class);
                //pass into the child dashboard
                Bundle bundle = new Bundle();
                bundle.putString("color", "#ed4c4c");
                bundle.putString("info",username);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        Green.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ColorSelector.this, ChildDashboard.class);
                //pass into the child dashboard
                Bundle bundle = new Bundle();
                bundle.putString("color", "#7eb95c");
                bundle.putString("info",username);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        Yellow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ColorSelector.this, ChildDashboard.class);
                //pass into the child dashboard
                Bundle bundle = new Bundle();
                bundle.putString("color", "#f4d720");
                bundle.putString("info",username);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        Blue.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ColorSelector.this, ChildDashboard.class);
                //pass into the child dashboard
                Bundle bundle = new Bundle();
                bundle.putString("color", "#3350b1");
                bundle.putString("info",username);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        Grey.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ColorSelector.this, ChildDashboard.class);
                //pass into the child dashboard
                Bundle bundle = new Bundle();
                bundle.putString("color", "#9c9898");
                bundle.putString("info",username);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        Purple.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ColorSelector.this, ChildDashboard.class);
                //pass into the child dashboard
                Bundle bundle = new Bundle();
                bundle.putString("color", "#7770ab");
                bundle.putString("info",username);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        Brown.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ColorSelector.this, ChildDashboard.class);
                //pass into the child dashboard
                Bundle bundle = new Bundle();
                bundle.putString("color", "#3d0707");
                bundle.putString("info",username);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        Lemon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ColorSelector.this, ChildDashboard.class);
                //pass into the child dashboard
                Bundle bundle = new Bundle();
                bundle.putString("color", "#c0fa9e");
                bundle.putString("info",username);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        Orange.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ColorSelector.this, ChildDashboard.class);
                //pass into the child dashboard
                Bundle bundle = new Bundle();
                bundle.putString("color", "#f8a72e");
                bundle.putString("info",username);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }

}
