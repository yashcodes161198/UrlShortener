package com.URLShortener.DTO;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * This class would act as a generic response to all the web-services
 *
 * @author sagar.jadhav
 * @version 1.0
 */
public class ServiceResDTO implements Serializable {

    private boolean status;

    @JsonInclude(Include.NON_NULL)
    private String statusCode;

    @JsonInclude(Include.NON_NULL)
    private String statusMessage;

    @JsonInclude(Include.NON_NULL)
    private Object response;

    @JsonInclude(Include.NON_NULL)
    private Object errorResponse;

    private ServiceResDTO(ServiceResponseBuilder serviceResponseBuilder) {
        this.status = serviceResponseBuilder.status;
        this.statusCode = serviceResponseBuilder.statusCode;
        this.statusMessage = serviceResponseBuilder.statusMessage;
        this.response = serviceResponseBuilder.response;
        this.errorResponse = serviceResponseBuilder.errorResponse;
    }

    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @return the statusCode
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * @return the statusMessage
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * @return the response
     */
    public Object getResponse() {
        return response;
    }

    /**
     * @return the errorResponse
     */
    public Object getErrorResponse() {
        return errorResponse;
    }

    public static class ServiceResponseBuilder {
        private boolean status;
        private String statusCode;
        private String statusMessage;
        private Object response;
        private Object errorResponse;

        public ServiceResponseBuilder setStatus(boolean status) {
            this.status = status;
            return this;
        }

        public ServiceResponseBuilder setStatusCode(String statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public ServiceResponseBuilder setStatusMessage(String statusMessage) {
            this.statusMessage = statusMessage;
            return this;
        }

        public ServiceResponseBuilder setResponse(Object response) {
            this.response = response;
            return this;
        }

        public ServiceResponseBuilder setErrorResponse(Object errorResponse) {
            this.errorResponse = errorResponse;
            return this;
        }

        public ServiceResDTO build() {
            ServiceResDTO serviceResponse = new ServiceResDTO(this);
            return serviceResponse;
        }

        @Override
        public String toString() {
            return "ServiceResponseBuilder [status=" + status + ", statusCode=" + statusCode + ", statusMessage=" + statusMessage + ", response=" + response + ", errorResponse=" + errorResponse + "]";
        }

    }

    @Override
    public String toString() {
        return "ServiceResDTO [status=" + status + ", statusCode=" + statusCode + ", statusMessage=" + statusMessage + ", response=" + response + ", errorResponse=" + errorResponse + "]";
    }

}

