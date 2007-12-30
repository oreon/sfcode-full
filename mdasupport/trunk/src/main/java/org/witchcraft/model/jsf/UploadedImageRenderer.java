/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.witchcraft.model.jsf;

import java.io.IOException;
import java.io.InputStream;

import javax.faces.context.FacesContext;
import javax.faces.context.ResponseStream;
import javax.faces.el.ValueBinding;
import javax.servlet.http.HttpSession;

import org.apache.myfaces.custom.dynamicResources.ResourceContext;
import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.apache.myfaces.custom.graphicimagedynamic.ImageContext;
import org.apache.myfaces.custom.graphicimagedynamic.ImageRenderer;

/**  
 * Helper class for displaying binary images from database
 * which have been uploaded using myfaces t:fileupload
 * @author Mathias Broekelmann
 * 
 */
public class UploadedImageRenderer implements ImageRenderer {
	public static final String CURRENT_IMAGE = "currImage";
	private UploadedFile file;


	public void setContext(FacesContext facesContext,
			ResourceContext resourceContext) throws IOException {
		
		ImageContext imageContext = (ImageContext) resourceContext;
		Object obj = imageContext.getParamters().get("file");
		System.out.println("file is " + imageContext.getParamters().get("file"));
		HttpSession session = (HttpSession)
			facesContext.getCurrentInstance().getExternalContext().getSession(false);
	    file = (UploadedFile) session.getAttribute(CURRENT_IMAGE);
		
	    /*
		ValueBinding vb = facesContext.getApplication().createValueBinding(
				"#{graphicImageDynamicBean}");
		GraphicImageDynamicBean value = (GraphicImageDynamicBean) vb
				.getValue(facesContext);
		if (value == null) {
			throw new IllegalStateException(
					"managed bean graphicImageDynamicBean not found");
		}*/
		
	}

	/**
	 * @see org.apache.myfaces.custom.graphicimagedynamic.ImageRenderer#getContentLength()
	 */
	public int getContentLength() {
		return  (int) file
				.getSize();
	}

	/**
	 * @see org.apache.myfaces.custom.graphicimagedynamic.ImageRenderer#getContentType()
	 */
	public String getContentType() {
		return file
				.getContentType();
	}

	/**
	 * @see org.apache.myfaces.custom.graphicimagedynamic.ImageRenderer#renderImage(javax.faces.context.FacesContext,
	 *      org.apache.myfaces.custom.graphicimagedynamic.ImageContext,
	 *      java.io.OutputStream)
	 */
	public void renderResource(ResponseStream out) throws IOException {

		InputStream is = file.getInputStream();
		try {
			byte[] buffer = new byte[1024];
			int len = is.read(buffer);
			while (len != -1) {
				out.write(buffer, 0, len);
				len = is.read(buffer);
			}
		} finally {
			is.close();
		}
	}

}
