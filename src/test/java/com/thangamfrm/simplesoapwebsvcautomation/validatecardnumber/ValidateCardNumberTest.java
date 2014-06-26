package com.thangamfrm.simplesoapwebsvcautomation.validatecardnumber;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ws.soap.client.SoapFaultClientException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.thangamfrm.simplesoapwebsvcautomation.validatecardnumber.model.ObjectFactory;
import com.thangamfrm.simplesoapwebsvcautomation.validatecardnumber.model.ValidateCardNumber;
import com.thangamfrm.simplesoapwebsvcautomation.validatecardnumber.model.ValidateCardNumberResponse;

public class ValidateCardNumberTest extends Assert {

	protected ValidateCardNumberClient client;

	protected ApplicationContext applicationContext;

	@BeforeClass(alwaysRun = true)
	protected void setUp() {
		applicationContext = new ClassPathXmlApplicationContext( "applicationContext.xml" );
		client = applicationContext.getBean("validateCardNumberClient", ValidateCardNumberClient.class);
	}

	@Test
	public void testValidateCardNumberInvalidVisaNumber() {
		ValidateCardNumber vcn = new ObjectFactory().createValidateCardNumber();
		vcn.setCardNumber("4111111111111111");
		vcn.setCardType("VISA");
		ValidateCardNumberResponse vcnr = client.validateCardNumber(vcn);
		assertEquals(vcnr.getValidateCardNumberResult(), "This Credit Card number is not valid.", 
				"Incorrect validate card number response!");
	}

	@Test
	public void testValidateCardNumberInvalidMasterCardNumber() {
		ValidateCardNumber vcn = new ObjectFactory().createValidateCardNumber();
		vcn.setCardNumber("4929731375864022");
		vcn.setCardType("MASTERCARD");
		ValidateCardNumberResponse vcnr = client.validateCardNumber(vcn);
		assertEquals(vcnr.getValidateCardNumberResult(), "This Credit Card number is not valid.", 
				"Incorrect validate card number response!");
	}

	@Test(expectedExceptions = SoapFaultClientException.class)
	public void testValidateCardNumberSoapFault() {
		ValidateCardNumber vcn = new ObjectFactory().createValidateCardNumber();
		vcn.setCardNumber("");
		vcn.setCardType("");
		client.validateCardNumber(vcn);
	}

}
