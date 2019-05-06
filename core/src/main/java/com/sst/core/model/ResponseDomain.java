package com.sst.core.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 封装application/json响应消息体
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ResponseDomain implements Serializable {

    private static final long serialVersionUID = 1L;

    public final static String SUCCESS_CODE = "000000";
    public final static String SUCCESS_MESSAGE = "success";

    public final static String FAILED_CODE = "100000";
    public final static String FAILED_MESSAGE = "failed";

    public final static String MISSING_CODE = "100001";
    public final static String MISSING_MESSAGE = "missing_parameters";

    private String result;

    private String resultDesc;

    private Object data;

    public static ResponseDomain getSuccessResponse() {
        ResponseDomain response = new ResponseDomain();
        response.setResult(SUCCESS_CODE);
        response.setResultDesc(SUCCESS_MESSAGE);
        return response;
    }

    public static ResponseDomain getFailedResponse() {
        ResponseDomain response = new ResponseDomain();
        response.setResult(FAILED_CODE);
        response.setResultDesc(FAILED_MESSAGE);
        return response;
    }

    public static ResponseDomain getMissingParameters() {
        ResponseDomain response = new ResponseDomain();
        response.setResult(MISSING_CODE);
        response.setResultDesc(MISSING_MESSAGE);
        return response;
    }

    public static ResponseDomain getResponse(String result, String resultDesc) {
        ResponseDomain response = new ResponseDomain();
        response.setResult(result);
        response.setResultDesc(resultDesc);
        return response;
    }

}
