package com.example.a12_13.ui.news;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_13.Adapter.home_Adapter;
import com.example.a12_13.Adapter.news_Adapter;
import com.example.a12_13.R;
import com.example.a12_13.bean.home_banenr_bean;
import com.example.a12_13.bean.home_tab;
import com.example.a12_13.bean.honme_tuijain_bean;
import com.example.a12_13.bean.new_bean;
import com.example.a12_13.bean.news_pl_bean;
import com.example.a12_13.home.news_init;
import com.example.a12_13.util.Netword;
import com.example.a12_13.util.OkResult;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class news extends Fragment {

    private NewsViewModel mViewModel;
    private Banner newsBanner;
    private TabLayout newsTab;
    private RecyclerView newsRecy;

    public static news newInstance() {
        return new news();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.news_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initdata();
    }

    private void initdata() {
        Netword.doGet("/prod-api/api/rotation/list?pageNum=1&pageSize=8&type=2", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    List<home_banenr_bean> banenr=new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<home_banenr_bean>>(){
                    }.getType());
                    newsBanner.setImages(banenr).setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object o, ImageView imageView) {
                            home_banenr_bean ban=(home_banenr_bean) o;
                            Netword.doImage(context,ban.getAdvImg(),imageView);
                        }
                    }).start();
                    newsBanner.setOnBannerListener(new OnBannerListener() {
                        @Override
                        public void OnBannerClick(int i) {
                            news_init.news_id=banenr.get(i).getTargetId();
                            startActivity(new Intent(getActivity(),news_init.class));
                        }
                    }).start();
                }else{
                    Toast.makeText(getActivity(), "msg", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Netword.doGet("/prod-api/press/category/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    List<home_tab> news=new  Gson().fromJson(jsonObject.optString("data"),new TypeToken<List<home_tab>>(){
                    }.getType());
                    for (int i=0;i<news.size();i++){
                        newsTab.addTab(newsTab.newTab().setText(news.get(i).getName()).setTag(news.get(i).getId()));
                    }
                    LinearLayout linearLayout = (LinearLayout) newsTab.getChildAt(0);
                    linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
                    linearLayout.setDividerDrawable(ContextCompat.getDrawable(getActivity(),
                            R.drawable.tab_style));
                    linearLayout.setDividerPadding(40);
                    newsTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                        @Override
                        public void onTabSelected(TabLayout.Tab tab) {
                            loada(tab.getTag());
                        }

                        @Override
                        public void onTabUnselected(TabLayout.Tab tab) {

                        }

                        @Override
                        public void onTabReselected(TabLayout.Tab tab) {

                        }
                    });
                    loada(news.get(0).getId());
                }else{
                    Toast.makeText(getActivity(), jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void loada(Object id) {
        Netword.doGet("/prod-api/press/press/list?type=" + id, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    List<new_bean> news=new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<new_bean>>(){
                    }.getType());
                    List<new_bean> mtuijian=new ArrayList<>();
                    List<news_pl_bean> pl=new ArrayList<>();
                    newsRecy.setLayoutManager(new LinearLayoutManager(getActivity()));
                    newsRecy.setAdapter(new news_Adapter(getActivity(),news,mtuijian,pl,0));
                }else{
                    Toast.makeText(getActivity(), jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView(View view) {
        newsBanner = view.findViewById(R.id.news_banner);
        newsTab = view.findViewById(R.id.news_tab);
        newsRecy = view.findViewById(R.id.news_recy);
    }
}