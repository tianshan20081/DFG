package com.gooker.dfg.ui.services;

import android.os.RemoteException;

public class PersonImpl extends IPerson.Stub {
	private String name;
	private String sex;
	private int age;

	@Override
	public void setName(String name) throws RemoteException {
		// TODO Auto-generated method stub
		this.name = name;
	}

	@Override
	public void setSex(String sex) throws RemoteException {
		// TODO Auto-generated method stub
		this.sex = sex;
	}

	@Override
	public void setAge(int age) throws RemoteException {
		// TODO Auto-generated method stub
		this.age = age;
	}

	@Override
	public String getPerson() throws RemoteException {
		// TODO Auto-generated method stub
		return "[Person name=" + name + ",sex=" + sex + ",age=" + age + "]";
	}

}
