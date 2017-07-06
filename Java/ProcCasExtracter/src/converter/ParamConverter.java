package converter;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class ParamConverter {
	public static void main(String[] args) throws UnsupportedEncodingException{
		Map<String, String> param = new HashMap<>();
		param.put("start", ""+0);
		param.put("limit", ""+2);
		param.put("filter", "contains(id,'dm')");
		System.out.println(converter("http://aatest.instance.openstack.sas.com:7980/capvalidation/validation-models", param));
	}
	
	public static String converter(String URL, Map<String, String> param){
		return URL+ converter(param);
	}
	
	public static String converter(Map<String, String> param){
		String httpParams ="?";
		for(String s: param.keySet()){
			String query="";
			try {query= URLEncoder.encode(param.get(s), "UTF-8");} 
			catch (Exception e) {e.printStackTrace();}
			httpParams+=s+"="+query+"&";
		}
		return httpParams.substring(0, httpParams.length()-1);
	}
}
