/**
 *
 */
package com.gooker.dfg.ui.cv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gooker.dfg.R;


/**
 * @author [ShaoCheng Zhang] Sep 8, 2013:1:58:15 PM
 * @Email [zhangshch2008@gmail.com]
 */
public class PhotoSmall2BigUI extends Activity {
    /*
     * (non-Javadoc)
     *
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ui_cv_photosmall2big);

        this.findViewById(R.id.ivSmallIcon).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(PhotoSmall2BigUI.this, BigImageShowerUI.class);
                startActivity(intent);
            }
        });
    }

}
