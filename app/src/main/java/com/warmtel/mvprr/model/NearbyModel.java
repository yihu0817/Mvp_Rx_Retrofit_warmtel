
package com.warmtel.mvprr.model;

import android.content.Context;

import com.warmtel.mvprr.RetrofitManager;
import com.warmtel.mvprr.bean.AroundMessageDTO;
import com.warmtel.mvprr.bean.ConfigResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * AppAction接口的实现类
 */
public class NearbyModel implements INearbyModel {

    private Context context;

    public NearbyModel(Context context) {
        this.context = context;
    }

    @Override
    public void getNearbyConfig(final CallbackListener<ConfigResult> listener) {
        Call<ConfigResult> myCall = RetrofitManager.getmRetrofitCall().getNearbyConfigs();
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
        Call<AroundMessageDTO> aroundCall = RetrofitManager.getmRetrofitCall().getNearbyAround();
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
