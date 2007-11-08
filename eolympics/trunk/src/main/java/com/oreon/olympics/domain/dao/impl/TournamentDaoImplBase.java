package com.oreon.olympics.domain.dao.impl;

import com.oreon.olympics.domain.Tournament;
import com.oreon.olympics.domain.dao.TournamentDao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.witchcraft.model.support.dao.BaseDao;

@Repository
public class TournamentDaoImplBase extends BaseDao<Tournament>
		implements
			TournamentDao {

	//// FINDERS ///// 

}
