package kr.or.ddit.counsel.TEST;

import java.util.HashMap;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public class MessageTest {
public static void main(String[] args) {
	 // String api_key = "NCSC1MY5HP0I6PBZ";
	  String api_key = "NCSK8ZIFL26XMBYU";
	 //   String api_secret = "S968MOWCSSRJ4GCLAF4AFSUHIKGQYZ8P";
	    String api_secret = "E9RMXYIMYG8YNS9HP9RFXDTD3JF00WK0";
	    Message coolsms = new Message(api_key, api_secret);

	    // 4 params(to, from, type, text) are mandatory. must be filled
	    HashMap<String, String> params = new HashMap<String, String>();
	    params.put("to", "01050616330");
	    params.put("from", "01050616330");
	    params.put("type", "SMS");
	    params.put("text", "테스트1 테스트2 테스트3");
	    params.put("app_version", "test app 1.2"); // application name and version

	    try {
	      JSONObject obj = (JSONObject) coolsms.send(params);
	      Long successCount = (Long) obj.get("success_count");
	      Long failCount = (Long) obj.get("error_count");
	      if(successCount>0) {
	    	  System.out.println("성공");
	      }else {
	    	  System.out.println("실패");
	      }
	      System.out.println("successCount : "+successCount);
	      System.out.println("failCount : "+failCount);
	     
	     
	      System.out.println(obj.toString());
	     
	    } catch (CoolsmsException e) {
	      System.out.println(e.getMessage());
	      System.out.println(e.getCode());
	    }
	    
}
}
