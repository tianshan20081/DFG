package com.gooker.dfg.domain;


import java.io.Serializable;

public class BaiDuLocation implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6826896517054539448L;

    private int status;
    private LocationInfo result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocationInfo getResult() {
        return result;
    }

    public void setResult(LocationInfo result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "BaiDuLocation [status=" + status + ", result=" + result + "]";
    }

}
