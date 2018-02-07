package org.dselent.scheduling.server.requests.request;

import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.miscellaneous.RequestParameterConverter;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Defines information for the delete request including the keys for the
 * header, parameters, and body and the request type (RequestMethod).
 * 
 * @author dselent, modified by fcampanelli
 *
 */
public class Delete {
	public static final RequestMethod REQUEST_TYPE = RequestMethod.POST;
	public static final String REQUEST_NAME = "submit";
	private static final List<HeaderKey> HEADER_KEY_LIST;
	private static final List<ParameterKey> PARAMETER_KEY_LIST;
	private static final List<BodyKey> BODY_KEY_LIST;

	public static enum HeaderKey
	{

	}

	public static enum ParameterKey
	{

	}

	public static enum BodyKey
	{
		REQUEST_ID
	}



	static
	{
		HEADER_KEY_LIST = new ArrayList<HeaderKey>();
		BODY_KEY_LIST = new ArrayList<BodyKey>();
		PARAMETER_KEY_LIST = new ArrayList<ParameterKey>();

		for(HeaderKey key : HeaderKey.values())
		{
			HEADER_KEY_LIST.add(key);
		}

		for(ParameterKey key : ParameterKey.values())
		{
			PARAMETER_KEY_LIST.add(key);
		}

		for(BodyKey key : BodyKey.values())
		{
			BODY_KEY_LIST.add(key);
		}

	};

	private Delete()
	{

	};

	public static String getHeaderName(HeaderKey key)
	{
		return RequestParameterConverter.convertKeyName(key);
	}

	public static List<String> getHeaderNameList()
	{
		List<String> headerNameList = new ArrayList<>();

		for(HeaderKey key : HEADER_KEY_LIST)
		{
			headerNameList.add(getHeaderName(key));
		}

		return headerNameList;
	}

	public static String getParameterName(ParameterKey key)
	{
		return RequestParameterConverter.convertKeyName(key);
	}

	public static List<String> getParameterNameList()
	{
		List<String> parameterNameList = new ArrayList<>();

		for(ParameterKey key : PARAMETER_KEY_LIST)
		{
			parameterNameList.add(getParameterName(key));
		}

		return parameterNameList;
	}

	public static String getBodyName(BodyKey key)
	{
		return RequestParameterConverter.convertKeyName(key);
	}

	public static List<String> getBodyNameList()
	{
		List<String> bodyNameList = new ArrayList<>();

		for(BodyKey key : BODY_KEY_LIST)
		{
			bodyNameList.add(getBodyName(key));
		}

		return bodyNameList;
	}
}
