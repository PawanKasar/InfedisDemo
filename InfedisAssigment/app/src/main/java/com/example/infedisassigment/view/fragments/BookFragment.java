package com.example.infedisassigment.view.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.infedisassigment.Model.BookModel;
import com.example.infedisassigment.Model.NewsModel;
import com.example.infedisassigment.R;
import com.example.infedisassigment.presenter.RetroClient.RetrofitApiUtils;
import com.example.infedisassigment.presenter.RetroClient.SecondRetrofitApiUtils;
import com.example.infedisassigment.presenter.Utilities.CallingImportantMethod;
import com.example.infedisassigment.presenter.adapter.BookAdapter;
import com.example.infedisassigment.presenter.adapter.NewsAdapter;
import com.example.infedisassigment.presenter.interfaces.SubUrlInterface;
import com.example.infedisassigment.view.activities.MainActivity;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BookFragment extends Fragment {

    RecyclerView rv_books;
    //
    private SubUrlInterface subApiInterface;
    private SecondRetrofitApiUtils secondRetrofitApiUtils;
    //
    ArrayList<BookModel.Item> itemArrayList;
    //
    BookAdapter bookAdapter;
    //
    private ProgressDialog pd;

    public BookFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_book, container, false);
        initViews(rootView);
        return rootView;
    }

    private void initViews(View rootView){
        MainActivity.titleMain.setText("Books");
        rv_books = rootView.findViewById(R.id.rv_books);

        try{
            pd = new ProgressDialog(getActivity());
            pd.setMessage("loading");
            pd.show();
            requestServerToFetchBooks();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void requestServerToFetchBooks(){
        subApiInterface = secondRetrofitApiUtils.getAPIService();
        subApiInterface.getBooks().enqueue(new Callback<BookModel>() {
            @Override
            public void onResponse(Call<BookModel> call, Response<BookModel> response) {
                try{
                    if (response.isSuccessful()){
                        if (response.body() != null){
                            itemArrayList = response.body().getItems();
                            setDataOnRecyclerView(itemArrayList);
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
            public void onFailure(Call<BookModel> call, Throwable t) {
                try {
                    CallingImportantMethod.showToast(getContext(), t.getMessage());
                    pd.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setDataOnRecyclerView(ArrayList<BookModel.Item> articleArrayList){
        rv_books.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv_books.setLayoutManager(layoutManager);
        bookAdapter = new BookAdapter(getActivity(),articleArrayList);
        rv_books.setAdapter(bookAdapter);
        bookAdapter.notifyDataSetChanged();
    }
}