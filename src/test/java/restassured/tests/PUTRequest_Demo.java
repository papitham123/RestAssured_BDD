package restassured.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;



public class PUTRequest_Demo {
	
	public static HashMap<String, String> hashmap = new HashMap<String, String>();
	Response response;
	String jsonString;


//Scenario 1: Update user using Map	
	@Test
	public void testUpdateUser()
	{
		RestAssured.baseURI="https://dummyapi.io/data/v1/user";
		

		Map<String, String> map = new HashMap<String,String>();
		map.put("lastName", "Steve6");
		map.put("firstName", "Smith6");
		map.put("email", "steve.s6@gmail.com");
		
		
		Response response = null;
		String id = "63c922c80cf41ee516890bce";
		try
		{
			response=RestAssured.given()
					.header("app-id","63c6f550f56487ebb8d435fc")
					.contentType(ContentType.JSON)
					.body(map)
					.put(id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		System.out.println("Updated Response is-->" +response.asString());
		System.out.println("Status Code is-->"+response.getStatusCode());
		
		assertEquals(200,response.getStatusCode());
	}
	
	
	//Scenario 2: Update user using randomStringutils class	
	
		@BeforeClass
		public void updateUser_Utils()
		{
			hashmap.put("lastName", Utils.lastName());
			hashmap.put("firstName", Utils.firstName());
			hashmap.put("email", Utils.email());
			
			
			System.out.println(hashmap);
		}
		
		
		@Test
		public void testupdateUser_Utils()
		
		{
			String id = "63c922c80cf41ee516890bce";
			response=
			given()
				.contentType(ContentType.JSON)
				.header("app-id","63c6f550f56487ebb8d435fc")
			
			.with()
				.body(hashmap)
				
				.pathParams("post",id)
			.when()
				.put("https://dummyapi.io/data/v1/user/{post}")
			.then()
				.statusCode(200)
				.extract().response();
				
			jsonString=response.asString();
			
			System.out.println("updated user is " +jsonString);
			
		}
	
}
