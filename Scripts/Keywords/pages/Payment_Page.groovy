package pages

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import utilities.SafeActions

public class Payment_Page {
	WebDriver driver=DriverFactory.getWebDriver()
	def map_Tablecount=[:]
	SafeActions safe=new SafeActions()
	String cardNumber=null;

	String paymentID= null;
	String referenceNumber=null;


	@Keyword
	def getRowAndColumn(TestObject nextObj,TestObject numberOfPages,String paymentId){
		getPaymentIdColumnCount("Payment ID")
		int rowNumber=-1
		boolean foundValue=false
		boolean lastpage=false

		WebElement table
		while(!lastpage && !foundValue){
			table= driver.findElement(By.xpath("(//div[@class='block_content'])[2]/table/tbody"))
			List<WebElement> rows=table.findElements(By.tagName('tr'))
			String pagesCount=WebUI.getText(numberOfPages)
			String[] pages=pagesCount.trim().split(" ")
			for(int i=0;i<rows.size();i++){
				println i
				List<WebElement> cols=rows.get(i).findElements(By.tagName('td'))
				//for(int j=0;j<cols.size();j++){
				String cellVaultReference = cols.get(map_Tablecount['Payment ID']).getText()
				println cellVaultReference
				if(cellVaultReference.equalsIgnoreCase(paymentId)){
					KeywordUtil.markPassed("The Payment is found in page "+pages[1])
					rowNumber=i
					WebUI.delay(6)
					foundValue=true
					lastpage=true
					break
				}

			}
			if(pages[1].equalsIgnoreCase(pages[3])){
				if(foundValue){
				}
				else{
					KeywordUtil.markFailed("The Payment is not found in all the pages")
					lastpage=true
					foundValue=true
				}
			}
			else{
				if(foundValue){

				}
				else{
					WebUI.delay(5)
					//safeActions.highLightElement(nextObj, GlobalVariable.delayBetweenTestSteps)
					WebUI.click(nextObj)
				}
			}
		}
		println rowNumber
		return rowNumber
	}

	@Keyword
	def verifyPaymentIdRecord(TestObject nextObj,TestObject numberOfPages,String PaymentId){
		getPaymentIdColumnCount("Payment ID")
		boolean foundValue=false
		boolean lastpage=false
		WebElement table
		while(!lastpage && !foundValue){
			table= driver.findElement(By.xpath("(//div[@class='block_content'])[2]/table/tbody"))
			List<WebElement> rows=table.findElements(By.tagName('tr'))
			String pagesCount=WebUI.getText(numberOfPages)
			String[] pages=pagesCount.trim().split(" ")
			for(int i=0;i<rows.size();i++){
				println i
				List<WebElement> cols=rows.get(i).findElements(By.tagName('td'))
				//for(int j=0;j<cols.size();j++){
				String cellVaultReference = cols.get(map_Tablecount['Payment ID']).getText()
				println cellVaultReference
				if(cellVaultReference.equalsIgnoreCase(PaymentId)){
					KeywordUtil.markPassed("The Payment is found in page "+pages[1])
					//safeActions.highLightElement(cols.get(map_Tablecount['Vault Ref #']), GlobalVariable.delayBetweenTestSteps)
					foundValue=true
					lastpage=true
					break
				}
			}
			if(pages[1].equalsIgnoreCase(pages[3])){
				if(foundValue){

				}
				else{
					KeywordUtil.markFailed("The Payment is not found in all the pages")
					lastpage=true
					foundValue=true
				}
			}
			else{
				if(foundValue){

				}
				else{
					WebUI.delay(5)
					//safeActions.highLightElement(nextObj, GlobalVariable.delayBetweenTestSteps)
					WebUI.click(nextObj)
				}
			}
		}
	}
	@Keyword
	def verifyAllRecordsFilteredByCardNumber(TestObject nextObj,TestObject numberOfPages,String creditCardNumber){
		getPaymentIdColumnCount("Payment ID")
		int columnCount = map_Tablecount['Payment ID']
		columnCount = columnCount + 1
		int pagesCount = getCount(nextObj,numberOfPages,creditCardNumber)
		boolean flag=true
		WebElement table = driver.findElement(By.xpath("(//div[@class='block_content'])[2]/table/tbody"))
		for(int i=1;i<=pagesCount;i++){
			List<WebElement> rows=table.findElements(By.tagName('tr'))
			for(int rowCount=0;rowCount<rows.size();rowCount++){
				println rowCount
				List<WebElement> cols = rows.get(rowCount).findElements(By.tagName('td'))
				String cellCardNumber = cols.get(map_Tablecount['Last 4']).getText()
				println cellCardNumber
				if(creditCardNumber.trim().substring(creditCardNumber.lastIndexOf("1")-3).equalsIgnoreCase(cellCardNumber.trim())){

				}
				else{
					flag=false
					break
				}
			}
			if(flag){
				WebUI.delay(1)
				WebUI.click(nextObj)
				WebUI.delay(2)
			}
			else{
				break
			}
		}
		if(flag){
			KeywordUtil.markPassed("All filtered Records have expected card Number")
		}
		else{
			KeywordUtil.markFailed("Filtered Records donot contain the expected card Number")
		}
	}


