package edu.nocccd.filemanager.client;

import java.util.HashMap;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;

/**
 * File Manager has two modes: Admin and normal mode.
 * The admin mode allows you to upload, rename, and delete files
 * The normal mode only allows you to download files.
 * Entry point classes define <code>onModuleLoad()</code>.
 * @author Brad Rippe (brippe at nocccd.edu), Firas Kassem
 */
public class Filemanager implements EntryPoint {
	
	private static HashMap<String, String> properties;
	private PropertiesServiceAsync propertiesSvc = GWT.create(PropertiesService.class);
	private static FileSystemTreeWidget fsTree;
	private static FileTableWidget fsTable;
	private static MainMenuWidget mmw;
	
	/**
	 * This is the entry point method.
	 * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
	 */	
	public void onModuleLoad() {
		
		String aparam = Window.Location.getParameter("t"); 	// 'a' for admin mode -- admin mode can upload
		boolean adm = false;								// set to false
		if(aparam != null)
			adm = true;
		
		getProperties();	// retrieve properties from the servlet

		DockLayoutPanel mainPanel = new DockLayoutPanel(Unit.EM);		
		FlowPanel headerPanel = new FlowPanel();
				  
		//Files Table
        fsTable = new FileTableWidget(adm);        
        HTML footer = new HTML();
        //Files Tree with a temp root name till getProperties returns
        fsTree = new FileSystemTreeWidget("Root", fsTable, footer);
        //MainMenu
		mmw = new MainMenuWidget(fsTree, adm);		
        headerPanel.add(mmw);   
        mainPanel.addNorth(headerPanel, 2);
        
        //Footer on south        
        DOM.setElementAttribute(footer.getElement(), "id", "footer");
        mainPanel.addSouth(footer, 2);
                
        //Main content of main layout is a Navigator(west) + content(east), with a splitter in between.
        SplitLayoutPanel mainContentPanel = new SplitLayoutPanel();

        //FileSystem Tree on west
        ScrollPanel treeScrollPanel = new ScrollPanel();        
        treeScrollPanel.add(fsTree);        
        mainContentPanel.addWest(treeScrollPanel, 200);

        //Main content area
        DockLayoutPanel contentMainPanel = new DockLayoutPanel(Unit.EM);
        ScrollPanel contentScrollPanel = new ScrollPanel();

        //Adding FilesTable
        contentScrollPanel.add(fsTable);
        contentMainPanel.add(contentScrollPanel);
        DOM.setElementAttribute(contentMainPanel.getElement(), "id", "content");
        mainContentPanel.add(contentMainPanel);
        
        mainPanel.add(mainContentPanel);
        RootLayoutPanel.get().add(mainPanel);        
		RootPanel.get("loading").setVisible(false);		
	}
	
	private void getProperties() {
        if (propertiesSvc == null) {
        	propertiesSvc = GWT.create(PropertiesService.class);
        }
        AsyncCallback<HashMap<String, String>> callback = new AsyncCallback<HashMap<String, String>>() {

            public void onFailure(Throwable thrwbl) {
            	GWT.log("ERROR: " + thrwbl.getMessage());
            	Window.alert("ERROR: " + thrwbl.getMessage());
            }
            public void onSuccess(HashMap<String, String> results) {
            	properties = results;
            	if(properties.size() > 0) {
            		FileSystemTreeWidget.setProperties(properties);
            		fsTree.createFileSystemTree();
            		FileTableWidget.setDownloadServlet(properties.get("download.servlet"));
            		mmw.setAboutMsg(properties.get("about.msg"));
            		mmw.setHelpMsg(properties.get("help.msg"));
            	} else {
            		Window.alert("Please contact your administrator!\nCan't get configuration. Properties file is missing!");
            	}
            }
        };
        propertiesSvc.getProperties(callback);        
    }
}

/*
 * Filemanager.java
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