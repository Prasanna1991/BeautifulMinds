package com.example.tektak.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Registration extends AppCompatActivity {
    private Button registration;
//    private EditText userName, passWord, name, childName;
    private EditText yourName, childName, eMail, passWord, confrmPassword, userName;
    private DBRegistLogin db;
    private DBStatusLogin db1;
    private TextView thankyou, incorrectPassword, statusR;

    //for spinner
    private Spinner relationship_dropdown, gender_dropdown;

    //for image picking
    private static final int SELECT_PICTURE1 = 101;
    private static final int SELECT_PICTURE2 = 201;
    public static final String IMAGE_TYPE = "image/*";
    private ImageView selectedImagePreview1, selectedImagePreview2;
    private String pathToPhoto1, pathToPhoto2;

    //for tick on terms and conditions
    private CheckBox checkBox;

    boolean yName, cName, eMal, uName, pWord, pCheck, rLation, gNder, pHot1, pHot2, tick, status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration1);

        yourName = (EditText) findViewById(R.id.yourName);
        childName = (EditText) findViewById(R.id.childName);
        eMail = (EditText) findViewById(R.id.eMail);
        userName = (EditText) findViewById(R.id.userName);
        passWord = (EditText) findViewById(R.id.passWordRegist);
        confrmPassword = (EditText) findViewById(R.id.passWordRegistConfrm);
        checkBox=(CheckBox) findViewById(R.id.checkBox);

        incorrectPassword=(TextView) findViewById(R.id.confirmPasswordCheck);
        incorrectPassword.setVisibility(View.GONE);

        statusR=(TextView) findViewById(R.id.statusRaise);
        statusR.setVisibility(View.GONE);

        db = new DBRegistLogin(this);
        db1 = new DBStatusLogin(this);

        //for spinner
        addItemsOnRelationshipDropDown();
        addItemsOnGenderDropDown();
//        addListenerOnSpinnerItemSelection();

        //for image picking
        findViewById(R.id.browseImage1).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // in onCreate or any event where your want the user to
                // select a file
                Intent intent = new Intent();
                intent.setType(IMAGE_TYPE);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        "Select picture"), SELECT_PICTURE1);
            }
        });
        selectedImagePreview1 = (ImageView) findViewById(R.id.browseImage1);

        findViewById(R.id.browseImage2).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // in onCreate or any event where your want the user to
                // select a file
                Intent intent = new Intent();
                intent.setType(IMAGE_TYPE);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        "Select picture"), SELECT_PICTURE2);
            }
        });
        selectedImagePreview2 = (ImageView) findViewById(R.id.browseImage2);

        registration=(Button) findViewById(R.id.register);
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                yName = true;
                cName = true;
                eMal = true;
                uName = true;
                pWord = true;
                pCheck = true;
                tick = true;
                status = true;

                if(yourName.getText().toString().equals(null)||yourName.getText().toString().equals(""))
                    yName=false;

                if(childName.getText().toString().equals(null)||childName.getText().toString().equals(""))
                    cName=false;

                if(eMail.getText().toString().equals(null)||eMail.getText().toString().equals(""))
                    eMal=false;

                if(userName.getText().toString().equals(null)||userName.getText().toString().equals(""))
                    uName=false;

                if(passWord.getText().toString().equals(null)||passWord.getText().toString().equals(""))
                    pWord=false;

                if(!passWord.getText().toString().equals(confrmPassword.getText().toString())) {
                    incorrectPassword.setVisibility(View.VISIBLE);
                    pCheck = false;
                }

                if(!checkBox.isChecked())
                    tick=false;

                if(yName==false || cName==false || eMal==false || uName == false || pWord == false || pCheck == false || tick == false) {
                    status = false;
                    statusR.setVisibility(View.VISIBLE);
                }


                if(status) {
                    db.insertData(userName.getText().toString(), passWord.getText().toString(), yourName.getText().toString(), String.valueOf(relationship_dropdown.getSelectedItem()),
                            childName.getText().toString(), pathToPhoto1, pathToPhoto2, eMail.toString(), String.valueOf(gender_dropdown.getSelectedItem()));
                    db1.insertData();
                    Intent intent = new Intent(Registration.this, RegistrationLogin.class);
                    startActivity(intent);
                }
                else
                {
                    statusR.setVisibility(View.VISIBLE);
                }
            }

        });

    }

    //add items on relationship dropdown spinner dynamically
    public void addItemsOnRelationshipDropDown(){
        relationship_dropdown = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.relationship, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        relationship_dropdown.setAdapter(adapter);
    }

    //add items on relationship dropdown spinner dynamically
    public void addItemsOnGenderDropDown(){
        gender_dropdown = (Spinner) findViewById(R.id.spinnerGender);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        gender_dropdown.setAdapter(adapter);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode ==RESULT_OK){
            Uri selectedImageUri = data.getData();
            if (requestCode == 101) {
                try {
                    selectedImagePreview1.setImageBitmap(new UserPicture(selectedImageUri, getContentResolver()).getBitmap());
                    pathToPhoto1 = String.valueOf(selectedImageUri);
                } catch (IOException e) {
                    Log.e(Registration.class.getSimpleName(), "Failed to load image", e);
                }
            }
            else if(requestCode==201){
                try {
                    selectedImagePreview2.setImageBitmap(new UserPicture(selectedImageUri, getContentResolver()).getBitmap());
                    pathToPhoto2 = String.valueOf(selectedImageUri);
                } catch (IOException e) {
                    Log.e(Registration.class.getSimpleName(), "Failed to load image", e);
                }
            }
        }
        else {
            //report failure
            Toast.makeText(getApplicationContext(), "Failed to get Intent data", Toast.LENGTH_LONG).show();
            Log.d(Registration.class.getSimpleName(), "Failed to get intent data, result code is " + resultCode);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registration, menu);
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
