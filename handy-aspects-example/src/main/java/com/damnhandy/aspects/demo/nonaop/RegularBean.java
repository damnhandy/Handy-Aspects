/**
 * 
 */
package com.damnhandy.aspects.demo.nonaop;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 * 
 * 
 * @author <a href="ryan@damnhandy.com">Ryan J. McDonough</a>
 * @version $Revision: 1.1 $
 */
public class RegularBean {

	private Timer timer;
	private String timeValue = new Date().toString();
	private String calculatedValue;
	private String name;
	private int number = 0;
	private List<String> items = new ArrayList<String>();
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public RegularBean() {
		TimerTask task = new TimerTask() {
			public void run() {
				setTimeValue(new Date().toString());
			}
		};
		timer = new Timer();
		timer.scheduleAtFixedRate(task,1000,1000);
	}
	
	public RegularBean(String name) {
		this();
		this.name = name;
	}
	/**
	 * @return Returns the items.
	 */
	public List<String> getItems() {
		return items;
	}

	/**
	 * @param items
	 *            The items to set.
	 */
	public void setItems(List<String> items) {
		List<String> oldItems = getItems();
		this.items = items;
		pcs.firePropertyChange("items",oldItems,items);
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            The name to set.
	 */
	public void setName(String name) {
		String oldName = getName();
		this.name = name;
		pcs.firePropertyChange("name",oldName,name);
	}

	/**
	 * @return Returns the timeValue.
	 */
	
	public String getTimeValue() {
		return timeValue;
	}
	
	public void doCalc() {
		int  number   = this.number++;
	    long duration = 6000L;
		try {
			setCalculatedValue("Working...");
			synchronized (this) {
				wait(duration);
			}
			setCalculatedValue(String.valueOf((number * Math.random()) * 27));
		} catch (InterruptedException ex) {
			setCalculatedValue("Exception!");
		}
	}
	
	
	public String getCalculatedValue() {
		return calculatedValue;
	}
	
	public void setCalculatedValue(String calculatedValue) {
		String oldCalculatedValue = getCalculatedValue();
		this.calculatedValue = calculatedValue;
		pcs.firePropertyChange("calculatedValue",oldCalculatedValue,calculatedValue);
	}

	/**
	 * @param timeValue
	 *            The timeValue to set.
	 */
	private void setTimeValue(String timeValue) {
		String oldTimeValue = getTimeValue();
		this.timeValue = timeValue;
		pcs.firePropertyChange("timeValue",oldTimeValue,timeValue);
	}

	public void addItem(String itemName) {
		items.add(itemName);
		pcs.firePropertyChange("items",null,items);
	}
	
	public void removeItem(String itemName) {
		items.remove(itemName);
		pcs.firePropertyChange("items",null,items);
	}

	/* (non-Javadoc)
	 * @see java.beans.PropertyChangeSupport#addPropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see java.beans.PropertyChangeSupport#addPropertyChangeListener(java.lang.String, java.beans.PropertyChangeListener)
	 */
	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(propertyName, listener);
	}

	/* (non-Javadoc)
	 * @see java.beans.PropertyChangeSupport#fireIndexedPropertyChange(java.lang.String, int, boolean, boolean)
	 */
	public void fireIndexedPropertyChange(String propertyName, int index, boolean oldValue, boolean newValue) {
		pcs.fireIndexedPropertyChange(propertyName, index, oldValue, newValue);
	}

	/* (non-Javadoc)
	 * @see java.beans.PropertyChangeSupport#fireIndexedPropertyChange(java.lang.String, int, int, int)
	 */
	public void fireIndexedPropertyChange(String propertyName, int index, int oldValue, int newValue) {
		pcs.fireIndexedPropertyChange(propertyName, index, oldValue, newValue);
	}

	/* (non-Javadoc)
	 * @see java.beans.PropertyChangeSupport#fireIndexedPropertyChange(java.lang.String, int, java.lang.Object, java.lang.Object)
	 */
	public void fireIndexedPropertyChange(String propertyName, int index, Object oldValue, Object newValue) {
		pcs.fireIndexedPropertyChange(propertyName, index, oldValue, newValue);
	}

	/* (non-Javadoc)
	 * @see java.beans.PropertyChangeSupport#firePropertyChange(java.beans.PropertyChangeEvent)
	 */
	public void firePropertyChange(PropertyChangeEvent evt) {
		pcs.firePropertyChange(evt);
	}

	/* (non-Javadoc)
	 * @see java.beans.PropertyChangeSupport#firePropertyChange(java.lang.String, boolean, boolean)
	 */
	public void firePropertyChange(String propertyName, boolean oldValue, boolean newValue) {
		pcs.firePropertyChange(propertyName, oldValue, newValue);
	}

	/* (non-Javadoc)
	 * @see java.beans.PropertyChangeSupport#firePropertyChange(java.lang.String, int, int)
	 */
	public void firePropertyChange(String propertyName, int oldValue, int newValue) {
		pcs.firePropertyChange(propertyName, oldValue, newValue);
	}

	/* (non-Javadoc)
	 * @see java.beans.PropertyChangeSupport#firePropertyChange(java.lang.String, java.lang.Object, java.lang.Object)
	 */
	public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
		pcs.firePropertyChange(propertyName, oldValue, newValue);
	}

	/* (non-Javadoc)
	 * @see java.beans.PropertyChangeSupport#getPropertyChangeListeners()
	 */
	public PropertyChangeListener[] getPropertyChangeListeners() {
		return pcs.getPropertyChangeListeners();
	}

	/* (non-Javadoc)
	 * @see java.beans.PropertyChangeSupport#getPropertyChangeListeners(java.lang.String)
	 */
	public PropertyChangeListener[] getPropertyChangeListeners(String propertyName) {
		return pcs.getPropertyChangeListeners(propertyName);
	}

	/* (non-Javadoc)
	 * @see java.beans.PropertyChangeSupport#hasListeners(java.lang.String)
	 */
	public boolean hasListeners(String propertyName) {
		return pcs.hasListeners(propertyName);
	}

	/* (non-Javadoc)
	 * @see java.beans.PropertyChangeSupport#removePropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see java.beans.PropertyChangeSupport#removePropertyChangeListener(java.lang.String, java.beans.PropertyChangeListener)
	 */
	public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(propertyName, listener);
	}
}
