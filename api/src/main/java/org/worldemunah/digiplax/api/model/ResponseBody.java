package org.worldemunah.digiplax.api.model;

import java.io.Serializable;

/**
 * User: Ariel
 * Date: 9/15/2019
 */
public class ResponseBody implements Serializable {

    private boolean success;
    private String errorMsg;

    protected ResponseBody() {
    }

    private ResponseBody(boolean success, String errorMsg) {
        this.success = success;
        this.errorMsg = errorMsg;
    }

    public static ResponseBody withError(String errorMsg) {
        return new ResponseBody(false, errorMsg);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "ResponseBody{" +
                "success=" + success +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
