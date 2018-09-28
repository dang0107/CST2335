package com.example.dknng.androidlabs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;


public class ListItemsActivity extends Activity {

    static final int REQUESTIMG=1;
    private ImageButton imageButton;
    private CheckBox checkbox;
    private Switch swt;
    protected static final String ACTIVITY_NAME = "LISTITEMSACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
        Log.i(ACTIVITY_NAME,"In onCreate()");

//Switch button
        swt = findViewById(R.id.Switch);
        swt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(ListItemsActivity.this,
                            "Switch is On", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ListItemsActivity.this,
                            "Switch is Off", Toast.LENGTH_LONG).show();
                }
            }
        });

//Checkbox
         checkbox = findViewById(R.id.checkBox4) ;
         checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 AlertDialog.Builder builder = new AlertDialog.Builder(ListItemsActivity.this);
                 builder.setMessage(R.string.dialog);
                 builder.setTitle(R.string.message);
                 builder.setPositiveButton(R.string.Ok, new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         Intent resultIntent = new Intent();
                         resultIntent.putExtra("Response","Here is my response");
                         setResult(Activity.RESULT_OK,resultIntent);
                        finish();
                     }
                 });

                 builder.setNegativeButton(R.string.Cancel, new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {

                     }
                 });
                 builder.show();
             }
         });
 //ImageButton
            imageButton = findViewById(R.id.imageButton2);
            imageButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if(takePictureIntent.resolveActivity(getPackageManager())!=null){
                        startActivityForResult(takePictureIntent, REQUESTIMG);
                    }
                }
            });
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == REQUESTIMG && resultCode == RESULT_OK)  {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageButton.setImageBitmap(imageBitmap);
        }


    }




    @Override
    protected void onResume(){
        super.onResume();
        Log.i(ACTIVITY_NAME,"In onResume()");
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.i(ACTIVITY_NAME,"In onStart()");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.i(ACTIVITY_NAME,"In onPause");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.i(ACTIVITY_NAME,"In onStop()");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i(ACTIVITY_NAME,"In onDestroy()");
    }
}
