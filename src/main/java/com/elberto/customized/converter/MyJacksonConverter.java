package com.elberto.customized.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class MyJacksonConverter extends ObjectMapper
{
	
	private static final long serialVersionUID = 1L;

	public MyJacksonConverter()
	{
		configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	}
}
