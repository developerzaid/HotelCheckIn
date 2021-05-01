package com.saboo.lodgecheckin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements IOCRCallBack  {

    private String mAPiKey = "88dba483e888957"; //TODO Add your own Registered API key
    private boolean isOverlayRequired;
    private String mImageUrl;
    private String mLanguage;
    private TextView mTxtResult;
    private IOCRCallBack mIOCRCallBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIOCRCallBack = this;
        mImageUrl = "http://dl.a9t9.com/blog/ocr-online/screenshot.jpg"; // Image url to apply OCR API
        mLanguage = "eng"; //Language
        isOverlayRequired = false;
        init();

    }


    private void init() {
        mTxtResult = (TextView) findViewById(R.id.main_text);
        TextView btnCallAPI = (TextView) findViewById(R.id.btn_click);

        if (btnCallAPI != null) {
            btnCallAPI.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OCRAsyncTask oCRAsyncTask = new OCRAsyncTask(MainActivity.this, mAPiKey, isOverlayRequired, mImageUrl, mLanguage,mIOCRCallBack);
                    oCRAsyncTask.execute();

                }
            });
        }
    }

    @Override
    public void getOCRCallBackResult(String response) {
        Log.d("tsome",""+response);

//            JSONObject json = new JSONObject(response);
//            JSONObject json_LL = json.getJSONObject("ParsedResults");
//            JSONObject json_LL4 = json.getJSONObject("ParsedText");
//            JSONArray jsonArray = json_LL.getJSONArray("ParsedText");
//            Log.d("34as",""+jsonArray);

//        try {
//            JSONArray jsonArray = new JSONArray("ParsedResults");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        try {
          JSONObject Tstr = new JSONObject(response);
          JSONObject is = (JSONObject) Tstr.getJSONArray("ParsedResults").get(0);
          String isl = is.getString("ParsedText");
          Log.d("some2323",isl);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        mTxtResult.setText("Tstr");
    }


}