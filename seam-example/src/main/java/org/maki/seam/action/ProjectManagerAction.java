package org.maki.seam.action;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.core.Events;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.maki.seam.model.Project;

@Name("projectManager")
// this component only be accessed when you are logged in.
@Restrict("#{identity.loggedIn}")
@Scope(ScopeType.CONVERSATION)
public class ProjectManagerAction implements Serializable {

  private static final long serialVersionUID = -1778950449269369532L;

  @In
  EntityManager entityManager;
  
  @In(required=false) @Out
  private Project project;
 
  @In
  private FacesMessages facesMessages;
  
  @In
  private Events events;

  @Logger
  private Log log;
 
  @Begin
  public void selectProject(Project project) {
    // move the transient instance into the persistence context
    this.project = entityManager.merge(project);
    log.info("User selected project: #{project.projectName}");
  }
  @End
  public void save() {
    entityManager.persist(project);
    facesMessages.add("Project, #{project.projectName} has been saved!");
    log.info("Project #{project.id} has been persisted");
    events.raiseTransactionSuccessEvent("projectSaved");
  }
  
  @End
  public void cancel() {
    System.out.println("in cancel");
  }
}