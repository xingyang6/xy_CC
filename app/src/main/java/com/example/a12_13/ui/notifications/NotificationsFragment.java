package com.example.a12_13.ui.notifications;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.a12_13.Adapter.zh_Adapter;
import com.example.a12_13.R;
import com.example.a12_13.util.Netword;
import com.example.a12_13.zhihui.zh_1;
import com.example.a12_13.zhihui.zh_2;
import com.example.a12_13.zhihui.zh_3;
import com.example.a12_13.zhihui.zh_4;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {
    private List<Integer> string=new ArrayList<>();

    private NotificationsViewModel notificationsViewModel;
    private TextView zhAdress;
    private TextView zhWeart;
    private Banner zhBanenr;
    private LinearLayout zh1;
    private LinearLayout zh2;
    private LinearLayout zh3;
    private LinearLayout zh4;
    private RecyclerView zhRemRecy;
    private RecyclerView zhTuijainRecy;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initdata();
    }

    private void initdata() {
        zhAdress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Netword.doAdress(getActivity(),zhAdress);
            }
        });
        zh1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), zh_1.class));
            }
        });
        zh2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (zh_1.sum==0){
                    Toast.makeText(getActivity(), "请先完成健康报告", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), zh_1.class));

                }else{
                    startActivity(new Intent(getActivity(), zh_2.class));
                }

            }
        });
        zh3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), zh_3.class));
            }
        });

        zh4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), zh_4.class));
            }
        });


        string.add(R.drawable.zh1);
        string.add(R.drawable.zh2);
        string.add(R.drawable.zh3);
        zhBanenr.setImages(string).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object o, ImageView imageView) {
                Glide.with(context).load(o).into(imageView);
            }
        }).start();
        zhRemRecy.setLayoutManager(new LinearLayoutManager(getActivity()));
        zhRemRecy.setAdapter(new zh_Adapter(getActivity(),0));
        zhTuijainRecy.setLayoutManager(new LinearLayoutManager(getActivity()));
        zhTuijainRecy.setAdapter(new zh_Adapter(getActivity(),1));
    }

    private void initView(View view) {
        zhAdress = view.findViewById(R.id.zh_adress);
        zhBanenr = view.findViewById(R.id.zh_banenr);
        zh1 = view.findViewById(R.id.zh_1);
        zh2 = view.findViewById(R.id.zh_2);
        zh3 = view.findViewById(R.id.zh_3);
        zh4 = view.findViewById(R.id.zh_4);
        zhRemRecy = view.findViewById(R.id.zh_rem_recy);
        zhTuijainRecy = view.findViewById(R.id.zh_tuijain_recy);
    }
}