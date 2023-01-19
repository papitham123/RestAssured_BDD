package restassured.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class POST_Request_Test {
	
	
	public static HashMap<String, String> hashmap = new HashMap<String, String>();
	Response response;
	String jsonString;
	
	//Scenario 1: Add user using Map
	
	
	
	
	@Test(priority=0)
	public void createUser()
	{
		RestAssured.baseURI= "https://dummyapi.io/data/v1/user/";
		
		
		Map<String, String> map = new HashMap<String,String>();
		map.put("lastName", "Steve5");
		map.put("firstName", "Smith5");
		map.put("email", "steve.s5@gmail.com");
		
	Response response = null;
	
	
	try 
	{
		response = RestAssured.given()
				.header("app-id","63c6f550f56487ebb8d435fc")
				.contentType(ContentType.JSON)
				.body(map)
				.post("/create");
				
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	
	System.out.println("Response is -->" +response.asString());
	System.out.println("Status code is-->"+response.getStatusCode());
	
	System.out.println("Does response contains registerDate?" +response.asString().contains("registerDate"));
	
	assertEquals(200, response.getStatusCode());
	
	}
	

	
	
//Scenario 2: Add user using randomStringutils class	
	
	@BeforeClass
	public void createUser_Utils()
	{
		hashmap.put("lastName", Utils.getlastName());
		hashmap.put("firstName", Utils.getfirstName());
		hashmap.put("email", Utils.getemail());
		
		
		RestAssured.baseURI="https://dummyapi.io/data/v1/user";
		RestAssured.basePath="/create";
		System.out.println(hashmap);
	}
	
	
	@Test(priority=1)
	public void testcreateUser_Utils()
	
	{
		response=
		given()
			.contentType(ContentType.JSON)
			.header("app-id","63c6f550f56487ebb8d435fc")
		
		.with()
			.body(hashmap)
		.when()
			.post()
		.then()
			.statusCode(200)
			.extract().response();
			
			
			
		jsonString=response.asString();
		
		System.out.println("New user created is " +jsonString);
		
	}




}
