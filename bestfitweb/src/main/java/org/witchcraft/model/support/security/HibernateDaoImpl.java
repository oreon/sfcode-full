package org.witchcraft.model.support.security;

import java.util.List;

import javax.persistence.Query;

import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.witchcraft.model.support.dao.BaseDao;

public class HibernateDaoImpl extends BaseDao<UserDetails> implements
		UserDetailsService{
	
	private static Logger logger = Logger.getLogger(HibernateDaoImpl.class);

    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException,
                                                              DataAccessException {
        if(name == null) {
            throw new IllegalArgumentException("Name is null");
        }
        
        String qryString = "select c from User c where c.username = ?1";
		Query query = entityManager.createQuery(qryString).setParameter(1, name);
		
        List result = query.getResultList();

        if(result.size() == 0) {
            throw new UsernameNotFoundException("User not found");
        }
        
        logger.info("Returning user " + result.get(0));
        
        return (UserDetails) result.get(0);
    }
}