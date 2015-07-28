package com.gooker.dfg.ui;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.gooker.dfg.R;

public class AnimatorsUI extends BaseUI {


    private ImageView ivAnim;
    private Button btnAnimNarrow;

    /**
     *
     */
    @Override
    protected void loadViewLayout() {

        setContentView(R.layout.activity_animators_ui);

    }

    /**
     *
     */
    @Override
    protected void findViewById() {
        ivAnim = (ImageView) findViewById(R.id.ivAnim);
        btnAnimNarrow = (Button) findViewById(R.id.btnAnimNarrow);
    }

    /**
     *
     */
    @Override
    protected void setListener() {
        btnAnimNarrow.setOnClickListener(this);
    }

    /**
     *
     */
    @Override
    protected void processLogic() {

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAnimNarrow:
                narrow();
                break;
        }
    }

    private void narrow() {
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(ivAnim, "scaleX",
                1.0f, .25f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(ivAnim, "scaleY",
                1.0f, .25f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(2000);
        animSet.setInterpolator(new LinearInterpolator());
        //两个动画同时执行
        animSet.playTogether(anim1, anim2);
        animSet.start();
    }
}
