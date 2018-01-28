package org.dselent.scheduling.server.miscellaneous;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Register all custom SQL query files here
 * 
 * @author dselent
 * Modified by Hannah Jauris
 *
 */
public class QueryPathConstants
{
	private static String BASE_QUERY_PATH = "sql" + File.separator + "Active" + File.separator;
	private static String SQL_EXTENSION = ".sql";

	private static String USERS_WITH_ROLE_PATH = BASE_QUERY_PATH + "CustomUsersWithRole" + SQL_EXTENSION;
	private static String REQUEST_TABLES_PATH = BASE_QUERY_PATH + "RequestTables" + SQL_EXTENSION;
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	

	public static String USERS_WITH_ROLE_QUERY = readFile(USERS_WITH_ROLE_PATH);
	public static String REQUEST_TABLES_QUERY = readFile(REQUEST_TABLES_PATH);
	
	private QueryPathConstants()
	{
		
	}
	
	public static String readFile(String path)
	{
		byte[] encodedbytes = null;
		
		try
		{
			Resource resource = new ClassPathResource(path);
			encodedbytes = Files.readAllBytes(Paths.get(resource.getURI()));
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
		
		return new String(encodedbytes);
	}

}
