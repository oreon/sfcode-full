package org.wc.server.dao.impl;

import org.springframework.stereotype.Repository;

import org.wc.server.dao.DummyDAO;
import org.wc.server.entity.DummyEntity;

/**
 * Plain DAO which provides only {@link org.wc.server.dao.impl.GenericHibernateDAOImpl} methods
 */
@Repository
public class DummyDAOImpl extends GenericHibernateDAOImpl<DummyEntity, Long> implements DummyDAO {
    
}
