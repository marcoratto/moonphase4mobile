/*
 * Copyright (C) 2008 Marco Ratto
 *
 * This file is part of the project Moon Phase Calculator For Mobile Phone.
 *
 * Moon Phase Calculator For Mobile Phone is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * any later version.
 *
 * Moon Phase Calculator For Mobile Phone is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Moon Phase Calculator For Mobile Phone; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */package uk.co.marcoratto.j2me.moonphase;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.Ticker;
import javax.microedition.midlet.MIDlet;

import uk.co.marcoratto.j2me.i18n.I18N;

/**
 * @author Marco Ratto
 */
public class PictureForm implements CommandListener{

    private static PictureForm instance;

    private final Command backCommand;

    private Displayable previous;
    private Display display;
    private MIDlet parent;
    
	private MoonCanvas canvas;
	
    public static PictureForm getInstance() {
        if(instance == null){
        	instance = new PictureForm();
        }
        return instance;
    }

    private PictureForm() {
        backCommand = new Command("Back", Command.BACK, 2);       
    }

    public void show(MIDlet m, Image i, String title) {
    	this.parent = m;
        this.display = Display.getDisplay(this.parent);
        this.previous = this.display.getCurrent();

    	this.canvas = new MoonCanvas(display, i, title);
    	this.canvas.addCommand(backCommand);
    	this.canvas.setCommandListener(this);
    	this.canvas.setTicker(new Ticker(I18N.getInstance().translate("appl.title")));
    	// this.canvas.setImage(i);
        this.display.setCurrent(canvas);
    }

    public void commandAction(Command command, Displayable disp) {
            if (command == backCommand) {
            	System.out.println("back");
            	this.display.setCurrent(this.previous);
            }     
    }
    
}
