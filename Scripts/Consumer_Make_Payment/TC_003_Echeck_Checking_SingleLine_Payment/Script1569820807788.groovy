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
CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('MAKE_ PAYMENT/Home_ Page/NEXT_BUTTON'), 'Continue', (([
            GlobalVariable.pageLoadTime]) as int[]))

'Select \'Electronic check\' radio button'
CustomKeywords.'utilities.SafeActions.safeCheck'(findTestObject('MAKE_ PAYMENT/Home_ Page/ELECTRONIC_CHECK'), 'Echeck', 
        (([GlobalVariable.pageLoadTime]) as int[]))

'Enter payment amount,reference nu8m,comments'
CustomKeywords.'pages.Payment_Page.setAmount'('1', paymentAmount, 'testing', referenceNum)

'Select \'Payment type\''
CustomKeywords.'utilities.SafeActions.safeSelectOptionInDropdownByVisibleText'(findTestObject('MAKE_ PAYMENT/Home_ Page/PAYMENT_TYPE'), 
    paymentType, 'Payment Type', (([GlobalVariable.pageLoadTime]) as int[]))

'Click on \'Continue\''
CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('MAKE_ PAYMENT/Home_ Page/CONTINUE'), 'Continue', (([GlobalVariable.pageLoadTime]) as int[]))

'Enter First and Last name,telephone'
CustomKeywords.'pages.Payment_Page.setPersonalDetails'(firstName, lastName, telephone)

'Enter address details'
CustomKeywords.'pages.Payment_Page.setLocationDetails'(address, zipCode)

'Click on \'Continue\''
CustomKeywords.'pages.Payment_Page.clickOnElement'(findTestObject('MAKE_ PAYMENT/Home_ Page/CONTINUE'))

'Select checkbox \'My billing information is same information i entered in previous page\''
CustomKeywords.'utilities.SafeActions.safeCheck'(findTestObject('MAKE_ PAYMENT/Consumer_Personal_ Details/CheckBox'), 'Billing CheckBox', 
        (([GlobalVariable.pageLoadTime]) as int[]))

WebUI.waitForPageLoad(2)

'Click on \'Continue\''
CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('MAKE_ PAYMENT/Home_ Page/CONTINUE'), 'Continue', (([GlobalVariable.pageLoadTime]) as int[]))

'Select Account type as \'Checking\''
CustomKeywords.'utilities.SafeActions.safeCheck'(findTestObject('MAKE_ PAYMENT/Consumer_Personal_ Details/ACC_TYPE_CHECKING'), 
    'Checking', (([GlobalVariable.pageLoadTime]) as int[]))

'Enter \'Routing Number\',checking acc num'
CustomKeywords.'pages.Payment_Page.setElectronicCheckInformation'(findTestObject('MAKE_ PAYMENT/Consumer_Personal_ Details/CHECKING_ACCOUNT_NUMBER'), 
    findTestObject('MAKE_ PAYMENT/Consumer_Personal_ Details/CONFIRM_CHECKING_ACC_NUM'), routingNum, checkingAccNum)

'Enter \'Email address\''
CustomKeywords.'utilities.SafeActions.safeType'(findTestObject('MAKE_ PAYMENT/Consumer_Personal_ Details/EMAIL_ADDRESS'), 
    emailAddress, 'EmailAddress', (([GlobalVariable.pageLoadTime]) as int[]))

'Enter \'Signature\''
CustomKeywords.'utilities.SafeActions.safeType'(findTestObject('MAKE_ PAYMENT/Consumer_Personal_ Details/SIGNATURE'), firstName, 
    'First name', (([GlobalVariable.pageLoadTime]) as int[]))

'Enter \'Agreement\' (Yes)'
CustomKeywords.'utilities.SafeActions.safeType'(findTestObject('MAKE_ PAYMENT/Consumer_Personal_ Details/AGREEMENT'), 'Yes', 
    'Agreement', (([GlobalVariable.pageLoadTime]) as int[]))

'Click on Continue'
CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('MAKE_ PAYMENT/Home_ Page/CHECK_CONTINUE'), 'Continue', 
        (([GlobalVariable.pageLoadTime]) as int[]))

'Click on Continue'
CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('MAKE_ PAYMENT/Home_ Page/CONTINUE'), 'Continue', (([GlobalVariable.pageLoadTime]) as int[]))

'Click on \'I agree\''
CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('MAKE_ PAYMENT/Consumer_Personal_ Details/I_AGREE'), 'I agree', 
        (([GlobalVariable.pageLoadTime]) as int[]))

'verify user details'
CustomKeywords.'pages.Payment_Page.verifyUserDetails'(firstName, lastName, emailAddress, 'VISA', 'Checking')

'Click on \'Process Payment\''
CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('MAKE_ PAYMENT/Verification_ Details/PROCESS_PAYMENT'), 
    'Process Payment', (([GlobalVariable.pageLoadTime]) as int[]))

'Click on \'Yes\''
CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('MAKE_ PAYMENT/Verification_ Details/YES_BUTTON'), 'yes button', 
        (([GlobalVariable.pageLoadTime]) as int[]))

'Verify paymnet status\r\n'
WebUI.verifyElementPresent(findTestObject('MAKE_ PAYMENT/Verification_ Details/PENDING'), 30)

'Note paymentId'
PaymentID = CustomKeywords.'pages.Payment_Page.verifyECheckPaymentApproval'(referenceNum)

'Click on \'Search Payment\''
CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('SEARCH_PAYMENT/SEARCH_PAYMENT'), 'Search Payment', (([GlobalVariable.pageLoadTime]) as int[]))

WebUI.waitForPageLoad(10)

CustomKeywords.'pages.Search_Page.switchToWindow'()

'Select \'E-check\' radio button'
CustomKeywords.'utilities.SafeActions.safeCheck'(findTestObject('SEARCH_PAYMENT/ELECTRONIC_CHECK'), 'E check', (([GlobalVariable.mediumWait]) as int[]))

'Enter billing last name,acc num'
CustomKeywords.'pages.Search_Page.setSearchDetails'(lastName, cardNum, checkingAccNum)

'Click on \'Search\''
CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('SEARCH_PAYMENT/SEARCH_BUTTON'), 'Search', (([GlobalVariable.pageLoadTime]) as int[]))

'Verify payment details'
CustomKeywords.'pages.Payment_Page.verifyPaymentDetailsInReceipt'(PaymentID)

'Click on \'Search\' icon to generate receipt'
CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('SEARCH_PAYMENT/SEARCH_ ICON'), 'SearchIcon', (([GlobalVariable.pageLoadTime]) as int[]))

WebUI.delay(GlobalVariable.mediumWait)

WebUI.scrollToElement(findTestObject('SEARCH_PAYMENT/CLOSE_BUTTON'), 30)

'Click on \'Email Receipt\''
CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('SEARCH_PAYMENT/EMAIL_ RECEIPT'), 'Email Receipt', (([GlobalVariable.pageLoadTime]) as int[]))

'Click on \'Send Email\''
CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('SEARCH_PAYMENT/SEND_ EMAIL_BUTTON'), 'Send Email', (([GlobalVariable.pageLoadTime]) as int[]))

