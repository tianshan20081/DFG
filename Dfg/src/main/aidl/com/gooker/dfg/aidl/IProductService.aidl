package com.gooker.dfg.aidl;
import com.gooker.dfg.aidl.Product;
interface IProductService{
	Map getMap(in String country,in Product product);
	Product getProduct();
}