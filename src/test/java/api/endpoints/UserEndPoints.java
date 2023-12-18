package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payloads.UserPayloads;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
//Create end points method for various api methods like Post,Delete,put and delete which
//we will call in our test methods to generate response
public class UserEndPoints {
	
	public static Response createNewUser(UserPayloads payload){
		Response response =given()
				          .contentType(ContentType.JSON)
				          .accept(ContentType.JSON)
				          .body(payload).when().post(URL_Details.createUser_url);
		return response;
	}
	
	public static Response getUser(String userName) {
		Response response =given().pathParam("username", userName)// 
		          .when().get(URL_Details.getUser_url);
		return response;
	}
	
	public static Response updateUser(UserPayloads payload, String userName) {
		Response response =given()
		          .contentType(ContentType.JSON)
		          .accept(ContentType.JSON)
		          .pathParam("username", userName)
		          .body(payload).when().put(URL_Details.updateUser_url);
		return response;
	}
	
	public static Response deleteUser(String userName) {
		Response response =given().pathParam("username", userName)
		          .when().delete(URL_Details.deleteUser_url);
		return response;
	}
}
