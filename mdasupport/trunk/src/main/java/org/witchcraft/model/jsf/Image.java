package org.witchcraft.model.jsf;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.apache.myfaces.custom.fileupload.UploadedFileDefaultFileImpl;

/** 
 * Represents an uploaded image
 * @author jesing
 *
 */
//TODO: find a way to do jaxb mapping e.g. @XmlJavaTypeAdapter(value=UploadedFileDefaultFileImpl.class)
public interface Image extends UploadedFile{

}
