package edu.nocccd.filemanager.client;

import java.io.Serializable;
import java.lang.Comparable;

/**
 * Stores file information.
 * @author Brad Rippe (brippe at nocccd.edu), Firas Kassem
 *
 */
public class FileWrapper implements Serializable, Comparable<Object> {

	private static final long serialVersionUID = 1L;
	private String name;
    private String path;
    private FileType kind;
    private String modifiedAt;

    /**
     * Creates a file wrapper
     * @param path the path of the file/directory
     * @param name the name of the file/directory
     * @param modifiedAt the last modified date
     */
    public FileWrapper(String path, String name, String modifiedAt) {
        this.name = name;
        this.path = path;
        this.modifiedAt = modifiedAt;
        this.kind = this.getFileType(extractFileExtention(name));
    }

    /**
     * Creates a file wrapper
     * @param path the path of the file/directory
     * @param name the name of the file/directory
     */
    public FileWrapper(String path, String name) {
        this.name = name;
        this.path = path;
        this.kind = this.getFileType(extractFileExtention(name));
    }

    /**
     * Creates a file wrapper
     * @param path the path of the file/directory
     */
    public FileWrapper(String path) {
        this.name = "";
        this.path = path;
        this.kind = FileType.DIR;
    }

    /**
     * Creates a file wrapper
     */
    public FileWrapper() {
    	this.name = "";
        this.path = "";
        this.modifiedAt = "";
        this.kind = FileType.OTHER;
    }

    /**
     * Gets the file name
     * @return file name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the file path
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * Gets the modified date
     * @return the modified a date
     */
    public String getModified() {
        return this.modifiedAt;
    }

    /**
     * Gets the kind of file
     * @return kind of file
     * @see edu.nocccd.filemanager.client.FileType
     */
    public FileType getKind() {
        return kind;
    }

    /**
     * Sets the file wrapper to a directory type
     */
    public void setIsDirectory() {
        this.kind = FileType.DIR;
    }
    
    /**
     * Compares the FileWrapper names to see which is alphabetically earlier than
     * another.
     * @param o the fileWrapper to be compared
     * @return a negative integer, zero, or a positive integer as this FileWrapper's name 
     * 			is less than, equal to, or greater than the specified FileWrapper's. 
     */
    public int compareTo(Object o) {
    	if(this.name == null || ((FileWrapper)o).getName() == null)
    		return 0;
    	return this.name.compareTo(((FileWrapper)o).getName());
    }
    
    private static String extractFileExtention(String file) {
        int dot = file.lastIndexOf('.');
        return file.substring(dot + 1).toLowerCase();
    }

    private FileType getFileType(String ext) {
    	if(ext.equals("avi"))
        	return FileType.AVI;
    	if(ext.equals("doc") || ext.equals("docx") || ext.equals("wbk") || ext.equals("dot"))
        	return FileType.DOC;
    	if(ext.equals("xls") || ext.equals("xlsx"))
        	return FileType.XLSX;
    	if(ext.equals("html") || ext.equals("htm"))
        	return FileType.HTML;
    	if(ext.equals("mov"))
        	return FileType.MOV;
    	if(ext.equals("mp3") || ext.equals("mpeg"))
        	return FileType.MP3;
        if(ext.equals("pdf"))
            return FileType.PDF;
        if(ext.equals("ppt") || ext.equals("pptx"))
            return FileType.PPTX;
        if(ext.equals("wmv"))
            return FileType.WMV;
        if(ext.equals("gif") || ext.equals("jpg") || ext.equals("png") || ext.equals("tif") || ext.equals("psd"))
            return FileType.IMG;
        if(ext.equals("txt"))
        	return FileType.TXT;
        if(ext.equals("wpd") || ext.equals("wpt"))
        	return FileType.WPD;
        if(ext.equals("zip"))
        	return FileType.ZIP;
        return FileType.OTHER;
    }    
}
/*
 * FileWrapper.java
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
