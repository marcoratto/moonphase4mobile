/*
 * Copyright (C) 2008 Marco Ratto
 *
 * This file is part of the project EasterJ2ME.
 *
 * EasterJ2ME is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * any later version.
 *
 * EasterJ2ME is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with EasterJ2ME; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package uk.co.marcoratto.j2me.info;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;
import javax.microedition.midlet.MIDlet;

import uk.co.marcoratto.j2me.i18n.I18N;

public class Info implements CommandListener {

	 private static Info istanza = null;
	 
	 private MIDlet parent;
	 private Display display;
	 private Displayable previous;
	 
	 private final Form form;
     private StringBuffer propbuf;
	 
     private final Command backCommand;
     
     private long free = 0;
     private long total = 0;

    /**
     * Do not allow anyone to create this class
     */
    private Info() {
    	readMemory();
        
        backCommand = new Command(I18N.getInstance().translate("info.button.back"), Command.BACK, 2);
        
        form = new Form(I18N.getInstance().translate("info.title"));
        propbuf = new StringBuffer(50);
        form.append(I18N.getInstance().translate("info.free.memory") + free + "\n");
        form.append(I18N.getInstance().translate("info.total.memory") + total + "\n");
        
        form.append(showProp("CHAPI-Version"));
        form.append(showProp("file.separator"));
        form.append(showProp("microedition.commports"));
		form.append(showProp("microedition.configuration"));
		form.append(showProp("microedition.encoding"));
		form.append(showProp("microedition.hostname"));
		form.append(showProp("microedition.io.file.FileConnection.version"));
		form.append(showProp("microedition.jtwi.version"));
		form.append(showProp("microedition.locale"));
		form.append(showProp("microedition.location.version"));
		form.append(showProp("microedition.m3g.version"));
		form.append(showProp("microedition.pim.version"));
		form.append(showProp("microedition.platform"));
		form.append(showProp("microedition.platform"));
		form.append(showProp("microedition.profiles"));
		form.append(showProp("microedition.sip.version"));
		form.append(showProp("microedition.smartcardslots"));
		form.append(showProp("wireless.messaging.mms.mmsc"));
		form.append(showProp("wireless.messaging.sms.smsc"));     
		form.addCommand(backCommand);
		form.setCommandListener(this);
		
	 }

    public static Info getInstance() {
      if (istanza == null) {
        try {
          istanza = new Info();
        } catch (Exception e) {
          System.out.println(e);
        }
      }
      return istanza;
    }

    public void show(MIDlet m) {
       this.parent = m;
       this.display = Display.getDisplay(m);
       this.previous = this.display.getCurrent();
        
       readMemory();
       
       form.set(0, new StringItem("", I18N.getInstance().translate("info.free.memory") + free + "\n"));
	   display.setCurrent(form);
	}

    /**
     * Show a property.
     */
    private String showProp(String prop) {
        String value = System.getProperty(prop);
        propbuf.setLength(0);
        propbuf.append(prop);
        propbuf.append("=");
        if (value == null) {
            propbuf.append("<undefined>");
        } else {
            propbuf.append("\"");
            propbuf.append(value);
            propbuf.append("\"");
        }
        propbuf.append("\n");
        return propbuf.toString();
    }

    public void commandAction(Command command, Displayable disp) {
        if (command == this.backCommand) {
        	System.out.println("back");
        	this.display.setCurrent(this.previous);
        }
    }
    
    private void readMemory() {
    	Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        this.free = runtime.freeMemory();
        this.total = runtime.totalMemory();
    }
}
