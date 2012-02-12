package com.hrb.tservices.facade;

import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.tools.ant.filters.StringInputStream;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.witchcraft.exceptions.ContractViolationException;
import org.xml.sax.InputSource;

import com.hrb.tservices.domain.offices.Office;
import com.hrb.tservices.domain.offices.OfficeLocatorMetrics;
import com.hrb.tservices.dtos.OfficeLocation;
import com.hrb.tservices.dtos.OfficeLocationList;
import com.hrb.tservices.dtos.Status;
import com.hrb.tservices.web.action.offices.OfficeAction;
import com.hrb.tservices.web.action.offices.OfficeLocatorMetricsAction;
import com.sun.org.apache.commons.beanutils.BeanUtils;

@Name("officeLocator")
public class OfficeLocator extends OfficeLocatorBase {
	private static final int ArrayList = 0;

	HttpClient client;

	@In(create = true)
	OfficeAction officeAction;
	
	@In(create = true)
	OfficeLocatorMetricsAction officeLocatorMetricsAction;
	
	public static final Integer MAX_RESULTS = 5;

	public static MessageFormat messageFormat = new MessageFormat(
			"http://maps.googleapis.com/maps/api/distancematrix/xml?origins={0}&destinations={1}&sensor=false");

	


	// @Override
	public List<OfficeLocation> locateOfficeByZipl(String zip, List<Office> offices) {

		List<OfficeLocation> results = new ArrayList<OfficeLocation>();
		List<String> args = new ArrayList<String>();
		args.add(zip);
		client = new HttpClient();

		for (Office office : offices) {
			OfficeLocation OfficeLocation = new OfficeLocation();
			try {
				BeanUtils.copyProperties(OfficeLocation, office);
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			if (args.size() < 2)
				args.add(office.getPostalCode());
			else
				args.set(1, office.getPostalCode());

			String url = messageFormat.format(args.toArray());
			GetMethod method = new GetMethod(url);
			try {
				int status = client.executeMethod(method);
				String responseBody = method.getResponseBodyAsString();

				Integer distance = getDistance(responseBody);
				if (distance != null) {
					OfficeLocation.setDistance(distance);
					results.add(OfficeLocation);
				}
			} catch (Exception e) {
				log.error("An error occured ", e);
			}
		}

		Collections.sort(results, new SortByDistanceComparator());
		return results;
	}

	private Integer getDistance(String responseBody) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		Integer distance = null;

		try {
			XPathFactory factory = XPathFactory.newInstance();

			XPath xpath = factory.newXPath();
			InputSource inputXml = new InputSource(new StringInputStream(
					responseBody));
			String value = (String) xpath.evaluate("//distance/value/text()",
					inputXml);
			InputSource inputXml2 = new InputSource(new StringInputStream(
					responseBody));
			String origin = (String)xpath.evaluate("//origin_address/text()", inputXml2);
			System.out.println("ORIGIN: " + origin);
			if (!StringUtils.isEmpty(value)) {
				distance = Integer.parseInt(value);
			}
			// System.out.println(value);

		} catch (Exception e) {
			log.error("an error occured getting the distance value", e);
			e.printStackTrace();
		}
		return distance;
	}

	public static void main(String[] args) {
		try {
			new OfficeLocator().parseres("T1A2Z9");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void parseres(String dest) {
		client = new HttpClient();
		List<String> args = new ArrayList<String>();
		args.add("t1a2p9");
		args.add(dest);
		String url = messageFormat.format(args.toArray());
		GetMethod method = new GetMethod(url);
		try {
			int status = client.executeMethod(method);
			String responseBody = method.getResponseBodyAsString();
			getDistance(responseBody);
			// OfficeLocation.setDistance(getDistance(responseBody));

			// results.add(OfficeLocation);
		} catch (Exception e) {
			e.printStackTrace();
			// log.error("An error occured ", e);
		}
	}
	
	


	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	protected OfficeLocationList doLocateOfficeByAddress(String address,
			String language, String city, String province, String securityKey,
			Integer maxResults) {
		
		maxResults = maxResults == null ? MAX_RESULTS: maxResults;
		
		// String zipstart = postalCode.substring(0, 2);
		String qry = "Select o from Office o where o.city = ?1";
		
		address = URLEncoder.encode(address);
		city = URLEncoder.encode(city);
		province = URLEncoder.encode(province);
		
		address = address.replace(" ", "");
		
		List<Office> offices = entityManager.createQuery(qry).setParameter(1,
				city).setMaxResults(maxResults).getResultList();

		List<OfficeLocation> officesLookedup =  locateOfficeByZipl(address + "," + city + "," + province, offices);
		OfficeLocationList officeLocationList = new OfficeLocationList();
		officeLocationList.setOfficeLocations(officesLookedup);
		updateMetrics(city, null);
		return officeLocationList;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected OfficeLocationList doLocateOfficeByPostalCode(String postalCode,
			String language, String securityKey, Integer maxResults) {
		
		maxResults = maxResults == null ? MAX_RESULTS: maxResults;
		
		if(maxResults == null)
			maxResults = DEFAULT_RECORDS;
		if(postalCode.length() < 6)
			throw new ContractViolationException("zip is too short");
		String zipstart = postalCode.substring(0, 2);
		String qry = "Select o from Office o where o.postalCode like ?1";
		
		List<Office> offices = entityManager.createQuery(qry).setParameter(1,
				zipstart + "%").setMaxResults(maxResults).getResultList();
		
		List<OfficeLocation> officesLookedup = (List<OfficeLocation>) locateOfficeByZipl(postalCode, offices);
		
		OfficeLocationList officeLocationList = new OfficeLocationList();
		officeLocationList.setOfficeLocations(officesLookedup);
		
		updateMetrics(null, postalCode);
		return officeLocationList;
	}

	
	protected void updateMetrics(String lookupCity, String lookupZip ){
		OfficeLocatorMetrics officeLocatorMetrics = new OfficeLocatorMetrics();
		initMetrics(officeLocatorMetrics);
		if(lookupCity != null )
			officeLocatorMetrics.setLookupCity(lookupCity);
		else
			officeLocatorMetrics.setLookupPostalCode(lookupZip);
		officeLocatorMetricsAction.persist(officeLocatorMetrics);
	}

}

class SortByDistanceComparator implements Comparator<OfficeLocation> {

	public int compare(OfficeLocation o1, OfficeLocation o2) {
		// if(o1.getDistance() == null) o1.setDistance(0)
		return o1.getDistance().compareTo(o2.getDistance());
	}
}