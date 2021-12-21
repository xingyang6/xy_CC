package com.example.a12_13.ui.personal;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.a12_13.Login;
import com.example.a12_13.MainActivity;
import com.example.a12_13.R;
import com.example.a12_13.bean.user_bean;
import com.example.a12_13.personal.personal_1;
import com.example.a12_13.personal.personal_2;
import com.example.a12_13.personal.personal_3;
import com.example.a12_13.personal.personal_4;
import com.example.a12_13.util.Netword;
import com.example.a12_13.util.OkResult;
import com.google.gson.Gson;

import org.json.JSONObject;

public class personal extends Fragment {

    private PersonalViewModel mViewModel;
    private ImageView personalImg;
    private TextView personalNickname;
    private LinearLayout lineean1;
    private LinearLayout lineean2;
    private LinearLayout lineean3;
    private LinearLayout lineean4;
    private Button button6;

    public static personal newInstance() {
        return new personal();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.personal_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initdata();
    }

    public void initdata() {
        Netword.doGet("/prod-api/api/common/user/getInfo", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    user_bean user=new Gson().fromJson(jsonObject.optString("user"),user_bean.class);
                    Netword.doImage(getActivity(),"/prod-api"+user.getAvatar(),personalImg);
                    personalNickname.setText(user.getNickName());
                }else{
                    Toast.makeText(getActivity(), jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
        lineean1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), personal_1.class));
            }
        });
        lineean2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), personal_2.class));
            }
        });
        lineean3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), personal_3.class));
            }
        });
        lineean4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), personal_4.class));
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mainActivity.finish();
                startActivity(new Intent(getActivity(), Login.class));
            }
        });
    }

    private void initView(View view) {
        personalImg = view.findViewById(R.id.personal_img);
        personalNickname = view.findViewById(R.id.personal_nickname);
        lineean1 = view.findViewById(R.id.lineean1);
        lineean2 = view.findViewById(R.id.lineean2);
        lineean3 = view.findViewById(R.id.lineean3);
        lineean4 = view.findViewById(R.id.lineean4);
        button6 = view.findViewById(R.id.button6);
    }
}