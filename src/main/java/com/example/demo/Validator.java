package com.example.demo;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Validator {
	FriendsRepository friend;
	private final Logger logger;

	public Validator()
	{
		this.friend=FriendsRepository.getInstance();
		this.logger=LoggerFactory.getLogger(this.getClass());
	}
	
	public void lengthValidator(String str)  
	{	try
		{
			if(str.length()>25)
			{
				throw new InputParametersException(str);
			}
		}
		catch(InputParametersException e)
		{	logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage()+" exceeded the pre-defined length");

		}
	}
	
	public void postValidator(friends friend)
	{	try
		{
		if(friend.getId()==0 || friend.getClassId()==null|| friend.getName()==null || friend.getCity()==null||
				friend.getBirthday()==null)
			{
				throw new InputParametersException(friend);
			}
		}
		catch(InputParametersException e)
		{	logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		
	}
	
	public void idValidator(int id) throws SQLException {
		int maxId=friend.getMaxId();
		try
		{
			if(id<=maxId)
			{
				throw new InputParametersException(id);
			}
		}
		catch(InputParametersException e)
		{	logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());

		}
	}

	public void updateAndgetIdValidator(int id) throws SQLException {
		int maxId=friend.getMaxId();
		try
		{
			if(id>maxId)
			{
				throw new InputParametersException(id);
			}
		}
		catch(InputParametersException e)
		{	logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());

		}
	}

	public void postValidator(Marksheet m) {
		try
		{
		if( m.getName()==null || m.getMarks()==0)
			{
				throw new InputParametersException(m);
			}
		}
		catch(InputParametersException e)
		{	logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}
	
}
