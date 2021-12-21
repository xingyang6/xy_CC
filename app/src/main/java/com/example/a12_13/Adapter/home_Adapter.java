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
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_13.R;
import com.example.a12_13.bean.honme_tuijain_bean;
import com.example.a12_13.bean.new_bean;
import com.example.a12_13.findwork.work_home;
import com.example.a12_13.home.news_init;
import com.example.a12_13.live.live_home;
import com.example.a12_13.util.Netword;
import com.example.a12_13.wz.wz_home;

import java.util.List;

public class home_Adapter extends RecyclerView.Adapter<home_Adapter.LeaViewHolder> {
    private Context context;
    private int type;
    private List<honme_tuijain_bean> tuijian;
    private  List<new_bean> news;
    public home_Adapter(Context context,List<honme_tuijain_bean> tuijian, List<new_bean> news,int a){
        this.context=context;
        this.type=a;
        this.tuijian=tuijian;
        this.news=news;
    }
    @NonNull
    @Override
    public LeaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LeaViewHolder leaViewHolder=null;
        switch (type){
            case 0:
                leaViewHolder=new LeaViewHolder(LayoutInflater.from(context).inflate(R.layout.tuijain__item,parent,false));
                break;
            case 1:
                leaViewHolder=new LeaViewHolder(LayoutInflater.from(context).inflate(R.layout.remen_item,parent,false));
                break;
            case 2:
                leaViewHolder=new LeaViewHolder(LayoutInflater.from(context).inflate(R.layout.news_item,parent,false));
                break;
            case 3:
                leaViewHolder=new LeaViewHolder(LayoutInflater.from(context).inflate(R.layout.sear_item,parent,false));
                break;
        }
        return leaViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LeaViewHolder holder, int position) {
        switch (type){
            case 0:
                switch (position){
//                    case 2:
//                        Netword.doImage(context,tuijian.get(position).getImgUrl(),holder.tuijianImg);
//                        holder.tuijianText.setText(tuijian.get(position).getServiceName());
//                        holder.itemView.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                               context.startActivity(new Intent(context, work_home.class));
//                            }
//                        });
                    case 8:
                        holder.tuijianImg.setImageResource(R.drawable.wz);
                        holder.tuijianText.setText("违章查询");
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                context.startActivity(new Intent(context, wz_home.class));
                            }
                        });
                        break;
                    case 7:
                        holder.tuijianImg.setImageResource(R.drawable.money);
                        holder.tuijianText.setText("生活缴费");
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                context.startActivity(new Intent(context, live_home.class));
                            }
                        });
                        break;

                    case 9:
                        holder.tuijianImg.setImageResource(R.drawable.ic_dashboard_black_24dp);
                        holder.tuijianText.setText("更多服务");
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Navigation.findNavController(v).navigate(R.id.navigation_dashboard);
                            }
                        });
                        break;
                    default:
                        Netword.doImage(context,tuijian.get(position).getImgUrl(),holder.tuijianImg);
                        holder.tuijianText.setText(tuijian.get(position).getServiceName());
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(context, "点击了"+tuijian.get(position).getServiceName(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                }
                break;
            case 1:
                Netword.doImage(context,news.get(position).getCover(),holder.remenImg);
                holder.remenText.setText(news.get(position).getTitle());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        news_init.news_id=news.get(position).getId();
                        context.startActivity(new Intent(context,news_init.class));
                    }
                });
                break;
            case 2:
                Netword.doImage(context,news.get(position).getCover(),holder.imageView3);
                holder.newsTitle.setText(news.get(position).getTitle());
                holder.newsCount.setText(Html.fromHtml(news.get(position).getContent()));
                holder.newsPlNum.setText(news.get(position).getCommentNum()+"");
                holder.newsData.setText(news.get(position).getPublishDate());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        news_init.news_id=news.get(position).getId();
                        context.startActivity(new Intent(context,news_init.class));
                    }
                });
                break;
            case 3:
                holder.textView4.setText(news.get(position).getTitle());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        news_init.news_id=news.get(position).getId();
                        context.startActivity(new Intent(context,news_init.class));
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
                a=10;
                break;
            case 1:
                a=news.size();
                break;
            case 2:
                a=news.size();
                break;
            case 3:
                a=news.size();
                break;
        }
        return a;
    }

    public class LeaViewHolder extends RecyclerView.ViewHolder {
        private ImageView tuijianImg;
        private TextView tuijianText;

        private ImageView remenImg;
        private TextView remenText;

        private ImageView imageView3;
        private TextView newsTitle;
        private TextView newsCount;
        private TextView newsPlNum;
        private TextView newsData;

        private TextView textView4;
        public LeaViewHolder(@NonNull View itemView) {
            super(itemView);
            switch (type){
                case 0:
                    tuijianImg = itemView.findViewById(R.id.tuijian_img);
                    tuijianText = itemView.findViewById(R.id.tuijian_text);
                    break;
                case 1:
                    remenImg = itemView.findViewById(R.id.remen_img);
                    remenText = itemView.findViewById(R.id.remen_text);
                    break;
                case 2:
                    imageView3 = itemView.findViewById(R.id.imageView3);
                    newsTitle = itemView.findViewById(R.id.news_title);
                    newsCount = itemView.findViewById(R.id.news_count);
                    newsPlNum = itemView.findViewById(R.id.news_pl_num);
                    newsData = itemView.findViewById(R.id.news_data);
                    break;
                case 3:
                    textView4 = itemView.findViewById(R.id.textView4);
                    break;

            }
        }
    }
}
