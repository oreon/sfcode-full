package com.hrb.tservices.websvc.message;

import java.util.List;

import javax.jws.WebService;

import com.hrb.tservices.domain.department.Partner;

@WebService
public interface PartnerWebService {

	public Partner loadById(Long id);

	public List<Partner> findByExample(Partner examplePartner);

	public void save(Partner partner);

}
