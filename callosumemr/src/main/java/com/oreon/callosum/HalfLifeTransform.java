package com.oreon.callosum;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.jackrabbit.extractor.HTMLTextExtractor;
import org.jsoup.nodes.Element;
import org.w3c.dom.Document;

public class HalfLifeTransform {

	public static void main(String[] args) throws HttpException, IOException {
		String[] arr = {
				"66-84 minutes",
				"288 hours",
				"19 to 48 hours (however, stored in fat deposits in body for prolonged periods)"

				,
				"15-20 days",
				"Several weeks",
				"10 minutes or less",
				"5 &amp;plusmn; 1 hours",

				"Rapid distribution half-life of about 2 minutes and an elimination half-life of about 9 minutes",
				"Plasma half-life is approximately 10 to 20 hours, terminal half-life is 21-100 hours" };

		String str = "DB03140";

		// System.out.println(Long.parseLong(str.substring(2)));

		
	}
}
