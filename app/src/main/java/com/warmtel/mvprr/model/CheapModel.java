package com.warmtel.mvprr.model;

import com.warmtel.mvprr.bean.NewsTextBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CheapModel implements ICheapModel {
    @Override
    public void setListAndAdsData(final CallbackListener<NewsTextBean> listener) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://c.m.163.com/")
                .build();

        RetrofitCall call = retrofit.create(RetrofitCall.class);
        Call<NewsTextBean> mCall = call.getNewsText();
        mCall.enqueue(new Callback<NewsTextBean>() {
            @Override
            public void onResponse(Call<NewsTextBean> call, Response<NewsTextBean> response) {
                NewsTextBean newsTextBean =  response.body();
                listener.onSuccess(newsTextBean);
            }

            @Override
            public void onFailure(Call<NewsTextBean> call, Throwable t) {
                listener.onFailure(t.getMessage());

            }
        });
    }
}
