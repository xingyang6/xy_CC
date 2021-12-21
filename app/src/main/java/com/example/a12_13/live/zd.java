package com.example.a12_13.live;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_13.Adapter.jf_Adapter;
import com.example.a12_13.R;
import com.example.a12_13.bean.jf_type_bean;
import com.example.a12_13.bean.zd_banner_bean;
import com.example.a12_13.bean.zd_news_bean;
import com.example.a12_13.util.Netword;
import com.example.a12_13.util.OkResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class zd extends Fragment {


    private TextView jfAdress;
    private Banner liveBanenr;
    private RecyclerView jfTuijainRecy;
    private RecyclerView jfNewsRecy;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_zd, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initdata();
    }

    private void initdata() {
        Netword.doGet("/prod-api/api/living/rotation/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    List<zd_banner_bean> banner=new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<zd_banner_bean>>(){
                    }.getType());
                    liveBanenr.setImages(banner).setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object o, ImageView imageView) {
                            zd_banner_bean ben=(zd_banner_bean)o;
                            Netword.doImage(getActivity(),ben.getAdvImg(),imageView);
                        }
                    }).start();
                }else{
                    Toast.makeText(getActivity(), jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
        Netword.doGet("/prod-api/api/living/category/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    List<jf_type_bean> banner=new Gson().fromJson(jsonObject.optString("data"),new TypeToken<List<jf_type_bean>>(){
                    }.getType());
                    List<zd_news_bean> banner1=new ArrayList<>();
                    jfTuijainRecy.setLayoutManager(new GridLayoutManager(getActivity(),4));
                    jfTuijainRecy.setAdapter(new jf_Adapter(getActivity(),banner,banner1,0));

                }else{
                    Toast.makeText(getActivity(), jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
        Netword.doGet("/prod-api/api/living/press/press/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    List<zd_news_bean> banner=new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<zd_news_bean>>(){
                    }.getType());
                    List<jf_type_bean> mbanner=new ArrayList<>();
                    jfNewsRecy.setLayoutManager(new LinearLayoutManager(getActivity()));
                    jfNewsRecy.setAdapter(new jf_Adapter(getActivity(),mbanner,banner,1));
                }
            }
        });

    }

    private void initView(View view) {
        jfAdress = view.findViewById(R.id.jf_adress);
        liveBanenr = view.findViewById(R.id.live_banenr);
        jfTuijainRecy = view.findViewById(R.id.jf_tuijain_recy);
        jfNewsRecy = view.findViewById(R.id.jf_news_recy);
    }
}