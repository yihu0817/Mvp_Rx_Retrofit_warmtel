package com.warmtel.mvprr.view;

import com.warmtel.mvprr.adpater.ReadNewsAdapter;
import com.warmtel.mvprr.bean.AdsBean;

import java.util.List;

/**
 * 定义UI视图逻辑
 */
public interface ICheapView {
    void setListViewAdapter(ReadNewsAdapter adpater);
    void showToastMessage(String message);
    void setBannerLayoutData(List<AdsBean> list);
}
