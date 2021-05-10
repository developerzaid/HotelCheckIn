package com.saboo.lodgecheckin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements IOCRCallBack  {

    private String mAPiKey = "88dba483e888957"; //TODO Add your own Registered API key
    private boolean isOverlayRequired;
    private String mImageUrl;
    private String mLanguage;
    private TextView mTxtResult;
    private IOCRCallBack mIOCRCallBack;
    private String isl;
    Context context;
    String[] strings ;
    TextView mApproveResult;
    TextView btnCallAPI;
    String ni;
    //    File fi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();


        mImageUrl = getIntent().getStringExtra("image");
        CheckIn checkIn = new CheckIn();
//      fi = checkIn.SendFile();

        mTxtResult = (TextView) findViewById(R.id.main_text);
        btnCallAPI = (TextView) findViewById(R.id.btn_click);
        mApproveResult = findViewById(R.id.iusop);


        mIOCRCallBack = this;
//      mImageUrl = "https://i.ibb.co/dKgJQjw/dfgdfg.png"; // Image url to apply OCR API
//      mImageUrl = fi;
        mLanguage = "eng"; //Language
        isOverlayRequired = false;


        OCRAsyncTask oCRAsyncTask = new OCRAsyncTask(MainActivity.this, mAPiKey, isOverlayRequired, mImageUrl, mLanguage,mIOCRCallBack);
        oCRAsyncTask.execute();


        btnCallAPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ni.equals("720667012634")){
                    mApproveResult.setText("Congratulations Zaid You are Granted to Checkin");

                }

            }
        });

    }





    @Override
    public void getOCRCallBackResult(String response) {
        Log.d("tsome",""+response);


        try {
          JSONObject Tstr = new JSONObject(response);
          JSONObject is = (JSONObject) Tstr.getJSONArray("ParsedResults").get(0);
          isl = is.getString("ParsedText");
//          strings = isl.split("\n");
//          ni = strings[3].replace(" ","");
//          Log.d("2343  "," "+ni);
          Log.d("some2323",isl);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mTxtResult.setText(isl);

//        mTxtResult.setText("USERNAME : "+strings[0]+"\nUSER AGE: "+strings[1]+"\n USER GENDER: "+strings[2]+"\n AADHAR NO: "+strings[3]);
    }


}