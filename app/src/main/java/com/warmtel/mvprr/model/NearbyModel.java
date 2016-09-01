
package com.warmtel.mvprr.model;

import android.content.Context;

import com.warmtel.mvprr.bean.AroundMessageDTO;
import com.warmtel.mvprr.bean.ConfigResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * AppAction接口的实现类
 */
public class NearbyModel implements INearbyModel {
    public static final String BASE_URL = "http://www.warmtel.com:8080/";
    private Context context;

    public NearbyModel(Context context) {
        this.context = context;
    }

    @Override
    public void getNearbyConfig(final CallbackListener<ConfigResult> listener) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitCall call = retrofit.create(RetrofitCall.class);
        Call<ConfigResult> myCall = call.getNearbyConfigs();
        myCall.enqueue(new Callback<ConfigResult>() {
            @Override
            public void onResponse(Call<ConfigResult> call, Response<ConfigResult> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ConfigResult> call, Throwable t) {
                listener.onFailure(t.getMessage());
            }
        });
    }

    @Override
    public void getNearbyAround(final CallbackListener<AroundMessageDTO> listener) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitCall call = retrofit.create(RetrofitCall.class);
        Call<AroundMessageDTO> aroundCall = call.getNearbyAround();
        aroundCall.enqueue(new Callback<AroundMessageDTO>() {
            @Override
            public void onResponse(Call<AroundMessageDTO> call, Response<AroundMessageDTO> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<AroundMessageDTO> call, Throwable t) {
                listener.onFailure(t.getMessage());
            }
        });
    }
}
