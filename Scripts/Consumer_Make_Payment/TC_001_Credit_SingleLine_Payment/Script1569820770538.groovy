import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

'Open Certpay application'
CustomKeywords.'utilities.SafeActions.openBrowser'(GlobalVariable.certpay_url, (([GlobalVariable.pageLoadTime]) as int[]))

'Enter valid Bureau code'
CustomKeywords.'utilities.SafeActions.safeType'(findTestObject('MAKE_ PAYMENT/Home_ Page/BUREAU_CODE'), bureauCode, 'Bureau code', 
        (([GlobalVariable.pageLoadTime]) as int[]))

'Click on \'Begin Payment\''
CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('MAKE_ PAYMENT/Home_ Page/BEGIN_PAYMENT'), 'Begin payment', 
        (([GlobalVariable.pageLoadTime]) as int[]))

'Click on \'Continue\''
CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('MAKE_ PAYMENT/Home_ Page/CONTINUE'), 'Continue', (([GlobalVariable.pageLoadTime]) as int[]))

'Select \'Bank card\' radio button'
CustomKeywords.'utilities.SafeActions.safeCheck'(findTestObject('MAKE_ PAYMENT/Home_ Page/BANK_CARD'), 'Bank card', (([GlobalVariable.pageLoadTime]) as int[]))

'Enter Payment Amount,reference num,comments'
CustomKeywords.'pages.Payment_Page.setAmount'('1', paymentAmount, 'testing', referenceNum)

WebUI.waitForPageLoad(0)

'Select \'Visa\' from card type dropdown'
CustomKeywords.'utilities.SafeActions.safeSelectOptionInDropdownByVisibleText'(findTestObject('MAKE_ PAYMENT/Home_ Page/CARD_TYPE'), 
    CardType, 'CardType', (([GlobalVariable.pageLoadTime]) as int[]))

'Select \'Payment type\''
CustomKeywords.'utilities.SafeActions.safeSelectOptionInDropdownByVisibleText'(findTestObject('MAKE_ PAYMENT/Home_ Page/PAYMENT_TYPE'), 
    paymentType, 'Payment Type', (([GlobalVariable.pageLoadTime]) as int[]))

'Click on \'Continue\''
CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('MAKE_ PAYMENT/Home_ Page/NEXT_BUTTON'), 'Continue', (([
            GlobalVariable.pageLoadTime]) as int[]))

'Enter First and Last name,telephone'
CustomKeywords.'pages.Payment_Page.setPersonalDetails'(firstName, lastName, telephone)

'Enter address and zip code\r\n'
CustomKeywords.'pages.Payment_Page.setLocationDetails'(address, zipCode)

WebUI.waitForElementClickable(findTestObject('MAKE_ PAYMENT/Home_ Page/CONTINUE'), 30)

'Click on \'Continue\''
CustomKeywords.'pages.Payment_Page.clickOnElement'(findTestObject('MAKE_ PAYMENT/Home_ Page/CONTINUE'))

WebUI.waitForElementClickable(findTestObject('MAKE_ PAYMENT/Consumer_Personal_ Details/CheckBox'), 30)

'"Select checkbox \'My billing information is same \ninformation i entered in previous page\'"'
CustomKeywords.'utilities.SafeActions.safeCheck'(findTestObject('MAKE_ PAYMENT/Consumer_Personal_ Details/CheckBox'), 'Billing CheckBox', 
        (([GlobalVariable.pageLoadTime]) as int[]))

WebUI.waitForPageLoad(2)

'Click on \'Continue\''
CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('MAKE_ PAYMENT/Home_ Page/CONTINUE'), 'Continue', (([GlobalVariable.pageLoadTime]) as int[]))

WebUI.delay(GlobalVariable.mediumWait)

'Enter card number,exp date, exp month and security code\r\n'
CustomKeywords.'pages.Payment_Page.setCardDetails'(cardNum, securityCode, expYear, expMonth)

