package com.autobestinfo.demo.core;


public class CoreResponseBody<T>  {

    private T  result;
    private String message;
    private Boolean success;
    private Exception error;

    public CoreResponseBody() {

    }

    /**
     * Create an responseBody used in ResponseEntity.
     *
     * @param success must not be {@literal null}.
     * @param result response result.
     * @param message response message.
     * @param error Exception.
     */
    public CoreResponseBody(Boolean success, T result, String message, Exception error) {
           this.success = success;
           this.result = result;
           this.message = message;
           this.error = error;
    }


    public String getMessage() {
        return message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public Exception getError() {
        return error;
    }

    public T getResult() {
        return result;
    }


    public CoreResponseBody<T> setResult(T result){
        this.result = result;
        return this;
    }


    public CoreResponseBody<T> setMessage(String message){
        this.message = message;
        return this;
    }

    public CoreResponseBody<T> setSuccess(Boolean success){
        this.success = success;
        return this;
    }

    public CoreResponseBody<T> setError(Exception error){
        this.error = error;
        return this;
    }

}
