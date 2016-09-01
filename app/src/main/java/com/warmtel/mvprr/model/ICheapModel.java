package com.warmtel.mvprr.model;

import com.warmtel.mvprr.bean.NewsTextBean;


public interface ICheapModel {
    void setListAndAdsData(final CallbackListener<NewsTextBean> listener);
}
