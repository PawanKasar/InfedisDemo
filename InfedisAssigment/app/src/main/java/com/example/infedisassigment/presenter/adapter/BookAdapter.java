package com.example.infedisassigment.presenter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.infedisassigment.Model.BookModel;
import com.example.infedisassigment.R;

import java.io.IOException;
import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.CustomViewHolder> {

    Context context;
    ArrayList<BookModel.Item> itemArrayList;

    public BookAdapter(Context context,ArrayList<BookModel.Item> itemArrayList){
        this.context = context;
        this.itemArrayList = itemArrayList;
    }

    @NonNull
    @Override
    public BookAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_custom_layout, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookAdapter.CustomViewHolder holder, int position) {
        try{
            BookModel.Item item = itemArrayList.get(position);

            holder.tvBookTitle.setText(item.getVolumeInfo().getTitle());
            holder.tvBookPublisher.setText(item.getVolumeInfo().getPublisher());
            holder.tvBookPublishYear.setText(item.getVolumeInfo().getPublishedDate());

            Glide
                    .with(context)
                    .load(item.getVolumeInfo().getImageLinks().getThumbnail())
                    .into(holder.ivBookImage);

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
        return itemArrayList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout viewForeground;
        private ImageView ivBookImage;
        private TextView tvBookTitle;
        private TextView tvBookPublisher;
        private TextView tvBookPublishYear;


        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            viewForeground = itemView.findViewById(R.id.viewForeground);
            ivBookImage = itemView.findViewById(R.id.iv_bookImage);
            tvBookTitle = itemView.findViewById(R.id.tv_bookTitle);
            tvBookPublisher = itemView.findViewById(R.id.tv_bookPublisher);
            tvBookPublishYear = itemView.findViewById(R.id.tv_bookPublishYear);
        }
    }
}
