package com.xyf.update.model;

import java.io.Serializable;

/**
 * Created by shxiayf on 2015/12/7.
 */
public class UpdateResponseBean implements Serializable {

    private int isUpdate;
    private String url;
    private long filesize;

    public void setIsUpdate(int isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setFilesize(long filesize) {
        this.filesize = filesize;
    }

    public int getIsUpdate() {
        return isUpdate;
    }

    public String getUrl() {
        return url;
    }

    public long getFilesize() {
        return filesize;
    }
}