	@Keyword
	def getPaymentIdColumnCount(String columnName){

		WebDriver driver=DriverFactory.getWebDriver()
		WebElement table= driver.findElement(By.xpath("(//div[@class='block_content'])[2]/table/thead"))
		List<WebElement> rows=table.findElements(By.tagName('tr'))
		for(int i=0;i<rows.size();i++){
			List<WebElement> cols=rows.get(i).findElements(By.tagName('th'))
			for(int j=0;j<cols.size();j++){
				if(cols.get(j).getText().equalsIgnoreCase(columnName)){
					map_Tablecount[columnName]=j
				}
			}
		}
		println map_Tablecount
	}

	@Keyword
	def setPersonalDetails(String firstname,String last,String telephone){

		//findTestData('Certpay/CertpayTestData').getValue('FirstName', row)
		safe.safeType(findTestObject('MAKE_ PAYMENT/Consumer_Personal_ Details/FIRST_NAME')
				,firstname , "FirstName", (([GlobalVariable.pageLoadTime]) as int[]))

		safe.safeType(findTestObject('MAKE_ PAYMENT/Consumer_Personal_ Details/LAST_NAME')
				, last, "LastName", (([GlobalVariable.pageLoadTime]) as int[]))

		safe.safeType(findTestObject('MAKE_ PAYMENT/Consumer_Personal_ Details/TELEPHONE')
				, telephone, "Telephone", (([GlobalVariable.pageLoadTime]) as int[]))

	}




	@Keyword
	def setLocationDetails(String address,String zipCode){
		safe.safeType(findTestObject('MAKE_ PAYMENT/Consumer_Personal_ Details/ADDRESS'),address, "Address",  (([GlobalVariable.pageLoadTime]) as int[]))

		safe.safeType( findTestObject('MAKE_ PAYMENT/Consumer_Personal_ Details/ZIP_CODE'),zipCode, "ZipCode",  (([GlobalVariable.pageLoadTime]) as int[]))
	}

	@Keyword
	def setCardDetails(String cardNum,String securityCode,String expYear,String expMonth){
		safe.safeType(findTestObject('MAKE_ PAYMENT/Consumer_Personal_ Details/CARD_NUM'),cardNum, "CardNum",  (([GlobalVariable.pageLoadTime]) as int[]))

		safe.safeSelectOptionInDropdownByVisibleText(findTestObject('MAKE_ PAYMENT/Consumer_Personal_ Details/EXP_MONTH'), expMonth, 'Month', (([GlobalVariable.pageLoadTime]) as int[]))
		safe.safeSelectOptionInDropdownByVisibleText(findTestObject('MAKE_ PAYMENT/Consumer_Personal_ Details/EXP_YEAR'), expYear, 'year', (([GlobalVariable.pageLoadTime]) as int[]))
		safe.safeType(findTestObject('MAKE_ PAYMENT/Consumer_Personal_ Details/SECURITY_CODE'),securityCode, "SecurityCode",  (([GlobalVariable.pageLoadTime]) as int[]))

		String cardNumber= WebUI.getAttribute(findTestObject('MAKE_ PAYMENT/Consumer_Personal_ Details/CARD_NUM'), 'value')
		WebUI.verifyMatch(cardNumber, cardNum, true)

		String last4=cardNumber.substring(11);
		String last5=cardNumber.substring(10);
		println last4;

		List<String> newList= new ArrayList<String>()
		newList.add(last4);
		newList.add(last5);
		//String[] strArray=new String[newList.size()];
		//strArray=newList.toArray(strArray)
		//for(String s:newList){

		//}

		//println(Arrays.toString(strArray))

		//String arrayList=Arrays.toString(strArray)
		println newList;
		return newList;


	}

	// Entering amount,reference number,comments
	@Keyword
	def setAmount(String index,String paymentAmount,String comments,String referenceNum){
		//Payment amount

		String amountXpath="//input[@id='ctl00_mainContent_AdditionalPayment_"+index+"_txtPaymentAmount']";
		TestObject amount=new TestObject()
		amount.addProperty("xpath", ConditionType.EQUALS, amountXpath)
		safe.safeType(amount, paymentAmount, 'Amount', (([GlobalVariable.pageLoadTime]) as int[]))

		//Reference Number
		String refXpath="//input[@id='ctl00_mainContent_AdditionalPayment_"+index+"_txtTaxID']"
		TestObject referenceNumber=new TestObject()
		referenceNumber.addProperty("xpath", ConditionType.EQUALS, refXpath)
		safe.safeType(referenceNumber, referenceNum, 'Amount', (([GlobalVariable.pageLoadTime]) as int[]))

		// Comments
		String commentXpath="//input[@id='ctl00_mainContent_AdditionalPayment_"+index+"_txtComments']";
		TestObject comment=new TestObject()
		comment.addProperty("xpath", ConditionType.EQUALS, commentXpath)
		safe.safeType(comment, comments, 'Comments', (([GlobalVariable.pageLoadTime]) as int[]))

	}

