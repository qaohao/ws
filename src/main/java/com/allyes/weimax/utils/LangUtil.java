package com.allyes.weimax.utils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 工具类
 * 
 * @author 张 萌
 * @version 1.0
 */
public class LangUtil {

	/**
	 * 空白字符串。
	 */
	private static final String BLANK = "";

	/**
	 * 字符串编码转换的实现方法 　　
	 * 
	 * @throws UnsupportedEncodingException
	 *             　　
	 */
	public static String changeCharset(String str, String oldCharset, String newCharset)
			throws UnsupportedEncodingException {
		if (str != null) {
			// 用旧的字符编码解码字符串。解码可能会出现异常。
			byte[] bs = str.getBytes(oldCharset);
			// 用新的字符编码生成字符串
			return new String(bs, newCharset);
		}
		return null;
	}

	/**
	 * 克隆javaBean。
	 * 
	 * @param bean
	 *            javaBean对象
	 * @return 新javaBean对象
	 * @see BeanUtils#cloneBean(Object)
	 * @see IllegalArgumentException
	 */
	public static Object cloneBean(Object bean) {
		try {
			return BeanUtils.cloneBean(bean);
		} catch (Exception e) {
			throw new IllegalArgumentException("bean对象不合法！", e);
		}
	}


	/**
	 * 克隆Map。
	 * 
	 * @param map
	 *            map对象
	 * @return 新map对象
	 * @see HashMap
	 * @see Hashtable
	 * @see IllegalArgumentException
	 */
	public static <K, V> Map<K, V> cloneMap(Map<K, V> map) {
		Map<K, V> newMap = null;
		if (map instanceof HashMap) {
			newMap = new HashMap<K, V>();
		} else if (map instanceof Hashtable) {
			newMap = new Hashtable<K, V>();
		} else {
			throw new IllegalArgumentException("不支持非HashMap以及Hashtable对象！");
		}
		newMap.putAll(map);
		return newMap;
	}
	
	/**
	 * @see Object#equals(Object)
	 */
	public static <T> boolean equals(T t1, T t2) {
		return t1 == null ? t2 == null : t1.equals(t2);
	}

	
	/**
	 * 获得超类的参数类型，取第一个参数类型
	 * @param <T> 类型参数
	 * @param clazz 超类类型
	 */
	public static <T> Class<T> getClassGenricType(final Class clazz) {
		return getClassGenricType(clazz, 0);
	}
	
	/**
	 * 根据索引获得超类的参数类型
	 * @param clazz 超类类型
	 * @param index 索引
	 */
	public static Class getClassGenricType(final Class clazz, final int index) {
		Type genType = clazz.getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}
		Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
		if (index >= params.length || index < 0) {
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return (Class) params[index];
	}
	
	/**
	 * 读取本类及所有父类定义的属性。
	 * 
	 * @param clazz 类型
	 * @return 所有属性
	 */
	public static List<Field> getFields(Class<?> clazz) {
		List<Field> fieldList = new ArrayList<Field>();
		for (Field field : clazz.getDeclaredFields()) {
			fieldList.add(field);
		}
		if (clazz.getSuperclass() != null) {
			fieldList.addAll(getFields(clazz.getSuperclass()));
		}
		return fieldList;
	}
	
	/**
	 * 判断字符串是空白或者空格字符串。
	 * <p>
	 * 例如：<br>
	 * <li>{@code isBlank(null)  = true}</li>
	 * <li>{@code isBlank("")    = true}</li>
	 * <li>{@code isBlank("  ")  = true}</li>
	 * <li>{@code isBlank("abc") = false}</li>
	 * 
	 * @param str
	 *            待检测字符串
	 * @return {@code str}为空白或者空格字符串时返回{@code true}
	 */
	public static boolean isBlank(String str) {
		return str == null || BLANK.equals(str.trim());
	}

	/**
	 * 将列表连接。
	 */
	public static String join(List list, String joinStr) {
		if (list == null || list.isEmpty()) {
			return BLANK;
		}

		joinStr = replaceNull(joinStr);
		StringBuffer result = new StringBuffer();
		result.append(list.get(0));
		for (int i = 1; i < list.size(); i++) {
			result.append(joinStr);
			result.append(list.get(i));
		}
		return result.toString();
	}
	
	public static <T> T newInstance(Class<T> clazz) {
		try {
			return clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static <T> List<T> array2List(T[] array) {
		if (ArrayUtils.isEmpty(array)) {
			return null;
		} else {
			List list = new ArrayList<T>();
			for (T t : array) {
				list.add(t);
			}
			return list;
		}
	}
	
	/**
	 * 字符串{@code null}替换。
	 * <p>
	 * 例如：<br>
	 * <li>{@code replaceNull(null)  = ""}</li>
	 * <li>{@code replaceNull("")    = ""}</li>
	 * <li>{@code replaceNull("abc") = "abc"}</li>
	 * 
	 * @param str
	 *            待替代字符串
	 * @return {@code str}为{@code null}时，返回{@code BLANK}
	 * @see LangUtil#BLANK
	 */
	public static String replaceNull(String str) {
		if (isBlank(str)) {
			return BLANK;
		} else {
			return str;
		}
	}

	public static String replaceNull(String str, String defaultStr) {
		if (isBlank(str)) {
			return defaultStr;
		} else {
			return str;
		}
	}
	
	public static <T> T replaceNull(T src, T def) {
		if (src == null) {
			return def;
		} else {
			return src;
		}
	}
	
	/**
	 * 四舍五入。
	 * 
	 * scale 保留的位数。
	 */
	public static BigDecimal round(Object d, int scale) {
		if (d == null) {
			d = "0";
		}
		BigDecimal b = new BigDecimal(d.toString());
		return b.setScale(scale, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 将元素转数组。
	 */
	public static <T> T[] toArray(T... elem) {
		return elem;
	}
	
	/**
	 * 将pojo对象转换成一个Map，只针对含有get方法的属性，如果参数<code>pojo</code>为空，则返回
	 * <code>null</code>，如果并非pojo对象返回<code>null</code>。
	 * 
	 * @param pojo
	 *            普通java对象
	 * @return 含有get方法的属性转成的Map
	 */
	public static Map<String, Object> transPojo2Map(final Object pojo) {
		if (pojo == null) {
			return null;
		}

		if (pojo instanceof Map) {
			return (Map) pojo;
		}

		Class clazz = pojo.getClass();
		// 无效pojo对象。
		if (clazz.isArray() || clazz.isInterface() || clazz.isAnnotation()
				|| clazz.isAnonymousClass() || clazz.isEnum()
				|| clazz.isPrimitive() || clazz.isAnonymousClass()
				|| clazz.isSynthetic()) {
			return null;
		}

		// 取出含get方法的属性的值，将其存入map中。
		Map<String, Object> map = new HashMap<String, Object>();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			String fieldName = field.getName();
			String methodName = "get" + StringUtils.capitalize(fieldName);
			Object value;
			try {
				value = clazz.getMethod(methodName).invoke(pojo);
				map.put(fieldName, value);
			} catch (Exception e) {
				continue;
			}
		}

		return map;
	}
	/**
	 * 防止工具类被实例化。
	 */
	private LangUtil() {
	}
	
	
}