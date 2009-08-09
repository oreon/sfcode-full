package org.cerebrum.domain.patient.action;

import java.io.File;
import java.io.FileFilter;
import java.io.Serializable;
import java.util.Date;
import java.util.EventObject;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;


import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Identity;

import com.icesoft.faces.component.inputfile.InputFile;
/*
import eu.eee.monitor.entity.Campaign;
import eu.eee.monitor.entity.Dataserie;
import eu.eee.monitor.entity.DataserieKeywordValue;
import eu.eee.monitor.entity.KeywordDef;
import eu.eee.monitor.entity.TimeValue;
import eu.eee.monitor.entity.Unit;
import eu.eee.monitor.entity.User;
import eu.eee.monitor.exception.IllegalImportMethodException;
import eu.eee.monitor.upload.DataserieImportDetail.ImportMethod;
*/

/*
 * TODO If a user selects to create a new Dataserie, but a dataserie with the same name already exists, 
 * there will be two dataseries with the same name!
 * 
 * */

/*@Stateful
@Name("dataImportController")
public class DataImportControllerAction implements DataImportController, Serializable {
		
	private static final long serialVersionUID = 1L;
	
	@Logger Log log;
	@In PluginController pluginController;
	@In EntityManager entityManager;
		
	private Campaign currentCampaign;
	private User currentUser;
	private UploadFileData uploadedFile;
	private int fileProgress;
    private String parentDirectory="";
    
    
    private FileHandler fileHandler = null;
	private List<DataserieImportDetail> importScheme = new LinkedList<DataserieImportDetail>();	
	
	@Begin 
	public void selectCampaign(Campaign campaign) {
		currentCampaign = entityManager.merge(campaign);
		currentUser =  (User) entityManager.createQuery("From User u where u.usrLogin=:usrLogin")
		.setParameter("usrLogin", Identity.instance().getUsername())
		.getSingleResult();
	}
	
	public void uploadFile(ActionEvent event) {
        log.info("uploadFile(ActionEvent event) called.");
    	emptyDirectory();
        InputFile inputFile = (InputFile) event.getSource();
    	
    	if (inputFile.getStatus() == InputFile.SAVED) {
        	if (this.parentDirectory.equals("")){
        		this.parentDirectory = inputFile.getFile().getParent();
        	}

        	uploadedFile = new UploadFileData(inputFile.getFileInfo(), inputFile.getFile());
        }
    }
    
    public void fileUploadProgress(EventObject event) {
    	InputFile ifile = (InputFile) event.getSource();
        fileProgress = ifile.getFileInfo().getPercent();
    }
	
    
	public FileHandler detectFileHandler() {
		FileHandler fh = null;
		int score = 0;
		for (Iterator<FileHandler> it = pluginController.getFileHandlers().iterator(); it.hasNext(); ) {
			FileHandler tmpFh = (FileHandler) it.next();
			int tmpScore = tmpFh.match(uploadedFile);
			score = (tmpScore > score) ? tmpScore : score;
		}		
		return fh;		
	}	

	@End
	public void executeImport() {
		for (Iterator<DataserieImportDetail> it = importScheme.iterator(); it.hasNext(); ) {
			DataserieImportDetail detail = (DataserieImportDetail) it.next();
			if (detail.importMethod != ImportMethod.SKIP) {
				try {
					persistDataserie(detail);
				} catch (IllegalImportMethodException e) {
					// TODO redirect to error page, or save the error and report it later					
					e.printStackTrace();
				}
			}
		}
	}
	
	private void persistDataserie(DataserieImportDetail detail) throws IllegalImportMethodException {
		Dataserie ds = null;
		
		if (detail.importMethod == ImportMethod.IMPORT_NEW) {
			ds = new Dataserie();
			//read unit from database
			Unit unit;
			try {
				unit = (Unit) entityManager.createQuery("From Unit u where u.unDescription=:description")
						.setParameter("description", fileHandler.readUnitDescription(detail.dsName))
						.getSingleResult();
			} catch (NoResultException e) {
				unit = new Unit();
				unit.setUnDescription(fileHandler.readUnitDescription(detail.dsName));
			}
			ds.setDsUnit(unit);
			//dataserie imported by current user
			ds.setDsCreatedBy(currentUser); 
		} 
		else { // try to extend an existing dataserie
			try {
				ds = (Dataserie) entityManager.createQuery("From Dataserie ds where ds.dsName=:dsName")
					.setParameter("dsName", detail.dsName)
					.getSingleResult();
			} catch (NoResultException e) {
				throw new IllegalImportMethodException("Trying to extend a non-existing dataserie!");
			}
		}
		
		//add dataserie to current campaign
		ds.getCampaigns().add(currentCampaign);
		
		//set all the keyword values. Create keyword definitions if necessary 
		for (Iterator<Map.Entry<String, String>> it = detail.getKeywordValues().entrySet().iterator(); it.hasNext(); ) {
			 Map.Entry<String, String> keywordValue = (Map.Entry<String, String>) it.next();
			 KeywordDef kwd;
			 //find the corresponding keyword definition in the database, or create it if it doesn't exist
			 try {
				 kwd = (KeywordDef) entityManager.createQuery("From KeywordDef kwd where kwd.kwdName = :kwdName")
				 	.setParameter("kwdName", keywordValue.getKey())
				 	.getSingleResult();
			 }
			 catch (NoResultException e) {
				 log.info("KeywordDef for '" + keywordValue.getKey() + "' was not found. It will be created...");
				 kwd = new KeywordDef();
				 kwd.setKwdName(keywordValue.getKey());
				 //entityManager.persist(kwd);
			 }
			 DataserieKeywordValue dkv = new DataserieKeywordValue();
			 dkv.setDkvKeywordDef(kwd);
			 dkv.setDkvValue(keywordValue.getValue());
			 ds.getDataserieKeywordValues().add(dkv);
		}
		
		//Fill buffer of fileHandler with values (do this once, to avoid reading the file on every call)
		fileHandler.readTimeValues(detail.dsName);
		
		//strategy to remove overlapping values:
		//first find the begin date and end date of the existing dataserie
		Date exBeginDate = (Date) entityManager.createQuery("Select min(tv.tvTimeStamp) from TimeValue tv " +
									"where tv.tvDataserie=:tvDataserie")
									.setParameter("tvDataserie", ds.getIdDataserie())
									.getSingleResult();
		Date exEndDate = (Date) entityManager.createQuery("Select max(tv.tvTimeStamp) from TimeValue tv " +
									"where tv.tvDataserie=:tvDataserie")
									.setParameter("tvDataserie", ds.getIdDataserie())
									.getSingleResult();
		
		if (detail.importMethod == ImportMethod.EXTEND_OVERWRITE) {
			//define the period of overlapping values
			Date beginDate = fileHandler.getTimeValuesBuffer().firstKey().before(exBeginDate) ? exBeginDate : fileHandler.getTimeValuesBuffer().firstKey();
			Date endDate = fileHandler.getTimeValuesBuffer().lastKey().after(exEndDate) ? exEndDate : fileHandler.getTimeValuesBuffer().lastKey();
			
			//delete the period
			entityManager.createQuery("delete from TimeValue tv where tv.tvDataserie=:tvDataserie " +
									    "and tv.tvTimeStamp between :beginDate and :endDate")
									    .setParameter("tvDataserie", ds.getIdDataserie())
									    .setParameter("beginDate", beginDate)
									    .setParameter("endDate", endDate);
		}
		
		//start importing timevalues
		for (Iterator<SortedMap.Entry<Date, Double>> it = fileHandler.getTimeValuesBuffer().entrySet().iterator(); it.hasNext(); ) {
			SortedMap.Entry<Date, Double> timevalue = (SortedMap.Entry<Date, Double>) it.next();
			//only import a value, if we can overwrite all values, or if it is outside the period that must kept untouched
			if (detail.importMethod == ImportMethod.EXTEND_OVERWRITE || detail.importMethod == ImportMethod.IMPORT_NEW || timevalue.getKey().before(exBeginDate) || timevalue.getKey().after(exEndDate))  {
				TimeValue tv = new TimeValue();
				tv.getIdTimeValue().setTvTimeStamp(timevalue.getKey());
				tv.setTvValue(timevalue.getValue());
				ds.getTimeValues().add(tv);
			}
		}
		
		entityManager.persist(ds);
	}
	
	public void emptyDirectory() {
    	try{
		 	if (this.parentDirectory!=null){
		        // remove the files that have been uploaded during this session 
		  		File[] files = null;
			    File dir = new File(parentDirectory);
			    // use a FileFilter to only get files & not directories 
			    if (dir!=null){
			    	FileFilter fileFilter = new FileFilter(){
			    		public boolean accept(File file){
			    			return !file.isDirectory();
			    		}
			    	};
			    	files = dir.listFiles(fileFilter);
			    }
			    if (files!=null){
			    	for (int i=0; i< files.length; i++){
			    		File f = (File)files[i];
			    		String fname=f.getName();
			    		if (f.exists()){
			    			f.delete();
			    			log.info("\t\tfile: "+fname+" deleted");
	 		    			if (log.isDebugEnabled())log.debug("file: "+fname+" deleted");
			    		}
			    	}
			    }
		 	}
	    } catch(Exception ex) {
	    	// no files to delete 
 	    	log.info("uploadDirectory is not set--no files to delete! exception");
 	    	ex.printStackTrace();
	    } 	    	
    }	

    public UploadFileData getUploadedFile() {
        return uploadedFile;
    }

    public int getFileProgress() {
        return fileProgress;
    }

    public String getParentDirectory() {
		return parentDirectory;
	}
    
   
	public void setFileHandler(FileHandler fileHandler) {
		this.fileHandler = fileHandler;
	}
	
	public FileHandler getFileHandler() {
		return fileHandler;
	}
	
	
	public void setCurrentCampaign(Campaign currentCampaign) {
		this.currentCampaign = currentCampaign;
	}
	
	public Campaign getCurrentCampaign() {
		return currentCampaign;
	}
	
	
	@Remove @Destroy
	public void destroy() {}
}*/