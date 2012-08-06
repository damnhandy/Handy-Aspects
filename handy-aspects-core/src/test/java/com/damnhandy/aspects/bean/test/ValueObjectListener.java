/**
 * 
 */
package com.damnhandy.aspects.bean.test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * @author ryan
 *
 */
public class ValueObjectListener implements PropertyChangeListener {
	private boolean changed = false;
	
	/* (non-Javadoc)
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		changed = !evt.getNewValue().equals(evt.getOldValue());
		System.out.println("New: "+evt.getNewValue()+ " Old: "+evt.getOldValue());
	}
	
	public boolean hasChanged() {
		return changed;
	}
	
	public void reset() {
		changed = false;
	}

}
