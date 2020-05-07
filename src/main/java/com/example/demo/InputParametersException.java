package com.example.demo;

public class InputParametersException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2928906109193163138L;
	String err;
	public InputParametersException(String str)
	{
		err=str+" exceeded the length limit";
		
	}
	
	public InputParametersException(friends friend)
	{
		err="None of the columns should be empty";
	}
	
	public InputParametersException(int id)
	{
		err="Duplicate Id/Invalid Id : "+id;
	}
	
	public InputParametersException(Marksheet m) {
		err="Name and marks columns should not be empty";
		}

	public String getMessage()
	{
		return("ERROR : "+err);
	}
}
