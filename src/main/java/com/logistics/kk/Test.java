package com.logistics.kk;

import java.util.Random;

public class Test {

	public static void main(String[] args) {
		Random rob = new Random();
		//0---999999
		int num = rob.nextInt(999999);
		System.out.println(num);
		String otp = String.format("%06d",num);
		System.out.println(otp);
	}
}
