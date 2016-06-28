package com.chdw.loc.util;

import com.chdw.loc.bean.FormatType;
import com.chdw.loc.bean.SdkHttpResult;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;
import java.lang.reflect.Type;


/**
 * json数据解析工具类
 */
public class GsonUtil {

	private static Gson gson = new Gson();

	public static String toJson(Object obj, Type type) {
		return gson.toJson(obj, type);
	}

	public static Object fromJson(String str,Type type){
		return gson.fromJson(str, type);
	}

	public static String toJson(Object obj) {
		return gson.toJson(obj);
	}

	public static String parseJson(String id,String username){
		try {
			String key = "3argexb6rnk1e";
			String secret = "AgOexcuEA03Q";
			SdkHttpResult tokenJson = ApiHttpClient.getToken(key, secret, id, username,
					"http://aa.com/a.png", FormatType.json);
			String res = GsonUtil.toJson(tokenJson);
			StringReader sr = new StringReader(res);
			JsonReader jr = new JsonReader(sr);
			jr.beginObject();
			if (jr.nextName().equals("code")) {
				jr.nextString();
				if (jr.nextName().equals("result")) {
					sr = new StringReader(jr.nextString());
					jr = new JsonReader(sr);
					jr.beginObject();
					if (jr.nextName().equals("code")) {
						jr.nextString();
					}
					if (jr.nextName().equals("userId")) {
						jr.nextString();
					}
					if (jr.nextName().equals("token")) {
						return jr.nextString();
					}
				}
			}
			jr.endObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}  
