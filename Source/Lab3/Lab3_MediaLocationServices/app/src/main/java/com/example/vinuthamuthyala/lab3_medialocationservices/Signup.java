package com.example.vinuthamuthyala.lab3_medialocationservices;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Context;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

public class Signup extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }
    public void onContinue(View v) {
        TextView tvfirstName=(TextView)findViewById((R.id.etFirstName));
        TextView tvlastName=(TextView)findViewById((R.id.etLastName));
        TextView tvemail=(TextView)findViewById(R.id.etEmail);
        String frstNameText,lastNameText,emailText;
        frstNameText=tvfirstName.getText().toString();
        lastNameText=tvlastName.getText().toString();
        emailText=tvemail.getText().toString();
        TextView errorText = (TextView)findViewById(R.id.tvError);
        if(frstNameText.isEmpty()||lastNameText.isEmpty()||emailText.isEmpty()) {
            errorText.setVisibility(View.VISIBLE);
        }
        else {
            errorText.setVisibility(View.INVISIBLE);
            Intent redirect = new Intent(Signup.this, Signup_continue.class);
            startActivity(redirect);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_signup, menu);
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
