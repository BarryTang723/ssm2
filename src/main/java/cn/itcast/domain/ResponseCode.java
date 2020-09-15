package cn.itcast.domain;

public class ResponseCode {
	private Integer code=0;
	private String msg="";
	
	public ResponseCode() {
		
	}
	public ResponseCode(Integer code,String msg) {
		this.code=code;
		this.msg=msg;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
