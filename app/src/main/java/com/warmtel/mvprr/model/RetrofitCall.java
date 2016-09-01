package com.warmtel.mvprr.model;

import com.warmtel.mvprr.bean.AroundMessageDTO;
import com.warmtel.mvprr.bean.ConfigResult;
import com.warmtel.mvprr.bean.NewsTextBean;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitCall {
    @GET("/around")
    Call<AroundMessageDTO> getNearbyAround();

    @GET("/configs")
    Call<ConfigResult> getNearbyConfigs();

    @GET("/nc/article/headline/T1348647909107/0-20.html")
    Call<NewsTextBean> getNewsText();
}
