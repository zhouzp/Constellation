package com.diors.json;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown=true)

public class ResponseMsgJason {
/*	"name": "MongoError",
 * 
    "err": "E11000 duplicate key error index: constellation.users.$name_1  dup key: { : null }",
    "code": 11000,
    "n": 0,
    "connectionId": 29,
    "ok": 1
    
{
    "status": "success",
    "msg": "Register successfully."
}
*/
/*	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("MongoError")
	private String mongoErrorwd;
	
	@JsonProperty("code")
	private String code;
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getPwd() {
		return pwd;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}*/
}
