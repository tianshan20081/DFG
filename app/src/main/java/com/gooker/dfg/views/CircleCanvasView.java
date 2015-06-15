package com.gooker.dfg.views;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class CircleCanvasView extends View {
	// 保存绘制历史
	public List<CircleInfo> mCircleInfos = new ArrayList<com.aoeng.degu.views.CircleCanvasView.CircleInfo>();

	// 保存实心圆相关信息
	public static class CircleInfo {
		private float x; // 圆心横坐标
		private float y; // 圆心纵坐标
		private float radius; // 半径
		private int color; // 画笔的的颜色

		public float getX() {
			return x;
		}

		public float getY() {
			return y;
		}

		public float getRadius() {
			return radius;
		}

		public int getColor() {
			return color;
		}

		public void setX(float x) {
			this.x = x;
		}

		public void setY(float y) {
			this.y = y;
		}

		public void setRadius(float radius) {
			this.radius = radius;
		}

		public void setColor(int color) {
			this.color = color;
		}

	}

	public CircleCanvasView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// 根据保存的绘制历史重绘所有的实心圆
		for (CircleInfo circleInfo : mCircleInfos) {
			Paint paint = new Paint();
			// 设置画笔颜色
			paint.setColor(circleInfo.getColor());
			// 绘制实心圆
			canvas.drawCircle(circleInfo.getX(), circleInfo.getY(), circleInfo.getRadius(), paint);
		}
	}

}
