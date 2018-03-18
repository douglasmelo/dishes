package com.vanhack.dishes.utils;

import java.util.Collection;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ToStringBuilder {

	public static String toString(Object object, Collection<String> excludeFieldNames) {
		return toString(object, excludeFieldNames.toArray(new String[excludeFieldNames.size()]));
	}

	public static String toString(Object object, String... excludeFieldNames) {
		return new ReflectionToStringBuilder(object, ToStringStyle.JSON_STYLE)
				.setExcludeFieldNames(excludeFieldNames)
				.toString();
	}
	
	public static String toString(Object object) {
		return org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString(object, ToStringStyle.JSON_STYLE);
	}
}
