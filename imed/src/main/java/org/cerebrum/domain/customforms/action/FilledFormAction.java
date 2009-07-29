package org.cerebrum.domain.customforms.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlMessage;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.persistence.Query;

import org.cerebrum.domain.customforms.CustomField;
import org.cerebrum.domain.customforms.CustomForm;
import org.cerebrum.domain.customforms.FieldType;
import org.cerebrum.domain.customforms.FilledField;
import org.cerebrum.domain.customforms.FilledForm;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.witchcraft.seam.action.BaseAction;

import com.icesoft.faces.component.ext.HtmlCheckbox;
import com.icesoft.faces.component.inputrichtext.InputRichText;

@Scope(ScopeType.CONVERSATION)
@Name("filledFormAction")
public class FilledFormAction extends BaseAction<FilledForm> implements
		java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private FilledForm filledForm;

	public List<UIComponent> getComponents() {
		createFields();
		return components;
	}

	public void setComponents(List<UIComponent> components) {
		this.components = components;
	}

	List<UIComponent> components = new ArrayList<UIComponent>();

	@DataModel
	private List<FilledForm> filledFormList;

	@Factory("filledFormList")
	@Observer("archivedFilledForm")
	public void findRecords() {
		search();
	}

	public FilledForm getEntity() {
		return filledForm;
	}

	@Override
	public void setEntity(FilledForm t) {
		this.filledForm = t;
	}

	@Override
	public void setEntityList(List<FilledForm> list) {
		this.filledFormList = list;
	}

	private void createFields() {
		FacesContext context = FacesContext.getCurrentInstance();
	
		if (filledForm.getCustomForm() == null && !	context.getMessages().hasNext()) {
			Query qry = entityManager
					.createQuery("select c From CustomForm c ");
			CustomForm form = (CustomForm) qry.getResultList().get(0);
			filledForm.setCustomForm(form);
			Set<CustomField> flds = form.getCustomFields();
			for (CustomField customField : flds) {

				HtmlOutputText outputText = new HtmlOutputText();
				outputText.setValue(customField.getName());
				components.add(outputText);
				// FacesContext.getCurrentInstance().getViewRoot().;

				if (customField.getType() == FieldType.TEXT) {
					//com.sun.faces.util.DebugUtil.printTree(FacesContext
					//		.getCurrentInstance().getViewRoot(), System.out);

					HtmlInputText txt = new HtmlInputText();
					txt.setLabel(customField.getName());
					txt.setId(customField.getName());
					txt.setRequired(true);
					txt.setValueExpression("name", FacesContext
							.getCurrentInstance().getApplication()
							.getExpressionFactory().createValueExpression(
									FacesContext.getCurrentInstance()
											.getELContext(),
									customField.getName(), String.class));
					// txt.setValue("hello jsf");

					// txt.setValueExpression("value",
					// "filledFormAction.filledForm."); A\
					UIComponent parent = findComponentById("formGrid");
					if (parent != null)
						parent.getChildren().add(txt);
					// txt.setParent(parent);
					components.add(txt);

				} else if (customField.getType() == FieldType.LARGE_TEXT) {
					InputRichText richText = new InputRichText();
					richText.setId(customField.getName());
					components.add(richText);
				} else if (customField.getType() == FieldType.YES_NO) {
					HtmlCheckbox checkbox = new HtmlCheckbox();
					checkbox.setId(customField.getName());
					components.add(checkbox);
				}
				
				
			}
		}
	}

	private UIComponent findComponentById(String id) {
		FacesContext context = FacesContext.getCurrentInstance();
		UIViewRoot root = context.getViewRoot();
		// return root.findComponent(id);
		return findComponent(root, id);
	}

	private UIComponent findComponent(UIComponent c, String id) {
		if (id.equals(c.getId())) {
			return c;
		}
		Iterator<UIComponent> kids = c.getFacetsAndChildren();
		while (kids.hasNext()) {
			UIComponent found = findComponent(kids.next(), id);
			if (found != null) {
				return found;
			}
		}
		return null;
	}

	@Override
	public String save() {
		Set<FilledField> fields = filledForm.getFilledFields();
		FacesContext context = FacesContext.getCurrentInstance();
		UIComponent parent = findComponentById("formGrid");
		List<UIComponent> comps = parent.getChildren();

		// TODO: This should not be index based - it should come from the id
		int i = 0;

		for (UIComponent comp : comps) {

			FilledField field = new FilledField();
			String value = null;
			String fieldName = "";

			if (comp instanceof HtmlInputText) {
				HtmlInputText hit = ((HtmlInputText) comp);
				hit.updateModel(context);
				value = (String) (hit.getValue());
				field.setValue(value);
				fieldName = hit.getLabel();
			}
			// comp.
		

			if (i > 0 && comps.get(i - 1) instanceof HtmlOutputText) {
				String fldName = (String)((HtmlOutputText)comps.get(i - 1)).getValue();
				Set<CustomField> customFlds = filledForm.getCustomForm()
						.getCustomFields();
				for (CustomField customField : customFlds) {
					if (customField.getName().equalsIgnoreCase(fldName)) {
						field.setCustomField(customField);
						field.setFilledForm(filledForm);
						fields.add(field);
						break;	
					}
				}
			}

			i++;
		}
		filledForm.setFilledFields(fields);
		return super.save();
	}

	/**
	 * This function adds associated entities to an example criterion
	 * 
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (filledForm.getCustomForm() != null) {
			criteria = criteria.add(Restrictions.eq("customForm.id", filledForm
					.getCustomForm().getId()));
		}

	}

	public void updateAssociations() {

	}

	private List<FilledField> listFilledFields;

	void initListFilledFields() {
		listFilledFields = new ArrayList<FilledField>();
		if (filledForm.getFilledFields().isEmpty()) {

		} else
			listFilledFields.addAll(filledForm.getFilledFields());
	}

	public List<FilledField> getListFilledFields() {
		if (listFilledFields == null) {
			initListFilledFields();
		}
		return listFilledFields;
	}

	public void setListFilledFields(List<FilledField> listFilledFields) {
		this.listFilledFields = listFilledFields;
	}

	public void deleteFilledFields(FilledField filledFields) {
		listFilledFields.remove(filledFields);
	}

	@Begin(join = true)
	public void addFilledFields() {
		FilledField filledFields = new FilledField();

		filledFields.setFilledForm(filledForm);

		listFilledFields.add(filledFields);
	}

	/*
	public void updateComposedAssociations() {

		filledForm.getFilledFields().clear();
		filledForm.getFilledFields().addAll(listFilledFields);

	}*/

	public List<FilledForm> getEntityList() {
		if (filledFormList == null) {
			findRecords();
		}
		return filledFormList;
	}

}
