package com.bluecrow.core.consts;

/**
 * @decription 异常信息 枚举
 */
public enum ExceptionEnum {
	// 成功
	SUCCESS(200, "OK"),

	SERVER_ERROR(699, "服务器出错, 请联系相关人员"),
	// 600 配置服务错误类型 end

	;

	/**
	 * 异常码
	 */
	private int code;

	/**
	 * 异常信息
	 */
	private String msg;

	/**
	 * 数据
	 */
	private Object data;

	private ExceptionEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	private ExceptionEnum(int code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	/**
	 * 获取  code
	 * @return code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * 获取  msg
	 * @return msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * 获取  data
	 * @return data
	 */
	public Object getData() {
		return data;
	}
}
