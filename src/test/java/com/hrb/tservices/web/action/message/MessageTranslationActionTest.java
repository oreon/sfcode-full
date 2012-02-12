

package com.hrb.tservices.web.action.message;


	import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.jboss.seam.mock.EnhancedMockHttpServletRequest;
import org.jboss.seam.mock.EnhancedMockHttpServletResponse;
import org.jboss.seam.mock.ResourceRequestEnvironment;
import org.jboss.seam.mock.ResourceRequestEnvironment.Method;
import org.jboss.seam.mock.ResourceRequestEnvironment.ResourceRequest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class MessageTranslationActionTest extends MessageTranslationActionTestBase{
	
	 ResourceRequestEnvironment sharedEnvironment;


	   @BeforeClass
	   public void prepareSharedEnvironment() throws Exception {

	       sharedEnvironment = new ResourceRequestEnvironment(this) {

	            @Override
	            public Map<String, Object> getDefaultHeaders() {

	               return new HashMap<String, Object>() {{

	                   put("Accept", "text/plain");

	               }};
	            }
	         };
	   }
	   
	 //  @Test 
	   public void mytest() throws HttpException, IOException{
		   HttpClient client = new HttpClient();
	        GetMethod method = new GetMethod(
	                "http://localhost:8080/taxservices/seam/resource/rest/fileMyTaxesRestService/viewOfferInvite?securityKey=2yxzaeyejyig&language=english");
	        int status = client.executeMethod(method);

	        Assert.assertEquals(200, status);
	        
	        String responseBodyAsString = method.getResponseBodyAsString();
	        
	        System.out.println(responseBodyAsString);
	   }
	
	   
	   //@Test
	   public void testviewoffer() throws Exception{

	      //Not shared: new ResourceRequest(new ResourceRequestEnvironment(this), Method.GET, "/my/relative/uri)
	      new ResourceRequest(sharedEnvironment, Method.GET, "/fileMyTaxesRestService/viewOfferInvite"){

	         @Override
	         protected void prepareRequest(EnhancedMockHttpServletRequest request)
	         {
	            request.addQueryParameter("foo", "123");
	            request.addHeader("Accept-Language", "en_US, de");
	         }


	         @Override
	         protected void onResponse(EnhancedMockHttpServletResponse response){
	            assert response.getStatus() == 200;
	            assert response.getContentAsString().equals("foobar");
	         }


	      }.run();

	   }
}
