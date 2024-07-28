package api.endpoints;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*

Swagger URI --> https://petstore.swagger.io

 Create user (Post) : https://petstore.swagger.io/v2/user
 Get User (Get) : https://petstore.swagger.io/v2/user/{username}
 Update user (Put) : https://petstore.swagger.io/v2/user/{username}
 Delete user (Delete) : https://petstore.swagger.io/v2/user/{username}

 */

public class Routes {

	Logger logger = LogManager.getLogger(Routes.class);
	

	public static String base_url = "https://petstore.swagger.io/v2";

	//User Model
	
	public static String post_url = base_url+"/user";
	
	public static String get_url = base_url+"/user/{username}";
	
	public static String update_url = base_url+"/user/{username}";
	
	public static String delete_url = base_url+"/user/{username}";
	
	//Store modle
	
	//Here you will create Store model URL's
	
	//Pet model
	
	//Here you will create Pet model URL's
}
