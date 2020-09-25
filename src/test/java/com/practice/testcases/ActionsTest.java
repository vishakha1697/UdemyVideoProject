package com.practice.testcases;

import org.testng.annotations.Test;

import com.practice.bases.BaseClass;

public class ActionsTest extends BaseClass {

	@Test
	public void actionTest() throws InterruptedException {

		click("fullScreenBtn_XPATH");
		click("playBtn_XPATH");

		forceWait();

		click("subtitlesBtn_XPATH");
		click("selectCaption_XPATH");
		click("subtitlesBtn_XPATH");

		forceWait();

		actions("bottomScreenHover_XPATH");
		click("speedBtn_XPATH");
		click("selectSpeed_XPATH");

		forceWait();

		actions("bottomScreenHover_XPATH");

		click("forwardBtn_XPATH");

		forceWait();

		actions("bottomScreenHover_XPATH");

		click("BackwardBtn_XPATH");
	

		forceWait();
		
		

	}

}
