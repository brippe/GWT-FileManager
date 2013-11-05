package edu.nocccd.filemanager.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Asynchronous interface used for remote procedure calls
 * @author Brad Rippe (brippe at nocccd.edu), Firas Kassem
 * @see com.google.gwt.user.client.rpc.AsyncCallback
 */
public interface FileSystemServiceAsync {
	
	/**
	 * Retrieves a list of file and directory information based on
	 * the file/directory specified
	 * @param file the file or directory to get the contents of
	 * @return a list of files and directories
	 */
	void getContents(FileWrapper file, AsyncCallback<FileWrapper[]> callback);
	
	/**
	 * Retrieves a list of file and directory information based on
	 * the path specified
	 * @param file the path to get the contents of
	 * @return a list of files and directories
	 */
    void getContents(String file, AsyncCallback<FileWrapper[]> callback);
    
    /**
	 * Deletes a file or directory 
	 * @param absoluteName the absolute path the file or directory
	 * @return true if and only if the file or directory is successfully deleted; false otherwise 
	 */
    void deleteFile(String absoluteName, AsyncCallback<Boolean> callback);
    
    /**
	 * Renames a file or directory 
	 * @param absoluteName the absolute path the file or directory
	 * @param newAbsoluteName the new name of the file or directory
	 * @return true if and only if the file or directory is successfully renamed; false otherwise 
	 */
    void renameFile(String absoluteName, String newAbsoluteName, AsyncCallback<Boolean> callback);
    
    /**
	 * Creates a directory 
	 * @param absoluteName the absolute path to the directory
	 * @return true if and only if the directory was successfully created; false otherwise 
	 */
    void mkDir(String absoluteName, AsyncCallback<Boolean> callback);
}

/*
 * FileSystemServiceAsync.java
 * 
 * Copyright (c) Nov 1, 2011 North Orange County Community College District. 
 * All rights reserved.
 * 
 * THIS SOFTWARE IS PROVIDED "AS IS," AND ANY EXPRESS OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE, ARE EXPRESSLY DISCLAIMED. IN NO EVENT SHALL
 * NORTH ORANGE COUNTY COMMUNITY COLLEGE DISTRICT OR ITS EMPLOYEES BE LIABLE FOR 
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES 
 * (INCLUDING, BUT NOT LIMITED, THE COSTS OF PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; 
 * LOSS OF USE, DATA OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED IN ADVANCE OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 * Redistribution and use of this software in source or binary forms, with or
 * without modification, are permitted, provided that the following conditions
 * are met.
 * 
 * 1. Any redistribution must include the above copyright notice and disclaimer
 *    and this list of conditions in any related documentation and, if feasible, in
 *    the redistributed software.
 * 
 * 2. Any redistribution must include the acknowledgment, "This product includes
 *    software developed by North Orange County Community College District, in any 
 *    related documentation and, if feasible, in the redistributed software."
 * 
 * 3. The names "NOCCCD" and "North Orange County Community College District" must 
 *    not be used to endorse or promote products derived from this software.
 */
