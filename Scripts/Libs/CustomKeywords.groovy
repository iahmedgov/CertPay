
/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */

import com.kms.katalon.core.testobject.TestObject

import java.lang.String


def static "utilities.Syn.getWaitTime"(
    	int[] optionalWaitArray	) {
    (new utilities.Syn()).getWaitTime(
        	optionalWaitArray)
}

def static "utilities.Syn.getTestCasename"() {
    (new utilities.Syn()).getTestCasename()
}

def static "pages.Quicksti.getAttributeValue"(
    	TestObject object	) {
    (new pages.Quicksti()).getAttributeValue(
        	object)
}

def static "pages.Quicksti.setUserPaymentDetails"(
    	String firstName	
     , 	String lastName	
     , 	String cardNum	) {
    (new pages.Quicksti()).setUserPaymentDetails(
        	firstName
         , 	lastName
         , 	cardNum)
}

def static "pages.Quicksti.setExpDate"(
    	String sExpMonth	
     , 	String sExpYear	
     , 	String securityCode	) {
    (new pages.Quicksti()).setExpDate(
        	sExpMonth
         , 	sExpYear
         , 	securityCode)
}

def static "pages.Quicksti.setPaymentAmountDetailsForSinglePayment"(
    	String amount	
     , 	String quantity	) {
    (new pages.Quicksti()).setPaymentAmountDetailsForSinglePayment(
        	amount
         , 	quantity)
}

def static "pages.Quicksti.verifyUserPaymentDetails"(
    	String paymentAmount	
     , 	String firstName	
     , 	String lastName	
     , 	String cardNumber	) {
    (new pages.Quicksti()).verifyUserPaymentDetails(
        	paymentAmount
         , 	firstName
         , 	lastName
         , 	cardNumber)
}

def static "pages.Quicksti.setAmountDetailsForMultiBureau"(
    	String index	
     , 	String paymentType	
     , 	String bureauNum	
     , 	String referenceNum	
     , 	String quantity	) {
    (new pages.Quicksti()).setAmountDetailsForMultiBureau(
        	index
         , 	paymentType
         , 	bureauNum
         , 	referenceNum
         , 	quantity)
}

def static "pages.Quicksti.setCommnets"(
    	String bureauNum	
     , 	String index	
     , 	String sComments	) {
    (new pages.Quicksti()).setCommnets(
        	bureauNum
         , 	index
         , 	sComments)
}

def static "pages.Quicksti.verifyPaymentApproval"(
    	TestObject testObject	) {
    (new pages.Quicksti()).verifyPaymentApproval(
        	testObject)
}

def static "pages.CommonActions.safeMouseOver"(
    	TestObject object	) {
    (new pages.CommonActions()).safeMouseOver(
        	object)
}

def static "pages.Search_Page.setSearchDetails"(
    	String lastName	
     , 	String cardNum	
     , 	String accNumber	) {
    (new pages.Search_Page()).setSearchDetails(
        	lastName
         , 	cardNum
         , 	accNumber)
}

def static "pages.Search_Page.verifyNoPaymentsFound"() {
    (new pages.Search_Page()).verifyNoPaymentsFound()
}

def static "pages.Search_Page.switchToWindow"() {
    (new pages.Search_Page()).switchToWindow()
}

def static "pages.Search_Page.validateApprovedTransactionsInReports"(
    	String paymentId	
     , 	Object cardtype	
     , 	String paymentAmount	) {
    (new pages.Search_Page()).validateApprovedTransactionsInReports(
        	paymentId
         , 	cardtype
         , 	paymentAmount)
}

def static "pages.Bureau_Login_Page.windowSwitching"() {
    (new pages.Bureau_Login_Page()).windowSwitching()
}

def static "pages.Bureau_Login_Page.clickingElement"(
    	TestObject object	) {
    (new pages.Bureau_Login_Page()).clickingElement(
        	object)
}

def static "pages.Bureau_Login_Page.loginToReportsSite"(
    	String username	
     , 	String password	
     , 	String accessCode	) {
    (new pages.Bureau_Login_Page()).loginToReportsSite(
        	username
         , 	password
         , 	accessCode)
}

def static "pages.Bureau_Login_Page.verifyPaymentInRealTimeMonitor"(
    	TestObject object	
     , 	String referenceNum	
     , 	String lastName	
     , 	String firstName	
     , 	String paymentID	) {
    (new pages.Bureau_Login_Page()).verifyPaymentInRealTimeMonitor(
        	object
         , 	referenceNum
         , 	lastName
         , 	firstName
         , 	paymentID)
}

def static "pages.Payment_Page.getRowAndColumn"(
    	TestObject nextObj	
     , 	TestObject numberOfPages	
     , 	String paymentId	) {
    (new pages.Payment_Page()).getRowAndColumn(
        	nextObj
         , 	numberOfPages
         , 	paymentId)
}

def static "pages.Payment_Page.verifyPaymentIdRecord"(
    	TestObject nextObj	
     , 	TestObject numberOfPages	
     , 	String PaymentId	) {
    (new pages.Payment_Page()).verifyPaymentIdRecord(
        	nextObj
         , 	numberOfPages
         , 	PaymentId)
}

def static "pages.Payment_Page.verifyAllRecordsFilteredByCardNumber"(
    	TestObject nextObj	
     , 	TestObject numberOfPages	
     , 	String creditCardNumber	) {
    (new pages.Payment_Page()).verifyAllRecordsFilteredByCardNumber(
        	nextObj
         , 	numberOfPages
         , 	creditCardNumber)
}