	@Keyword
	def verifyUserDetails(String firstName,String lastName,String emailAddress,String cardType,String accountType){
		WebUI.scrollToElement(findTestObject('MAKE_ PAYMENT/Verification_ Details/FIRST_NAME'), 30)
		String sfirstName=WebUI.getText(findTestObject('MAKE_ PAYMENT/Verification_ Details/FIRST_NAME'))
		WebUI.verifyMatch(firstName, sfirstName, true, FailureHandling.STOP_ON_FAILURE)

		String sLastName=WebUI.getText(	findTestObject('MAKE_ PAYMENT/Verification_ Details/LAST_NAME'))
		WebUI.verifyMatch(lastName, sLastName, true, FailureHandling.STOP_ON_FAILURE)
		Thread.sleep(2000);

		String email=WebUI.getText(	findTestObject('MAKE_ PAYMENT/Verification_ Details/EMAIL_ADDRESS'))
		WebUI.verifyMatch(emailAddress, email, true, FailureHandling.STOP_ON_FAILURE)
		try{
			if(accountType.empty)
				//if(WebUI.verifyElementPresent(findTestObject('MAKE_ PAYMENT/Verification_ Details/CARD_TYPE'),0))
			{

				String cardType1=WebUI.getText(findTestObject('MAKE_ PAYMENT/Verification_ Details/CARD_TYPE'))

				WebUI.verifyMatch(cardType, cardType1, true, FailureHandling.STOP_ON_FAILURE)
				KeywordUtil.markPassed("CardType matched")
			}
			else{
				String accType=WebUI.getText(findTestObject('Object Repository/MAKE_ PAYMENT/Verification_ Details/ACH_ACC_TYPE'))
				WebUI.verifyMatch(accountType,accType,true,FailureHandling.STOP_ON_FAILURE)
				Thread.sleep(1000);
			}
		}
		catch(StepFailedException e){
			KeywordUtil.markError("Unable to verify element present")
		}
	}


	// Payment Verifications
	@Keyword
	def verifyCardPaymentApproval(String referenceNum){
		String paymentID=WebUI.getText( findTestObject('MAKE_ PAYMENT/Verification_ Details/PAY_ID_CARD'))
		Thread.sleep(3000);
		referenceNumber =WebUI.getText(findTestObject('MAKE_ PAYMENT/Verification_ Details/REFERENCE_NUM'))
		if(referenceNumber.equals(referenceNum))
			println('Payment successfull with Payment ID :'+paymentID)
		return paymentID;
	}
	@Keyword
	def verifyECheckPaymentApproval(String referenceNum)
	{
		String paymentID=WebUI.getText(findTestObject('Object Repository/MAKE_ PAYMENT/Verification_ Details/PAY_ID_ELECTRIC_CHECK'))
		Thread.sleep(2000);
		referenceNumber=WebUI.getText(	findTestObject('Object Repository/MAKE_ PAYMENT/Verification_ Details/REF_NUM_E_CHECK'))

		if(referenceNumber.equals(referenceNum))
			println('Payment successfull with Payment ID :'+paymentID)
		return paymentID;
	}



	@Keyword
	def verifyPaymentDetailsInReceipt(String paymentId){
		boolean bFlag=true;
		String referenceNum=WebUI.getText(findTestObject('Object Repository/SEARCH_PAYMENT/REFERNCE_NUM'))
		String sPaymentId=WebUI.getText(findTestObject('Object Repository/SEARCH_PAYMENT/PAYMENT_ID'))
		if(referenceNum.equals(referenceNumber)){


			assert WebUI.verifyMatch(sPaymentId,paymentId,  true, FailureHandling.STOP_ON_FAILURE)
			Thread.sleep(2000);
			return bFlag;
		}
		else {
			println(" PaymentID do not matched ")

		}
		println referenceNum;
		println sPaymentId;
	}
	@Keyword
	def setElectronicCheckInformation(TestObject testObject,TestObject testObject2,String routingNumber,String checkingAccNum){

		safe.safeType(		findTestObject('Object Repository/MAKE_ PAYMENT/Consumer_Personal_ Details/ROUTING_NUMBER')
				, routingNumber, 'RoutingNumber', (([GlobalVariable.pageLoadTime]) as int[]))

		if(WebUI.verifyElementPresent(testObject, 30)){


			safe.safeType(	testObject, checkingAccNum, 'CheckAccNum', (([GlobalVariable.pageLoadTime]) as int[]))

			safe.safeType(	testObject2, checkingAccNum, 'ConfirmCheckAccNum', (([GlobalVariable.pageLoadTime]) as int[]))
		}
	}
	@Keyword
	def clickOnElement(TestObject object){
		WebElement element =WebUiCommonHelper.findWebElement(object,30)
		WebUI.executeJavaScript("arguments[0].click()",Arrays.asList(element))
	}
}