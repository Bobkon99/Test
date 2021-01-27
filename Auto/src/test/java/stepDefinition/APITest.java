package stepDefinition;

import org.apache.log4j.Logger;

import org.apache.log4j.xml.DOMConfigurator;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

import java.io.IOException;

import io.restassured.http.Headers;
import io.restassured.http.Header;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.XLUtils;

public class APITest {
	private static Logger Log = Logger.getLogger(APITest.class.getName());

	//@Test
	public static void test1_get () {

		DOMConfigurator.configure("log4j.xml");
		//specify the URI
		RestAssured.baseURI= "https://reqres.in/api";
		Log.info("Need info");
		//Request Object
		RequestSpecification  httpRequest= RestAssured.given();

		//Response Object
		Response response = httpRequest.request(Method.GET,"users?page=2");

		String responseBody = response.getBody().asString();
		Assert.assertEquals(responseBody.contains("Michael"),true); 
		//System.out.println("Response body is:" +responseBody);
		System.out.println("Response body is:" + response.jsonPath().prettify());

		JsonPath jsonpath=response.jsonPath();
		System.out.println("Firs Names are:" + jsonpath.get("data.first_name"));

		int statusCode =response.getStatusCode();
		System.out.println("Status code is:" +statusCode);
		Assert.assertEquals(statusCode,200); 

		Headers allHeaders = response.getHeaders();
		for (Header header : allHeaders)
		{
			System.out.println(header.getName()+ " : " + header.getValue());
		}

	}

	//@Test
	public static void test2_put () {

		//specify the URI
		RestAssured.baseURI= "https://reqres.in/api";
		Log.info("Need info");
		//Request Object
		RequestSpecification  httpRequest= RestAssured.given();

		//Request payload send along with POST request
		JSONObject requestParams= new JSONObject();

		requestParams.put("id", "14");
		requestParams.put("email", "Ragahv.Gar@reqres.in");
		requestParams.put("first_name","Don");
		requestParams.put("last_name","John");
		requestParams.put("avatar","https://reqres.in/img/faces/7-image.jpg");

		httpRequest.header("Content-Type","application/json");
		httpRequest.body(requestParams.toJSONString()); // attach above param data to request

		//Response Object
		Response response = httpRequest.request(Method.POST,"users?page=2");


		String responseBody = response.getBody().asString();

		Assert.assertEquals(responseBody.contains("Howell"),true); 
		//System.out.println("Response body is:" +responseBody);
		System.out.println("Response body is:" + response.jsonPath().prettify());

		int statusCode =response.getStatusCode();
		System.out.println("Status code is:" +statusCode);
		Assert.assertEquals(statusCode,201); 

		//String successCode	= response.jsonPath().get("SuccessCode");
		//Assert.assertEquals(successCode,201); 
	}

	@Test(dataProvider="userdata")
	public static void test2_put_dd (String id, String email, String fname, String lname, String avatar) {

		//specify the URI
		RestAssured.baseURI= "https://reqres.in/api";
		Log.info("Need info");
		//Request Object
		RequestSpecification  httpRequest= RestAssured.given();

		//Request payload send along with POST request
		JSONObject requestParams= new JSONObject();

		requestParams.put("id", id);
		requestParams.put("email", email);
		requestParams.put("first_name",fname);
		requestParams.put("last_name",lname);
		requestParams.put("avatar",avatar);

		httpRequest.header("Content-Type","application/json");
		httpRequest.body(requestParams.toJSONString()); // attach above param data to request

		//Response Object
		Response response = httpRequest.request(Method.POST,"users?page=2");


		String responseBody = response.getBody().asString();

		Assert.assertEquals(responseBody.contains(lname),true); 
		//System.out.println("Response body is:" +responseBody);
		System.out.println("Response body is:" + response.jsonPath().prettify());

		int statusCode =response.getStatusCode();
		System.out.println("Status code is:" +statusCode);
		Assert.assertEquals(statusCode,201); 

		//String successCode	= response.jsonPath().get("SuccessCode");
		//Assert.assertEquals(successCode,201); 
	}

	@DataProvider(name="userdata")
	String [][] getUserData() throws IOException {

		String inputpath = System.getProperty("user.dir") + "./Data/TestData2.xlsx";
		int rownum = XLUtils.getRowCnt(inputpath,"Sheet2");
		int colcnt = XLUtils.getCellCnt(inputpath, "Sheet2",1);

		String usrdata[][] = new String[rownum][colcnt];
		for (int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colcnt;j++)
			{
				usrdata[i-1][j]=XLUtils.getCellData(inputpath, "Sheet2", i, j);
			}
		}

		//			String usrdata[][]= { {"14","Ragahv.Gar@reqres.in","Donss","Josashn","https://reqres.in/img/faces/7-image.jpg"},
		//						 {"15","Ragsahv.Gar@reqres.in","Dosan","Joasahn","https://reqres.in/img/faces/7-image.jpg"},	
		//						 {"16","Ragahsv.Gar@reqres.in","Dosasn","Joasashn","https://reqres.in/img/faces/7-image.jpg"}};
		return(usrdata);
	}

} 
//jenkines integrated
