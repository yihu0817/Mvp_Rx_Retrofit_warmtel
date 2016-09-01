package com.warmtel.mvprr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseMvpActivity<V, T extends BasePresenter<V>> extends AppCompatActivity {
    /*定义一个Presenter 用于解绑持有的View
       *
       * 在onCreate进行初始化Presenter的操作
       * 在onResume中进行绑定
       * 在onDestroy 中进行解绑
       *
       **/
    public T presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = initPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attach((V) this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dettach();
    }

    /* 这里是适配为不同的View 装载不同Presenter*/
    public abstract T initPresenter();
}