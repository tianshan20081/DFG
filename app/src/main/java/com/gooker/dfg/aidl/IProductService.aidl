package com.aoeng.degu.aidl;
import com.aoeng.degu.aidl.Product;
interface IProductService{
	Map getMap(in String country,in Product product);
	Product getProduct();
}