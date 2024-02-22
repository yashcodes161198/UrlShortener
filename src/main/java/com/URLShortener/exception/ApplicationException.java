package com.URLShortener.exception;

/**
 * @author sagar.jadhav
 *
 */
public class ApplicationException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 589764036475427329L;

    /**
     *

     */
    private String errorCode;

    /**
     *
     */
    private String errorMsg;
    /**
     *
     */
    private String reason;

    /**
     * @param errorCode
     */
    public ApplicationException(String errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }


    /**
     * @param errorCode
     * @param errorMsg
     */
    public ApplicationException(String errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    /**
     * @param errorCode
     * @param errorMsg
     * @param reason
     */
    public ApplicationException(String errorCode, String errorMsg, String reason) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.reason = reason;
    }

    /**
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return the errorMsg
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * @param errorMsg the errorMsg to set
     */
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason the reason to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "ApplicationException [errorCode=" + errorCode + ", errorMsg=" + errorMsg + "]";
    }
}

