package edu.nocccd.filemanager.client.table;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.TableCellElement;
import com.google.gwt.dom.client.TableRowElement;
import com.google.gwt.event.dom.client.MouseEvent;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.FlexTable;

/**
 * Flex table that can handle double clicks
 * @author Brad Rippe (brippe at nocccd.edu)
 */
public class FileTable extends FlexTable {
	
	// FileCell is a way to create a cell
	// cell constructor is protected in HTMLTable.Cell
	// workaround
	class FileCell extends Cell {
		protected FileCell(int rowIndex, int cellIndex) {
			super(rowIndex, cellIndex);
		}
	}
	
	public Cell getCellForEvent(MouseEvent<? extends EventHandler> event) {
        Element td = getEventTargetCell(Event.as(event.getNativeEvent()));
        if (td == null) {
          return null;
        }

        int row = TableRowElement.as(td.getParentElement()).getSectionRowIndex();
        int column = TableCellElement.as(td).getCellIndex();
        return new FileCell(row, column);
    }
}

/*
 * FileTable.java
 * 
 * Copyright (c) Jul 3, 2013 North Orange County Community College District. All
 * rights reserved.
 * 
 * THIS SOFTWARE IS PROVIDED "AS IS," AND ANY EXPRESS OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE, ARE EXPRESSLY DISCLAIMED. IN NO EVENT SHALL
 * NORTH ORANGE COUNTY COMMUNITY COLLEGE DISTRICT OR ITS EMPLOYEES BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED, THE COSTS OF PROCUREMENT OF SUBSTITUTE
 * GOODS OR SERVICES; LOSS OF USE, DATA OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED IN ADVANCE OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * 
 * Redistribution and use of this software in source or binary forms, with or
 * without modification, are permitted, provided that the following conditions
 * are met.
 * 
 * 1. Any redistribution must include the above copyright notice and disclaimer
 * and this list of conditions in any related documentation and, if feasible, in
 * the redistributed software.
 * 
 * 2. Any redistribution must include the acknowledgment, "This product includes
 * software developed by North Orange County Community College District, in any
 * related documentation and, if feasible, in the redistributed software."
 * 
 * 3. The names "NOCCCD" and "North Orange County Community College District"
 * must not be used to endorse or promote products derived from this software.
 */