def static "pages.Payment_Page.getPaymentIdColumnCount"(
    	String columnName	) {
    (new pages.Payment_Page()).getPaymentIdColumnCount(
        	columnName)
}

def static "pages.Payment_Page.setPersonalDetails"(
    	String firstname	
     , 	String last	
     , 	String telephone	) {
    (new pages.Payment_Page()).setPersonalDetails(
        	firstname
         , 	last
         , 	telephone)
}

def static "pages.Payment_Page.setLocationDetails"(
    	String address	
     , 	String zipCode	) {
    (new pages.Payment_Page()).setLocationDetails(
        	address
         , 	zipCode)
}

def static "pages.Payment_Page.setCardDetails"(
    	String cardNum	
     , 	String securityCode	
     , 	String expYear	
     , 	String expMonth	) {
    (new pages.Payment_Page()).setCardDetails(
        	cardNum
         , 	securityCode
         , 	expYear
         , 	expMonth)
}

def static "pages.Payment_Page.setAmount"(
    	String index	
     , 	String paymentAmount	
     , 	String comments	
     , 	String referenceNum	) {
    (new pages.Payment_Page()).setAmount(
        	index
         , 	paymentAmount
         , 	comments
         , 	referenceNum)
}

def static "pages.Payment_Page.verifyUserDetails"(
    	String firstName	
     , 	String lastName	
     , 	String emailAddress	
     , 	String cardType	
     , 	String accountType	) {
    (new pages.Payment_Page()).verifyUserDetails(
        	firstName
         , 	lastName
         , 	emailAddress
         , 	cardType
         , 	accountType)
}

def static "pages.Payment_Page.verifyCardPaymentApproval"(
    	String referenceNum	) {
    (new pages.Payment_Page()).verifyCardPaymentApproval(
        	referenceNum)
}

def static "pages.Payment_Page.verifyECheckPaymentApproval"(
    	String referenceNum	) {
    (new pages.Payment_Page()).verifyECheckPaymentApproval(
        	referenceNum)
}

def static "pages.Payment_Page.verifyPaymentDetailsInReceipt"(
    	String paymentId	) {
    (new pages.Payment_Page()).verifyPaymentDetailsInReceipt(
        	paymentId)
}

def static "pages.Payment_Page.setElectronicCheckInformation"(
    	TestObject testObject	
     , 	TestObject testObject2	
     , 	String routingNumber	
     , 	String checkingAccNum	) {
    (new pages.Payment_Page()).setElectronicCheckInformation(
        	testObject
         , 	testObject2
         , 	routingNumber
         , 	checkingAccNum)
}

def static "pages.Payment_Page.clickOnElement"(
    	TestObject object	) {
    (new pages.Payment_Page()).clickOnElement(
        	object)
}

def static "utilities.SafeActions.openBrowser"(
    	String url	
     , 	int[] optionWaitTime	) {
    (new utilities.SafeActions()).openBrowser(
        	url
         , 	optionWaitTime)
}

def static "utilities.SafeActions.highLightElement"(
    	TestObject testObject	
     , 	int timeout	) {
    (new utilities.SafeActions()).highLightElement(
        	testObject
         , 	timeout)
}

def static "utilities.SafeActions.safeCheck"(
    	TestObject testObject	
     , 	String friendlyWebElementName	
     , 	int[] optionWaitTime	) {
    (new utilities.SafeActions()).safeCheck(
        	testObject
         , 	friendlyWebElementName
         , 	optionWaitTime)
}

def static "utilities.SafeActions.safeType"(
    	TestObject testObject	
     , 	String text	
     , 	String friendlyWebElementName	
     , 	int[] optionWaitTime	) {
    (new utilities.SafeActions()).safeType(
        	testObject
         , 	text
         , 	friendlyWebElementName
         , 	optionWaitTime)
}

def static "utilities.SafeActions.getAttribute"(
    	TestObject testObject	) {
    (new utilities.SafeActions()).getAttribute(
        	testObject)
}

def static "utilities.SafeActions.safeClick"(
    	TestObject testObject	
     , 	String friendlyWebElementName	
     , 	int[] optionWaitTime	) {
    (new utilities.SafeActions()).safeClick(
        	testObject
         , 	friendlyWebElementName
         , 	optionWaitTime)
}

def static "utilities.SafeActions.safeGetText"(
    	TestObject testObject	
     , 	String friendlyWebElementName	
     , 	int[] optionWaitTime	) {
    (new utilities.SafeActions()).safeGetText(
        	testObject
         , 	friendlyWebElementName
         , 	optionWaitTime)
}

def static "utilities.SafeActions.safeSelectOptionInDropdownByVisibleText"(
    	TestObject testObject	
     , 	String sVisibleTextOptionToSelect	
     , 	String friendlyWebElementName	
     , 	int[] optionWaitTime	) {
    (new utilities.SafeActions()).safeSelectOptionInDropdownByVisibleText(
        	testObject
         , 	sVisibleTextOptionToSelect
         , 	friendlyWebElementName
         , 	optionWaitTime)
}

def static "utilities.SafeActions.getAttributeValue"(
    	TestObject testObject	
     , 	String attribute	
     , 	String friendlyWebElementName	
     , 	int[] optionWaitTime	) {
    (new utilities.SafeActions()).getAttributeValue(
        	testObject
         , 	attribute
         , 	friendlyWebElementName
         , 	optionWaitTime)
}
