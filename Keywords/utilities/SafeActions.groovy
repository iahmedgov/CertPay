package utilities

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.StaleElementReferenceException
import org.openqa.selenium.WebDriver
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.exception.StepErrorException
import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.exception.KatalonRuntimeException
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

public class SafeActions {
	Syn syn=new Syn()
	@Keyword
	def openBrowser(String url, int... optionWaitTime){
		boolean bFlag=true;
		int waitTime=0;
		try {
			KeywordUtil.logInfo("Opening Browser")
			waitTime=syn.getWaitTime(optionWaitTime)
			if(bFlag) {
				WebUI.openBrowser(url);
				WebUI.maximizeWindow()
				KeywordUtil.markPassed("Browser launched successfully")
			}
			else {
				KeywordUtil.markFailedr("Unable to open browser with url "+url+"in time -" +waitTime+ "Seconds")
			}
		}
		catch (StepFailedException e){
			KeywordUtil.Error("Unable to open browser")
		}
	}
	@Keyword
	def highLightElement(TestObject testObject,int timeout){
		if(GlobalVariable.HighLightElements.equalsIgnoreCase("true")){
			//println "in High light method"
			String attributeValue="border:2px solid blue";
			WebDriver driver=DriverFactory.getWebDriver()
			JavascriptExecutor je=(JavascriptExecutor)driver
			WebElement element=WebUiCommonHelper.findWebElement(testObject,timeout)
			String getAttribute=element.getAttribute("style")
			je.executeScript("arguments[0].setAttribute('style',arguments[1]);",element,attributeValue)
			try{
				Thread.sleep(500)
			}
			catch(InterruptedException e) {
				KeywordUtil.markError(syn.getTestCasename()+"sleep interrupted - ")
			}
			je.executeScript("arguments[0].setAttribute('style',arguments[1]);",element,getAttribute)
		}
	}

	@Keyword
	def safeCheck(TestObject testObject, String friendlyWebElementName,int... optionWaitTime){
		int waitTime=0;
		try {

			waitTime=syn.getWaitTime(optionWaitTime)
			WebUI.waitForElementPresent(testObject, waitTime)
			highLightElement(testObject,10)
			if(WebUI.verifyElementPresent(testObject,waitTime)) {
				//				if(WebUI.verifyElementChecked(testObject,waitTime))
				//
				//					KeywordUtil.markFailed(syn.getTestCasename()+"Checkbox '" +friendlyWebElementName+ "'is already selected" )
				//			}
				//else {
				highLightElement(testObject,30)
				WebUI.click(testObject)
				KeywordUtil.markPassed(syn.getTestCasename()+"Checkbox '" +friendlyWebElementName+ "'is selected" )
			}

			else{
				KeywordUtil.markFailed(syn.getTestCasename() +friendlyWebElementName+ "was not found in DOM in time "+waitTime)
			}
		}
		catch (StaleElementReferenceException e){
			KeywordUtil.markError(syn.getTestCasename() +friendlyWebElementName+ "is not attached to page document in time "+waitTime+" No such element Exception")
		}
	}



	@Keyword
	def safeType(TestObject testObject,String text, String friendlyWebElementName,int... optionWaitTime){
		int waitTime=0;
		try {

			waitTime=syn.getWaitTime(optionWaitTime)
			WebUI.waitForElementPresent(testObject, waitTime)
			if(!WebUI.verifyElementVisible(testObject))
				WebUI.scrollToElement(testObject, waitTime)
			if(WebUI.verifyElementPresent(testObject,waitTime)){
				highLightElement(testObject,waitTime)
				//WebUI.click(testObject)
				WebUI.setText(testObject, text)

				KeywordUtil.markPassed(syn.getTestCasename()+"Entered'"+text+" into " +friendlyWebElementName )
			}

			else{
				KeywordUtil.markError(syn.getTestCasename() +"Unable to enter "+text+  " in "+friendlyWebElementName +"in time "+waitTime)
			}
		}


		catch (StaleElementReferenceException e){
			KeywordUtil.markError(syn.getTestCasename() +friendlyWebElementName+ "is not attached to page document in time "+waitTime+" No such element Exception")
		}
	}

	@Keyword
	def getAttribute(TestObject testObject){
		try{
			//String attributeValue=WebUI.getAttribute(testObject, "value")
			int attribute=WebUI.getAttribute(testObject, "value")
			println attribute;

		}
		catch(Exception e){
			//WebUI.takeScreenshot(reportsFolderPath+"/getAttributeValue.png")
			KeywordUtil.markError("Expection while getting Class Attribute")
		}
		return attribute;
	}


	@Keyword
	def safeClick(TestObject testObject, String friendlyWebElementName,int... optionWaitTime){
		int waitTime=0;
		try {
			KeywordUtil.logInfo("safeClicking")
			waitTime=syn.getWaitTime(optionWaitTime)
			//if(waitUntilClickable(testObject,friendlyWebElementName,waitTime)){
			if(WebUI.verifyElementPresent(testObject,waitTime)){
				WebUI.waitForElementClickable(testObject, waitTime)
				highLightElement(testObject,30)

				WebUI.click(testObject)

				KeywordUtil.markPassed(syn.getTestCasename()+"Clicked on the " +friendlyWebElementName )
			}

			else{
				KeywordUtil.markError(syn.getTestCasename() +"Unable to click on "+friendlyWebElementName +"in time "+waitTime)
			}
		}
		catch (StepFailedException e){
			KeywordUtil.markFailed(syn.getTestCasename() +friendlyWebElementName+ "is not attached to page document in time "+waitTime+" No such element Exception")
		}
	}


