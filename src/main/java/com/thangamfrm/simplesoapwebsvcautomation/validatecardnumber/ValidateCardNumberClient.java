package com.thangamfrm.simplesoapwebsvcautomation.validatecardnumber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.SoapMessage;

import com.thangamfrm.simplesoapwebsvcautomation.validatecardnumber.model.ValidateCardNumber;
import com.thangamfrm.simplesoapwebsvcautomation.validatecardnumber.model.ValidateCardNumberResponse;

public class ValidateCardNumberClient  {

	@Autowired
	protected WebServiceTemplate webServiceTemplate;

	public ValidateCardNumberResponse validateCardNumber(ValidateCardNumber vcn) {
		return (ValidateCardNumberResponse) webServiceTemplate.marshalSendAndReceive(vcn, new WebServiceMessageCallback() {
			public void doWithMessage(WebServiceMessage message) {
				((SoapMessage)message).setSoapAction("http://www.webservicex.net/ValidateCardNumber");
			}
		});
    }

}
