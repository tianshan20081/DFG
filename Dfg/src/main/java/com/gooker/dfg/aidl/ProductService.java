package com.gooker.dfg.aidl;

import java.util.HashMap;
import java.util.Map;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;


public class ProductService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return new ProductServiceImpl();
    }

    public class ProductServiceImpl extends IProductService.Stub {

        @Override
        public Map getMap(String country, Product product) throws RemoteException {
            // TODO Auto-generated method stub
            Map map = new HashMap<String, String>();
            map.put("country", country);
            map.put("id", product.getId());
            map.put("name", product.getName());
            map.put("price", product.getPrice());
            map.put("product", product);
            return map;
        }

        @Override
        public Product getProduct() throws RemoteException {
            // TODO Auto-generated method stub
            Product product = new Product();
            product.setId(20);
            product.setName("Iphone 5S");
            product.setPrice(1234.34f);
            return product;
        }

    }
}
