import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.testdata.ExcelData as ExcelData
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

ExcelData data = findTestData('Certpay/SingleBureau_MultiLine')
List<String> amountList = []

List<String> referenceList = []

List<String> paymentTypeList = []

List<String> quantityList = []
List<String> commentsList = []
List<String> paymentIdList=[]
ArrayList<String> list=new ArrayList<String>();
for (def index : (1..data.getRowNumbers())) {
	firstName=data.getValue('FirstName',index)
	lastName=data.getValue('LastName', index)
	cardNum=data.getValue('CardNumber',index)
	expMonth=data.getValue('ExpMonth',index)
	expYear=data.getValue('ExpYear',index)
	securityCode=data.getValue('SecurityCode',index)
	paymentAmount=data.getValue('PaymentAmount',index)
	paymentType=data.getValue('PaymentType',index)
	quantity=data.getValue('Quantity',index)
	comments=data.getValue('Comments',index)
	referenceNum=data.getValue('ReferenceNum',index)
	address=data.getValue('Address',index)
	zipCode=data.getValue('Zipcode',index)
	telephone=data.getValue('Telephone',index)
	emailAddress=data.getValue('EmailAddress',index)
	
	cardType=data.getValue('CardType',index)
	amountList = paymentAmount.split(',')
	referenceList=referenceNum.split(',')
	paymentTypeList=paymentType.split(',')
	commentsList=comments.split(',')
	quantityList=quantity.split(',')
	
	
'Open certpay Quicksti appliction'
CustomKeywords.'utilities.SafeActions.openBrowser'(GlobalVariable.Quicksti_Url, (([GlobalVariable.pageLoadTime]) as int[]))

'Click on \'Use multi bureau entry screen\' checkbox'
CustomKeywords.'utilities.SafeActions.safeCheck'(findTestObject('CERTPAY_QUICKSTI/Login_ Page/MULTI_BUREAU_CHECKBOX'), 'Multi Bureau', 
        (([GlobalVariable.pageLoadTime]) as int[]))

'Enter \'Teller Id\''
CustomKeywords.'utilities.SafeActions.safeType'(findTestObject('CERTPAY_QUICKSTI/Login_ Page/TELLER_ID'), GlobalVariable.teller_id, 
    'Bureau code', (([GlobalVariable.pageLoadTime]) as int[]))

'Click on \'Next\''
CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('CERTPAY_QUICKSTI/Login_ Page/NEXT_BUTTON'), 'Next button', 
        (([GlobalVariable.pageLoadTime]) as int[]))

for(int i=0;i<=amountList.size()-1;i++){

'Enter comments'

String s=Integer.toString(i);
CustomKeywords.'pages.Quicksti.setCommnets'('0',s, commentsList.get(i))

'Enter Payment amount'
CustomKeywords.'utilities.SafeActions.safeType'(findTestObject('CERTPAY_QUICKSTI/Payment_Information_Page/PAYMENT_AMOUNT'),
	amountList.get(i), 'Payment Amount', (([GlobalVariable.pageLoadTime]) as int[]))

'Enter payment type,reference num,quantity'
CustomKeywords.'pages.Quicksti.setAmountDetailsForMultiBureau'(s, paymentTypeList.get(i), '0', referenceList.get(i), quantityList.get(i))

}

amount_Value = CustomKeywords.'pages.Quicksti.getAttributeValue'(findTestObject('CERTPAY_QUICKSTI/Payment_Information_Page/SUB_TOTAL'))

'Click on \'Click here to manually enter card details\''
CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('CERTPAY_QUICKSTI/Payment_Information_Page/MANUAL_ENTRY_BUTTON'), 
    'ManualEntry', (([GlobalVariable.pageLoadTime]) as int[]))

' Enter firstname,lastname, Visa card number'
CardNumber = CustomKeywords.'pages.Quicksti.setUserPaymentDetails'(firstName, lastName, cardNum)

'Enter exp date,security code'
CustomKeywords.'pages.Quicksti.setExpDate'(expMonth, expYear, securityCode)

'Enter address'
CustomKeywords.'utilities.SafeActions.safeType'(findTestObject('CERTPAY_QUICKSTI/Payment_Information_Page/ADDRESS'), address, 
    'Address', (([GlobalVariable.pageLoadTime]) as int[]))

'Enter zip code'
CustomKeywords.'utilities.SafeActions.safeType'(findTestObject('CERTPAY_QUICKSTI/Payment_Information_Page/ZIP_CODE'), zipCode, 
    'zipcode', (([GlobalVariable.pageLoadTime]) as int[]))

'Enter telephone'
CustomKeywords.'utilities.SafeActions.safeType'(findTestObject('CERTPAY_QUICKSTI/Payment_Information_Page/TELEPHONE'), telephone, 
    'Telephone', (([GlobalVariable.pageLoadTime]) as int[]))

'enter email address'
CustomKeywords.'utilities.SafeActions.safeType'(findTestObject('CERTPAY_QUICKSTI/Payment_Information_Page/EMAIL_ADDRESS'), 
   emailAddress, 'EMAIL address', (([GlobalVariable.pageLoadTime]) as int[]))

'Click on Next'
CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('CERTPAY_QUICKSTI/Payment_Information_Page/NEXT_BUTTON'), 
    'Next', (([GlobalVariable.pageLoadTime]) as int[]))

