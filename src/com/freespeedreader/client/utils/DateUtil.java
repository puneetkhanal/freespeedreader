package com.freespeedreader.client.utils;

public class DateUtil {

	public static String getDuration(long time) {
		StringBuffer str = new StringBuffer();
		long hours = time / (60000 * 60);
		long minutes = (time - (hours * 60 * 60000)) / 60000;
		long seconds = (time - (hours * 60 * 60000) - (minutes * 60000)) / 1000;
		if (hours < 10)
			str.append("0" + hours + ":");
		else
			str.append(hours + ":");

		if (minutes < 10)
			str.append("0" + minutes + ":");
		else
			str.append(minutes + ":");

		if (seconds < 10)
			str.append("0" + seconds);
		else
			str.append(seconds);

		return str.toString();

	}

}
