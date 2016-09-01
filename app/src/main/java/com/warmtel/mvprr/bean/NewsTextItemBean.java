package com.warmtel.mvprr.bean;

import java.util.ArrayList;
import java.util.List;
public class NewsTextItemBean {
    private String title;
    private String digest;
    private String imgsrc;
    private String docId;
    private List<NewImageSrcBean> imgextra = new ArrayList<>();
    private List<AdsBean> ads;
    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }
    public List<NewImageSrcBean> getImgextra() {
        return imgextra;
    }

    public void setImgextra(List<NewImageSrcBean> imgextra) {
        this.imgextra = imgextra;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AdsBean> getAds() {
        return ads;
    }

    public void setAds(List<AdsBean> ads) {
        this.ads = ads;
    }

}
