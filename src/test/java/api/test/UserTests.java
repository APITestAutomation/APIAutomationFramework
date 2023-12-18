package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payloads.UserPayloads;
import io.restassured.response.Response;

public class UserTests {
	Faker faker;
	UserPayloads payloads;
	//Create test data for all fields present in payloads i.e request 
	@BeforeClass
	public void setUpData() {
		faker=new Faker();
		payloads=new UserPayloads();
		
		payloads.setId(faker.idNumber().hashCode());
		payloads.setUsername(faker.name().username());
		payloads.setFirstName(faker.name().username());
		payloads.setLastName(faker.name().lastName());
		payloads.setEmail(faker.internet().safeEmailAddress());
		payloads.setPassword(faker.internet().password(8, 10));
		payloads.setPhone(faker.phoneNumber().cellPhone());
	}
	//Run test for create new user 	
	@Test(priority=1)
	public void createUserTest(){
		Response response = UserEndPoints.createNewUser(payloads); // Call endpoints method to generate response
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200); // verify status code
		
	}
	
	//Run test to get new user
	@Test(priority=2)
	public void getUserTest() {
		
		Response response=  UserEndPoints.getUser(this.payloads.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200); // verify status code
		
	}
	
	@Test(priority=3)
	public void modifyUserTest() {
		//Modify username data
		payloads.setUsername(faker.name().username());
		payloads.setFirstName(faker.name().username());
		payloads.setLastName(faker.name().lastName());
		
		//Call put method to update username,firstname and lastname
		Response response=  UserEndPoints.updateUser(payloads,this.payloads.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200); // verify status code
		
		// Check response after update 
		Response responseAfterUpdate=  UserEndPoints.getUser(this.payloads.getUsername());
		responseAfterUpdate.then().log().all();
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200); // verify status code
	}
	
	@Test(priority=4)
    public void deleteUserTest() {
		Response response=  UserEndPoints.deleteUser(this.payloads.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	

}
