package com.mlk.home.common.utils;

import java.io.Serializable;

public class Message implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private boolean           success          = true;
    private String            msg              = "成功";
    private Object obj;

    public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public Message(boolean success, String msg) {
        super();
        this.success = success;
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Message() {
        super();
    }

    public Message(String msg) {
        super();
        this.msg = msg;
    }

}
