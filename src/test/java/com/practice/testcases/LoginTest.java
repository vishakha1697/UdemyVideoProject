package com.practice.testcases;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import com.practice.bases.BaseClass;
import com.practice.utilities.TestUtilties;


public class LoginTest extends BaseClass {
	
	@Test(dataProviderClass = TestUtilties.class, dataProvider = "dp")
	public void loginTest(String email,String password) {
		
		type("email_NAME", email);
		type("password_NAME",password);
		click("loginBtn_NAME");
	}

}
