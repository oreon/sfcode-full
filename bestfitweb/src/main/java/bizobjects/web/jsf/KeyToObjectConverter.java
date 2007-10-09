package bizobjects.web.jsf;

import java.util.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.servlet.ServletContext;

import org.witchcraft.model.support.BusinessEntity;

public class KeyToObjectConverter implements Converter {

	protected Logger log = Logger.getLogger("com.crazysquirrel");

	public KeyToObjectConverter() {
	}

	public String getAsString(FacesContext facesContext,
			UIComponent uiComponent, Object obj) {
		// log.severe("Object recieved was: " + obj + " Type: "
		// + obj.getClass().getName());

		System.out.println("returning " + obj);

		if (obj == null) {

			return "";
		}
		return ((BusinessEntity) obj).getId().toString();
	}

	public Object getAsObject(FacesContext facesContext,
			UIComponent uiComponent, String str) throws ConverterException {

		ServletContext context = (ServletContext) facesContext.getCurrentInstance().getExternalContext();
		//HttpSession session = context.get
		BusinessEntity be = (BusinessEntity) ((UIInput) uiComponent).getValue();

		System.out.println("get as objet returning "
				+ (be != null ? be.getId() : ""));

		return be;
	}

	/*
	private Object createNewValue(FacesContext context, UIComponent component,
			long id) {
		component.get
		ValueExpression ve = component.getValueExpression("value");
		try {
			PersistentBean pk = (PersistentBean) ve.getType(
					context.getELContext()).newInstance();
			pk.setId(id);
			return pk;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(
					"pbConverer error could not instanciate the assignable object of the persistent bean",
					e);
		}
	}*/
}