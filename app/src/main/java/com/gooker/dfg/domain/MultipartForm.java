package com.gooker.dfg.domain;

import java.io.File;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * @author aoeng Aug 17, 2014 7:20:25 PM
 * 
 *         FileUpload tools
 */
public class MultipartForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1358025942823218510L;

	private String action;
	// submit method POST / GET
	private String metod;
	// normal Field key : value (String : String)
	private Map<String, String> normalField = new LinkedHashMap<String, String>();
	// File Field key : value (String : File)
	private Map<String, File> fileField = new LinkedHashMap<String, File>();

	public MultipartForm() {
		super();
	}

	public MultipartForm(String action, String metod, Map<String, String> normalField, Map<String, File> fileField) {
		super();
		this.action = action;
		this.metod = metod;
		this.normalField = normalField;
		this.fileField = fileField;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getMetod() {
		return metod;
	}

	public void setMetod(String metod) {
		this.metod = metod;
	}

	public Map<String, String> getNormalField() {
		return normalField;
	}

	public void setNormalField(Map<String, String> normalField) {
		this.normalField = normalField;
	}

	public Map<String, File> getFileField() {
		return fileField;
	}

	public void setFileField(Map<String, File> fileField) {
		this.fileField = fileField;
	}

	@Override
	public String toString() {
		return "MultipartForm [action=" + action + ", metod=" + metod + ", normalField=" + normalField + ", fileField=" + fileField + "]";
	}

}
