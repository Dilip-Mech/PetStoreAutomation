package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.ExtentReportManager;
import io.restassured.response.Response;


@Listeners(ExtentReportManager.class)
public class userTests {

	Faker faker;

	User userPayload;
	
	public Logger logger;

	@BeforeClass
	public void setupData() {

		faker = new Faker();
		userPayload = new User();

		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		//Logs
		
		logger = LogManager.getLogger(this.getClass());
		

	}

	@Test(priority = 1)
	public void testPostUser() {
	
		logger.info("**************** Creating User ****************");

		Response response = UserEndPoints.createUser(userPayload);

		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("**************** User is Created****************");
	}

	@Test(priority = 2)
	public void testGetUserByName() {

		logger.info("**************** Reading User Info ****************");
		Response response = UserEndPoints.readUser(this.userPayload.getUsername());

		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("**************** User Info is displayed ****************");
	}


	@Test(priority = 3)
	public void testUpdateUserByName() {

		logger.info("**************** Updating User Info ****************");
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());

		Response response = UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();
		
		logger.info("**************** User Updated ****************");

		Assert.assertEquals(response.getStatusCode(), 200);

		//Checking data after update

		Response responseAfterUpdate = UserEndPoints.readUser(this.userPayload.getUsername());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);

	}



	@Test(priority = 4)
	public void testDeleteUserByName() {
		
		logger.info("**************** Deleting User ****************");

		Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());

		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("**************** User Deleted ****************");

	}
}
