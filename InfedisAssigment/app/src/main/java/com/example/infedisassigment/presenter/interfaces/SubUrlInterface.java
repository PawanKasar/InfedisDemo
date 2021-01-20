package com.example.infedisassigment.presenter.interfaces;


import com.example.infedisassigment.Model.NewsModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SubUrlInterface {

    /*@GET("genre/movie/list?api_key=68e82445c8842ba8616d0f6bf0e97a41")
    Call<GenreModel> getGenereList();*/

    @GET("everything?q=bitcoin&from=2020-12-20&sortBy=publishedAt&apiKey=df6f74696d4c46c5a3852248dcc4b510")
    Call<NewsModel> getNewsDetails();

}
