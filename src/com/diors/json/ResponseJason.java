package com.diors.json;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown=true)
public class ResponseJason {
	/*{
	    "status": "success",
	    "msg": "Register successfully."
	}*/
	
	@JsonProperty("status")
	private String status;
	
	@JsonProperty("msg")
	private String msg;
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
