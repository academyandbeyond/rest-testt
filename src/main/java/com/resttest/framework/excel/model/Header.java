package com.resttest.framework.excel.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Header {

    @SerializedName("api-key")
    @Expose
    private String apikey;
    @Expose
    private String contenttype;
    @Expose
    private String accept;
    @Expose
    private String accepttype;
    @Expose
    private String channeltype;

//    private String apikey;
//    private String contenttype;
//    private String accept;
//    private String accepttype;
//    private String channeltype;

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getContenttype() {
        return contenttype;
    }

    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public String getAccepttype() {
        return accepttype;
    }

    public void setAccepttype(String accepttype) {
        this.accepttype = accepttype;
    }

    public String getChanneltype() {
        return channeltype;
    }

    public void setChanneltype(String channeltype) {
        this.channeltype = channeltype;
    }

}


