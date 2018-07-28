package com.example.tektak.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Splash extends AppCompatActivity {

    private ImageView splash_pic;
    private DBStatusLogin db;
    private boolean flag;
    private TextView test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.activity_splash);


        db = new DBStatusLogin(this);
        splash_pic=(ImageView) findViewById(R.id.splash_img);

        //test = (TextView) findViewById(R.id.testLoginStatus);

        splash_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = db.check_login_status();
                if (flag) {
                    //test.setText("True");
                    Intent intent = new Intent(Splash.this, SecondLogin.class);

                    String username = db.getUserName();
                    Log.d("helloFromSplash", username);

                    //pass login info to another activity
                    Bundle bundle = new Bundle();
                    bundle.putString("stuff", username);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    //test.setText("false");
                    Intent intent = new Intent(Splash.this, RegistrationLogin.class);
                    startActivity(intent);
                }

            }

        });

    }

}
