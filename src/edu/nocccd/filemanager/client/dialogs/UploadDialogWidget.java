package edu.nocccd.filemanager.client.dialogs;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.VerticalPanel;

import edu.nocccd.filemanager.client.FileSystemTreeWidget;
import gwtupload.client.IUploadStatus.Status;
import gwtupload.client.IUploader;
import gwtupload.client.MultiUploader;

/**
 * Creates the upload dialog for file uploads
 * @author Brad Rippe (brippe at nocccd.edu)
 *
 */
public class UploadDialogWidget extends Composite {

	private FileSystemTreeWidget fsTree;
	private DialogBox uploadDialog;
	private Hidden hiddenPath = new Hidden("aPath");
    private String title = "File Upload";    
    private String path = "";
    private MultiUploader mUploader = new MultiUploader();  
    
    private IUploader.OnFinishUploaderHandler onFinishUploaderHandler = new IUploader.OnFinishUploaderHandler() {		
		public void onFinish(IUploader uploader) {
			if(uploader.getStatus() == Status.SUCCESS) {
				fsTree.updateTree();
			}			
		}
	};
    
    /**
     * Creates a upload dialog
     * @param fsTree the application's FileSystemTreeWidget
     */
    public UploadDialogWidget(FileSystemTreeWidget fsTree){
        VerticalPanel panel = new VerticalPanel();
        uploadDialog = createDialog();
        uploadDialog.hide();
        panel.add(createOpenButton());
        this.fsTree = fsTree;
        initWidget(panel);
    }

    /**
     * Creates the command to open the dialog
     * @return the command
     */
    public Command createOpenCommand(){    	
        return new Command() {
            public void execute() {
            	path = fsTree.getPath();
            	hiddenPath.setValue(path);
            	mUploader.reset();
            	uploadDialog.show();
            }
        };
    }
    
    /**
     * Displays the dialog
     * @return the command for opening the dialog
     */
    public void hide() {    	    	
    	uploadDialog.hide();
    }

    private DialogBox createDialog(){
    	VerticalPanel vPanel = new VerticalPanel();
    	vPanel.setSpacing(4);
    	vPanel.setWidth("275px");
        mUploader.addOnFinishUploadHandler(onFinishUploaderHandler);
        mUploader.add(hiddenPath);
        vPanel.add(mUploader);
        Button btn = new Button("Close", new ClickHandler() {
            public void onClick(ClickEvent event) {            	
            	uploadDialog.hide();
            }
        });
        vPanel.add(btn);
        vPanel.setCellHorizontalAlignment(btn,  HasHorizontalAlignment.ALIGN_CENTER);
        
        DialogBox dialog = new DialogBox(false);
        dialog.setAnimationEnabled(true);
        dialog.center();
        dialog.setText(title);
        dialog.setGlassEnabled(true);
        dialog.add(vPanel);
        return dialog;
    }

    private Button createOpenButton(){
        return new Button(title, new ClickHandler(){
            public void onClick(ClickEvent ce) {
            	mUploader.reset();
            	uploadDialog.show();
            }
        });
    }
}

/*
 * UploadDialogWidget.java
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
