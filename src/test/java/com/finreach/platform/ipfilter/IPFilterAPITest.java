package com.finreach.platform.ipfilter;

import static org.junit.Assert.assertEquals;


import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import org.junit.Test;



public class IPFilterAPITest {
	
	@Test
	public void testcreateIPFilterConfig()
	  throws Exception {
	  
	     // GivenE
		 JSONObject obj = new JSONObject();

	      obj.put("ipAddress", "10.11.12.20");
	      obj.put("filterProtocol","HTTP");
	      
	      HttpClient   httpClient    = HttpClientBuilder.create().build();
	      HttpPost     post          = new HttpPost("http://localhost:8080/enterprise.framework.ipfilter/v1/ipfilters");
	      StringEntity params = new StringEntity(obj.toString());
	      post.addHeader("content-type", "application/json");
	      post.setEntity(params);
	      HttpResponse httpResponse = httpClient.execute(post);
	 
	 
	      System.out.println(httpResponse);
		   // Then
		   assertEquals(
		     httpResponse.getStatusLine().getStatusCode(),
		     HttpStatus.SC_OK);
	}
	
	/*@Test
	public void testExecutivePerformanceAPIInvalidRequest()
	  throws Exception {
	  
	   // GivenE
		JSONObject obj = new JSONObject();

	      obj.put("number_of_calls", "30");
	      obj.put("je", new String[]{"1,2,3,4","5,7,6,4,6","5,8,7,5,10","7,5,6,14,6","10,4,9,5,12","6,10,11,4,6"});
	      obj.put("manager", new String("20,12,25,13,20,3,3,3,9,2,7,1,7,11,10"));
	   HttpUriRequest request = new HttpGet( "https://localhost:8080/executives/performance" + obj );
	 
	   // When
	   HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
	 
	   // Then
	   assertEquals(
			     httpResponse.getStatusLine().getStatusCode(),
			     HttpStatus.SC_BAD_REQUEST);
	}
	*/
	@Test
	public void testGetIPFilterConfig()
	  throws Exception {
	  
	   HttpUriRequest request = new HttpGet( "https://localhost:8080/enterprise.framework.ipfilter/v1/ipfilters" );
	 
	   // When
	   HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
	 
	   // Then
	   assertEquals(
			     httpResponse.getStatusLine().getStatusCode(),
			     HttpStatus.SC_BAD_REQUEST);
	}
	
}
