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

'Enter Payment Amount,refernce num,comments'
CustomKeywords.'pages.Payment_Page.setAmount'('1', paymentAmount, 'testing', referenceNum)

'Click on \'Add another property\''
CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('MAKE_ PAYMENT/Home_ Page/ADD_ANOTHER_PROPERTY'), 'Multi-Payment', 
        (([GlobalVariable.pageLoadTime]) as int[]))

'Enter Payment Amount,refernce num,comments'
CustomKeywords.'pages.Payment_Page.setAmount'('2', paymentAmount2, 'Payment for testing', '22')

'Select \'Bank card\' radio button'
CustomKeywords.'utilities.SafeActions.safeCheck'(findTestObject('MAKE_ PAYMENT/Home_ Page/BANK_CARD'), 'Bank card', (([GlobalVariable.pageLoadTime]) as int[]))

WebUI.waitForPageLoad(2)

'Select \'Discover\' from card type dropdown'
CustomKeywords.'utilities.SafeActions.safeSelectOptionInDropdownByVisibleText'(findTestObject('MAKE_ PAYMENT/Home_ Page/CARD_TYPE'), 
    cardType, 'CardType', (([GlobalVariable.pageLoadTime]) as int[]))

'Select \'Payment type\''
CustomKeywords.'utilities.SafeActions.safeSelectOptionInDropdownByVisibleText'(findTestObject('MAKE_ PAYMENT/Home_ Page/PAYMENT_TYPE'), 
    paymentType, 'Payment Type', (([GlobalVariable.pageLoadTime]) as int[]))

'Click on \'Continue\''
CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('MAKE_ PAYMENT/Home_ Page/CONTINUE'), 'Continue', (([GlobalVariable.pageLoadTime]) as int[]))

'Enter First and Last name,telephone'
CustomKeywords.'pages.Payment_Page.setPersonalDetails'(firstName, lastName, telephone)

'Enter address and Zip code'
CustomKeywords.'pages.Payment_Page.setLocationDetails'(address, zipCode)

WebUI.waitForElementClickable(findTestObject('MAKE_ PAYMENT/Home_ Page/CONTINUE'), 30)

'Click on continue'
CustomKeywords.'pages.Payment_Page.clickOnElement'(findTestObject('MAKE_ PAYMENT/Home_ Page/CONTINUE'))

'"Select checkbox \'My billing information is same \ninformation i entered in previous page\'"'
CustomKeywords.'utilities.SafeActions.safeCheck'(findTestObject('MAKE_ PAYMENT/Consumer_Personal_ Details/CheckBox'), 'Billing CheckBox', 
        (([GlobalVariable.pageLoadTime]) as int[]))

WebUI.waitForPageLoad(0)

'Click on continue'
CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('MAKE_ PAYMENT/Home_ Page/CONTINUE'), 'Continue', (([GlobalVariable.pageLoadTime]) as int[]))

'Enter card number,exp date,exp month,security code'
CustomKeywords.'pages.Payment_Page.setCardDetails'(cardNum, securityCode, expYear, expMonth)

'Enter email address'
CustomKeywords.'utilities.SafeActions.safeType'(findTestObject('MAKE_ PAYMENT/Consumer_Personal_ Details/EMAIL_ADDRESS'), 
    emailAddress, 'EmailAddress', (([GlobalVariable.pageLoadTime]) as int[]))

'Click on Continue'
CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('MAKE_ PAYMENT/Home_ Page/CONTINUE'), 'Continue', (([GlobalVariable.pageLoadTime]) as int[]))

'Click on \'I agree\''
CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('MAKE_ PAYMENT/Consumer_Personal_ Details/I_AGREE'), 'I agree', 
        (([GlobalVariable.pageLoadTime]) as int[]))

'Verify user details'
CustomKeywords.'pages.Payment_Page.verifyUserDetails'(firstName, lastName, emailAddress, cardType, '')

'Click on \'Process Payment\''
CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('MAKE_ PAYMENT/Verification_ Details/PROCESS_PAYMENT'), 
    'Process Payment', (([GlobalVariable.pageLoadTime]) as int[]))

'Click on \'Yes\''
CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('MAKE_ PAYMENT/Verification_ Details/YES_BUTTON'), 'yes button', 
        (([GlobalVariable.pageLoadTime]) as int[]))

'Verify paymnet Approval'
WebUI.verifyElementPresent(findTestObject('MAKE_ PAYMENT/Verification_ Details/APPROVED'), 30)

'Verify payment details and note paymnetId'
PaymentID = CustomKeywords.'pages.Payment_Page.verifyCardPaymentApproval'(referenceNum)

'Click on \'Search Payment\''
CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('SEARCH_PAYMENT/SEARCH_PAYMENT'), 'Search Payment', (([GlobalVariable.pageLoadTime]) as int[]))

WebUI.waitForPageLoad(10)

CustomKeywords.'pages.Search_Page.switchToWindow'()

'Select \'Credit or debit card\' radio button'
CustomKeywords.'utilities.SafeActions.safeCheck'(findTestObject('SEARCH_PAYMENT/CREDIT_OR_DEBIT_CARD'), 'Credit card', (([
            GlobalVariable.mediumWait]) as int[]))

'Enter billing last name,card num'
CustomKeywords.'pages.Search_Page.setSearchDetails'(lastName, cardNum, accNum)

'Click on \'Search\''
CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('SEARCH_PAYMENT/SEARCH_BUTTON'), 'Search', (([GlobalVariable.pageLoadTime]) as int[]))

'Verify payment details'
CustomKeywords.'pages.Payment_Page.verifyPaymentDetailsInReceipt'(PaymentID)

'Click on \'Search\' icon to generate receipt'
CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('SEARCH_PAYMENT/SEARCH_ ICON'), 'SearchIcon', (([GlobalVariable.pageLoadTime]) as int[]))

WebUI.delay(GlobalVariable.mediumWait)

'scroll to close button\r\n'
WebUI.scrollToElement(findTestObject('SEARCH_PAYMENT/CLOSE_BUTTON'), 0)

'Click on \'Email Receipt\''
CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('SEARCH_PAYMENT/EMAIL_ RECEIPT'), 'Email Receipt', (([GlobalVariable.pageLoadTime]) as int[]))

'Click on \'Send Email\''
CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('SEARCH_PAYMENT/SEND_ EMAIL_BUTTON'), 'Send Email', (([GlobalVariable.pageLoadTime]) as int[]))

