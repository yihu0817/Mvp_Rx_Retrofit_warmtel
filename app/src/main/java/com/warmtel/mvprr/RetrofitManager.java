package com.warmtel.mvprr;

import android.content.Context;
import android.util.Log;

import com.warmtel.mvprr.Utils.HttpNetUtil;
import com.warmtel.mvprr.model.RetrofitCall;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 2016/9/2.
 */
public class RetrofitManager {
    private static String DEFAULT_CACHE_DIR_PIC = "okhttp";
    public static final String BASE_URL = "http://www.warmtel.com:8080/";
    public static RetrofitCall mRetrofitCall;
    private static Context mContext;

    /**
     * 使用Retrofit之前调用
     * 建议在Appliction中初始化
     */
    public static void init(Context context) {
        mContext = context;

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(provideOfflineCacheInterceptor())
                .addNetworkInterceptor(provideCacheInterceptor())
                .cache(provideCache())
                .build();

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        mRetrofitCall = mRetrofit.create(RetrofitCall.class);
    }

    public static RetrofitCall getmRetrofitCall() {
        if (mRetrofitCall != null) {
            return mRetrofitCall;
        } else {
            throw new IllegalStateException("RequestQueue not initialized");
        }
    }

    /**
     * 有网络时第一次请求从服务器，在20后重新请求服务器，无网络时30秒内从缓存取数据，
     * 超过时间后无法获取数据，自己可根据实际需求进行设置；
     * 离线缓存：
     *
     * @return
     */
    public static Interceptor provideOfflineCacheInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                /**
                 * 未联网获取缓存数据
                 */
                if (!HttpNetUtil.isNetworkConnected(mContext)) {
                    //在20秒缓存有效，此处测试用，实际根据需求设置具体缓存有效时间
                    CacheControl cacheControl = new CacheControl.Builder()
                            .maxStale(20, TimeUnit.SECONDS)
                            .build();
                    request = request.newBuilder()
                            .cacheControl(cacheControl)
                            .build();
                }
                return chain.proceed(request);
            }
        };
    }

    /**
     * 有网络时在限定时间内多次请求取缓存，超过时间重新请求：
     *
     * @return
     */
    public static Interceptor provideCacheInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());

                // re-write response header to force use of cache
                // 正常访问同一请求接口（多次访问同一接口），给30秒缓存，超过时间重新发送请求，否则取缓存数据
                CacheControl cacheControl = new CacheControl.Builder()
                        .maxAge(30, TimeUnit.SECONDS)
                        .build();

                return response.newBuilder()
                        .header("CACHE_CONTROL", cacheControl.toString())
                        .build();
            }
        };
    }

    //设置缓存目录和缓存空间大小
    private static Cache provideCache() {
        File cacheDir = null;
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            // cacheDir 缓存路径 /mnt/sdcard/Android/data/<pkg name>/cache/<name>
            // fileDir 缓存路径 /mnt/sdcard/Android/data/<pkg name>/files/<name>
            cacheDir = new File(mContext.getExternalCacheDir(), DEFAULT_CACHE_DIR_PIC);
        } else {
            // cacheDir 缓存路径 /data/data/<pkg name>/cache/<name>
            cacheDir = new File(mContext.getCacheDir(), DEFAULT_CACHE_DIR_PIC);
        }

        Cache cache = null;
        try {
            cache = new Cache(cacheDir, 10 * 1024 * 1024); // 10 MB
        } catch (Exception e) {
            Log.e("cache", "Could not create Cache!");
        }
        return cache;
    }

    private static OkHttpClient provideOkHttpClient() {
        //日志
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
//                .addInterceptor(interceptor)
                .addInterceptor(provideOfflineCacheInterceptor())
                .addNetworkInterceptor(provideCacheInterceptor())
                .cache(provideCache())
                .build();
    }
}
