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

	private static String USERS_WITH_ROLE_PATH = BASE_QUERY_PATH + "customUsersWithRole" + SQL_EXTENSION;
	private static String REQUEST_TABLES_PATH = BASE_QUERY_PATH + "requestTables" + SQL_EXTENSION;
	private static String REQUESTS_ONE_USER_PATH = BASE_QUERY_PATH + "requestsOneUser" +SQL_EXTENSION;
	private static String SECTIONS_INFO_PATH = BASE_QUERY_PATH + "sectionsInfo" +SQL_EXTENSION;
	private static String SECTIONS_ONE_FACULTY_PATH = BASE_QUERY_PATH + "sectionsOneFaculty" +SQL_EXTENSION;
	private static String COURSE_INFO_PATH = BASE_QUERY_PATH + "courseInfo" +SQL_EXTENSION;
	private static String COURSE_INFO_ONE_PATH = BASE_QUERY_PATH + "courseInfoOne" +SQL_EXTENSION;
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	

	public static String USERS_WITH_ROLE_QUERY = readFile(USERS_WITH_ROLE_PATH);
	public static String REQUEST_TABLES_QUERY = readFile(REQUEST_TABLES_PATH);
	public static String REQUESTS_ONE_USER_QUERY = readFile(REQUESTS_ONE_USER_PATH);
	public static String SECTIONS_INFO_QUERY = readFile(SECTIONS_INFO_PATH);
	public static String SECTIONS_ONE_FACULTY_QUERY = readFile(SECTIONS_ONE_FACULTY_PATH);
	public static String COURSE_INFO_QUERY = readFile(COURSE_INFO_PATH);
	public static String COURSE_INFO_ONE_QUERY = readFile(COURSE_INFO_ONE_PATH);
	
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
