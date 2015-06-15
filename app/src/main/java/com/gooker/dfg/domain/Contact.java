/**
 * 
 */
package com.gooker.dfg.domain;

/**
 * @author [ShaoCheng Zhang] Sep 3, 2013:11:41:10 AM
 * @Email [zhangshch2008@gmail.com]
 */
public class Contact {
	private String name;
	private String sortKey;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the sortKey
	 */
	public String getSortKey() {
		return sortKey;
	}

	/**
	 * @param sortKey
	 *            the sortKey to set
	 */
	public void setSortKey(String sortKey) {
		this.sortKey = sortKey;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Contact [name=" + name + ", sortKey=" + sortKey + "]";
	}

}
