package edu.nocccd.filemanager.client.dialogs;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * File Manager's about dialog box. Used to provide information about the 
 * file manager to the users.
 * 
 * @author Brad Rippe (brippe at nocccd.edu)
 */
public class AboutDialogWidget extends Composite {

	private DialogBox aboutDialog;
    private String title = "About";
    private HTML msgTxt;
    
    /**
     * Creates an about dialog for the file manager
     */
    public AboutDialogWidget(){
        VerticalPanel panel = new VerticalPanel();
        aboutDialog = createDialog();
        aboutDialog.hide();
        panel.add(createOpenButton());
        initWidget(panel);        
    }
        
    /**
     * Hides the dialog box
     */
    public void hide() {
    	aboutDialog.hide();
    }

    /**
     * Displays the dialog
     * @return the command for opening the dialog
     */
    public Command createOpenCommand(){
        return new Command() {
            public void execute() {
                aboutDialog.show();
            }
        };
    }
    
    /**
     * Sets the html message for the about dialog
     * @param msg html message
     */
    public void setMsgTxt(String msg) {
    	msgTxt.setHTML(msg);
    }
    
    private DialogBox createDialog(){
        VerticalPanel panel = new VerticalPanel();
        DialogBox dialog = new DialogBox(true);
        dialog.setAnimationEnabled(true);
        dialog.center();
        dialog.setText(title);
        dialog.setGlassEnabled(true);
        msgTxt = new HTML();
        panel.add(msgTxt);
        panel.add(createCloseButton());
        dialog.add(panel);

        return dialog;
    }    

    private Button createOpenButton(){
        return new Button(title, new ClickHandler(){
            public void onClick(ClickEvent ce) {
                aboutDialog.show();
            }
        });
    }

    private Button createCloseButton(){
        return new Button("close", new ClickHandler(){
            public void onClick(ClickEvent ce) {
               aboutDialog.hide();
            }
        });
    }
}

/*
 * AboutDialogWidget.java
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
