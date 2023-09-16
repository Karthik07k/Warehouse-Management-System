package com.logistics.kk.util;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

//JDK 8 (static method in interface)
public interface MyAppUtil {

	public static Map<Integer,String> convertListToMap(
			List<Object[]> list) 
	{
		return list.stream()
				.collect(
						Collectors.toMap(
								ob->(Integer)ob[0], ob->(String)ob[1])
						);

	}

	public static String genPwd() {
		return UUID.randomUUID()
				.toString()
				.replaceAll("-", "")
				.substring(0, 8)
				;
	}

	public static String genOtp() {
		return String.format("%06d", 
				new Random().nextInt(999999));
	}
}
