package edu.nocccd.filemanager.server;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import edu.nocccd.filemanager.client.FileSystemService;
import edu.nocccd.filemanager.client.FileWrapper;

/**
 * The implementation of the FileSystemService that allows remote clients to access local 
 * files and directories
 * @author Brad Rippe (brippe at nocccd.edu), Firas Kassem
 * @see edu.nocccd.filemanager.client.FileSystemService
 *
 */
public class FileSystemServiceImpl extends RemoteServiceServlet implements FileSystemService {
	private static final long serialVersionUID = 1L;
    private static SimpleDateFormat dateFormater;
    private static final ThreadLocal<SimpleDateFormat> tSDF = new ThreadLocal<SimpleDateFormat>();
    private static final Logger LOGGER = LoggerFactory.getLogger(FileSystemServiceImpl.class);

    static{
        dateFormater = new SimpleDateFormat("MMM d, yy");
    }
    
    /**
	 * Retrieves a list of file and directory information based on
	 * the file/directory specified
	 * @param file the file or directory to get the contents of
	 * @return a list of files and directories
	 */
    public FileWrapper[] getContents(FileWrapper file) {
        File fsFile = new File(file.getPath());
        if (fsFile.isDirectory()) {
            return this.buildFilesList(fsFile.listFiles());
        }
        return null;
    }

    /**
	 * Retrieves a list of file and directory information based on
	 * the path specified
	 * @param file the path to get the contents of
	 * @return a list of files and directories
	 */
    public FileWrapper[] getContents(String file) {
        return this.getContents(new FileWrapper(file));
    }

    /**
	 * Deletes a file or directory 
	 * @param absoluteName the absolute path the file or directory
	 * @return true if and only if the file or directory is successfully deleted; false otherwise 
	 */
    public Boolean deleteFile(String absoluteName) {
        LOGGER.info("deleting : "+absoluteName);
        File f = new File(absoluteName);
        if(!f.exists())
        	return false;
        if(f.isDirectory())
        	return deleteRecursive(f);
        return f.delete();
    }
    
    /**
	 * Renames a file or directory 
	 * @param absoluteName the absolute path the file or directory
	 * @param newAbsoluteName the new name of the file or directory
	 * @return true if and only if the file or directory is successfully renamed; false otherwise 
	 */
    public Boolean renameFile(String absoluteName, String newAbsoluteName) {
        LOGGER.info("rename : "+absoluteName+" to "+newAbsoluteName);
        
        if(newAbsoluteName.endsWith("null"))
        	return false;
        
        File f = new File(absoluteName);
        if(!f.exists())
        	return false;        
        return f.renameTo(new File(newAbsoluteName));
    }
    
    /**
	 * Creates a directory 
	 * @param absoluteName the absolute path to the directory
	 * @return true if and only if the directory was successfully created; false otherwise 
	 */
    public Boolean mkDir(String absoluteName) {
    	LOGGER.info("create : "+absoluteName);
        File f = new File(absoluteName);
        if(f.exists())
        	return false;        
        return f.mkdir();
    }
    
    /**
     * By default File#delete fails for non-empty directories, it works like "rm". 
     * We need something a little more brutual - this does the equivalent of "rm -r"
     * @param path Root File Path
     * @return true if the file and all sub files/directories have been removed
     */
    private boolean deleteRecursive(File path) {
        if (!path.exists()) return false;
        boolean ret = true;
        if (path.isDirectory()){
            for (File f : path.listFiles()){
                ret = ret && deleteRecursive(f);
            }
        }
        return ret && path.delete();
    }
    
    private FileWrapper[] buildFilesList(File[] files) {

        FileWrapper[] result = new FileWrapper[files.length];
        for (int i = 0; i < files.length; i++) {
            result[i] = new FileWrapper(files[i].getAbsolutePath(), files[i].getName(), dateFormat( files[i].lastModified()));
            if (files[i].isDirectory()) {
                result[i].setIsDirectory();
            }
        }
        Arrays.sort(result);
        return result;
    }
    
    private static String dateFormat(long dateLong) {
    	SimpleDateFormat sdf = tSDF.get();
    	if(sdf == null) {
    		sdf = new SimpleDateFormat("MMM d, yy");
    		tSDF.set(sdf);
    	}
        return sdf.format(new Date(dateLong));
    }
}

/*
 * FileSystemServiceImpl.java
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
