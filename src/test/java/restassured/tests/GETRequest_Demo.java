package restassured.tests;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


//To Check

//Scenario 1: get count of ids present
//negative cases 1: when header app-id is not provide


public class GETRequest_Demo {
	
	
	//Global Variables
	Response response;
	String jsonString;
	
	
	//Scenario 1: To print all user list
	@Test
	public void getUserList() 
	
	
	{
		response=
		given()
			.header("app-id","63c6f550f56487ebb8d435fc")
			
		.when()
			.get("https://dummyapi.io/data/v1/user")
		.then()
		//.log().body()   //This also prints all body response 
		.statusCode(200)
		.statusLine("HTTP/1.1 200 OK")
		.extract().response();
		response.asString();
		System.out.println("user list is -->" +jsonString);
		
			
	}
	
	
	//Scenario 2:Paging:Test with multiple "queryParam" page = 1 and limit =10
	@Test
	public void getUsers_Pagination()
	{
		
		
		given()
			.header("app-id","63c6f550f56487ebb8d435fc")
			.queryParam("page", "1").queryParam("limit", "10")
			
		.when()
			.get("https://dummyapi.io/data/v1/user")
		.then()
		.statusCode(200)
		.statusLine("HTTP/1.1 200 OK")
		.and()
		.assertThat().body("data.id", hasSize(10))
		//.assertThat().body("total",equalTo(100))
		.assertThat().body("page", equalTo(1))
		.assertThat().body("limit", equalTo(10))
		.header("Content-Type","application/json; charset=utf-8");
	}
	
	
	
	//Scenario 3:Test to extract one record with "queryParam" create=1
	@Test
	
	public void getUsers_CreatedQueryParams()
	{
		
		RestAssured.baseURI= "https://dummyapi.io/data/v1/user";

		given()
			
			.header("app-id","63c6f550f56487ebb8d435fc")
			.queryParam("created", "1")
		.when()
			.get()
		.then()
		.statusCode(200)
		.statusLine("HTTP/1.1 200 OK")
		.and()
		//.assertThat().body("data.id", hasSize(1))
		//.assertThat().body("total",equalTo(1))
		.assertThat().body("page", equalTo(0))
		.assertThat().body("limit", equalTo(20))
		.header("Content-Type","application/json; charset=utf-8");
		
	}
	
	
	
	//Scenario 4: To Get user by ID: //Test using Path Parameters which gets appended to URI
	
	@Test
	public void getUserById()
	{
	
		String id = "63c923870cf41e1122890cbf";
		
		response=
		given()
			.header("app-id","63c6f550f56487ebb8d435fc")
			.log().all()
		
		.with()
			.pathParams("post",id)
		.when()
			.get("https://dummyapi.io/data/v1/user/{post}")// appends the id in {post}
		.then()
			.statusCode(200)
		.extract().response();
		jsonString = response.asString();
		System.out.println("GET User by id response is-->" +jsonString);

	}
	
	

}
