/**
 * 
 */
package com.damnhandy.swing.util;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.UIManager;
import javax.swing.border.Border;

/**
 * @author ryan
 *
 */
public class UIUtils {
	public static final String JGOODIES_WINDOWS = 
		"com.jgoodies.looks.windows.WindowsLookAndFeel";
	
	public static Border createTitledBorder(String title) {
		Border etchedBorder = 
    		BorderFactory.createEtchedBorder();
    	Border border = 
    		BorderFactory.createTitledBorder(etchedBorder,title);
    	return border;
	}

	public static void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(JGOODIES_WINDOWS);
		} catch(Exception e) {
			System.out.println("Goodies LnF not available, using System");
			try {
				String name = UIManager.getSystemLookAndFeelClassName();
				UIManager.setLookAndFeel(name);
			} catch (Exception e1) {
				e1.printStackTrace();
			} 
		}
	}
	
	public static void setWaitCursor(Component c) {
		c.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	}
	
	public static void setNormalCursor(Component c) {
		c.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	
    /**
     * Locates the given component on the screen's center.
     * 
     * @param component   the component to be centered
     */
    public static void centerWindowOnScreen(Component component) {
        Dimension paneSize = component.getSize();
        Dimension screenSize = component.getToolkit().getScreenSize();
        component.setLocation(
            (screenSize.width  - paneSize.width)  / 2,
            ((screenSize.height - paneSize.height) / 2) - 80);
    }
}

