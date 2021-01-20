package com.example.infedisassigment.presenter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.infedisassigment.Model.NewsModel;
import com.example.infedisassigment.R;

import java.io.IOException;
import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    Context context;
    ArrayList<NewsModel.Article> articleArrayList;

    public NewsAdapter(Context context,
                       ArrayList<NewsModel.Article> articleArrayList){
        this.context = context;
        this.articleArrayList = articleArrayList;
    }

    @NonNull
    @Override
    public NewsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newslayout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.MyViewHolder holder, int position) {
        try {
            NewsModel.Article items = articleArrayList.get(position);

            holder.tvTitle.setText(items.getTitle());
            holder.tvDescription.setText(items.getDescription());

            Glide
                    .with(context)
                    .load(items.getUrlToImage())
                    .into(holder.imgvPic);

        }catch (Exception ex){
            try {
                throw new IOException(ex.toString());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public int getItemCount() {
        return articleArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private CardView cvNews;
        private ImageView imgvPic;
        private TextView tvTitle;
        private TextView tvDescription;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cvNews =  itemView.findViewById(R.id.cv_news);
            imgvPic = itemView.findViewById(R.id.imgv_pic);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);
        }
    }
}
