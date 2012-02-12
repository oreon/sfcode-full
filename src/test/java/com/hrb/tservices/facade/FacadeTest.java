package com.hrb.tservices.facade;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Assumes a running server
 * @author Owner
 *
 */
public class FacadeTest {
	
	String secKey ;
	
	public static final String URL = "http://localhost:8080/taxservices";
	public static final String PATH = "/seam/resource/rest";
	HttpClient client;
	
	//@BeforeClass
	public void setup() throws HttpException, IOException{
		client = new HttpClient();
		PostMethod post = new PostMethod(URL + PATH + "/authenticatorServiceRestService/login");
        NameValuePair[] data = {
          new NameValuePair("username", "TD"),
          new NameValuePair("password", "td")
        };
        post.setRequestBody(data);
        client.executeMethod(post);
        secKey = post.getResponseBodyAsString();
		System.out.println("seckey is " + secKey);
		
	}
	
	//@Test
	public void testViewOfferInvite() throws HttpException, IOException{
		//HttpClient client = new HttpClient();
		GetMethod method = new GetMethod(
				URL + PATH + "/getTaxNewsRestService/getCategories?securityKey=" + 
					secKey + "&language=english");
		int status = client.executeMethod(method);

		Assert.assertEquals(200, status);

		String responseBodyAsString = method.getResponseBodyAsString();
		System.out.println(responseBodyAsString);
	}
	
	//@Test
	public void testGetCategories() throws HttpException, IOException{
		//HttpClient client = new HttpClient();
		GetMethod method = new GetMethod(
				URL + PATH + "/fileMyTaxesRestService/viewOfferInvite?securityKey=" + 
					secKey + "&language=english");
		int status = client.executeMethod(method);

		Assert.assertEquals(200, status);

		String responseBodyAsString = method.getResponseBodyAsString();
		System.out.println(responseBodyAsString);
	}
	
	
	//@AfterClass
	public void tearDown() throws HttpException, IOException{
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod("http://localhost:8080/taxservices/seam/resource/rest/authenticatorServiceRestService/logout");
        
       // post.setRequestBody(data);
        client.executeMethod(post);
	}

}
