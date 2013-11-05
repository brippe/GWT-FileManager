package edu.nocccd.filemanager.client.dialogs;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import edu.nocccd.filemanager.client.FileSystemService;
import edu.nocccd.filemanager.client.FileSystemServiceAsync;
import edu.nocccd.filemanager.client.FileSystemTreeWidget;

/**
 * New folder dialog for the file manager
 * @author Brad Rippe (brippe at nocccd.edu)
 *
 */
public class NewDialogWidget extends Composite {

	private FileSystemServiceAsync fileSystemSvc = GWT.create(FileSystemService.class);
	private FileSystemTreeWidget fsTree;
	private DialogBox newDialog;
	private TextBox nameTxtBox;
	private Label descLbl;
    private String title = "New Folder";
    private String folderName = "";
    private String path = "";
    
    /**
     * Creates a help dialog
     */
    public NewDialogWidget(FileSystemTreeWidget fsTree){
        VerticalPanel panel = new VerticalPanel();
        this.fsTree = fsTree;
        descLbl = new Label();
        newDialog = createDialog();
        newDialog.hide();
        panel.add(createOpenButton());        
        initWidget(panel);
    }

    /**
     * Creates the command to open the dialog
     * @return the command
     */
    public Command createOpenCommand(){    	
        return new Command() {
            public void execute() {
            	folderName = "";	// clear folderName
            	path = fsTree.getPath();
            	nameTxtBox.setText("");
            	descLbl.setText("You are creating a folder in the \"" +
    	        		fsTree.getTree().getSelectedItem().getText() + 
    	        		"\" folder. Type the name of your new folder:");
                newDialog.show();
            }
        };
    }
    
    /**
     * Displays the dialog
     */
    public void hide() {
    	newDialog.hide();
    }
    
    /**
     * Resets the dialog's name field
     */
    public void reset() {
    	nameTxtBox.setText("");
    }

    private DialogBox createDialog(){
        VerticalPanel panel = new VerticalPanel();
        DialogBox dialog = new DialogBox(true);
        dialog.setAnimationEnabled(true);
        dialog.center();
        dialog.setText(title);
        dialog.setGlassEnabled(true);
        panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        panel.add(descLbl);
        nameTxtBox = new TextBox();
        panel.add(nameTxtBox);        
        HorizontalPanel hPanel = new HorizontalPanel();        
        hPanel.add(createOKButton());
        hPanel.add(createCancelButton());
        panel.add(hPanel);
        dialog.add(panel);
        
        return dialog;
    }

    private Button createOpenButton(){
        return new Button(title, new ClickHandler(){
            public void onClick(ClickEvent ce) {
            	nameTxtBox.setText("");
            	descLbl.setText("You are creating a folder in the \"" +
            	        		fsTree.getTree().getSelectedItem().getText() + 
            	        		"\" folder.<br/>Type the name of your <b>new</b> folder:");
                newDialog.show();
            }
        });
    }

    private Button createOKButton(){    	
        return new Button("OK", new ClickHandler(){
            public void onClick(ClickEvent ce) {
            	newDialog.hide();
            	folderName = nameTxtBox.getText();
            	if(folderName == null || folderName.length() <= 1) {
            		Window.alert("Your folder has to be more than one character!");
            		return;
            	} else if(folderName.contains("\\") || folderName.contains("/")) {
            		Window.alert("Your folder cannot contain a '\\' or '/'!");
            		return;
            	}
            	
            	/*
            	Client Code can't use File to create or determine if a file exists
            	File f = new File(path+"/"+folderName);
            	if(f.exists()) {
            		Window.alert("The folder '" + folderName + "' already exists!");
            		return;
            	}*/
            	            	
            	if (fileSystemSvc == null) {
                    fileSystemSvc = GWT.create(FileSystemService.class);
                }
                AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {

                    public void onFailure(Throwable thrwbl) {
                        //Something wrong. to be handled.
                    	GWT.log("ERROR: " + thrwbl.getMessage());
                    }
                    public void onSuccess(Boolean result) {
                    	fsTree.updateTree(); // updates tree and table
                    }
                };                
                fileSystemSvc.mkDir(path + FileSystemTreeWidget.getFileSeparator() + folderName, callback);                            	
            }
        });
    }
    
    private Button createCancelButton(){    	
        return new Button("Cancel", new ClickHandler(){
            public void onClick(ClickEvent ce) {
            	newDialog.hide();
            	folderName = "";
            	nameTxtBox.setText("");
            }
        });        
    }
}

/*
 * NewDialogWidget.java
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