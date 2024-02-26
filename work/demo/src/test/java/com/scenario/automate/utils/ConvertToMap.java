package com.scenario.automate.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ConvertToMap {
	Map<Object, Object> jsonHoldMap;

	private JSONObject toJsonObject(String jsonString) throws JSONException {
		return new JSONObject(jsonString);
	}

	private List<Object> toList(JSONArray array) throws JSONException {
		List<Object> list = new ArrayList<Object>();
		for (int i = 0; i < array.length(); i++) {
			Object value = array.get(i);
			if (value instanceof JSONArray) {
				value = toList((JSONArray) value);
			} else if (value instanceof JSONObject) {
				value = toMap((JSONObject) value);
			}
			list.add(value);
		}
		return list;
	}

	private Map<String, Object> toMap(JSONObject obj) throws JSONException {
		Map<String, Object> map = new HashMap<String, Object>();
		Iterator<?> keys = obj.keys();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			Object value = obj.get(key);
			if (value instanceof JSONArray) {
				value = toList((JSONArray) value);
			} else if (value instanceof JSONObject) {
				value = toMap((JSONObject) value);
			}
			map.put(key, value);
		}
		return map;
	}

	public void setMap(String jsonString) throws JSONException {
		this.jsonHoldMap = new HashMap<>();
		JSONObject jObject = toJsonObject(jsonString);
		Iterator<?> keys = jObject.keys();

		while (keys.hasNext()) {
			String key = (String) keys.next();
			Object value = jObject.get(key);

			if (value instanceof JSONArray) {
				jsonHoldMap.put(key, toList((JSONArray) value));
				continue;
			}

			if (value instanceof JSONObject) {
				jsonHoldMap.put(key, toMap((JSONObject) value));
				continue;
			}

			jsonHoldMap.put(key, value);
		}
	}

	public Map<Object, Object> getMap() {
		return (this.jsonHoldMap == null) ? null : this.jsonHoldMap;
	}
}
