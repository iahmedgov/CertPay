package internal

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.main.TestCaseMain


/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */
public class GlobalVariable {
     
    /**
     * <p></p>
     */
    public static Object certpay_url
     
    /**
     * <p></p>
     */
    public static Object pageLoadTime
     
    /**
     * <p></p>
     */
    public static Object mediumWait
     
    /**
     * <p></p>
     */
    public static Object HighLightElements
     
    /**
     * <p></p>
     */
    public static Object userName
     
    /**
     * <p></p>
     */
    public static Object password
     
    /**
     * <p></p>
     */
    public static Object accessCode
     
    /**
     * <p></p>
     */
    public static Object Consumer_Url
     
    /**
     * <p></p>
     */
    public static Object Quicksti_Url
     
    /**
     * <p></p>
     */
    public static Object bureau_code
     
    /**
     * <p></p>
     */
    public static Object teller_id
     
    /**
     * <p></p>
     */
    public static Object VISA
     
    /**
     * <p></p>
     */
    public static Object DiscoverCard
     
    /**
     * <p></p>
     */
    public static Object AMEX_Card
     
    /**
     * <p></p>
     */
    public static Object MasterCard
     
    /**
     * <p></p>
     */
    public static Object emailAddress
     
    /**
     * <p></p>
     */
    public static Object zipCode
     
    /**
     * <p></p>
     */
    public static Object telephone
     
    /**
     * <p></p>
     */
    public static Object firstName
     
    /**
     * <p></p>
     */
    public static Object lastName
     
    /**
     * <p></p>
     */
    public static Object Reports_url
     

    static {
        try {
            def selectedVariables = TestCaseMain.getGlobalVariables("default")
			selectedVariables += TestCaseMain.getGlobalVariables(RunConfiguration.getExecutionProfile())
            selectedVariables += RunConfiguration.getOverridingParameters()
    
            certpay_url = selectedVariables['certpay_url']
            pageLoadTime = selectedVariables['pageLoadTime']
            mediumWait = selectedVariables['mediumWait']
            HighLightElements = selectedVariables['HighLightElements']
            userName = selectedVariables['userName']
            password = selectedVariables['password']
            accessCode = selectedVariables['accessCode']
            Consumer_Url = selectedVariables['Consumer_Url']
            Quicksti_Url = selectedVariables['Quicksti_Url']
            bureau_code = selectedVariables['bureau_code']
            teller_id = selectedVariables['teller_id']
            VISA = selectedVariables['VISA']
            DiscoverCard = selectedVariables['DiscoverCard']
            AMEX_Card = selectedVariables['AMEX_Card']
            MasterCard = selectedVariables['MasterCard']
            emailAddress = selectedVariables['emailAddress']
            zipCode = selectedVariables['zipCode']
            telephone = selectedVariables['telephone']
            firstName = selectedVariables['firstName']
            lastName = selectedVariables['lastName']
            Reports_url = selectedVariables['Reports_url']
            
        } catch (Exception e) {
            TestCaseMain.logGlobalVariableError(e)
        }
    }
}
