package com.damnhandy.aspects.bean.test;

import org.junit.Assert;
import org.junit.Test;

import com.damnhandy.aspects.bean.JavaBean;

/**
 *
 * A JavaBeanTest.
 *
 * @author <a href="ryan@damnhandy.com">Ryan J. McDonough</a>
 * @version $Revision: 1.1 $
 */
public class JavaBeanTest
{

   /**
    * No Event should fire when the ValueObject is created.
    * Admitedly, this is a crappy test since we're adding the
    * listener after creation.
    * @throws Exception
    */
   @Test
   public void testCreation() throws Exception
   {
      ValueObjectListener listener = new ValueObjectListener();
      ValueObject vo = new ValueObject("Test 1", new Integer(17));
      ((JavaBean) vo).addPropertyChangeListener(listener);
      Assert.assertFalse(listener.hasChanged());
   }

   /**
    * Tests that change events are properly being fired.
    * @throws Exception - If the Aspect is not applied properly,
    * a variety of Exceptions may be thrown
    */
   public void testPropertyChange() throws Exception
   {
      ValueObjectListener listener = new ValueObjectListener();
      ValueObject vo = new ValueObject("Test 1", new Integer(17));
      ((JavaBean) vo).addPropertyChangeListener(listener);
      vo.setName("New Name");
      Assert.assertTrue(listener.hasChanged());
      listener.reset();
      vo.setValue(20);
      Assert.assertTrue(listener.hasChanged());
      listener.reset();
      vo.addValue("Test 1");
      Assert.assertTrue(listener.hasChanged());
      listener.reset();
      vo.addValue("Test 2");
      Assert.assertTrue(listener.hasChanged());
      listener.reset();
      vo.addValue("Test 3");
      Assert.assertTrue(listener.hasChanged());
      listener.reset();
      vo.removeValue("Test 2");
      Assert.assertTrue(listener.hasChanged());
      listener.reset();
   }

}
