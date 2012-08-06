package com.damnhandy.aspects.bean;

import java.beans.IntrospectionException;
import java.beans.PropertyChangeListener;
import java.lang.reflect.InvocationTargetException;

/**
 * The interface that will be introduced to the advised class so that it 
 * can support bound properties. 
 * 
 * @author Ryan J. McDonough
 * @since 1.0
 */
public interface JavaBean {

	/**
	 * Adds a new PropertyChangeListener to the target
	 * @param listener
	 * @see java.beans.PropertyChangeSupport#addPropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public abstract void addPropertyChangeListener(
			PropertyChangeListener listener);

	/**
	 * Adds a new PropertyChangeListener to the target for the given property name
	 * @param propertyName
	 * @param listener
	 * @see java.beans.PropertyChangeSupport#addPropertyChangeListener(java.lang.String, java.beans.PropertyChangeListener)
	 */
	public abstract void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener);
	
	/**
	 * 
	 * @param obj
	 * @return
	 * @throws IntrospectionException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public String getFieldName(Object obj) throws IntrospectionException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException;
	/**
	 * @param propertyName
	 * @param index
	 * @param oldValue
	 * @param newValue
	 * @see java.beans.PropertyChangeSupport#fireIndexedPropertyChange(java.lang.String, int, java.lang.Object, java.lang.Object)
	 */
	public abstract void fireIndexedPropertyChange(String propertyName,
			int index, Object oldValue, Object newValue);

	/**
	 * @param propertyName
	 * @param oldValue
	 * @param newValue
	 * @see java.beans.PropertyChangeSupport#firePropertyChange(java.lang.String, java.lang.Object, java.lang.Object)
	 */
	public abstract void firePropertyChange(String propertyName,
			Object oldValue, Object newValue);

	/**
	 * @return
	 * @see java.beans.PropertyChangeSupport#getPropertyChangeListeners()
	 */
	public abstract PropertyChangeListener[] getPropertyChangeListeners();

	/**
	 * @param propertyName
	 * @return
	 * @see java.beans.PropertyChangeSupport#getPropertyChangeListeners(java.lang.String)
	 */
	public abstract PropertyChangeListener[] getPropertyChangeListeners(
			String propertyName);

	/**
	 * @param propertyName
	 * @return
	 * @see java.beans.PropertyChangeSupport#hasListeners(java.lang.String)
	 */
	public abstract boolean hasListeners(String propertyName);



	/**
	 * @param listener
	 * @see java.beans.PropertyChangeSupport#removePropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public abstract void removePropertyChangeListener(
			PropertyChangeListener listener);

	/**
	 * @param propertyName
	 * @param listener
	 * @see java.beans.PropertyChangeSupport#removePropertyChangeListener(java.lang.String, java.beans.PropertyChangeListener)
	 */
	public abstract void removePropertyChangeListener(String propertyName,
			PropertyChangeListener listener);
}