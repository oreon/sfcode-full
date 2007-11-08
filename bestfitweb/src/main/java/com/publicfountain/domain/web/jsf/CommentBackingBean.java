package com.publicfountain.domain.web.jsf;

import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;

import com.publicfountain.domain.Comment;
import com.publicfountain.domain.service.CommentService;

public class CommentBackingBean extends BaseBackingBean<Comment> {

	private Comment comment = new Comment();

	private CommentService commentService;

	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	public Comment getComment() {
		return comment;
	}

	public void set(Comment comment) {
		this.comment = comment;
	}

	@SuppressWarnings("unchecked")
	public BaseService<Comment> getBaseService() {
		return commentService;
	}

	public Comment getEntity() {
		return getComment();
	}

	public String search() {
		action = SEARCH;
		return "search";
	}

	/** Returns a success string upon selection of an entity in model - majority of work is done
	 * in the actionListener selectEntity
	 * @return - "success" if everthing goes fine
	 * @see - 
	 */
	public String select() {
		return "edit";
	}

	/** This action Listener Method is called when a row is clicked in the dataTable
	 *  
	 * @param event contains the database id of the row being selected 
	 */
	public void selectEntity(ActionEvent event) {

		UIParameter component = (UIParameter) event.getComponent()
				.findComponent("editId");

		// parse the value of the UIParameter component    	 
		long id = Long.parseLong(component.getValue().toString());

		comment = commentService.load(id);
	}

}
