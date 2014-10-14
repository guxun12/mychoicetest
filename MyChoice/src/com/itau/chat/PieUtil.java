package com.itau.chat;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @since 
 * @version 2012-12-17
 * @author wwang
 */
public class PieUtil {
	public static String getContactsByMessage(List<Integer> list, String title) {
		JSONObject j = new JSONObject();
		try {
			JSONArray array = new JSONArray();

			JSONObject map1 = new JSONObject();
			map1.put("name", "新佳能");
			map1.put("color", "#ffc41b");
			map1.put("value", list.get(0));

//			JSONObject map2 = new JSONObject();
//			map2.put("name", "三星");
//			map2.put("color", "#7da8ff");
//			map2.put("value", list.get(1));

			JSONObject map3 = new JSONObject();
			map3.put("name", "卡西欧");
			map3.put("color", "#76a871");
			map3.put("value", list.get(1));

			array.put(map1);
//			array.put(map2);
			array.put(map3);
			j.put("data", array);
			j.put("title", title);
			j.put("bili", "单位：人");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return j.toString();
	}
}
