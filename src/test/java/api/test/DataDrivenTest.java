package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payloads.UserPayloads;
import api.utilities.DataProviderExcel;
import io.restassured.response.Response;

public class DataDrivenTest {
	
	@Test(priority=1, dataProvider="Data",dataProviderClass=DataProviderExcel.class)
	public void testPostNewUser(String userID,String username, String fname,String lname, String emailid,String password,String phoneno) {
		UserPayloads payloads= new UserPayloads();
		
		payloads.setId(Integer.parseInt(userID));
		payloads.setUsername(username);
		payloads.setFirstName(fname);
		payloads.setLastName(lname);
		payloads.setEmail(emailid);
		payloads.setPassword(password);
		payloads.setPhone(phoneno);
		
		Response response = UserEndPoints.createNewUser(payloads); // Call endpoints method to generate response
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200); // verify status code
	}
	
	@Test(priority=2,dataProvider="usernames",dataProviderClass=DataProviderExcel.class)
	public void testDeleteUser(String username){
		Response response=  UserEndPoints.deleteUser(username);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}

}
