/**
 * 
 */
package com.damnhandy.aspects.demo.aop;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.damnhandy.swing.util.UIUtils;
import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.FormLayout;

/**
 * @author ryan
 *
 */
@SuppressWarnings("serial")
public class AopPanel extends JPanel
{
   private JLabel timeValue;

   private JLabel calculatedValue;

   private JTextField nameField;

   private JLabel nameListenerLabel;

   private JTextField selectedItem;

   private JButton timeValueButton;

   private JButton addItemButton;

   private JButton removeItemButton;

   private JList itemsList;

   private SelectionInList<String> items;

   private PresentationModel<AopBean> beanModel;

   private AopBean bean;

   public AopPanel()
   {
      initComponents();
   }

   private void initComponents()
   {
      /*
       * Init the models 1st
       */
      bean = new AopBean("Advised Bean");
      beanModel = new PresentationModel<AopBean>(bean);
      items = new SelectionInList<String>(beanModel.getModel("items"));
      bean.addItem("Test 1");
      /*
       * Create & Bind the components
       */
      itemsList = BasicComponentFactory.createList(items);
      timeValue = BasicComponentFactory.createLabel(beanModel.getModel("timeValue"));
      calculatedValue = BasicComponentFactory.createLabel(beanModel.getModel("calculatedValue"));
      nameField = BasicComponentFactory.createTextField(beanModel.getModel("name"));
      nameListenerLabel = BasicComponentFactory.createLabel(beanModel.getModel("name"));
      selectedItem = BasicComponentFactory.createTextField(items.getSelectionHolder());

      removeItemButton = new JButton(new RemoveItemAction());
      addItemButton = new JButton(new AddItemAction());
      timeValueButton = new JButton(new GetTimeValueAction());
      this.setBorder(UIUtils.createTitledBorder("AOP Version"));
      this.add(buildLayout());
   }

   private JPanel buildLayout()
   {
      FormLayout layout = new FormLayout("70dlu,3dlu,140dlu", "");
      DefaultFormBuilder builder = new DefaultFormBuilder(layout);
      builder.setDefaultDialogBorder();
      builder.append("Name:", nameField);
      builder.nextLine();
      builder.append("Name Listener:", nameListenerLabel);
      builder.nextLine();
      builder.append("Time Value:", timeValue);
      builder.nextLine();
      builder.append("Calculated Value:", calculatedValue);
      builder.nextLine();
      builder.append("Selected Item:", selectedItem);
      builder.nextLine();
      builder.append("Item:", new JScrollPane(itemsList));
      builder.nextLine();
      builder.append(buildButtonBar(), 3);
      return builder.getPanel();
   }

   private JComponent buildButtonBar()
   {
      return ButtonBarFactory.buildCenteredBar(timeValueButton, addItemButton, removeItemButton);
   }

   private class RemoveItemAction extends AbstractAction
   {

      public RemoveItemAction()
      {
         super("Remove Item");
      }

      public void actionPerformed(ActionEvent evt)
      {
         String selection = (String) items.getSelection();
         bean.removeItem(selection);
      }
   }

   private class AddItemAction extends AbstractAction
   {

      public AddItemAction()
      {
         super("Add Item");
      }

      public void actionPerformed(ActionEvent evt)
      {
         String newItem = selectedItem.getText();
         bean.addItem(newItem);
      }
   }

   private class GetTimeValueAction extends AbstractAction
   {

      public GetTimeValueAction()
      {
         super("Test w/ SyncModel");
      }

      public void actionPerformed(ActionEvent evt)
      {

      }
   }

}
