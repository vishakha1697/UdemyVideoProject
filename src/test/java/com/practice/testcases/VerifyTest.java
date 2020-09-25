package com.practice.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.practice.bases.BaseClass;

public class VerifyTest extends BaseClass {

	
	@Test
	public void verifyTest() {
		
		actions("bottomScreenHover_XPATH");
		
	
		Assert.fail("Forcefully Failing to see whether Screenshot is generating or not");
		
	}
}
