package org.dselent.scheduling.server.miscellaneous;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonResponseCreator
{
	public enum ResponseKey
	{
		SUCCESS,
		ERROR;
	}

	public static String getJSONResponse(ResponseKey responseKey, Object responseObjectList) throws JsonProcessingException
	{
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(responseKey.toString().toLowerCase(), responseObjectList);

		return mapper.writeValueAsString(map);
	}

	public static String getJSONResponse(ResponseKey responseKey, List<Object> responseObjectList) throws JsonProcessingException
	{
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(responseKey.toString().toLowerCase(), responseObjectList);

		return mapper.writeValueAsString(map);
	}

	public static String getJSONResponse(List<Object> success, List<Object> error) throws JsonProcessingException
	{
		return getJSONResponse(success, error, new ObjectMapper());
	}

	public static String getJSONResponse(List<Object> success, List<Object> error,  ObjectMapper mapper) throws JsonProcessingException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(ResponseKey.SUCCESS.toString().toLowerCase(), success);
		map.put(ResponseKey.ERROR.toString().toLowerCase(), error);

		return mapper.writeValueAsString(map);
	}
}
