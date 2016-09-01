package com.warmtel.mvprr;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public abstract class BaseMvpFragment<V, T extends BasePresenter<V>> extends Fragment {
    /*定义一个Presenter 用于解绑持有的View
       *
       * 在onAttach进行初始化Presenter的操作
       * 在onCreate中进行绑定
       * 在onDestroy 中进行解绑
       *
       **/
    public T mPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mPresenter = initPresenter();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.attach((V) this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.dettach();
    }

    /* 这里是适配为不同的View 装载不同Presenter*/
    public abstract T initPresenter();
}