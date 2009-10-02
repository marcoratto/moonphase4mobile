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
 */
package uk.co.marcoratto.j2me.moonphase;


import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import uk.co.marcoratto.j2me.log.Logger;


public class MoonCanvas extends Canvas {
    private Display parentDisplay;
    
    private static Logger log = Logger.getLogger(MoonCanvas.class);

    /** buffer image of the screen */
    private Image image;
    
    private String title;
    
    public MoonCanvas(Display display, Image i, String title) {
        super();
        this.parentDisplay = display;
        this.title = title;
        image = i;
        this.parentDisplay.setCurrent(this);
    }

    public void setImage(Image i) {
    	this.image = i;
    }

    public void paint(Graphics g) {
        int screenWidth = getWidth();
        int screenHeight = getHeight();
        
        log.debug("screenWidth=" + screenWidth);
        log.debug("screenHeight=" + screenHeight);

        g.setColor(0x000000);
        g.fillRect(0, 0, screenWidth, screenHeight);
                
		int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();
        log.debug("imageWidth=" + imageWidth);
        log.debug("imageHeight=" + imageHeight);
        
        int imageX = (screenWidth - imageWidth) / 2;
        int imageY = (screenHeight - imageHeight) / 2;
        log.debug("imageX=" + imageX);
        log.debug("imageY=" + imageY);
        
        g.drawImage(image, imageX, imageY, Graphics.LEFT | Graphics.TOP);
        
        g.setColor(0xFFFFFF);
        g.setFont(Font.getFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
        int fontSize = Font.getDefaultFont().getHeight();
        log.debug("fontSize=" + fontSize);
        int stringX = screenWidth / 2;
        int stringY = fontSize;
        log.debug("stringX=" + stringX);
        log.debug("stringY=" + stringY);
        g.drawString(this.title, stringX, stringY, Graphics.TOP | Graphics.HCENTER);
 
    }

    protected void keyPressed(int keycode) {
    }
    
}
