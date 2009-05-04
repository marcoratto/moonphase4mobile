/*
 * Copyright (C) 2008 Marco Ratto
 *
 * This file is part of the project Beat Swatch Clock For J2ME.
 *
 * Beat Swatch Clock For J2ME is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * any later version.
 *
 * Beat Swatch Clock For J2ME is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Beat Swatch Clock For J2ME; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */package uk.co.marcoratto.j2me.moonphase;

import java.util.Calendar;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.midlet.MIDlet;

import uk.co.marcoratto.j2me.i18n.I18N;
import uk.co.marcoratto.j2me.log.Logger;

/**
 * @author Marco Ratto
 */
public class YearForm implements CommandListener{
	
	private static Logger logger = Logger.getLogger(YearForm.class);

    private static YearForm instance;

    private final Form form;
    private final DateField dateField;
    private final Command confirmCommand;
    private final Command cancelCommand;
    
    private Displayable previous;
    private Display display;
    private MIDlet parent;
    
    private Calendar returnValue;
        
    public static YearForm getInstance() {
        if(instance == null) {
        	logger.debug("YearForm private istance.");
        	instance = new YearForm();
        }
        return instance;
    }

    public YearForm () {
        confirmCommand = new Command("Ok", Command.BACK, 2);
        cancelCommand = new Command("Cancel", Command.EXIT, 2);
        
		dateField = new DateField(I18N.getInstance().translate("date.label"), DateField.DATE);
		dateField.setDate(Calendar.getInstance().getTime());
		
        // Form related setup;
        form = new Form("Year");
        form.append(dateField);
        form.addCommand(confirmCommand);
        form.addCommand(cancelCommand);

        form.setCommandListener(this);
    }
    
    public void show(MIDlet m) {
    	this.parent = m;
        this.display = Display.getDisplay(this.parent);
        this.previous = this.display.getCurrent();
        this.parent = m;

        this.returnValue = null;
        
        this.display.setCurrent(form);
    }

    public void commandAction(Command command, Displayable disp) {
            if (command == confirmCommand) {
            	logger.debug("Confirm pressed.");  
            	this.returnValue = Calendar.getInstance();
            	this.returnValue.setTime(this.dateField.getDate());
            	this.display.setCurrent(this.previous);
            } else if (command == cancelCommand) {
            	logger.debug("Cancel pressed.");
            	this.returnValue = null;
            }
    }
    
    public Calendar getDate() {
    	if (this.returnValue == null) {
        	logger.debug("Date=null");
    		return null;
    	}
    	logger.debug("Date=" + this.returnValue.getTime().toString());
    	return this.returnValue;
    }
}
