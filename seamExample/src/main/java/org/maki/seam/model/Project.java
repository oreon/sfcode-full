package org.maki.seam.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

@Entity
@Table(name="Project")
public class Project extends ModelBase {
  private static final long serialVersionUID = 6615844921088924875L;
  
  @Column(name="project_name")
  @NotNull
  @Length(min=3, max=50)
  private String projectName;
  
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name="project_start_date")
  private Date projectStartDate;

  public Project() { }

  public Project(String createdAtIp, User createdByUser,
      Date dateCreated, Date dateUpdated, Long id,
      String updatedAtIp, User updatedByUser, Long version, 
      String projectName, Date projectStartDate) {

    super(createdByUser, dateCreated, dateUpdated,
        id, updatedByUser, version);

    this.projectName = projectName;
    this.projectStartDate = projectStartDate;
  }

  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  public Date getProjectStartDate() {
    return projectStartDate;
  }

  public void setProjectStartDate(Date projectStartDate) {
    this.projectStartDate = projectStartDate;
  }

}
