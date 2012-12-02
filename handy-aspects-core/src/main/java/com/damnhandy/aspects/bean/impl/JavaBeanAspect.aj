package com.damnhandy.aspects.bean.impl;

import java.util.Collection;

import com.damnhandy.aspects.bean.Observable;
import com.damnhandy.aspects.bean.Silent;
import com.damnhandy.aspects.bean.JavaBean;

/**
 * An aspect which listens for property changes on classes marked with the 
 * {@link Observable} annotation. By default, all properties of an 
 * advised class are bound unless marked with the {@link Silent} annotation.
 * 
 * @author Ryan J. McDonough
 * @since 1.0
 * @version $Revision: 1.1 $
 */
public aspect JavaBeanAspect extends AbstractJavaBeanAspect
{

   /**
    * Makes any class marked with @Observable implement the JavaBean interface and
    * backs it with the implementation defined by this aspect.
    */
   declare parents : (@Observable *) implements JavaBean;

   /**
    * Picks out activity which happens within a constructor invocation of a
    * type marked with an @Observable annotation. Generally, we don't want
    * fields set within a constructor to fire events.
    */
   pointcut withinNewObservable() : withincode((@Observable *).new(..));

   /**
    * Fire a property change event on any method within a class with an
    * @Obserbable annotation which sets a property value. An event will not be 
    * fired if the field is marked @Slient or if the field values are set within a
    * constructor.
    * 
    * @param bean - the JavaBean instance
    */
   pointcut setters(JavaBean bean): 
		target(bean) && set(* (@Observable *).*) && withincode(* @Observable *.set*(..)) &&
		!withinNewObservable() && !set(@Silent * (@Observable *).*);

   /**
    * 
    */
   pointcut collectionAdd() : 
		(call(public boolean java.util.Collection+.add(..)) || 
		 call(public boolean java.util.Collection+.addAll(..)))
		&& withincode(* (@Observable *).add*(..)) 
		&& !withincode(@Silent * (@Observable *).add*(..));

   /**
    * Finds a remove method within a type marked @Observable that
    * calls 
    */
   pointcut collectionRemove() : 
		(call(public boolean java.util.Collection+.remove(..)) || 
		 call(public boolean java.util.Collection+.removeAll(..)))
		&& withincode(* (@Observable *).remove*(..)) 
		&& !withincode(@Silent * (@Observable *).remove*(..));

   /**
    * 
    */
   pointcut listRemove() : 
		(call(public * java.util.List+.remove(int)))
		&& withincode(* (@Observable *).remove*(..)) 
		&& !withincode(@Silent * (@Observable *).remove*(..));

   /**
    * Targets a collection property of a JavaBean which is modified by
    * via an adder method such as <code>addObject(Object obj)</code>.
    * 
    * @param collection - the underlying collection which items are added to
    * @param bean - the JavaBean instance which contains the called method
    */
   pointcut collectionMutator(@SuppressWarnings("rawtypes") Collection collection, JavaBean bean): 
		target(collection) && this(bean) && !withinNewObservable() &&
		(collectionAdd() || collectionRemove() || listRemove());

}
