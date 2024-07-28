package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.Dataprovider;
import io.restassured.response.Response;

public class DataDrivenTest {
	
	@Test(priority = 1,dataProvider = "Data",dataProviderClass = Dataprovider.class)
	public void testPostuser(String userID, String userName,String firstName, String lastName, String email, String password, String phone) {
		
		User userPayload = new User();
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstname(firstName);
		userPayload.setLastname(lastName);
		userPayload.setEmail(email);
		userPayload.setPassword(password);
		userPayload.setPhone(phone);
		
		Response response = UserEndPoints.createUser(userPayload);
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 2,dataProvider = "UserNames",dataProviderClass =Dataprovider.class )
	public void testDeleteUserByName(String userName) {
		
		Response response = UserEndPoints.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
} 
