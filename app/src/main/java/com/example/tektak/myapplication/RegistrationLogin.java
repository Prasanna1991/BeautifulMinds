package com.example.tektak.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegistrationLogin extends AppCompatActivity {

    private Button login;
    private EditText username, password;
    private EditText text1, text2;
    String user_name, pass_word;
    private DBRegistLogin db;
    private DBStatusLogin db1;
    TextView success, fail, registration;
    private EditText tempText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_registration_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.pasword);
        db = new DBRegistLogin(this);
        db1=new DBStatusLogin(this);

        fail = (TextView) findViewById(R.id.loginWarn);
        fail.setVisibility(View.GONE);

        login=(Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_name = username.getText().toString();
                pass_word = password.getText().toString();
                if (db.checkUserNamePasW(user_name, pass_word)) {
//                    success.setVisibility(View.VISIBLE);

//                    System.out.println(user_name);
                    Log.d("My tag", user_name);
                    db1.login_status(user_name);
                    Intent intent = new Intent(RegistrationLogin.this, SecondLogin.class);

                    //pass login info to another activity
                    Bundle bundle = new Bundle();
                    bundle.putString("stuff", user_name);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else {
                    fail.setVisibility(View.VISIBLE);
                }
            }

        });

        registration=(TextView) findViewById(R.id.registration);
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationLogin.this, Registration.class);
                startActivity(intent);
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registration_login, menu);
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
}
