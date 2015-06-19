/**
 *
 */
package com.gooker.dfg.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.view.MotionEvent;
import android.view.View;

import com.gooker.dfg.R;
import com.gooker.dfg.utils.SuDoKuGame;


/**
 * Jun 3, 2014 1:44:48 PM
 */
public class SuDoKuView extends View {
    private float width;
    private float hight;
    private int selX;
    private int selY;
    private SuDoKuGame game;

    /**
     * @param context
     */
    public SuDoKuView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        game = new SuDoKuGame();
    }

    /*
     * (non-Javadoc)
     *
     * @see android.view.View#onSizeChanged(int, int, int, int)
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        // TODO Auto-generated method stub
        this.width = w / 9f;
        this.hight = h / 9f;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    /*
     * (non-Javadoc)
     *
     * @see android.view.View#onDraw(android.graphics.Canvas)
     */
    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        // 画背景
        Paint bgPaint = new Paint();
        bgPaint.setColor(getResources().getColor(R.color.aliceblue));
        canvas.drawRect(0, 0, getWidth(), getHeight(), bgPaint);

        Paint linePaint = new Paint();
        linePaint.setColor(getResources().getColor(R.color.black));
        float lineSpace = 0.5f;
        // 画出九条横线,九条竖线
        for (int i = 1; i < 9; i++) {
            if (i % 3 == 0) {
                // 横线
                canvas.drawLine(0, hight * i - lineSpace, getWidth(), hight * i - lineSpace,
                        linePaint);
                canvas.drawLine(0, hight * i + lineSpace, getWidth(), hight * i + lineSpace,
                        linePaint);
                // 竖线
                canvas.drawLine(width * i - lineSpace, 0, width * i - lineSpace, getHeight(),
                        linePaint);
                canvas.drawLine(width * i + lineSpace, 0, width * i + lineSpace, getHeight(),
                        linePaint);
            } else {
                // 横线
                canvas.drawLine(0, hight * i, getWidth(), hight * i, linePaint);
                // 竖线
                canvas.drawLine(width * i, 0, width * i, getHeight(), linePaint);
            }

        }
        // 填充数字
        Paint numPaint = new Paint();
        numPaint.setColor(getResources().getColor(R.color.black));
        numPaint.setStyle(Paint.Style.STROKE);
        numPaint.setTextSize(hight * .75f);
        numPaint.setTextAlign(Paint.Align.CENTER);

        FontMetrics frmFontMetrics = numPaint.getFontMetrics();
        float x = width / 2f;
        float y = hight / 2f - (frmFontMetrics.ascent + frmFontMetrics.descent) / 2f;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                canvas.drawText(game.getTileStr(i, j), i * width + x, j * hight + y, numPaint);
            }
        }
        super.onDraw(canvas);
    }

    /*
     * (non-Javadoc)
     *
     * @see android.view.View#onTouchEvent(android.view.MotionEvent)
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub

        if (event.getAction() != MotionEvent.ACTION_DOWN) {
            return super.onTouchEvent(event);
        }
        selX = (int) (event.getX() / width);
        selY = (int) (event.getY() / hight);
        int used[] = game.getUsedTilesByCoor(selX, selY);
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < used.length; i++) {
            buffer.append(used[i]);
        }

        SudokuKeyDialog keyDialog = new SudokuKeyDialog(getContext(), this, used);
        keyDialog.show();

        return true;

    }

    public void setSelectedTile(int tile) {
        if (game.setTileIfValid(selX, selY, tile)) {
            invalidate();
        }
    }
}
