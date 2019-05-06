package com.sst.core.exception;

public class BizException extends Exception {
	private static final long serialVersionUID = 1L;

	private String errCode; // 错误代码

	public BizException(String errCode) {
		this.errCode = errCode;
	}

	public BizException(String errCode, String message) {
		super(message);
		this.errCode = errCode;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public BizException(String message, Throwable cause) {
		super(message, cause);
	}
}