'Enter Email address\r\n'
CustomKeywords.'utilities.SafeActions.safeType'(findTestObject('MAKE_ PAYMENT/Consumer_Personal_ Details/EMAIL_ADDRESS'), 
    emailAddress, 'EmailAddress', (([GlobalVariable.pageLoadTime]) as int[]))

'Click on continue'
CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('MAKE_ PAYMENT/Home_ Page/CONTINUE'), 'Continue', (([GlobalVariable.pageLoadTime]) as int[]))

'Click on i agree\r\n'
CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('MAKE_ PAYMENT/Consumer_Personal_ Details/I_AGREE'), 'I agree', 
        (([GlobalVariable.pageLoadTime]) as int[]))

'Verify user details like first and last name,card type ,email address'
CustomKeywords.'pages.Payment_Page.verifyUserDetails'(firstName, lastName, emailAddress, CardType, '')

'Click on process payment'
CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('MAKE_ PAYMENT/Verification_ Details/PROCESS_PAYMENT'), 
    'Process Payment', (([GlobalVariable.pageLoadTime]) as int[]))

'Click on \'Yes\''
CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('MAKE_ PAYMENT/Verification_ Details/YES_BUTTON'), 'yes button', 
        (([GlobalVariable.pageLoadTime]) as int[]))

'Verify Payment Approval'
WebUI.verifyElementPresent(findTestObject('MAKE_ PAYMENT/Verification_ Details/APPROVED'), 30)

'Verify payment approval and note the paymentId'
PaymentId = CustomKeywords.'pages.Payment_Page.verifyCardPaymentApproval'(referenceNum)

'Click on \'Search Payment\''
CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('SEARCH_PAYMENT/SEARCH_PAYMENT'), 'Search Payment', (([GlobalVariable.pageLoadTime]) as int[]))

WebUI.waitForPageLoad(10)

CustomKeywords.'pages.Search_Page.switchToWindow'()

WebUI.waitForElementClickable(findTestObject('SEARCH_PAYMENT/CREDIT_OR_DEBIT_CARD'), 0)

'Select \'Credit or debit card\' radio button'
CustomKeywords.'utilities.SafeActions.safeCheck'(findTestObject('SEARCH_PAYMENT/CREDIT_OR_DEBIT_CARD'), 'Credit card', (([
            GlobalVariable.mediumWait]) as int[]))

'Enter billing last name,card number'
CustomKeywords.'pages.Search_Page.setSearchDetails'(lastName, cardNum, accNum)

'Click on search\r\n'
CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('SEARCH_PAYMENT/SEARCH_BUTTON'), 'Search', (([GlobalVariable.pageLoadTime]) as int[]))

'Verify payment id\r\n'
CustomKeywords.'pages.Payment_Page.verifyPaymentDetailsInReceipt'(PaymentId)

'Click on \'Search\' icon to generate receipt'
CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('SEARCH_PAYMENT/SEARCH_ ICON'), 'SearchIcon', (([GlobalVariable.pageLoadTime]) as int[]))

WebUI.delay(GlobalVariable.mediumWait)

'Scroll to close button'
WebUI.scrollToElement(findTestObject('SEARCH_PAYMENT/CLOSE_BUTTON'), 0)

'Click on \'Email Receipt\''
CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('SEARCH_PAYMENT/EMAIL_ RECEIPT'), 'Email Receipt', (([GlobalVariable.pageLoadTime]) as int[]))

//CustomKeywords.'utilities.SafeActions.safeType'(findTestObject('SEARCH_PAYMENT/EMAIL_ ADDRESS'), emailAddress, 'EmailAddress', 
// (([GlobalVariable.pageLoadTime]) as int[]))
'Click on \'Send Email\''
CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('SEARCH_PAYMENT/SEND_ EMAIL_BUTTON'), 'Send Email', (([GlobalVariable.pageLoadTime]) as int[]))

