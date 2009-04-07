package org.wcdemo.xstories;

import java.io.File;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("state")
@Scope(ScopeType.SESSION)
public class ApplicationStateHolders {

	private boolean icePanelExpanded=false;

	public boolean isIcePanelExpanded() {
		
		return icePanelExpanded;
	}

	public void setIcePanelExpanded(boolean icePanelExpanded) {
		
		this.icePanelExpanded = icePanelExpanded;
	}
	
	
	 File file;
	    String fileLocation;

	    public File getFile() {
	        return file;
	    }
	    
	    public String getFileLocation(){
	        return fileLocation;
	    }

	    public void setFile(File file) {
	        this.file = file;
	        fileLocation = file.getPath();
	    }

	
}
