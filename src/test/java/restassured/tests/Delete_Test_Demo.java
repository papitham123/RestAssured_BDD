package restassured.tests;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Delete_Test_Demo {
	
	Response response = null;
	String jsonString;
	
	
	
//Scenario 1: To test Delete user	
	
	@Test
	public void TestDeleteUser()
	{
		RestAssured.baseURI="https://dummyapi.io/data/v1/user";
		
		
		String id = "63c924bba226451c833ae6d5";
		try
		{
			response=RestAssured.given()
					.header("app-id","63c6f550f56487ebb8d435fc")
					.contentType(ContentType.JSON)
					.delete(id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		System.out.println("Delete Response id is=->" +response.asString());
		System.out.println("Status Code is-->"+response.getStatusCode());
		assertEquals(200,response.getStatusCode());

	}
	
//Scenario 2: To Test Delete User which does not exist	
	
	@Test
	public void UserIdDoesnotExist()
	{
		
		RestAssured.baseURI="https://dummyapi.io/data/v1/user";
		
		
		String id = "63c7d2e197083eeb7b1ad0c8";
		try
		{
			response=RestAssured.given()
					.header("app-id","63c6f550f56487ebb8d435fc")
					.contentType(ContentType.JSON)
					.delete(id);
					
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		System.out.println("Delete Response id is=->" +response.asString());
		System.out.println("Status Code is-->"+response.getStatusCode());
		
		jsonString = response.asString();
		
		assertEquals(404,response.getStatusCode());
			
	}
	
	
	@Test
	 public void userID_doesnotexist_BDDStyle()
	 {
		 
		 String id = "63c7d2e197083eeb7b1ad0c8";
			
			response=
			given()
				.header("app-id","63c6f550f56487ebb8d435fc")
				//.log().all()
			
			.with()
				.pathParams("post",id)
			.when()
				.delete("https://dummyapi.io/data/v1/user/{post}")// appends the id in {post}
			.then()
				.statusCode(404)
				.body("error", equalTo("RESOURCE_NOT_FOUND"))
			.extract().response();
			jsonString = response.asString();
			System.out.println("Delete response is-->" +jsonString);
		 
	 }
}
