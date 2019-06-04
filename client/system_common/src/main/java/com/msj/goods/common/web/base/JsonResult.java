package com.msj.goods.common.web.base;

public class JsonResult {
    public static final int SUCESS =0;
    public static final int FAILURE =-1;
    private Object data;
    private String msg = "";
    private int code;

    private String stringCode;


    public static JsonResult success(Object data, String msg) {
        JsonResult result = new JsonResult();
        result.code = SUCESS;
        result.msg = msg;
        result.data = data;
        return result;
    }

    public static JsonResult success() {
        JsonResult result = new JsonResult();
        result.code = SUCESS;
        return result;
    }

    public static JsonResult success(String msg) {
        JsonResult result = new JsonResult();
        result.msg = msg;
        result.code = SUCESS;
        return result;
    }

    public static JsonResult failure(String msg) {
        return failure(FAILURE, msg);
    }

    public static JsonResult failure(int code, String msg) {
        JsonResult result = new JsonResult();
        result.msg = msg;
        result.code = code;
        return result;
    }

    public static JsonResult failureString(String stringCode, String msg) {
        JsonResult result = new JsonResult();
        result.msg = msg;
        result.stringCode = stringCode;
        return result;
    }

    public Object getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public double getCode() {
        return code;
    }

    public String getStringCode(){return stringCode;}

}
