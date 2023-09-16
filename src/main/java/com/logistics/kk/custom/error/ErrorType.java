package com.logistics.kk.custom.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorType {

	public ErrorType(String string, String string2, String message2) {
		// TODO Auto-generated constructor stub
	}
	private String dateTime;
	private String module;
	private String message;
	
}
