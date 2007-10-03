package usermanagement.dao.impl;

import usermanagement.Authority;
import usermanagement.dao.AuthorityDao;

import bizobjects.Customer;
import bizobjects.dao.CustomerDao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.witchcraft.model.support.dao.BaseDao;

@Repository
public class AuthorityDaoImpl extends BaseDao<Authority>
		implements
			AuthorityDao {

	//// FINDERS ///// 

}
