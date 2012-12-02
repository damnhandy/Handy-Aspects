package com.damnhandy.aspects.bean.impl;

import java.beans.PropertyChangeSupport;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyChangeListener;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.util.Collection;
import org.aspectj.lang.reflect.FieldSignature;
import com.damnhandy.aspects.bean.JavaBean;

/**
 * An abstract aspect which which declares an instance of PropertyChangeSupport along
 * with a series of delegate methods. Sub-classes of this aspect must define the
 * point cuts and the inter-type declaration.
 * 
 * @author Ryan J. McDonough
 * @since 1.0
 * @version $Revision: 1.1 $
 *
 */
public abstract aspect AbstractJavaBeanAspect
{

   /**
    * 
    * 
    * @return
    */
   private PropertyChangeSupport JavaBean.pcs = new PropertyChangeSupport(this);

   /**
    * 
    * 
    * @return
    */
   private BeanInfo JavaBean.beanInfo;

   /**
    * A pointcut which picks out setter methods and/or the setting of a 
    * JavaBean property.
    * 
    * @param bean - the target JavaBean
    */
   abstract pointcut setters(JavaBean bean);

   /**
    * Abstract pointcut which is used to identify mutator methods which 
    * are responsible for modify an underlying collection property
    * @param collection - the underlying collection which is being modified
    * @param bean - the JavaBean instance which contains the called method
    */
   abstract pointcut collectionMutator(@SuppressWarnings("rawtypes") Collection collection, JavaBean bean);

   /**
    * 
    */
   boolean around(@SuppressWarnings("rawtypes") Collection collection, JavaBean bean) : collectionMutator(collection,bean) {
      try
      {
         String propertyName = bean.getFieldName(collection);
         /*
          * If the name is found, fire an event
          */
         if (propertyName != null)
         {
            boolean result = proceed(collection, bean);
            bean.firePropertyChange(propertyName, null, collection);
            return result;
         }
         /*
          * otherwise, proceed as usual
          */
         return proceed(collection, bean);
      }
      catch (Exception e)
      {
         return false;
      }
   }

   /**
    * 
    */
   void around(JavaBean bean) : setters(bean) {
      FieldSignature fieldSig = (FieldSignature) thisJoinPoint.getSignature();
      Field field = fieldSig.getField();
      try
      {
         Object oldValue = field.get(bean);
         proceed(bean);
         Object newValue = field.get(bean);
         bean.firePropertyChange(field.getName(), oldValue, newValue);
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   /**
    * 
    * @return
    */
   public BeanInfo JavaBean.getBeanInfo() throws IntrospectionException
   {
      if (beanInfo == null)
      {
         beanInfo = Introspector.getBeanInfo(getClass());
      }
      return beanInfo;
   }

   /**
    * Uses the BeanInfo class to get the property name of a given object. This is
    * achieved by reading the PropertyDescriptors from the Bean class and seeing if 
    * the object being called is in fact a member of the bean.  
    * 
    * TODO: Not sure if this is the brightest way to do this. Is there a better way? 
    * Ideally, this would be achieved in a pointcut.
    * 
    * @param obj - the object which we want to know the field name of
    * @return the field name of the object within this bean instance
    * @throws IntrospectionException
    * @throws InvocationTargetException 
    * @throws IllegalAccessException 
    * @throws IllegalArgumentException 
    */
   public String JavaBean.getFieldName(Object potentialField) throws IntrospectionException, IllegalArgumentException,
         IllegalAccessException, InvocationTargetException
   {
      PropertyDescriptor[] fields = getBeanInfo().getPropertyDescriptors();
      for (int i = 0; i < fields.length; i++)
      {
         Method getter = fields[i].getReadMethod();
         Object propertyValue = getter.invoke(this, new Object[] {});
         if (potentialField.equals(propertyValue))
         {
            return fields[i].getName();
         }
      }
      return null;
   }

   /**
    * Adds a new PropertyChangeListener to the target
    * @param listener
    * @see java.beans.PropertyChangeSupport#addPropertyChangeListener(java.beans.PropertyChangeListener)
    */
   public void JavaBean.addPropertyChangeListener(PropertyChangeListener listener)
   {
      pcs.addPropertyChangeListener(listener);
   }

   /**
    * Adds a new PropertyChangeListener to the target for the given property name
    * @param propertyName
    * @param listener
    * @see java.beans.PropertyChangeSupport#addPropertyChangeListener(java.lang.String, java.beans.PropertyChangeListener)
    */
   public void JavaBean.addPropertyChangeListener(String propertyName, PropertyChangeListener listener)
   {
      pcs.addPropertyChangeListener(propertyName, listener);
   }

   /**
    * @param propertyName
    * @param index
    * @param oldValue
    * @param newValue
    * @see java.beans.PropertyChangeSupport#fireIndexedPropertyChange(java.lang.String, int, java.lang.Object, java.lang.Object)
    */
   public void JavaBean.fireIndexedPropertyChange(String propertyName, int index, Object oldValue, Object newValue)
   {
      pcs.fireIndexedPropertyChange(propertyName, index, oldValue, newValue);
   }

   /**
    * @param propertyName
    * @param oldValue
    * @param newValue
    * @see java.beans.PropertyChangeSupport#firePropertyChange(java.lang.String, java.lang.Object, java.lang.Object)
    */
   public void JavaBean.firePropertyChange(String propertyName, Object oldValue, Object newValue)
   {
      pcs.firePropertyChange(propertyName, oldValue, newValue);
   }

   /**
    * @return
    * @see java.beans.PropertyChangeSupport#getPropertyChangeListeners()
    */
   public PropertyChangeListener[] JavaBean.getPropertyChangeListeners()
   {
      return pcs.getPropertyChangeListeners();
   }

   /**
    * @param propertyName
    * @return
    * @see java.beans.PropertyChangeSupport#getPropertyChangeListeners(java.lang.String)
    */
   public PropertyChangeListener[] JavaBean.getPropertyChangeListeners(String propertyName)
   {
      return pcs.getPropertyChangeListeners(propertyName);
   }

   /**
    * @param propertyName
    * @return
    * @see java.beans.PropertyChangeSupport#hasListeners(java.lang.String)
    */
   public boolean JavaBean.hasListeners(String propertyName)
   {
      return pcs.hasListeners(propertyName);
   }

   /**
    * @param listener
    * @see java.beans.PropertyChangeSupport#removePropertyChangeListener(java.beans.PropertyChangeListener)
    */
   public void JavaBean.removePropertyChangeListener(PropertyChangeListener listener)
   {
      pcs.removePropertyChangeListener(listener);
   }

   /**
    * @param propertyName
    * @param listener
    * @see java.beans.PropertyChangeSupport#removePropertyChangeListener(java.lang.String, java.beans.PropertyChangeListener)
    */
   public void JavaBean.removePropertyChangeListener(String propertyName, PropertyChangeListener listener)
   {
      pcs.removePropertyChangeListener(propertyName, listener);
   }
}
