package org.witchcraft.seam.action;

import java.util.HashMap;
import java.util.Map;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("panelMenuStateBean")
@Scope(ScopeType.SESSION)
public class PanelMenuStateBean {
	
    HashMap<String, Boolean> menueState=new HashMap<String, Boolean>();

    public Map getState() {
        return menueState;
    }  
}