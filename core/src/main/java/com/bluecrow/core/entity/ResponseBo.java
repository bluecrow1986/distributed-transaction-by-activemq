package com.bluecrow.core.entity;

import java.io.Serializable;
import java.util.UUID;

import com.bluecrow.core.consts.ExceptionEnum;
import com.bluecrow.core.consts.ResponseConst;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;


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
        return responseBo(ExceptionEnum.SUCCESS.getCode(), ExceptionEnum.SUCCESS.getMsg(), null);
    }

	/**
     * 成功统一返回报文
     * @param data 数据
     * @return controller统一返回值
     */
    public static <T> ResponseBo<T> ok(T data) {
        return responseBo(ExceptionEnum.SUCCESS.getCode(), ExceptionEnum.SUCCESS.getMsg(), data);
    }

    /**
     * 成功统一返回报文
     * @param code 编码
     * @param msg 内容
     * @param data 数据
     * @return controller统一返回值
     */
    public static <T> ResponseBo<T> ok(int code, String msg, T data) {
        return responseBo(code, msg, data);
    }

    /**
     * 成功统一返回报文
     * @param data 分页参数
     * @return controller统一返回值
     */
    public static <T> ResponseBo<PageInfo<T>> ok(PageInfo<T> data) {
        return responseBoWithPage(ExceptionEnum.SUCCESS.getCode(), ExceptionEnum.SUCCESS.getMsg(), data);
    }

    /**
     * 成功统一返回报文
     * @param code 编码
     * @param msg 内容
     * @param data 分页参数
     * @return controller统一返回值
     */
    public static <T> ResponseBo<PageInfo<T>> ok(int code, String msg, PageInfo<T> data) {
        return responseBoWithPage(code, msg, data);
    }

    /**
     * 错误统一返回报文
     * @return controller统一返回值
     */
    public static <T> ResponseBo<T> error() {
        return responseBoWithUUID(ExceptionEnum.SERVER_ERROR.getCode(), ExceptionEnum.SERVER_ERROR.getMsg(), null, null);
    }

    /**
     * 错误统一返回报文
     * @param code 编码
     * @return controller统一返回值
     */
    public static <T> ResponseBo<T> error(int code) {
        return responseBoWithUUID(code, ExceptionEnum.SERVER_ERROR.getMsg(), null, null);
    }
    
    /**
     * 错误统一返回报文
     * @param msg 内容
     * @return controller统一返回值
     */
    public static <T> ResponseBo<T> error(String msg) {
    	return responseBoWithUUID(ExceptionEnum.SERVER_ERROR.getCode(), msg, null, null);
    }

    /**
     * 错误统一返回报文
     * @param code 编码
     * @param msg 内容
     * @return controller统一返回值
     */
    public static <T> ResponseBo<T> error(int code, String msg) {
        return responseBoWithUUID(code, msg, null, null);
    }
    
    /**
     * 错误统一返回报文
     * @param exception 错误枚举
     * @param desc 错误描述
     * @return controller统一返回值
     */
    public static <T> ResponseBo<T> error(ExceptionEnum exception, String desc) {
    	return responseBoWithUUID(exception.getCode(), exception.getMsg(), desc, null);
    }
    
    /**
     * 错误统一返回报文
     * @param code 编码
     * @param msg 内容
     * @param desc 描述
     * @param data 数据
     * @return controller统一返回值
     */
    public static <T> ResponseBo<T> error(int code, String msg, String desc, T data) {
        return responseBoWithUUID(code, msg, desc, data);
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
        json.put(ResponseConst.CODE, code);
        json.put(ResponseConst.MSG, msg);
        json.put(ResponseConst.DATA, data);
        return json;
    }

    /**
     * 统一返回报文
     * @param code 编码
     * @param msg 内容
     * @param desc 描述
     * @param data 数据
     * @return controller统一返回值
     */
    public static <T> ResponseBo<T> responseBoWithUUID(int code, String msg, String desc, T data) {
        ResponseBo<T> json = new ResponseBo<>();
        json.put(ResponseConst.CODE, code);
        json.put(ResponseConst.MSG, msg);
        json.put(ResponseConst.DESC, desc);
        json.put(ResponseConst.UUID, UUID.randomUUID().toString());
        json.put(ResponseConst.DATA, data);
        return json;
    }

    /**
     * 统一返回报文
     * @param code 编码
     * @param msg 内容
     * @param data 分页参数
     * @return controller统一返回值
     */
    public static <T> ResponseBo<PageInfo<T>> responseBoWithPage(int code, String msg, PageInfo<T> data) {
        ResponseBo<PageInfo<T>> json = new ResponseBo<>();
        json.put(ResponseConst.CODE, code);
        json.put(ResponseConst.MSG, msg);
        json.put(ResponseConst.UUID, UUID.randomUUID().toString());
        json.put(ResponseConst.DATA, data);
        return json;
    }
    
    /**
     * 统一返回报文
     * @param code 编码
     * @param msg 内容
     * @param desc 描述
     * @param outId 外部ID(调用方传入)
     * @param data 数据
     * @return controller统一返回值
     */
    public static <T> ResponseBo<T> responseBo(int code, String msg, String desc, String outId, T data) {
    	ResponseBo<T> json = new ResponseBo<>();
		json.put(ResponseConst.CODE, code);
		json.put(ResponseConst.MSG, StringUtils.isEmpty(msg) || msg.equals("null") ? null : msg);
		json.put(ResponseConst.DESC, StringUtils.isEmpty(desc) || desc.equals("null") ? null : desc);
		json.put(ResponseConst.OUT_ID, StringUtils.isEmpty(outId) || outId.equals("null") ? null : outId);
		json.put(ResponseConst.DATA, StringUtils.isEmpty(data) || data.equals("null") ? null : data);
        
        return json;
    }
}
