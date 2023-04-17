package com.cap.fatrip.util;

import lombok.extern.log4j.Log4j2;

import java.lang.reflect.Field;

@Log4j2
public class ServiceUtil {
	// field가 객체 타입이면 deep 복사가 되지 않는다. (참조 복사)
	public static <T, K> void copyObject(T origin, K copy) {

		Class<?> o_class = origin.getClass();
		Class<?> c_class = copy.getClass();

		try {
			for (Field o_field : o_class.getDeclaredFields()) {
				try {
					o_field.setAccessible(true);
					String o_fieldName = o_field.getName();
					Field c_field = c_class.getDeclaredField(o_fieldName);
					c_field.setAccessible(true);

					String tmpStr = String.valueOf(o_field.get(origin));
					if (c_field.getType() == Integer.TYPE) {
						c_field.set(copy, Integer.valueOf(tmpStr));
					} else if (c_field.getType() == Float.TYPE) {
						c_field.set(copy, Float.valueOf(tmpStr));
					} else if (c_field.getType() == Double.TYPE) {
						c_field.set(copy, Double.valueOf(tmpStr));
					} else if (c_field.getType() == String.class) {
						c_field.set(copy, tmpStr);
					} else {
						c_field.set(copy, o_field.get(origin));
					}
				} catch (NoSuchFieldException ignored) {
				}
			}
		} catch (Exception e) {
			log.error("copy error");
			throw new RuntimeException(e);
		}
	}

}
