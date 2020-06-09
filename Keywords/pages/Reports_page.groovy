package pages

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import utilities.SafeActions

public class Reports_page {



	WebDriver driver=DriverFactory.getWebDriver()
	def map_Tablecount=[:]

	SafeActions safe=new SafeActions()

	@Keyword
	def getPaymentIdColumnCount(String columnName){

		WebDriver driver=DriverFactory.getWebDriver()
		WebElement table= driver.findElement(By.xpath("(//table[@cellpadding='4'])[1]"))
		List<WebElement> rows=table.findElements(By.tagName('tr'))
		//for(int i=0;i<rows.size();i++){
		List<WebElement> cols=rows.get(0).findElements(By.tagName('td'))
		for(int j=0;j<cols.size();j++){
			if(cols.get(j).getText().equalsIgnoreCase(columnName)){
				map_Tablecount[columnName]=j
			}
		}
		//}
		println map_Tablecount
	}
	@Keyword
	def verifyPaymentIdRecord(String PaymentId){
		getPaymentIdColumnCount("Payment ID")
		//boolean foundValue=false
		//boolean lastpage=false
		WebElement table
		//while(!lastpage && !foundValue){
		table= driver.findElement(By.xpath("(//table[@cellpadding='4']/tbody)[1]"))
		List<WebElement> rows=table.findElements(By.tagName('tr'))

		for(int i=1;i<rows.size();i++){
			//println i
			List<WebElement> cols=rows.get(i).findElements(By.tagName('td'))
			//for(int j=0;j<cols.size();j++){
			String cellVaultReference = cols.get(map_Tablecount['Payment ID']).getText()
			//println cellVaultReference
			if(cellVaultReference.equalsIgnoreCase(PaymentId)){
				WebElement element=driver.findElement(By.linkText(PaymentId))
				//element.click()
				//println "success"
				//WebUI.click(cols.get(map_Tablecount['Payment ID']))
				//WebUI.scrollToElement(element, 30)
				KeywordUtil.markPassed("The Payment is found in page ")

				break
			}
		}

	}
}
