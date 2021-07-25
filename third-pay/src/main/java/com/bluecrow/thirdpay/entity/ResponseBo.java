package com.bluecrow.thirdpay.entity;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;


/**
 * @Package: com.hessianhealth.phoenix.followup.core.resbo
 * @Decription controller统一返回值
 * @author zhangq 
 * @date 2021年2月1日 上午10:33:44
 */
public class ResponseBo<T> extends JSONObject implements Serializable  {

	private static final long serialVersionUID = -2871765240104837657L;
	
	/**
     * 成功统一返回报文
     * @return controller统一返回值
     */
    public static <T> ResponseBo<T> ok() {
        return responseBo(200, "ok", null);
    }

    /**
     * 统一返回报文
     * @param code 编码
     * @param msg 内容
     * @param data 数据
     * @return controller统一返回值
     */
    public static <T> ResponseBo<T> responseBo(int code, String msg, T data) {
        ResponseBo<T> json = new ResponseBo<>();
        json.put("code", code);
        json.put("msg", msg);
        json.put("data", data);
        return json;
    }
}
