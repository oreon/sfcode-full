package org.maki.seam.action;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.security.Restrict;
import org.maki.seam.model.Project;

@Name("projectSearch")
@Scope(ScopeType.SESSION)
// this component only be accessed when you are logged in.
@Restrict("#{identity.loggedIn}")
public class ProjectSearchAction implements Serializable {

  private static final long serialVersionUID = 8442718649044580446L;

  @In
  EntityManager entityManager;
  
  private String searchCriteria;
  private int pageSize = 10;
  private int page = 0;
  private int firstRecord;
  private long lastRecord;
  private boolean nextPageAvailable;
  private boolean previousPageAvailable;

  private long totalProjects = 0;
  
  @SuppressWarnings("unused")
  @DataModel
  private List<Project> projects;

  @Observer("projectSaved")
  public void findProjects() {
    page = 0;
    queryProjects();
  }

  public void nextPage() {
    page++;
    queryProjects();
  }
  public void previousPage() {
    if (page > 0) {
      page--;
    }
    queryProjects();
  }
  
  protected void queryProjects() {
    // determine the number of project available
    Query countQuery = entityManager
    .createQuery("SELECT count(p) FROM Project p WHERE lower(p.projectName) like #{pattern}");
    
    // fetch project instances from db
    Query dataQuery = entityManager
        .createQuery("SELECT p FROM Project p WHERE lower(p.projectName) like #{pattern}");
    
    int firstResult = page*pageSize;
    @SuppressWarnings("unchecked")
    List<Project> p = dataQuery.setMaxResults(pageSize+1).setFirstResult(firstResult).getResultList();

    totalProjects = (Long)countQuery.getSingleResult();
    
    previousPageAvailable = page > 0;
    firstRecord = firstResult+1;
    nextPageAvailable = p.size() > pageSize;
    if (nextPageAvailable) {
      projects = p.subList(0, pageSize);
      lastRecord = firstResult + pageSize;
    } else {
      projects = p;
      lastRecord = totalProjects;
    }
    
  }

  @Factory(value="pattern", scope=ScopeType.EVENT)
  public String getSearchPattern() {
    return searchCriteria == null ? "%" :
      "%" + searchCriteria.toLowerCase().replace('*', '%') + "%";
  }
  public String getSearchCriteria() {
    return searchCriteria;
  }

  public void setSearchCriteria(String searchCriteria) {
    this.searchCriteria = searchCriteria;
  }
  public int getPageSize() {
    return pageSize;
  }
  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }
  public long getPage() {
    return page;
  }
  public void setPage(int page) {
    this.page = page;
  }

  public long getTotalProjects() {
    return totalProjects;
  }

  public void setTotalProjects(long totalPages) {
    this.totalProjects = totalPages;
  }

  public boolean isNextPageAvailable() {
    return nextPageAvailable;
  }

  public boolean isPreviousPageAvailable() {
    return previousPageAvailable;
  }

  public int getFirstRecord() {
    return firstRecord;
  }

  public long getLastRecord() {
    return lastRecord;
  }
}
