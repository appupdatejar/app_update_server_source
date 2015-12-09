package com.xyf.update.model;

import java.io.Serializable;

/**
 * Created by shxiayf on 2015/12/8.
 */
public class UpdateConfigBean implements Serializable {

    private String version;
    private String packagename;
    private String path;
    private int canNotShowDialog;   //0:canNotCancel 1:canCancel

    public void setCanNotShowDialog(int canNotShowDialog) {
        this.canNotShowDialog = canNotShowDialog;
    }

    public int getCanNotShowDialog() {
        return canNotShowDialog;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setPackagename(String packagename) {
        this.packagename = packagename;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getVersion() {
        return version;
    }

    public String getPackagename() {
        return packagename;
    }

    public String getPath() {
        return path;
    }
}
