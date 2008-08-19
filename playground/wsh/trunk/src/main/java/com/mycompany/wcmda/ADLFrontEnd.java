package com.mycompany.wcmda;

import javax.jws.WebService;

import org.apache.log4j.Logger;

import com.cc.dto.BaseDTO;

@WebService
public interface ADLFrontEnd<T> {
	
	public void save(T object);
	
	public T findById(String id);

}
