package com.example.vinuthamuthyala.lab2_ase_mymagicdateapp;

import android.content.Intent;
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

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
    public void checkUserCredentials(View v)
    {
        EditText etusrname = (EditText)findViewById(R.id.etUsername);
        EditText etpwd = (EditText) findViewById(R.id.etPassword);
        TextView errorText = (TextView)findViewById(R.id.lblError);
        String username = etusrname.getText().toString();
        String password = etpwd.getText().toString();

        boolean validationFlag = false;

        if(!username.isEmpty() && !password.isEmpty()) {
            if(username.equals("Vinutha") && password.equals("Vinutha")) {
                validationFlag = true;

            }
        }
        if(!validationFlag)
        {
            errorText.setVisibility(View.VISIBLE);
        }
        else
        {

            Intent redirect = new Intent(Login.this, Home.class);
            startActivity(redirect);
        }

    }
}
