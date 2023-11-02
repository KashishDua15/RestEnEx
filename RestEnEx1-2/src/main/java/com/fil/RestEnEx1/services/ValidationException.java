package com.fil.RestEnEx1.services;

import java.util.Arrays;

public class ValidationException extends Exception {
	
	String errormsg;

	public ValidationException(String errormsg) {
		super();
		this.errormsg = errormsg;
	}

	@Override
	public String toString() {
		return "ValidationException [errormsg=" + errormsg + "]";
	}

	

	
	
}
