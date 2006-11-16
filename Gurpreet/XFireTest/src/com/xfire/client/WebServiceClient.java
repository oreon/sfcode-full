package com.xfire.client;

import java.net.MalformedURLException;



import org.codehaus.xfire.XFire;
import org.codehaus.xfire.XFireFactory;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;

import com.xfire.example.IBankingService;

public class WebServiceClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			System.out.println(callWebService("001", "002", 200.00, "$"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String callWebService(String fromAccount, String toAccount,
			double amount, String currency) throws MalformedURLException,
			Exception {

		// Create a metadata of the service
		Service serviceModel = new ObjectServiceFactory()
				.create(IBankingService.class);
		System.out.println("callSoapServiceLocal(): got service model.");

		// Create a proxy for the deployed service
		XFire xfire = XFireFactory.newInstance().getXFire();
		XFireProxyFactory factory = new XFireProxyFactory(xfire);

		String serviceUrl = "http://localhost:8080/XfireTest/services/Banking";

		IBankingService client = null;
		try {
			client = (IBankingService) factory.create(serviceModel, serviceUrl);
		} catch (MalformedURLException e) {
			System.out.println("WsClient.callWebService(): EXCEPTION: " + e.toString());
		}

		// Invoke the service
		String serviceResponse = "";
		try {
			serviceResponse = client.transferFunds(fromAccount, toAccount,
					amount, currency);
		} catch (Exception e) {
			System.out.println("WsClient.callWebService(): EXCEPTION: " + e.toString());
			serviceResponse = e.toString();
		}
		System.out.println("WsClient.callWebService(): status=" + serviceResponse);

		// Return the response
		return serviceResponse;
	}

}
