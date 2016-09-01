package com.warmtel.mvprr.view;

import com.warmtel.mvprr.adpater.MerchantAdapter;
import com.warmtel.mvprr.bean.ConfigResult;

/**
 * Created by Administrator on 2016/9/1.
 */
public interface INearbyView {
    void setmExpandPopTabData(ConfigResult data);

    void setListAdapter(MerchantAdapter adapter);

    void showToastMessage(String message);
}
