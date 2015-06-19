/**
 *
 */
package com.gooker.dfg.views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.gooker.dfg.R;


/**
 * Jun 3, 2014 3:13:55 PM
 */
public class SudokuKeyDialog extends Dialog {
    // 用来存放代表对话框当中按钮的对象
    private final View keys[] = new View[9];
    private final int[] used;
    private SuDoKuView shuduView;

    @Override
    // 当Dialog第一次显示时，会调用该方法
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        // 设置对话框标题
        setTitle("请选择一个数字填入：");
        // 用于为该Dialog设置布局文件
        setContentView(R.layout.ui_game_sudo_key);
        findViews();
        // 遍历整个used数组
        for (int i = 0; i < used.length; i++) {
            if (used[i] != 0) {
                System.out.println(used[i]);
                // 设置按钮为不可见的
                keys[used[i] - 1].setVisibility(View.INVISIBLE);
            }
        }
        // 为对话框当中所有的按钮设置监听器
        setListeners();
    }

    // 通知shuduView对象，刷新这个九宫格显示的数据
    private void returnResult(int tile) {
        // TODO Auto-generated method stub
        shuduView.setSelectedTile(tile);
        // 取消对话框的显示
        dismiss();
    }

    private void setListeners() {
        // 遍历整个keys数组
        for (int i = 0; i < keys.length; i++) {
            final int t = i + 1;
            keys[i].setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {

                    returnResult(t);
                }
            });
        }

    }

    private void findViews() {
        // TODO Auto-generated method stub
        keys[0] = findViewById(R.id.keypad_1);
        keys[1] = findViewById(R.id.keypad_2);
        keys[2] = findViewById(R.id.keypad_3);
        keys[3] = findViewById(R.id.keypad_4);
        keys[4] = findViewById(R.id.keypad_5);
        keys[5] = findViewById(R.id.keypad_6);
        keys[6] = findViewById(R.id.keypad_7);
        keys[7] = findViewById(R.id.keypad_8);
        keys[8] = findViewById(R.id.keypad_9);
    }

    // 构造函数的第二个参数当中保存着当前单元格已经使用过的数字，此used数组为外界传入的

    /**
     * @param context
     */
    public SudokuKeyDialog(Context context, SuDoKuView shuduView, int[] used) {
        super(context);
        // TODO Auto-generated constructor stub
        this.used = used;
        this.shuduView = shuduView;
    }

}
