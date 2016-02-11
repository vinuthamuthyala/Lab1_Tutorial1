package com.example.vinuthamuthyala.lab3_medialocationservices;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.provider.MediaStore;
import android.hardware.camera2.CameraDevice;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.database.Cursor;



import java.lang.String;


public class Signup_continue extends AppCompatActivity {

    Button signup;
    public int code=0;
    ImageView profilePic;

    Bitmap imgdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_continue);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        signup=(Button)findViewById(R.id.btnSignup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpAction(v);
            }
        });
        profilePic=(ImageView)findViewById(R.id.imgPicture);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_signup, menu);
        return true;
    }

    public void signUpAction(View view)
    {
        // validation(view);

    }

public void selectPhoto(View vw){
    final CharSequence[] items = { "Take Photo", "Choose from Gallery", "Cancel" };
    AlertDialog.Builder builder = new AlertDialog.Builder(Signup_continue.this);
    builder.setTitle("Add Photo!").setItems(items, new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int option) {
            Object o=option;
            for(int i=0;i<5;i++)
            {
                Log.d("Option",o.toString());
            }
            if (option == 0) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 1);
            } else if (option == 1) {

                Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 2);



//                Intent intent = new Intent(
//                            Intent.ACTION_GET_CONTENT);
//                            //android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    intent.setType("image/*");
                    //startActivityForResult(intent,2);
                } else if (option==2) {
                    dialog.dismiss();
                }
        }
    });
    builder.create().show();
    }


@Override
public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in A0ndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
        return true;
    }

    return super.onOptionsItemSelected(item);
}
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            profilePic.setImageBitmap(photo);
            imgdata=photo;
        }
        else if(requestCode== 2 && resultCode == RESULT_OK)
        {
            Uri selectedImage = data.getData();
            String[] filePath = { MediaStore.Images.Media.DATA };
            Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePath[0]);
            String picturePath = c.getString(columnIndex);
            c.close();
            Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
            imgdata=thumbnail;
            profilePic.setImageBitmap(thumbnail);
        }
    }
    public void onClickSignup(View v){
        Intent redirect = new Intent(Signup_continue.this, Signup_Success.class);
        startActivity(redirect);
    }
    public void onClickGetLocationBtn(View v){
        Intent redirect = new Intent(Signup_continue.this, GetLocation.class);
        redirect.putExtra("imageData",imgdata);
        startActivity(redirect);
    }

}
