
package com.warmtel.mvprr.model;


import com.warmtel.mvprr.bean.AroundMessageDTO;
import com.warmtel.mvprr.bean.ConfigResult;

/**
 * 接收app层的各种Action
 */
public interface INearbyModel {
    /**
     * 获取二级菜单数据
     */
    void getNearbyConfig(final CallbackListener<ConfigResult> listener);

    /**
     * 获取附近列表数据
     */
    void getNearbyAround(final CallbackListener<AroundMessageDTO> listener);

}
