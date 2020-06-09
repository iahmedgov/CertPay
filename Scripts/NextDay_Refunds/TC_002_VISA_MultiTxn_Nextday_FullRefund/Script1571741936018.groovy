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

WebUI.callTestCase(findTestCase('Certpay_Quicksti/TC_002_MultiBureauSite_SingleBureau_MultiLinePayment'), [('paymentAmount') : '22.00', ('quantity') : '2'
        , ('referenceNum') : '11', ('comments') : 'Testing', ('paymentAmount2') : '33.00', ('paymentType') : 'Licenses', ('firstName') : 'Nickel'
        , ('lastName') : 'James', ('securityCode') : '207', ('zipCode') : '76103', ('emailAddress') : 'testing@zenq.com'
        , ('expMonth') : '11', ('expYear') : '2026', ('address') : '23-9', ('telephone') : '9898676543', ('referenceNum2') : '12'], 
    FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('SAME_DAY_REFUNDS/REQUEST_REFUND'), 'Request refund', (([
            GlobalVariable.pageLoadTime]) as int[]))

CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('SAME_DAY_REFUNDS/SET_TO_FULL_REFUND'), 'Set to full refund', 
        (([GlobalVariable.pageLoadTime]) as int[]))

'Select \'Bank card\' radio button'
CustomKeywords.'utilities.SafeActions.safeCheck'(findTestObject('SAME_DAY_REFUNDS/CUSTOMER_REQUESTED_REFUND_RADIO_BUTTON'), 
    'Customer requested', (([GlobalVariable.pageLoadTime]) as int[]))

'Select Payment type'
CustomKeywords.'utilities.SafeActions.safeSelectOptionInDropdownByVisibleText'(findTestObject('SAME_DAY_REFUNDS/REASON_FOR_REFUND_DROPDOWN'), 
    'Not For This Bureau', 'Reason for Refund', (([GlobalVariable.pageLoadTime]) as int[]))

CustomKeywords.'pages.Sameday_Refund_Page.verifyRefundAmount'()

CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('SAME_DAY_REFUNDS/SUBMIT_REQUEST_BUTTON'), 'Submit request', 
        (([GlobalVariable.pageLoadTime]) as int[]))

WebUI.waitForElementClickable(findTestObject('SAME_DAY_REFUNDS/CONFIRM_REQUEST_BUTTON'), 30)

CustomKeywords.'utilities.SafeActions.safeClick'(findTestObject('SAME_DAY_REFUNDS/CONFIRM_REQUEST_BUTTON'), 'Confirm submission', 
        (([GlobalVariable.pageLoadTime]) as int[]))

WebUI.verifyElementPresent(findTestObject('SAME_DAY_REFUNDS/EDIT_REVIEW_REFUND'), 30)

