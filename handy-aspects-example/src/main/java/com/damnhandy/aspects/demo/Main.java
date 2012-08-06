/**
 * 
 */
package com.damnhandy.aspects.demo;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import com.damnhandy.aspects.demo.aop.AopPanel;
import com.damnhandy.aspects.demo.nonaop.RegularPanel;
import com.damnhandy.swing.util.UIUtils;

/**
 * 
 * @author Ryan J. McDonough
 *
 */
public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8661525943647861559L;
	private JSplitPane splitPane;
	private JTabbedPane tabs;
	public Main() {
		initComponents();
	}


	private void initComponents() {
		this.setTitle("Handy Aspect Library Demo 0.1");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.tabs = new JTabbedPane();
		this.splitPane = new JSplitPane();
		this.splitPane.setOneTouchExpandable(false);
		this.splitPane.setRightComponent(new RegularPanel());
		this.splitPane.setLeftComponent(new AopPanel());
		tabs.addTab("JavaBean & SyncModel",splitPane);
		this.add(tabs);
		this.pack();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UIUtils.setLookAndFeel();
		final Main main = new Main();
		SwingUtilities.invokeLater(new Runnable() {
	         public void run() {
	        	 UIUtils.centerWindowOnScreen(main);
	        	 main.setVisible(true);
	         }
	     });
	}
	
	
}
