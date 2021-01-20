package com.example.infedisassigment.view.fragments;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.infedisassigment.R;


public class HomeFragment extends Fragment implements View.OnClickListener{

    private CardView cvNews;
    private LinearLayout layoutNews;
    private ImageView imgvPic;
    private CardView cvBooks;
    private LinearLayout layoutBooks;
    private ImageView imgvBookPic;



    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        initViews(rootView);

        return rootView;
    }

    private void initViews(View rootView){
        cvNews = rootView.findViewById(R.id.cv_news);
        layoutNews = rootView.findViewById(R.id.layout_news);
        imgvPic = rootView.findViewById(R.id.imgv_pic);
        cvBooks = rootView.findViewById(R.id.cv_books);
        layoutBooks = rootView.findViewById(R.id.layout_books);
        imgvBookPic = rootView.findViewById(R.id.imgv_book_pic);

        Glide
                .with(getActivity())
                .asGif()
                .load(R.raw.giphy)
                .into(imgvPic);

        Glide
                .with(getActivity())
                .asGif()
                .load(R.raw.down)
                .into(imgvBookPic);

        cvNews.setOnClickListener(this);
        cvBooks.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cv_news:
                break;

            case R.id.cv_books:
                break;

        }
    }

    private void openFragment(){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, new HomeFragment())
                .addToBackStack(null)
                .commit();
    }
}