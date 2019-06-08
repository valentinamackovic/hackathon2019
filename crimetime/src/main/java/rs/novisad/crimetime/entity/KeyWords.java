package rs.novisad.crimetime.entity;

import java.util.Arrays;
import java.util.List;

public class KeyWords {

	public static List<String> words=Arrays.asList("ubist", "pljacka", "opljacka", "kidnap", "upuca", "pretuc", "utuc", "pretuk",
			"ranjen", "povredj", "napad", "pucnjav", "nesta", "obij", "ukra", "ukras", "silova", "dilova");

	public static String getRegexString() {
		StringBuilder builder = new StringBuilder();
		for (String string : words
			 ) {
			builder.append(string);
			builder.append("|");
		}
		String temp = builder.toString();
		return temp.substring(0, temp.length() - 1);
	}
	
}
