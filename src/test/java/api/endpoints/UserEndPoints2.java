package api.endpoints;

import api.payload.User;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

//User Endpoints.java
// Created for perform CRUD---> Create, Read, Update, Delete request to the user API's.  

public class UserEndPoints2 {

	//Payload Request body

	public static ResourceBundle getURL() {
		//Load Properties
		ResourceBundle bundle = ResourceBundle.getBundle("API");

		return bundle;

	}


	Logger logger = LogManager.getLogger(UserEndPoints2.class);

	public static Response createUser(User payload){

		String post_URL = getURL().getString("post_url");

		Response response= given().
				contentType(ContentType.JSON).
				accept(ContentType.JSON).body(payload).when()
				.post(post_URL);

		return response;
	}

	public static Response readUser(String userName){

		String get_url = getURL().getString("get_url");
		Response response= given().pathParam("username", userName).when()
				.get(get_url);

		return response;
	}

	public static Response updateUser(String useName,User payload){

		String update_url = getURL().getString("update_url");

		Response response= given().
				contentType(ContentType.JSON).
				accept(ContentType.JSON).pathParam("username", useName).
				body(payload).when()
				.put(update_url);

		return response;
	}

	public static Response deleteUser(String userName){

		String delete_url = getURL().getString("delete_url");


		Response response= given().pathParam("username", userName).when()
				.delete(delete_url);

		return response;
	}
}
