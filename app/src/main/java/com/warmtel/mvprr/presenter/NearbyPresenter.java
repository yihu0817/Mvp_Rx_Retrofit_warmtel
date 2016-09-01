package com.warmtel.mvprr.presenter;

import android.content.Context;

import com.warmtel.mvprr.BasePresenter;
import com.warmtel.mvprr.adpater.MerchantAdapter;
import com.warmtel.mvprr.bean.AroundMessageDTO;
import com.warmtel.mvprr.bean.AroundsDTO;
import com.warmtel.mvprr.bean.ConfigResult;
import com.warmtel.mvprr.bean.MerchantBean;
import com.warmtel.mvprr.model.CallbackListener;
import com.warmtel.mvprr.model.INearbyModel;
import com.warmtel.mvprr.model.NearbyModel;
import com.warmtel.mvprr.view.INearbyView;

import java.util.ArrayList;

public class NearbyPresenter extends BasePresenter<INearbyView> implements INearbyPresenter{
    private Context mContext;
    private MerchantAdapter mMerchantAdapter;
    private INearbyModel mNearbyModel;

    public NearbyPresenter(Context context) {
        mContext = context;
        mNearbyModel = new NearbyModel(mContext);
        mMerchantAdapter = new MerchantAdapter(mContext);
    }

    @Override
    public void setListAdapter() {
        mView.setListAdapter(mMerchantAdapter);
    }

    /**
     * 设置二级菜单数据源
     */
    @Override
    public void setExpandPopTabViewData() {
        mNearbyModel.getNearbyConfig(new CallbackListener<ConfigResult>() {
            @Override
            public void onSuccess(ConfigResult data) {
                mView.setmExpandPopTabData(data);
            }

            @Override
            public void onFailure(String message) {
                mView.showToastMessage(message);
            }
        });
    }

    /**
     * 设置列表数据
     */
    @Override
    public void setListViewData() {
        mNearbyModel.getNearbyAround(new CallbackListener<AroundMessageDTO>() {
            @Override
            public void onSuccess(AroundMessageDTO data) {
                AroundsDTO aroundsDTO = data.getInfo();
                ArrayList<MerchantBean> merchantLists = (ArrayList<MerchantBean>) aroundsDTO.getMerchantKey();
                mMerchantAdapter.setListData(merchantLists);
            }

            @Override
            public void onFailure(String message) {
                mView.showToastMessage(message);
            }
        });
    }
}
