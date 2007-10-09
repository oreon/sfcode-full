package com.publicfountain.domain.web.jsf;

import com.publicfountain.domain.Comment;
import java.util.ArrayList;
import java.util.List;
import com.publicfountain.domain.service.CommentService;

import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import javax.faces.application.FacesMessage;

import org.springframework.dao.DataAccessException;

import org.witchcraft.model.support.errorhandling.BusinessException;
import org.witchcraft.model.jsf.JSFUtils;

public class CommentBackingBean {

	private Comment comment = new Comment();

	private CommentService commentService;

	private String action; //whether action is search or update/add new 

	private static final String SEARCH = "SEARCH";

	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	public Comment getComment() {
		return comment;
	}

	public void set(Comment comment) {
		this.comment = comment;
	}

	/**Write values to the database 
	 * @return - "success" if everthing goes fine
	 */
	public String update() {
		try {
			commentService.save(comment);
		} catch (BusinessException be) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Update Error: ", be.getMessage()));
			return "failure";
		} catch (DataAccessException dae) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Update Error: ", dae.getMessage()));
			return "failure";
		} catch (Exception ex) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Update Error: ", ex.getMessage()));
			return "failure";
		}

		return "success";
	}

	/**Write values to the database 
	 * @return - "success" if everthing goes fine
	 */
	public String delete() {
		try {
			commentService.delete(comment);
		} catch (DataAccessException dae) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Delete Error: ", dae.getMessage()));
			return "failure";
		}

		return "success";
	}

	public String search() {
		action = SEARCH;
		return "search";
	}

	/**If update is canceled we go to the listing page - invoked in response to clicking cancel 
	 * on save/edit record form
	 * @return - "success" (always)
	 */
	public String cancelUpdate() {
		return "success";
	}

	public String cancelSearch() {
		return "success";
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
	 * @param event contians the database id of the row being selected 
	 */
	public void selectEntity(ActionEvent event) {

		UIParameter component = (UIParameter) event.getComponent()
				.findComponent("editId");

		// parse the value of the UIParameter component    	 
		long id = Long.parseLong(component.getValue().toString());

		comment = commentService.load(id);
	}

	/**Get a list of  comments - if action is search , get a subset otherwise
	 * get all
	 * @return - a list of comments 
	 */
	public List<Comment> getComments() {
		List<Comment> comments = null;
		if (action != null && action.equals(SEARCH))
			comments = commentService.searchByExample(comment);
		else
			comments = commentService.loadAll();

		return comments;
	}

	public List<SelectItem> getAsSelectItems() {
		List<Comment> comments = commentService.loadAll();
		return JSFUtils.getAsSelectItems(comments);
	}

}
