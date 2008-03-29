package com.oreon.kgauge.service;

import com.oreon.kgauge.domain.GrantedAuthority;
import com.oreon.kgauge.dao.GrantedAuthorityDao;
import org.witchcraft.model.support.service.BaseService;

import javax.jws.WebParam;
import javax.jws.WebService;

/** The Service interface for entity - GrantedAuthority
 * @author - Witchcraft Generated {Do not Modify } 
 * 
 */
@WebService
public interface GrantedAuthorityService
		extends
			GrantedAuthorityDao,
			BaseService<GrantedAuthority> {

}
