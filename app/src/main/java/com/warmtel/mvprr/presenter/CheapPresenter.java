package com.warmtel.mvprr.presenter;

import android.content.Context;

import com.warmtel.mvprr.BasePresenter;
import com.warmtel.mvprr.adpater.ReadNewsAdapter;
import com.warmtel.mvprr.bean.AdsBean;
import com.warmtel.mvprr.bean.NewsTextBean;
import com.warmtel.mvprr.bean.NewsTextItemBean;
import com.warmtel.mvprr.model.CallbackListener;
import com.warmtel.mvprr.model.ICheapModel;
import com.warmtel.mvprr.model.CheapModel;
import com.warmtel.mvprr.view.ICheapView;

import java.util.List;

public class CheapPresenter extends BasePresenter<ICheapView> implements ICheapPresenter{
    private ICheapModel mCheapModel;
    private ReadNewsAdapter mReadNewsAdapter;

    public CheapPresenter(Context context) {
        mCheapModel = new CheapModel();
        mReadNewsAdapter = new ReadNewsAdapter(context);
    }

    @Override
    public void setListViewAdapter() {
        mView.setListViewAdapter(mReadNewsAdapter);
    }

    @Override
    public void setListBannerViewData() {
        mCheapModel.setListAndAdsData(new CallbackListener<NewsTextBean>() {
            @Override
            public void onSuccess(NewsTextBean data) {
                List<NewsTextItemBean> newsTextItemBeanList = data.getT1348647909107();
                mReadNewsAdapter.addDatas(0,newsTextItemBeanList);
                List<AdsBean> adsLists = newsTextItemBeanList.get(0).getAds();
                mView.setBannerLayoutData(adsLists);
            }

            @Override
            public void onFailure(String message) {
                mView.showToastMessage(message);
            }
        });
    }
}
