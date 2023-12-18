package api.endpoints;

//Swagger uri: https://petstore.swagger.io/

// Create user(POST): https://petstore.swagger.io/v2/user
// Get user(GET): https://petstore.swagger.io/v2/user/{username}
//Update user(PUT): https://petstore.swagger.io/v2/user/{username}
//Delete user(DELETE): https://petstore.swagger.io/v2/user/{username}

// store all urls for your module so that it can be used in your endpoints methods

public class URL_Details {
	
	public static String base_url="https://petstore.swagger.io/v2";
    
	//User module endpoints
	
	public static String createUser_url=base_url+"/user";
	public static String getUser_url=base_url+"/user/{username}";
	public static String updateUser_url=base_url+"/user/{username}";
	public static String deleteUser_url=base_url+"/user/{username}";
	
}
