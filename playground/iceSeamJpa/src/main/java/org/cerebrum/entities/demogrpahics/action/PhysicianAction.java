package org.cerebrum.entities.demogrpahics.action;

import java.util.List;

import org.cerebrum.entities.demogrpahics.Physician;
import org.cerebrum.seam.action.base.BaseAction;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;

@Scope(ScopeType.CONVERSATION)
@Name("physicianAction")
public class PhysicianAction extends BaseAction {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Physician physician;

	@DataModel
	private List<Physician> physicianList;

	@Factory("physicianList")
	public void findStduents() {
		physicianList = entityManager
				.createQuery(
						"select physician from Physician physician order by physician.lastName")
				.getResultList();
	}

	@Begin
	public String select(Physician physician) {
		this.physician = entityManager.merge(physician);
		log
				.info("User selected Physician: #{physician.displayName} #{physician.id} #{physician.firstName}");
		return "/editPhysician.jspx";
	}

	@End
	public String save() {
		facesMessages.add("Successfully saved  #{physician.displayName}");
		entityManager.persist(physician);
		return "/listPhysician.jspx";
	}

}
