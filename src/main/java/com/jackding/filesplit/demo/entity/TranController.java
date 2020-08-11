package com.jackding.filesplit.demo.entity;

/**
 * @author dh
 * @version 1.0
 * @date created by 2020/8/11 17:19
 * @description
 **/
public class TranController {
    private String tranId;
    private String isFile;
    private String splitKey;
    private String splitMode;
    private String maxThread;

    public String getTranId() {
        return tranId;
    }

    public void setTranId(String tranId) {
        this.tranId = tranId;
    }

    public String getIsFile() {
        return isFile;
    }

    public void setIsFile(String isFile) {
        this.isFile = isFile;
    }

    public String getSplitKey() {
        return splitKey;
    }

    public void setSplitKey(String splitKey) {
        this.splitKey = splitKey;
    }

    public String getSplitMode() {
        return splitMode;
    }

    public void setSplitMode(String splitMode) {
        this.splitMode = splitMode;
    }

    public String getMaxThread() {
        return maxThread;
    }

    public void setMaxThread(String maxThread) {
        this.maxThread = maxThread;
    }
}
