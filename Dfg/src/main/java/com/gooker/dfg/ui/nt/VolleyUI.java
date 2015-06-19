package com.gooker.dfg.ui.nt;

import android.app.Activity;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


public class VolleyUI extends Activity {

    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        String url = "";
//		requestQueue.add(new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
//
//			@Override
//			public void onResponse(JSONObject response) {
//				// TODO Auto-generated method stub
//				try {
//					response.toJSONArray(null);
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}, null));

    }
}
