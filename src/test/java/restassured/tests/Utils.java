package restassured.tests;

import org.apache.commons.lang3.RandomStringUtils;

public class Utils {
	
	
	public static String getlastName()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(1);
		return("John"+generatedString);
	}

	
	public static String getfirstName()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(1);
		return("Davis"+generatedString);
	}
	
	public static String getemail()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(3);
		return(generatedString+"@gmail.com");
	}
	
	
	public static String firstName()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(1);
		return("Ben"+generatedString);
	}
	
	
	public static String lastName()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(1);
		return("Stokes"+generatedString);
	}
	
	public static String email()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(4);
		return(generatedString+"@gmail.com");
	}
	
	
	
	
}