	@Keyword
	def waitUntilClickable(TestObject testObj,String friendlyWebElementName,int... optionWaitTime){
		int waitTime=syn.getWaitTime(optionWaitTime)
		boolean bFlag=false
		try{
			KeywordUtil.logInfo(syn.getTestCaseName()+"waiting until "+friendlyWebElementName+" is Clickable")
			WebUI.waitForElementClickable(testObj,waitTime)
			if(WebUI.verifyElementVisible(testObj)){
				bFlag=true
				//KeywordUtil.logInfo(syn.getTestCaseName()+friendlyWebElementName+" is displayed and is Clickable")
			}
			else{
				KeywordUtil.markFailed(syn.getTestCasename()+friendlyWebElementName+" is not visible to perform Click action")
			}
		}
		catch(Exception e){
			KeywordUtil.markError(syn.getTestCasename()+friendlyWebElementName+" is not Clickable")
		}
	}
	@Keyword
	def String safeGetText(TestObject testObject,String friendlyWebElementName, int...optionWaitTime){
		int waitTime=0;
		//String sValue=null;
		try{
			waitTime=syn.getWaitTime(optionWaitTime)

			if(WebUI.verifyElementPresent(testObject,waitTime)) {
				String sValue= WebUI.getText(testObject)
				println("Text is "+sValue)
				KeywordUtil.markPassed("Get text from "+friendlyWebElementName+" in time" +waitTime)
			}
			else{
				KeywordUtil.markFailed(friendlyWebElementName+" is not present in the web page")
			}
		}
		catch(StaleElementReferenceException e){
			KeywordUtil.markError(syn.getTestCasename()+friendlyWebElementName+ "is not attached to page document- StaleElementReferenceException")
		}

		catch(Exception e){
			KeywordUtil.markError(syn.getTestCasename()+"Unable to get text from "+friendlyWebElementName+ "- "+e)
		}
		return sValue;

	}
	@Keyword
	def safeSelectOptionInDropdownByVisibleText(TestObject testObj,String sVisibleTextOptionToSelect,String friendlyWebElementName,int...optionWaitTime){
		int waitTime=0;
		try{
			waitTime=syn.getWaitTime(optionWaitTime);
			WebUI.waitForElementVisible(testObj, waitTime)
			if(WebUI.verifyElementPresent(testObj,waitTime)){
				highLightElement(testObj,waitTime)
				WebUI.click(testObj)
				WebUI.selectOptionByLabel(testObj, sVisibleTextOptionToSelect, true)

				KeywordUtil.markPassed("selected dropdown option by visible text  "+sVisibleTextOptionToSelect+"from "+friendlyWebElementName)
			}
			else{
				KeywordUtil.markFailed(syn.getTestCasename()+"Unable to select dropdown option by visible text  "+sVisibleTextOptionToSelect+"from "+friendlyWebElementName)
			}
		}
		catch(StaleElementReferenceException e){
			KeywordUtil.markFailed(syn.getTestCasename()+" Dropdown option by visible text  "+sVisibleTextOptionToSelect+"from "+friendlyWebElementName+" is not attached to page document -StaleElementReferenceException")
		}

	}

	@Keyword
	def String getAttributeValue(TestObject testObject,String attribute,String friendlyWebElementName, int...optionWaitTime){
		int waitTime=0;
		String sValue=null;
		try{
			waitTime=syn.getWaitTime(optionWaitTime)
			WebUI.waitForElementHasAttribute(testObject, attribute, GlobalVariable.pageLoadTime)
			if(WebUI.verifyElementPresent(testObject,waitTime)) {
				sValue=WebUI.getAttribute(testObject, attribute)
				//sValue= WebUI.getText(testObject)
				println("Attribute value  is "+sValue)
				KeywordUtil.markPassed("Get attribute value from "+friendlyWebElementName+" in time" +waitTime)
			}
			else{
				KeywordUtil.markFailed(friendlyWebElementName+" is not present in the web page")
			}
		}
		catch(StaleElementReferenceException e){
			KeywordUtil.markError(syn.getTestCasename()+friendlyWebElementName+ "is not attached to page document- StaleElementReferenceException")
		}
		//		catch(NoSuchElementException e){
		//			KeywordUtil.markError(syn.getTestCaseName()+friendlyWebElementName+ "is not found in DOM in time "+waitTime+"NoSuchElementException")
		//		}
		catch(Exception e){
			KeywordUtil.markError(syn.getTestCasename()+"Unable to get attribute value from "+friendlyWebElementName+ "- "+e)
		}
		return sValue;

	}

	@Keyword
	def safeClickwithScroll(TestObject testObj,String friendlyWebElementName,int... optionWaitTime){
		int waitTime=0
		try{
			waitTime=syn.getWaitTime(optionWaitTime)
			WebUI.waitForElementPresent(testObj, waitTime)
			if(!WebUI.verifyElementVisible(testObj))
				WebUI.scrollToElement(testObj, waitTime)
			//WebUI.scrollToElement(testObj, waitTime)
			if(waitUntilClickable(testObj, friendlyWebElementName, waitTime)){
				highLightElement(testObj,waitTime)
				WebUI.click(testObj)
				KeywordUtil.logInfo(syn.getTestCasename()+" Clicked on the "+friendlyWebElementName)
			}
			else{
				KeywordUtil.markFailed(friendlyWebElementName+" is not clickable in time -"+waitTime+" seconds")
			}
		}
		catch(Exception e){
			//WebUI.takeScreenshot(reportsFolderPath+"/safeClickwithScroll.png")
			KeywordUtil.markError(friendlyWebElementName+" was not found on the webpage")
		}
	}



}