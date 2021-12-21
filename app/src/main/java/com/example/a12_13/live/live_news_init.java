package com.example.a12_13.live;

import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a12_13.R;
import com.example.a12_13.bean.live_news_bean;
import com.example.a12_13.bean.news_init_bean;
import com.example.a12_13.home.news_init;
import com.example.a12_13.util.BaseActivity;
import com.example.a12_13.util.Netword;
import com.example.a12_13.util.OkResult;
import com.google.gson.Gson;

import org.json.JSONObject;

public class live_news_init extends BaseActivity {
    public static int news_id;
    private TextView newsInitTitle1;
    private ImageView newsInitImg1;
    private TextView newsInitCount1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_news_init);
        setTitle("新闻详情");
        initView();
        initdata();
    }

    private void initdata() {
        Netword.doGet("/prod-api/api/living/press/press/" + news_id, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    live_news_bean init=new Gson().fromJson(jsonObject.optString("data"),live_news_bean.class);
                    Netword.doImage(live_news_init.this,init.getCover(),newsInitImg1);
                    newsInitTitle1.setText(init.getTitle());
                    newsInitCount1.setText(Html.fromHtml(init.getContent()));
                }else{
                    Toast.makeText(live_news_init.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        newsInitTitle1 = findViewById(R.id.news_init_title1);
        newsInitImg1 = findViewById(R.id.news_init_img1);
        newsInitCount1 = findViewById(R.id.news_init_count1);
    }
}