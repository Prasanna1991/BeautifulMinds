package com.example.tektak.myapplication;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SecondLogin extends AppCompatActivity {
    private CircleImageView parent, child;
    private ImageButton passwordCheck;
    private EditText password;
    private TextView incorrectMessage, childName;
    private DBRegistLogin db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_login);

        parent = (CircleImageView) findViewById(R.id.parent);
        child = (CircleImageView) findViewById(R.id.child);
        password = (EditText)findViewById(R.id.passwordSecondLogin);
        passwordCheck = (ImageButton) findViewById(R.id.passwordCheck);
        incorrectMessage = (TextView) findViewById(R.id.incorrectMessage);
//        childName =(TextView) findViewById(R.id.childNameSecondLogin);

        Bundle bundle = getIntent().getExtras();
        final String stuff = bundle.getString("stuff");

        //to get Child photo
        db = new DBRegistLogin(this);
        List<String> pathsToPhoto = new ArrayList<>();
        pathsToPhoto = db.getPathToPhoto(stuff);
        Uri selectedImageUriChild = Uri.parse(pathsToPhoto.get(1));
        child.setImageURI(selectedImageUriChild);

        //to get Parent photo
        Uri selectedImageUriParent = Uri.parse(pathsToPhoto.get(0));
        parent.setImageURI(selectedImageUriParent);

//        //to get Child Name
//        String child_Name = db.getChildName(stuff);
//        childName.setText(child_Name);

        //make the password invisible unless pressed
        password.setVisibility(View.GONE);
        passwordCheck.setVisibility(View.GONE);
        incorrectMessage.setVisibility(View.GONE);

        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password.setVisibility(View.VISIBLE);
                passwordCheck.setVisibility(View.VISIBLE);
                //open place for password section and then pass to parent dashboard
            }

        });

        child.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //pass into the child dashboard
                Intent intent = new Intent(SecondLogin.this, ColorSelector.class);
                intent.putExtra("info",stuff);
                startActivity(intent);
            }
        });

        passwordCheck.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String pass_word = password.getText().toString();
                if(db.checkUserNamePasW(stuff, pass_word)){
                    Intent intent = new Intent(SecondLogin.this, ParentDashboard.class);
                    startActivity(intent);
                }
                else{
                    //dispaly password incorrect message
                    incorrectMessage.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second_login, menu);
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
