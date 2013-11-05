package edu.nocccd.filemanager.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.MenuBar;

import edu.nocccd.filemanager.client.dialogs.AboutDialogWidget;
import edu.nocccd.filemanager.client.dialogs.HelpDialogWidget;
import edu.nocccd.filemanager.client.dialogs.NewDialogWidget;
import edu.nocccd.filemanager.client.dialogs.UploadDialogWidget;


/**
 * The main top application menu for the application.
 * @author Brad Rippe (brippe at nocccd.edu), Firas Kassem
 *
 */
public class MainMenuWidget extends Composite {
		
	private MenuBar menu;
	private NewDialogWidget newDialog;
	private UploadDialogWidget uploadDialog;
    private HelpDialogWidget helpDialog;
    private AboutDialogWidget aboutDialog;
    private boolean isAdmin = true;
    
    /**
     * Creates the main file menu
     */
    public MainMenuWidget(FileSystemTreeWidget fsTree, boolean isAdmin) {
    	this.isAdmin = isAdmin;
        helpDialog = new HelpDialogWidget();
        aboutDialog = new AboutDialogWidget();
        if(isAdmin) {
        	newDialog = new NewDialogWidget(fsTree);
        	uploadDialog = new UploadDialogWidget(fsTree);
        	fsTree.setMainMenu(this);
        }

        createMenu();
        initWidget(menu);
    }
    
    /**
     * Returns the new directory dialog
     * @return the new directory dialog
     */
    public NewDialogWidget getNewDialogWidget() {
    	return newDialog;
    }
    
    /**
     * Returns the upload file dialog
     * @return the upload file dialog
     */
    public UploadDialogWidget getUploadDialogWidget() {
    	return uploadDialog;
    }
    
    /**
     * Sets the about dialog message
     * @param msg html about message
     */
    public void setAboutMsg(String msg) {
    	aboutDialog.setMsgTxt(msg);
    }
    
    /**
     * Sets the help dialog message
     * @param msg html help message
     */
    public void setHelpMsg(String msg) {
    	helpDialog.setMsgTxt(msg);
    }

    private void createMenu() {
        menu = new MenuBar();
        /* Admin Features */
        if(isAdmin) {
	        MenuBar fileMenu = new MenuBar(true);
	        fileMenu.addItem("New folder", newDialog.createOpenCommand());
	        /*fileMenu.addItem("Rename", new Command() {
	        	   	public void execute() {
	        	   		Window.alert("Rename");
	        	   	}
	        	});*/
	        fileMenu.addItem("Upload", uploadDialog.createOpenCommand());
	        menu.addItem("File", fileMenu);
        }
        /* END Admin Features */
        
        MenuBar helpMenu = new MenuBar(true);
        helpMenu.addItem("Help", helpDialog.createOpenCommand());
        helpMenu.addItem("About", aboutDialog.createOpenCommand());
        menu.addItem("Help", helpMenu);               
    }
}

/*
 * MainMenuWidget.java
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