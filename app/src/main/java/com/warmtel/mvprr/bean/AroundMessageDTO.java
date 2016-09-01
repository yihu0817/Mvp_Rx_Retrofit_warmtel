/**
 * Copyright 2012 viktor.zhou
 */
package com.warmtel.mvprr.bean;


public class AroundMessageDTO {
    private String resultCode;
    private String resultInfo;
    private AroundsDTO info;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultInfo() {
        return resultInfo;
    }

    public void setResultInfo(String resultInfo) {
        this.resultInfo = resultInfo;
    }

    public AroundsDTO getInfo() {
        return info;
    }

    public void setInfo(AroundsDTO info) {
        this.info = info;
    }

}
