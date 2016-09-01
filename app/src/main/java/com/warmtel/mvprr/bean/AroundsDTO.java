package com.warmtel.mvprr.bean;

import java.util.List;

@SuppressWarnings("serial")
public class AroundsDTO {
    public PageInfo pageInfo;

    public List<MerchantBean> getMerchantKey() {
        return merchantKey;
    }

    public void setMerchantKey(List<MerchantBean> merchantKey) {
        this.merchantKey = merchantKey;
    }

    public List<MerchantBean> merchantKey;


    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

}
