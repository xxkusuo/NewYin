package com.gxtc.yyj.newyin.mvp.model.bean;

/**
 * Created by Jam on 2017/7/28.
 */

public class ErrorBean {

    /**
     * error : User requests out of rate limit!
     * error_code : 10023
     * request : /2/statuses/home_timeline.json
     */

    private String error;
    private long error_code;
    private String request;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public long getError_code() {
        return error_code;
    }

    public void setError_code(long error_code) {
        this.error_code = error_code;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
}
