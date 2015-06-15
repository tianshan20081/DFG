package com.gooker.dfg.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class LogFileUploadResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 334587098255111328L;
	/**
	 * 
	 */
	private int status;
	private String statusDesc;
	private List<String> fileNames;
	private Date fileUploadDate;

	public LogFileUploadResult() {
		super();
	}

	public LogFileUploadResult(int status, String statusDesc, List<String> fileNames, Date fileUploadDate) {
		super();
		this.status = status;
		this.statusDesc = statusDesc;
		this.fileNames = fileNames;
		this.fileUploadDate = fileUploadDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public List<String> getFileNames() {
		return fileNames;
	}

	public void setFileNames(List<String> fileNames) {
		this.fileNames = fileNames;
	}

	public Date getFileUploadDate() {
		return fileUploadDate;
	}

	public void setFileUploadDate(Date fileUploadDate) {
		this.fileUploadDate = fileUploadDate;
	}

	@Override
	public String toString() {
		return "LogFileUploadResult [status=" + status + ", statusDesc=" + statusDesc + ", fileNames=" + fileNames + ", fileUploadDate=" + fileUploadDate + "]";
	}

}
