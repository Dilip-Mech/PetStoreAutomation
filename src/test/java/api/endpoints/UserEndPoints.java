package api.endpoints;

import api.payload.User;

import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

//User Endpoints.java
// Created for perform CRUD---> Create, Read, Update, Delete request to the user API's.  

public class UserEndPoints {

	//Payload Request body
	
	Logger logger = LogManager.getLogger(UserEndPoints.class);

	public static Response createUser(User payload){
		
		Response response= given().
				contentType(ContentType.JSON).
				accept(ContentType.JSON).body(payload).when()
				.post(Routes.post_url);

		return response;
	}

	public static Response readUser(String userName){

		Response response= given().pathParam("username", userName).when()
				.get(Routes.get_url);

		return response;
	}

	public static Response updateUser(String useName,User payload){
		Response response= given().
				contentType(ContentType.JSON).
				accept(ContentType.JSON).pathParam("username", useName).
				body(payload).when()
				.put(Routes.update_url);

		return response;
	}

	public static Response deleteUser(String userName){

		Response response= given().pathParam("username", userName).when()
				.delete(Routes.delete_url);

		return response;
	}
}
