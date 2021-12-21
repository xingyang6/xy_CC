package com.example.a12_13.Adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_13.R;
import com.example.a12_13.bean.jf_type_bean;
import com.example.a12_13.bean.zd_news_bean;
import com.example.a12_13.live.dian_init;
import com.example.a12_13.live.live_news_init;
import com.example.a12_13.live.shui;
import com.example.a12_13.util.Netword;

import java.util.List;

public class jf_Adapter extends RecyclerView.Adapter<jf_Adapter.LearViewHolder> {
    private Context context;
    private int type;
    private List<jf_type_bean> banner;
    private List<zd_news_bean> banner1;
    public jf_Adapter(Context context,List<jf_type_bean> banner,List<zd_news_bean> banner1,int a){
        this.context=context;
        this.type=a;
        this.banner=banner;
        this.banner1=banner1;
    }
    @NonNull
    @Override
    public LearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LearViewHolder learViewHolder=null;
        switch (type){
            case 0:
                learViewHolder=new LearViewHolder(LayoutInflater.from(context).inflate(R.layout.zf_type_item,parent,false));
                break;
            case 1:
                learViewHolder=new LearViewHolder(LayoutInflater.from(context).inflate(R.layout.news_item,parent,false));
                break;
        }
        return learViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LearViewHolder holder, int position) {
            switch (type){
                case 0:
                    switch (position){
                        case 1:
                            Netword.doImage(context,banner.get(position).getImgUrl(),holder.zfImg);
                            holder.zfText.setText(banner.get(position).getLiveName());
                            holder.itemView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    context.startActivity(new Intent(context, shui.class));
                                }
                            });
                            break;
                        case 2:
                            Netword.doImage(context,banner.get(position).getImgUrl(),holder.zfImg);
                            holder.zfText.setText(banner.get(position).getLiveName());
                            holder.itemView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    context.startActivity(new Intent(context, dian_init.class));
                                }
                            });
                            break;
                        default:
                            Netword.doImage(context,banner.get(position).getImgUrl(),holder.zfImg);
                            holder.zfText.setText(banner.get(position).getLiveName());
                            holder.itemView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(context, "点击了"+banner.get(position).getLiveName(), Toast.LENGTH_SHORT).show();
                                }
                            });
                            break;
                    }

                    break;
                case 1:
                    holder.newsTitle.setText(banner1.get(position).getTitle());
                    holder.newsCount.setText(HtmlCompat.fromHtml(banner1.get(position).getContent(),HtmlCompat.FROM_HTML_MODE_LEGACY));
                    holder.newsPlNum.setText(banner1.get(position).getCommentNum()+"");
                    Netword.doImage(context,banner1.get(position).getCover(),holder.imageView3);
                    holder.newsData.setText(banner1.get(position).getPublishDate());
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            live_news_init.news_id=banner1.get(position).getId();
                            context.startActivity(new Intent(context,live_news_init.class));
                        }
                    });
                    break;
            }
    }

    @Override
    public int getItemCount() {
        int a=0;
        switch (type){
            case 0:
                a=banner.size();
                break;
            case 1:
                a=banner1.size();
                break;
        }
        return a;
    }

    public class LearViewHolder extends RecyclerView.ViewHolder {
        private ImageView zfImg;
        private TextView zfText;

        private TextView newsTitle;
        private TextView newsCount;
        private ImageView newsPl;
        private TextView newsPlNum;
        private TextView newsData;
        private ImageView imageView3;


        public LearViewHolder(@NonNull View itemView) {
            super(itemView);
            switch (type){
                case 0:
                    zfImg = itemView.findViewById(R.id.zf_img);
                    zfText = itemView.findViewById(R.id.zf_text);
                    break;
                case 1:
                    newsTitle = itemView.findViewById(R.id.news_title);
                    newsCount = itemView.findViewById(R.id.news_count);
                    imageView3 = itemView.findViewById(R.id.imageView3);
                    newsPlNum = itemView.findViewById(R.id.news_pl_num);
                    newsData = itemView.findViewById(R.id.news_data);
                    break;
            }
        }
    }
}
