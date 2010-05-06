package com.restfully.shop.test;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;


/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public class TestRestful
{
   private static class HttpPatch extends HttpPost
   {
      public HttpPatch(String s)
      {
         super(s);
      }

      public String getMethod()
      {
         return "PATCH";
      }
   }

   @Test
   public void testCustomerResource() throws Exception
   {
     System.out.println("*** Create a new Customer ***");
      String newCustomer = "1";
      DefaultHttpClient client = new DefaultHttpClient();

        //  HttpPost post = new HttpPost("http://localhost:9095/customers");
    // to deploy on jboss
    HttpPost post = new HttpPost("http://localhost:8080/ex04_1_b/addCust/1");
  //  HttpPost post = new HttpPost("http://localhost:8080/ex04_1_b/1");
      StringEntity entity = new StringEntity(newCustomer);
      entity.setContentType("application/xml");
      post.setEntity(entity);
      HttpClientParams.setRedirecting(post.getParams(), false);
      HttpResponse response = client.execute(post);

      Assert.assertEquals(200, response.getStatusLine().getStatusCode());
      showResponse(response);
     /* System.out.println("**** After Update ***");
      
      HttpGet get = new HttpGet("http://localhost:8080/ex04_1_b/getCustomer/100");
      response = client.execute(get);
      Assert.assertEquals(200, response.getStatusLine().getStatusCode());

      System.out.println("Content-Type: " + response.getEntity().getContentType());
      showResponse(response);*/
   }
   
   private void showResponse(HttpResponse response)throws Exception{
	      System.out.println("Content-Type: " + response.getEntity().getContentType());
	      BufferedReader reader = new BufferedReader(new
	              InputStreamReader(response.getEntity().getContent()));

	      String line = reader.readLine();
	      while (line != null)
	      {
	         System.out.println(line);
	         line = reader.readLine();
	      }
}
}
