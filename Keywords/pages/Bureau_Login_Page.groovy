package pages

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.WebElement

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
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import utilities.SafeActions

public class Bureau_Login_Page {
	SafeActions safe=new SafeActions()


	@Keyword
	def windowSwitching(){
		WebUI.click(	findTestObject('Object Repository/MAKE_ PAYMENT/Home_ Page/BUREAU_LOGIN'))
		//			int index=WebUI.getWindowIndex()
		//	     String title1=WebUI.getWindowTitle()
		//	        println(index)
		WebUI.switchToWindowIndex(1)
	}
	@Keyword
	def clickingElement(TestObject object){
		WebElement element =WebUiCommonHelper.findWebElement(object,30)
		WebUI.executeJavaScript("arguments[0].click()",Arrays.asList(element))
	}

	@Keyword
	def loginToReportsSite(String username,String password,String accessCode){

		safe.safeType(	findTestObject('Object Repository/MAKE_ PAYMENT/Home_ Page/USER_NAME'), username, 'username', (([
			GlobalVariable.pageLoadTime]) as int[]))

		safe.safeType(	findTestObject('Object Repository/MAKE_ PAYMENT/Home_ Page/BUREAU_PASSWORD')
				, password, 'password', (([
					GlobalVariable.pageLoadTime]) as int[]))
		safe.safeType(		findTestObject('Object Repository/MAKE_ PAYMENT/Home_ Page/BUREAU_ACCESS_CODE'), accessCode, 'AccessCode', (([
			GlobalVariable.pageLoadTime]) as int[]))

		safe.safeClick(	findTestObject('Object Repository/MAKE_ PAYMENT/Home_ Page/LOGIN_BUTTON')
				, 'LOGIN', (([
					GlobalVariable.pageLoadTime]) as int[]))
	}
	@Keyword
	def verifyPaymentInRealTimeMonitor(TestObject object,String referenceNum,String lastName,String firstName,String paymentID){
		String paymentId=WebUI.getText(	object)
		try{


			if(WebUI.verifyElementPresent(findTestObject('Object Repository/BUREAU_LOGIN/PAYMENT_ID'),30)) {
				//WebUI.verifyMatch(paymentId,paymentID,true)
				WebUI.click(findTestObject('Object Repository/BUREAU_LOGIN/PAYMENT_ID'))

				//WebUI.scrollToElement(findTestObject('Object Repository/BUREAU_LOGIN/FIRST_NAME'), 0)
				String reference=WebUI.getText(	findTestObject('Object Repository/BUREAU_LOGIN/REFERENCE_NUM'))
				WebUI.verifyMatch(referenceNum, reference, true, FailureHandling.STOP_ON_FAILURE)

				String sFirstName =WebUI.getText(findTestObject('Object Repository/BUREAU_LOGIN/FIRST_NAME'))
				WebUI.verifyMatch(sFirstName, firstName, true, FailureHandling.STOP_ON_FAILURE)

				String sLastName=WebUI.getText(	findTestObject('Object Repository/BUREAU_LOGIN/LAST_NAME'))
				WebUI.verifyMatch(sLastName, lastName, true, FailureHandling.STOP_ON_FAILURE)

				WebUI.delay(2)
			}
			else{
				KeywordUtil.markFailed("Payment id not matched")
			}
		}
		catch(StepFailedException e){
			KeywordUtil.markError("Unable to verify match " +paymentId+" with "+paymentID)
		}
	}
}
