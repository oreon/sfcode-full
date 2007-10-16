package com.publicfountain.domain.service.impl;

import com.publicfountain.domain.Comment;
import com.publicfountain.domain.service.CommentService;
import com.publicfountain.domain.dao.CommentDao;
import java.util.List;
import com.publicfountain.domain.service.CommentService;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import org.apache.log4j.Logger;

import usermanagement.Authority;
import usermanagement.service.AuthorityService;

import org.witchcraft.model.support.dao.GenericDAO;
import org.witchcraft.model.support.errorhandling.BusinessException;
import org.witchcraft.model.support.service.BaseServiceImpl;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class CommentServiceImplBase extends BaseServiceImpl<Comment>
		implements
			CommentService {

	private static final Logger log = Logger
			.getLogger(CommentServiceImplBase.class);

	private CommentDao commentDao;

	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	@Override
	public GenericDAO<Comment> getDao() {
		return commentDao;
	}

	//// Delegate all crud operations to the Dao ////

	public Comment save(Comment comment) {
		Long id = comment.getId();

		commentDao.save(comment);

		return comment;
	}

	public void delete(Comment comment) {
		commentDao.delete(comment);
	}

	public Comment load(Long id) {
		return commentDao.load(id);
	}

	public List<Comment> loadAll() {
		return commentDao.loadAll();
	}

	public List<Comment> searchByExample(Comment comment) {
		return commentDao.searchByExample(comment);
	}

	/*
	public List query(String queryString, Object... params) {
		return basicDAO.query(queryString, params);
	}*/

}
