package com.example.a12_13.wz;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a12_13.R;
import com.example.a12_13.bean.wz_init_1_bean;
import com.example.a12_13.util.BaseActivity;
import com.example.a12_13.util.Netword;
import com.example.a12_13.util.OkResult;
import com.google.gson.Gson;

import org.json.JSONObject;

public class wz_init_1 extends BaseActivity {
    public static int cai_id;
    private LinearLayout init1Data;
    private ImageView imageView6;
    private TextView init1Code;
    private TextView init1Time;
    private TextView init1Date;
    private TextView init1IllegalAddress;
    private TextView init1DeductMarks;
    private TextView init1Money;
    private TextView init1DisposeState;
    private TextView licencePlate;
    private ImageView imageView11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wz_init_1);
        setTitle("违章详情");
        initView();
        initdata();
    }

    private void initdata() {
        Netword.doGet("/prod-api/api/traffic/illegal/" + cai_id, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code") == 200) {
                    wz_init_1_bean init = new Gson().fromJson(jsonObject.optString("data"), wz_init_1_bean.class);
                    init1Code.setText(init.getNoticeNo());
                    init1Time.setText(init.getBadTime());
                    init1Date.setText("违章行为：" + init.getTrafficOffence());
                    init1IllegalAddress.setText("违章地点：" + init.getIllegalSites());
                    init1DeductMarks.setText("扣分：" + init.getDeductMarks());
                    init1Money.setText("罚款：" + init.getMoney() + "元");
                    init1DisposeState.setText("处理状态：" + init.getDisposeState());
                    licencePlate.setText("牌照:" + init.getLicencePlate());
                    Netword.doImage(wz_init_1.this,init.getImgUrl(),imageView11);
                } else {
                    Toast.makeText(wz_init_1.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        init1Data = findViewById(R.id.init1_data);
        imageView6 = findViewById(R.id.imageView6);
        init1Code = findViewById(R.id.init1_code);
        init1Time = findViewById(R.id.init1_time);
        init1Date = findViewById(R.id.init1_date);
        init1IllegalAddress = findViewById(R.id.init1_illegalAddress);
        init1DeductMarks = findViewById(R.id.init1_deductMarks);
        init1Money = findViewById(R.id.init1_money);
        init1DisposeState = findViewById(R.id.init1_disposeState);
        licencePlate = findViewById(R.id.licencePlate);
        imageView11 = findViewById(R.id.imageView11);
    }
}