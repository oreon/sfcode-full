package com.mycompany.wcmda;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jws.WebService;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.cc.core.IdGenerator;
import com.cc.dto.BaseDTO;
import com.cc.dto.OneDto;

@WebService(endpointInterface = "com.mycompany.wcmda.ADLFrontEnd", serviceName = "adlService")
public class ADLFrontEndImpl<T> {
	private static final Logger logger = Logger
			.getLogger(ADLFrontEndImpl.class);
	
	
	static final String[] specialKeys = { "_action", "successUrl", "errorUrl",
			"id" };
	static final String[] immutableCaseKeys = { "successUrl", "errorUrl"};
	
	static final List<String> specialKeysList = Arrays.asList(specialKeys);
	static final List<String> immutableCaseKeysList = Arrays.asList(immutableCaseKeys);
	
	private static int  count = 0;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		BaseDTO dto = new OneDto();
		((OneDto)dto).setLastName("hicks");
		
		Field[] fields = dto.getClass().getDeclaredFields();
		Map<String, Object> mapRequest = new HashMap<String, Object>();
		
		new ADLFrontEndImpl().populateFromFields(dto, mapRequest);
		

	}

	private static void sendData(String data)
			throws MalformedURLException, IOException {
		URL url = new URL(
				"http://localhost:8080/pocCivlit/forms/DataBaseFormHandler");
		URLConnection conn = url.openConnection();
		conn.setDoOutput(true);

		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(conn
				.getOutputStream()));
		out.write(data);
		out.flush();
		out.close();
		BufferedReader in = new BufferedReader(new InputStreamReader(conn
				.getInputStream()));

		String response;
		while ((response = in.readLine()) != null) {
			System.out.println(response);
		}
		in.close();
	}

	static String mapToString(Map<String, Object> map, String tableName) {
		
		StringBuffer buffer = new StringBuffer();
		Set<String> keys = map.keySet();
		int cnt = 0;
		for (String key : keys) {
			String upperCaseKey = getUpperCase(key);
			buffer.append(getPrefix(key,  tableName) + upperCaseKey + "=" + map.get(key));
			if (++cnt < keys.size()) {
				buffer.append("&");
			}
		}
		System.out.println("returning " + buffer.toString());
		return buffer.toString();

	}

	private static String getPrefix(String field, String tableName) {
		if (!immutableCaseKeysList.contains(field))
			return  "table." + tableName + "." ;
		return StringUtils.EMPTY;
	}

	private static String getUpperCase(String key) {
		if(!specialKeysList.contains(key))
			return key.toUpperCase();
		return key;
	}

	public T findById(String id) {
		return null;
	}


	public void save(T dto) {
		Map<String, Object> mapRequest = new HashMap<String, Object>();
		try {
			performSetup(mapRequest);
			BaseDTO bdto = (BaseDTO)dto;
			
			String id = bdto.getId();
			
			if(id == null){
				mapRequest.put("_action", "insert");
				id = IdGenerator.newId(); 
			}

			populateFromFields(bdto, mapRequest);

			String data = mapToString( mapRequest, bdto.getTableName());
			sendData(data);
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
	}

	private void performSetup(Map<String, Object> mapRequest) {
		mapRequest.put("_action", "update");
		mapRequest.put("successUrl", "success.jsp");
		mapRequest.put("errorUrl", "error.jsp");
	}

	private void populateFromFields(BaseDTO dto, Map<String, Object> mapRequest) {
		Method[] methods = dto.getClass().getDeclaredMethods();
		for (Method method : methods) {
			String methodName = method.getName();
			
			if(methodName.startsWith("get")){
				try {
					Object result = method.invoke(dto);
					String fldName = fieldNameFromMethod(methodName);
					mapRequest.put(fldName, result);
					System.out.println( result + " :" + fldName);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}
	
	
	public void saveOld(BaseDTO dto) {
		try {

			Map<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("_action", "insert");
			mapRequest.put("firstName", "VanGogh");
			mapRequest.put("lastName", "Henry" + ++count);
			mapRequest.put("successUrl", "success.jsp");
			mapRequest.put("errorUrl", "error.jsp");
			mapRequest.put("id", com.cc.core.IdGenerator.newId());
			
			String data = mapToString( mapRequest, "cc_poc");
			sendData(data);
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
	}
	
	protected String fieldNameFromMethod(String methodName){
		if(methodName.startsWith("get")){
			return methodName.substring(3);//what if method name is only 3 ?
		}
		
		return null;
	}

	public void save(BaseDTO object) {
		// TODO Auto-generated method stub
		
	}
}
