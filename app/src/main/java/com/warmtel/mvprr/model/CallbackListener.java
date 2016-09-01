package com.warmtel.mvprr.model;

/**
 * Action的处理结果回调监听器
 */
public interface CallbackListener<T> {
    void onSuccess(T data);

    void onFailure(String message);
}
