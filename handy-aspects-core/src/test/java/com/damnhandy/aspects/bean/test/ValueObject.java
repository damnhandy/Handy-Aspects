/**
 *
 */
package com.damnhandy.aspects.bean.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.damnhandy.aspects.bean.Observable;

/**
 * @author ryan
 *
 */
@Observable
public class ValueObject implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -1067071642544894858L;

	private String name;
	private Integer value;
	private List<String> values = new ArrayList<String>();

	public ValueObject() {

	}


	/**
	 * @param name
	 * @param value
	 */
	public ValueObject(String name, Integer value) {
		this.name = name;
		this.value = value;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return Returns the value.
	 */
	public Integer getValue() {
		return value;
	}
	/**
	 * @param value The value to set.
	 */
	public void setValue(Integer value) {
		this.value = value;
	}
	/**
	 * @return Returns the values.
	 */
	public List<String> getValues() {
		return values;
	}
	/**
	 * @param values The values to set.
	 */
	public void setValues(List<String> values) {
		this.values = values;
	}

	public void addValue(String value) {
		values.add(value);
	}

	public void removeValue(String value) {
		values.remove(value);
	}

}
