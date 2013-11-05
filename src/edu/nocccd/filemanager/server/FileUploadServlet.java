package edu.nocccd.filemanager.server;

import gwtupload.server.UploadAction;
import gwtupload.server.exceptions.UploadActionException;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.util.Streams;


/**
 * Servlet for uploading files to the server
 * @author Brad Rippe (brippe at nocccd.edu)
 * 
 */
public class FileUploadServlet extends UploadAction {
	
	private static final long serialVersionUID = 1L;
	Hashtable<String, String> receivedContentTypes = new Hashtable<String, String>();
	
	Hashtable<String, File> receivedFiles = new Hashtable<String, File>();
	
	private static final String PROPERTIES_FILE = "/WEB-INF/classes/filemanager.properties";
	private static final String FILE_SEPERATOR = System.getProperty("file.separator");
	private String uploadDirectory;
	private String uploadDirectory2;
	private String uploadDirectory3;
	private String uploadDirectory4;	
	private String path = "c:\\myfiles\\root1";
	
	private static boolean restrict = false;
	private static String validHost = "127.0.0.1"; 	// accept requests from this address 127.0.0.1
													// changed in the filemanager.properties		
	/**
	 * @see javax.servlet.http.HttpServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		Properties properties = new Properties();
		try {
			properties.load(getServletContext().getResourceAsStream(
					PROPERTIES_FILE));
		} catch (IOException ioe) {
			throw new ServletException(ioe);
		}
		uploadDirectory = properties.getProperty("root.directory", "target");
		uploadDirectory2 = properties.getProperty("root2.directory", "target");
		uploadDirectory3 = properties.getProperty("root3.directory", "target");
		uploadDirectory4 = properties.getProperty("root4.directory", "target");
		validHost = properties.getProperty("valid.host", "localhost");
		restrict = new Boolean(properties.getProperty("restrict", "true")).booleanValue();
	}
	
	@Override
	public String executeAction(HttpServletRequest request, List<FileItem> sessionFiles) throws UploadActionException {
		String response = "";
    
	    for (FileItem item : sessionFiles) {
	    	
	    	if (item.isFormField() && item.getFieldName().equals("aPath")) {
	    		path = item.getString();
	    		if(path != null && !path.endsWith(FILE_SEPERATOR))
					path += FILE_SEPERATOR;
	    	} else if (!item.isFormField()) {

	    		try {
	        	
	    			String filePath = item.getName();
	    			String fileName = filePath.substring(filePath.lastIndexOf(FILE_SEPERATOR) + 1);
				
	    			if(path.startsWith(uploadDirectory) || path.startsWith(uploadDirectory2) ||
	    					path.startsWith(uploadDirectory3) || path.startsWith(uploadDirectory4)) {
	    				File file = new File(path, fileName);
	    				Streams.copy(item.getInputStream(), new FileOutputStream(file), true);
	    				receivedFiles.put(item.getFieldName(), file);
	    				receivedContentTypes.put(item.getFieldName(), item.getContentType());
	    				
	    				response += file.getName() + " saved!";
	    			}

	    		} catch (Exception e) {
	    			throw new UploadActionException(e);
	    		}
	      	}
	    }
	    
	    removeSessionFileItems(request);
	    return response;
	}
}

/*
 * FileUploadServlet.java.java
 * 
 * Copyright (c) Oct 31, 2011 North Orange County Community College District.
 * All rights reserved.
 * 
 * THIS SOFTWARE IS PROVIDED "AS IS," AND ANY EXPRESS OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE, ARE EXPRESSLY DISCLAIMED. IN NO EVENT SHALL
 * NORTH ORANGE COUNTY COMMUNITY COLLEGE DISTRICT OR ITS EMPLOYEES BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED, THE COSTS OF PROCUREMENT OF SUBSTITUTE
 * GOODS OR SERVICES; LOSS OF USE, DATA OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED IN ADVANCE OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * 
 * Redistribution and use of this software in source or binary forms, with or
 * without modification, are permitted, provided that the following conditions
 * are met.
 * 
 * 1. Any redistribution must include the above copyright notice and disclaimer
 * and this list of conditions in any related documentation and, if feasible, in
 * the redistributed software.
 * 
 * 2. Any redistribution must include the acknowledgment, "This product includes
 * software developed by North Orange County Community College District, in any
 * related documentation and, if feasible, in the redistributed software."
 * 
 * 3. The names "NOCCCD" and "North Orange County Community College District"
 * must not be used to endorse or promote products derived from this software.
 */