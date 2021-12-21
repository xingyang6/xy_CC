package com.example.a12_13.live;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.a12_13.R;
import com.example.a12_13.util.Netword;

public class huhao extends Fragment {
    private String[] data = {"水费", "电费"};
    public static String huhao="";
    public static String huming="";
    public static String fz="";

    private LinearLayout jfLiner;
    private Spinner huhaoSpinner;
    private EditText huhaoHuming;
    private EditText huhaoHuhao;
    private Button button10;
    public static ImageView imageView13;
    public static TextView textView43;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_huhao, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initdata();
    }

    private void initdata() {
        if (!fz.isEmpty()) {
            imageView13.setVisibility(View.GONE);
            textView43.setText(fz);
        }
        if (!huhao.isEmpty()){
            huhaoHuhao.setText(huhao);
        }
        if (!huming.isEmpty()){
            huhaoHuming.setText(huming);
        }

        jfLiner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(getActivity(),live_fz.class));
            }
        });
        Netword.doSpinner(getActivity(), huhaoSpinner, data);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (huhaoHuming.getText().toString().isEmpty() | huhaoHuhao.getText().toString().isEmpty()|fz.isEmpty()) {
                    Toast.makeText(getActivity(), "信息不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    huhao = huhaoHuhao.getText().toString().trim();
                    huming = huhaoHuming.getText().toString().trim();
                    Toast.makeText(getActivity(), "保存成功", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(v).navigate(R.id.navigation_live_home);
                }
            }
        });


    }

    private void initView(View view) {
        jfLiner = view.findViewById(R.id.jf_liner);
        huhaoSpinner = view.findViewById(R.id.huhao_spinner);
        huhaoHuming = view.findViewById(R.id.huhao_huming);
        huhaoHuhao = view.findViewById(R.id.huhao_huhao);
        button10 = view.findViewById(R.id.button10);
        imageView13 = view.findViewById(R.id.imageView13);
        textView43 = view.findViewById(R.id.textView43);
    }
}