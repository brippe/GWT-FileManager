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
 * Help dialog for the file manager
 * @author Brad Rippe (brippe at nocccd.edu), Firas Kassem
 *
 */
public class HelpDialogWidget extends Composite {

	private DialogBox helpDialog;
    private String title = "Help";
    private HTML msgTxt;
    
    /**
     * Creates a help dialog
     */
    public HelpDialogWidget(){
        VerticalPanel panel = new VerticalPanel();
        helpDialog = createDialog();
        helpDialog.hide();
        panel.add(createOpenButton());
        initWidget(panel);        
    }

    /**
     * Creates the command to open the dialog
     * @return the command
     */
    public Command createOpenCommand() {
        return new Command() {
            public void execute() {            	
                helpDialog.show();
            }
        };
    }
    
    /**
     * Displays the dialog
     * @return the command for opening the dialog
     */
    public void hide() {
    	helpDialog.hide();
    }
    
    /**
     * Sets the html message for the help dialog
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
                helpDialog.show();
            }
        });
    }

    private Button createCloseButton(){
        return new Button("close", new ClickHandler(){
            public void onClick(ClickEvent ce) {
                helpDialog.hide();
            }
        });
    }
}

/*
 * HelpDialogWidget.java
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