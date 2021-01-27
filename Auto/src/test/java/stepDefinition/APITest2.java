package stepDefinition;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class APITest2 {

		@Test
		void test_01_get() throws IOException {
			
			Response response = RestAssured.get("https://reqres.in/api/users?page=2");
			//System.out.println(response.getBody().asString());
			System.out.println(response.jsonPath().prettify());
			int statusCode = response.statusCode();
			Assert.assertEquals(statusCode,200);
			System.out.println("The Status code is : " + response.statusCode());
			
			FileWriter filewriter = new FileWriter("target\\users" + System.currentTimeMillis() + ".json");
			filewriter.write(response.jsonPath().prettify());
			filewriter.close();
			System.out.println("JSON Object successfully copied");
		}
		
		
		@Test
		void test_02_get() throws IOException {
			
			given()
			.get("https://reqres.in/api/users?page=2")
			.then()
			.statusCode(200)
			//.body("data.id[0]", equalTo(7))
			.log().all()
			.body("data.first_name", hasItems("Lindsay","Michael"));
			
			}

		@Test
		public void test_03_post()  {
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", "13");
			map.put("email", "Ragahv.Gar@reqres.in");
			map.put("first_name","Ragahv");
			map.put("last_name","Gar");
			map.put("avatar","https://reqres.in/img/faces/7-image.jpg");
			JSONObject request= new JSONObject(map);
			//System.out.println(request);
			System.out.println(request.toJSONString());
			
		}
		@Test
		public void test_04_post()  {
			
			baseURI= "https://reqres.in/api/users?page=2";
			JSONObject request= new JSONObject();
			request.put("id", "13");
			request.put("email", "Ragahv.Gar@reqres.in");
			request.put("first_name","Ragahv");
			request.put("last_name","Gar");
			request.put("avatar","https://reqres.in/img/faces/7-image.jpg");
			//System.out.println(request);
			System.out.println(request.toJSONString());
			
			given().contentType(ContentType.JSON).accept(ContentType.JSON).header("Content-Type","application/Json").
				body(request.toJSONString()).
			when().
				post("https://reqres.in/api/users?page=2").
			then().
				statusCode(201).log().all();
			}
		@Test
		public void test_05_patch()  {
			
			baseURI= "https://reqres.in/api/users?page=2";
			JSONObject request= new JSONObject();
			request.put("id", "13");
			request.put("email", "Ragahv.Gar@reqres.in");
			request.put("first_name","Ragahv");
			request.put("last_name","Gary");
			request.put("avatar","https://reqres.in/img/faces/7-image.jpg");
			//System.out.println(request);
			System.out.println(request.toJSONString());
			
			given().contentType(ContentType.JSON).accept(ContentType.JSON).header("Content-Type","application/Json").
				body(request.toJSONString()).
			when().
				patch("https://reqres.in/api/users?page=2").
			then().
				statusCode(200).log().all();
			}
}
