package com.gooker.dfg.ui.uis;

import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import com.gooker.dfg.ui.BaseUI;
import com.gooker.dfg.utils.common.LogUtils;
import com.gooker.dfg.utils.common.StringUtils;

import java.io.File;

public class FileInfosUI extends BaseUI {
	/**
	 * 
	 * <intent-filter> < action
	 * android:name="android.intent.action.VIEW"></action> < category
	 * android:name="android.intent.category.DEFAULT"></category> < data
	 * android:mimeType="text/plain"></data> < /intent-filter> 还有其它常用的如：
	 * 
	 * text/plain（纯文本）
	 * 
	 * text/html（HTML文档）
	 * 
	 * application/xhtml+xml（XHTML文档）
	 * 
	 * image/gif（GIF图像）
	 * 
	 * image/jpeg（JPEG图像）【PHP中为：image/pjpeg】
	 * 
	 * image/png（PNG图像）【PHP中为：image/x-png】
	 * 
	 * video/mpeg（MPEG动画）
	 * 
	 * application/octet-stream（任意的二进制数据）
	 * 
	 * application/pdf（PDF文档）
	 * 
	 * application/msword（Microsoft Word文件）
	 * 
	 * message/rfc822（RFC 822形式）
	 * 
	 * multipart/alternative（HTML邮件的HTML形式和纯文本形式，相同内容使用不同形式表示）
	 * 
	 * application/x-www-form-urlencoded（使用HTTP的POST方法提交的表单）
	 * 
	 * multipart/form-data（同上，但主要用于表单提交时伴随文件上传的场合）
	 */
	private TextView tvName;

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void loadViewLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.ui_uis_actions);
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		tvName = (TextView) findView(R.id.tvName);
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void processLogic() {
		// TODO Auto-generated method stub
		Uri uri = getIntent().getData();
		if (null != uri) {
			LogUtils.e(uri.toString());
			String path = uri.getPath();
			LogUtils.e("uri.getPath()" + path);
			if (!StringUtils.isEmpty(path)) {
				File file = new File(path);
				if (!file.exists() || file.isDirectory()) {
					return;
				}
				String name = file.getName();
				String length = StringUtils.formatFileSize(file.length());
				String userInfo = uri.getUserInfo();
				StringBuffer sb = new StringBuffer();
				sb.append("FileName:").append(name).append("\n");
				sb.append("absolutePath").append(path).append("\n");
				sb.append("FileLength:").append(length).append("\n");
				sb.append("userInfo").append(userInfo).append("\n");
				tvName.setText(sb.toString());
			}
		}
	}

}
