package com.example.vinuthamuthyala.lab2_ase_mymagicdateapp;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Home extends AppCompatActivity {
    Context mContext;
    String dayText,monthText;
    TextView outputTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        outputTextView=(TextView)findViewById(R.id.tvResult);
        mContext = getBaseContext();


    }
    public void logout(View v) {
        Intent redirect = new Intent(Home.this, Login.class);
        startActivity(redirect);
    }
    private void hideKeyboard(View editableView) {
        InputMethodManager imm = (InputMethodManager)mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editableView.getWindowToken(), 0);
    }
    public void getDateFact(View v)
    {
        TextView daytextview=(TextView)findViewById(R.id.etDay);
        TextView monthtextview=(TextView)findViewById(R.id.etMonth);
        TextView errorText = (TextView)findViewById(R.id.tvError);
        dayText=daytextview.getText().toString();
        monthText=monthtextview.getText().toString();
        if(dayText.isEmpty()||monthText.isEmpty()) {
            errorText.setVisibility(View.VISIBLE);
        }
        else {
            errorText.setVisibility(View.INVISIBLE);

            String getURL = "https://numbersapi.p.mashape.com/" + monthText + "/" + dayText + "/date?fragment=true&json=true";
            OkHttpClient client = new OkHttpClient();
            try {

                Request request = new Request.Builder()
                        .url(getURL)
                        .header("X-Mashape-Key", "7144jAHYwGmsh5mD0jDSFwTJmkmGp1eePz8jsnBeJae883746u")
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        //System.out.println(e.getMessage());
                        System.out.println("Call On failure");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final JSONObject jsonResult;
                        final String result = response.body().string();
                        try {
                            jsonResult = new JSONObject(result);
                            final String resultTextArray = jsonResult.get("text").toString();
                            final String resultYearArray = jsonResult.get("year").toString();

                            Log.d("JSON Result String", jsonResult.toString());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    hideKeyboard(outputTextView);
                                    outputTextView.setText("This day in the year " + resultYearArray + " is that " + resultTextArray);
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
            } catch (Exception ex) {
                outputTextView.setText(ex.getMessage());

            }
        }

    }




}

