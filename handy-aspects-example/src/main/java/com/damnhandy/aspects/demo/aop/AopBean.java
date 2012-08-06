/**
 * 
 */
package com.damnhandy.aspects.demo.aop;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.damnhandy.aspects.bean.Observable;

/**
 * 
 * @author <a href="ryan@damnhandy.com">Ryan J. McDonough</a>
 * @version $Revision: 1.1 $
 */
@Observable
public class AopBean {

	private Timer timer;
	private String timeValue = new Date().toString();
	private String calculatedValue;
	private String name;

	private List<String> items = new ArrayList<String>();

	public AopBean() {
		TimerTask task = new TimerTask() {
			
			public void run() {
				setTimeValue(new Date().toString());
			}
		};
		timer = new Timer();
		timer.scheduleAtFixedRate(task,1000,1000);
	}
	
	public AopBean(String name) {
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
		this.items = items;
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
		this.name = name;
	}

	/**
	 * @return Returns the timeValue.
	 */
	
	public String getTimeValue() {
		return timeValue;
	}
	

	
	
	public String getCalculatedValue() {
		return calculatedValue;
	}
	
	public void setCalculatedValue(String calculatedValue) {
		this.calculatedValue = calculatedValue;
	}

	/**
	 * @param timeValue
	 *            The timeValue to set.
	 */
	private void setTimeValue(String timeValue) {
		this.timeValue = timeValue;
	}

	public void addItem(String itemName) {
		items.add(itemName);
	}
	
	public void removeItem(String itemName) {
		items.remove(itemName);
	}
}
