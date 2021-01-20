package com.example.infedisassigment.view.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.infedisassigment.Model.NewsModel;
import com.example.infedisassigment.R;
import com.example.infedisassigment.presenter.RetroClient.RetrofitApiUtils;
import com.example.infedisassigment.presenter.Utilities.CallingImportantMethod;
import com.example.infedisassigment.presenter.adapter.NewsAdapter;
import com.example.infedisassigment.presenter.interfaces.SubUrlInterface;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainFragment extends Fragment {

    //
    RecyclerView rv_sliderNews;
    //
    private SubUrlInterface subApiInterface;
    private RetrofitApiUtils retrofitApiUtils;
    //
    ArrayList<NewsModel.Article> newsArrayList;
    //
    NewsAdapter newsAdapter;
    //
    private ProgressDialog pd;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        initViews(rootView);

        return rootView;
    }

    private void initViews(View rootView){
        rv_sliderNews = rootView.findViewById(R.id.rv_sliderNews);
        try{
            pd = new ProgressDialog(getActivity());
            pd.setMessage("loading");
            pd.show();
            requestServerToFetchData();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void requestServerToFetchData(){
        subApiInterface = retrofitApiUtils.getAPIService();
        subApiInterface.getNewsDetails().enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                try{
                    if (response.isSuccessful()){
                        if (response.body() != null){
                            newsArrayList = response.body().getArticles();
                            setDataOnRecyclerView(newsArrayList);
                            pd.dismiss();
                        }else {
                            CallingImportantMethod.showToast(getContext(),"No data");
                            pd.dismiss();
                        }
                    }else {
                        CallingImportantMethod.showToast(getContext(),"No response from server");
                        pd.dismiss();
                    }
                }catch (Exception ex){
                    try {
                        pd.dismiss();
                        throw new IOException(ex.toString());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                try {
                    CallingImportantMethod.showToast(getContext(), t.getMessage());
                    pd.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setDataOnRecyclerView(ArrayList<NewsModel.Article> articleArrayList){
        rv_sliderNews.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv_sliderNews.setLayoutManager(layoutManager);
        newsAdapter = new NewsAdapter(getActivity(),articleArrayList);
        rv_sliderNews.setAdapter(newsAdapter);
        newsAdapter.notifyDataSetChanged();
    }

}