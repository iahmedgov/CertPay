package pages

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

public class CommonActions {
	@Keyword
	def safeMouseOver(TestObject object){

		println (GlobalVariable.mediumWait).getClass()

		WebUI.focus(object)
		//WebElement element=WebUiCommonHelper.findWebElement(object, 30)
		try{
			if(WebUI.verifyElementPresent(object)){
				WebUI.mouseOver(object)
				KeywordUtil.markPassed(" MouseOver on elemnet")
				Thread.sleep(2000);
			}
			else {
				KeywordUtil.markFailed("Element not present")
			}
		}
		catch(StepFailedException e){
			KeywordUtil.markFailed("Unable to mouse hover on element")
		}
	}

}
