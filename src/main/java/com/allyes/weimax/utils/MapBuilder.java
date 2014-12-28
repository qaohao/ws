package com.allyes.weimax.utils;

import java.util.Map;

public final class MapBuilder {
	Map innerMap;

	private <T extends Map> MapBuilder(Class<T> clazz) {
		innerMap = LangUtil.newInstance(clazz);
	}

	public static <T extends Map> MapBuilder create(Class<T> clazz) {
		return new MapBuilder(clazz);
	}

	public MapBuilder add(Object key, Object value) {
		innerMap.put(key, value);
		return this;
	}

	public MapBuilder addAll(Map map) {
		innerMap.putAll(map);
		return this;
	}

	public Map map() {
		return innerMap;
	}
	
}