'Verify user details like first name ,last name'
CustomKeywords.'pages.Quicksti.verifyUserPaymentDetails'(amountList.get(0), firstName, lastName, CardNumber)

WebUI.delay(2)

WebUI.scrollToElement(findTestObject('CERTPAY_QUICKSTI/Payment_Details_Verification_Page/PROCESS'), 30)

'Click on Process'
CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('CERTPAY_QUICKSTI/Payment_Details_Verification_Page/PROCESS'), 
    'Process', (([GlobalVariable.pageLoadTime]) as int[]))

'Verify payment approval'
paymentID = CustomKeywords.'pages.Quicksti.verifyPaymentApproval'(findTestObject('CERTPAY_QUICKSTI/Payment_Details_Verification_Page/APPROVED'))

'Navigate to reports site'
WebUI.navigateToUrl(GlobalVariable.Reports_url)

'Enter username,password,access code and click Login'
CustomKeywords.'pages.Bureau_Login_Page.loginToReportsSite'(GlobalVariable.userName, GlobalVariable.password, GlobalVariable.accessCode)


WebUI.delay(1)

'Click Reporting'
CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('REPORTS_PAGE/REPORTING'), 'Reporting', (([GlobalVariable.pageLoadTime]) as int[]))

CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('REPORTS_PAGE/REPORTING'), 'Reporting', (([GlobalVariable.pageLoadTime]) as int[]))

'Click on Quick find Payment'
CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('REPORTS_PAGE/QUICK_FIND_PAYMENT'), 'Quick find payment', 
        (([GlobalVariable.pageLoadTime]) as int[]))

'Enter payment id'
CustomKeywords.'utilities.SafeActions.safeType'(findTestObject('SEARCH_PAYMENT/PAYMENT_ID_INPUT_FIELD'), paymentID, 'Payment id', 
        (([GlobalVariable.pageLoadTime]) as int[]))

'Click on Search'
CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('SEARCH_PAYMENT/SEARCH_BUTTON_1'), 'Search button', (([GlobalVariable.pageLoadTime]) as int[]))

'Verify Payment id,amount,card type'
CustomKeywords.'pages.Search_Page.validateApprovedTransactionsInReports'(paymentID, cardType, amount_Value)
//return PaymentID;


paymentIdList.add(paymentID);
println paymentIdList;

CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('SAME_DAY_REFUNDS/REQUEST_REFUND'), 'Request refund', (([
            GlobalVariable.pageLoadTime]) as int[]))

CustomKeywords.'utilities.SafeActions.safeType'(findTestObject('SAME_DAY_REFUNDS/AMOUNT_TO_REFUND'), refundAmount, 'Amount', 
        (([GlobalVariable.pageLoadTime]) as int[]))

'Select \'Bank card\' radio button'
CustomKeywords.'utilities.SafeActions.safeCheck'(findTestObject('SAME_DAY_REFUNDS/CUSTOMER_REQUESTED_REFUND_RADIO_BUTTON'), 
    'Customer requested', (([GlobalVariable.pageLoadTime]) as int[]))

'Select Payment type'
CustomKeywords.'utilities.SafeActions.safeSelectOptionInDropdownByVisibleText'(findTestObject('SAME_DAY_REFUNDS/REASON_FOR_REFUND_DROPDOWN'), 
    'Not For This Bureau', 'Reason for Refund', (([GlobalVariable.pageLoadTime]) as int[]))

originalAmount = CustomKeywords.'pages.Quicksti.getAttributeValue'(findTestObject('SAME_DAY_REFUNDS/ORIGINAL_AMOUNT'))

if( WebUI.verifyLessThanOrEqual(refundAmount, originalAmount)){
	WebUI.delay(2)

    CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('SAME_DAY_REFUNDS/SUBMIT_REQUEST_BUTTON'), 'Submit request', 
            (([GlobalVariable.pageLoadTime]) as int[]))

    WebUI.waitForElementClickable(findTestObject('SAME_DAY_REFUNDS/CONFIRM_REQUEST_BUTTON'), 30)

    CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('SAME_DAY_REFUNDS/CONFIRM_REQUEST_BUTTON'), 'Confirm submission', 
            (([GlobalVariable.pageLoadTime]) as int[]))
	WebUI.delay(2)
    WebUI.verifyElementPresent(findTestObject('SAME_DAY_REFUNDS/EDIT_REVIEW_REFUND'), 30)
	
	WebUI.mouseOver(findTestObject('REPORTS_PAGE/ADMIN'))
	
	//CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('REPORTS_PAGE/REFUNDS'), 'Refunds', (([GlobalVariable.pageLoadTime]) as int[]))
	WebUI.mouseOver(findTestObject('REPORTS_PAGE/REFUNDS'))
	
	CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('REPORTS_PAGE/PROCESS_REFUNDS'), 'Process Refunds', (([GlobalVariable.pageLoadTime]) as int[]))
	
	CustomKeywords.'pages.Reports_page.getPaymentIdColumnCount'('Payment ID')
	CustomKeywords.'pages.Reports_page.verifyPaymentIdRecord'(paymentID)
} else {
    println('Amount to Refund should not be greater than original amount')
}


}
