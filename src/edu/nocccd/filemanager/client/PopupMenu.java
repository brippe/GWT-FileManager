package edu.nocccd.filemanager.client;

import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;

/**
 * PopupMenu for right clicks over the table view of the application for
 * uploading and creating new directories in the application.
 * @author Brad Rippe (brippe at nocccd.edu)
 *
 */
public class PopupMenu extends DecoratedPopupPanel {
	
	/**
	 * Creates a popup menu for the admin features.
	 * This popup uses the commands from the main menu
	 * @param fsTree the application's FileSystemTreeWidget
	 */
	public PopupMenu(FileSystemTreeWidget fsTree) {
		super(true);		
    	MenuBar popMenuBar = new MenuBar(true);
		MenuItem newFolderItem = new MenuItem("New Folder", fsTree.getMainMenu().getNewDialogWidget().createOpenCommand());		
		MenuItem uploadItem = new MenuItem("Upload", fsTree.getMainMenu().getUploadDialogWidget().createOpenCommand());		
		popMenuBar.addItem(newFolderItem);
		popMenuBar.addItem(uploadItem);
		setAutoHideEnabled(true);		
		setWidget(popMenuBar);		  
	}	
}

/*
 * PopupMenu.java.java
 * 
 * Copyright (c) Jul 31, 2012 North Orange County Community College District. 
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