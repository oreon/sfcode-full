package com.hrb.tservices.web.action.message;

import java.net.URISyntaxException;

import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.jboss.resteasy.plugins.server.resourcefactory.POJOResourceFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.hrb.tservices.domain.offices.Office;
import com.hrb.tservices.web.action.facade.FileMyTaxesService;
import com.thoughtworks.xstream.XStream;

public class MarketingMessageActionTest extends MarketingMessageActionTestBase {

	@BeforeClass
	public void setUp() throws URISyntaxException {
		// Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();
		// Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();
		/*
		 * TJWSEmbeddedJaxrsServer tjws = new TJWSEmbeddedJaxrsServer();
		 * tjws.setPort(7070);
		 * 
		 * // tjws.setDispatcher(dispatcher);
		 * 
		 * Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();
		 * POJOResourceFactory noDefaults = new POJOResourceFactory(
		 * FileMyTaxesRestService.class);
		 * dispatcher.getRegistry().addResourceFactory(noDefaults);
		 * tjws.start();
		 */
	}

	@Test
	public void mytest() {
		
		XStream xstream = new XStream();
		//MarketingMessage message = em.find(MarketingMessage.class, 1L);
		//message.getMessageTranslations().iterator().next();
		/*
		TaxNews news = em.find(TaxNews.class, 1L);
		news.getTaxNewsTranslations().iterator().next();
		System.out.println(xstream.toXML(news));
		
		FaqQuestion question = em.find(FaqQuestion.class, 1L);
		question.getQuestionTranslations().iterator().next();
		System.out.println(xstream.toXML(question));*/
		
		Office office = em.find(Office.class, 6L);
		System.out.println(xstream.toXML(office));
	}

	// @Test
	public void testfetchHistory() throws URISyntaxException {
		Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();
		POJOResourceFactory noDefaults = new POJOResourceFactory(
				FileMyTaxesService.class);
		dispatcher.getRegistry().addResourceFactory(noDefaults);
		MockHttpRequest request = MockHttpRequest
				.get("/seam/resource/rest/fileMyTaxesRestService/viewOfferInvite?securityKey=3456&language=french");
		MockHttpResponse response = new MockHttpResponse();

		dispatcher.invoke(request, response);
		System.out.println(response.getErrorMessage());
		System.out.println(response.getContentAsString());

		// Assert.assertEquals(..);
	}

}
