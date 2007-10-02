package org.witchcraft.model.support.security;

import java.util.List;

import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HibernateDaoImpl extends HibernateDaoSupport implements
		UserDetailsService{
	
	private static Logger logger = Logger.getLogger(HibernateDaoImpl.class);

    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException,
                                                              DataAccessException {
        if(name == null) {
            throw new IllegalArgumentException("Name is null");
        }
        
        List result = getHibernateTemplate().find("from User u where u.username like ?",
                               name);

        if(result.size() == 0) {
            throw new UsernameNotFoundException("User not found");
        }
        
        logger.info("Returning user " + result.get(0));
        
        return (UserDetails) result.get(0);
    }
}