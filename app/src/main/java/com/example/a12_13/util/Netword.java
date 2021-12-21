package com.example.a12_13.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a12_13.R;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lljjcoder.style.citypickerview.CityPickerView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Netword {
    public static String token;
    public static   String baseurl;
    public static final OkHttpClient client=new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                @NotNull
                @Override
                public Response intercept(@NotNull Chain chain) throws IOException {
                    Request.Builder builder=chain.request().newBuilder();
                    if (token!=null){
                        builder.addHeader("Authorization",token);
                    }
                    return chain.proceed(builder.build());
                }
            }).build();

    private static final MediaType JSON=MediaType.Companion.parse("application/json;charset=utf-8");

    public static void doGet(String path,OkResult okResult){
        Request request=new Request.Builder().url(baseurl+path).get().build();
        client.newCall(request).enqueue(new OkCallback(okResult));
    }
    public static void doDelect(String path,OkResult okResult){
        Request request=new Request.Builder().url(baseurl+path).delete().build();
        client.newCall(request).enqueue(new OkCallback(okResult));
    }
    public  static void doPost(String path,String parm,OkResult okResult){
            Request request=new Request.Builder().url(baseurl+path).post(RequestBody.create(parm,JSON)).build();
            client.newCall(request).enqueue(new OkCallback(okResult));
    }
    public  static void doPut(String path,String parm,OkResult okResult){
        Request request=new Request.Builder().url(baseurl+path).put(RequestBody.create(parm,JSON)).build();
        client.newCall(request).enqueue(new OkCallback(okResult));
    }
    public static void doImage(Context context, String path, ImageView imageView){
        Glide.with(context).load(baseurl+path).into(imageView);
    }
    public static void doAdress(Context context, TextView textView){
        CityPickerView mpick=new CityPickerView();
        mpick.init(context);
        CityConfig cityConfig=new CityConfig.Builder().build();
        mpick.setConfig(cityConfig);
        mpick.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                textView.setText(province.getName()+city+district);
            }

            @Override
            public void onCancel() {
                ToastUtils.showLongToast(context,"取消");
            }
        });
        mpick.showCityPicker();
    }
    public static void doSpinner(Context context, Spinner spinner,String[] a){
        ArrayAdapter<String> mpinner=new ArrayAdapter<>(context,R.layout.item,a);
        mpinner.setDropDownViewResource(R.layout.item);
        spinner.setAdapter(mpinner);
    }
    public static void doCallphone(Context context,String s){
        Intent intent=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+s));
        context.startActivity(intent);
    }



    private static final Handler HANDLER=new Handler(Looper.getMainLooper());
    static class OkCallback implements Callback{
        private final OkResult okResult;

        OkCallback(OkResult okResult) {
            this.okResult = okResult;
        }

        @Override
        public void onFailure(@NotNull Call call, @NotNull IOException e) {
            e.printStackTrace();
            HANDLER.post(new Runnable() {
                @Override
                public void run() {

                }
            });

        }

        @Override
        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String string= Objects.requireNonNull(response.body().string());
                HANDLER.post(()->{
                    try {
                        okResult.succes(new JSONObject(string));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                });
        }
    }
}
