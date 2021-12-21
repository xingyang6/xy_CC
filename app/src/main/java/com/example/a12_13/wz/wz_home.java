package com.example.a12_13.wz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.a12_13.R;
import com.example.a12_13.util.BaseActivity;
import com.example.a12_13.util.Netword;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class wz_home extends BaseActivity {
    public static String hp;
    private String adress;
    private String number;
    private List<Integer> data=new ArrayList<>();
    private String[] hp_adress = {"京", "沪", "浙", "苏", "粤", "鲁", "晋", "冀",

            "豫", "川", "渝", "辽", "吉", "黑", "皖", "鄂",

            "津", "贵", "云", "桂", "琼", "青", "新", "藏",

            "蒙", "宁", "甘", "陕", "闽", "赣", "湘"};
    private String[] car_type={"大型车辆","小型车辆","客车","火车"};
    private Banner wzBanenr;
    private Spinner carSpnnner;
    private Spinner wzSpinner;
    private EditText carFd;
    private Button button9;
    private EditText carCp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wz_home);
        setTitle("违章查询");
        initView();
        initdata();
    }

    private void initdata() {
        data.add(R.drawable.car1);
        data.add(R.drawable.car2);
        data.add(R.drawable.car3);
        data.add(R.drawable.car4);
        wzBanenr.setImages(data).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object o, ImageView imageView) {
                Glide.with(context).load(o).into(imageView);
            }
        }).start();
        Netword.doSpinner(wz_home.this,carSpnnner,car_type);
        wzSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                adress=hp_adress[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Netword.doSpinner(wz_home.this,wzSpinner,hp_adress);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (carCp.getText().toString().isEmpty()){
                    Toast.makeText(wz_home.this, "请输入完整车牌号", Toast.LENGTH_SHORT).show();
                }else{
                    hp=adress+carCp.getText().toString().trim();
                    startActivity(new Intent(wz_home.this,wz_init.class));
                }
            }
        });

    }

    private void initView() {
        wzBanenr = findViewById(R.id.wz_banenr);
        carSpnnner = findViewById(R.id.car_spnnner);
        wzSpinner = findViewById(R.id.wz_spinner);
        carFd = findViewById(R.id.car_fd);
        button9 = findViewById(R.id.button9);
        carCp = findViewById(R.id.car_cp);
    }
}