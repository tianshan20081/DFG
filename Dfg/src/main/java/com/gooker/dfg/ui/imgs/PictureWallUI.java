package com.gooker.dfg.ui.imgs;


import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gooker.dfg.R;
import com.gooker.dfg.domain.BaiDuLocation;
import com.gooker.dfg.domain.ImageGroup;
import com.gooker.dfg.domain.ImageInfo;
import com.gooker.dfg.parser.BaiDuLocationParser;
import com.gooker.dfg.services.DataCallback;
import com.gooker.dfg.ui.BaseUI;
import com.gooker.dfg.utils.DataUtils;
import com.gooker.dfg.utils.PhotoUtils;
import com.gooker.dfg.utils.RequestVO;
import com.gooker.dfg.utils.common.FileUtils;
import com.gooker.dfg.utils.common.ImageUtils;
import com.gooker.dfg.utils.common.LogUtils;
import com.gooker.dfg.utils.common.SystemUtils;
import com.gooker.dfg.utils.common.UIUtils;
import com.gooker.dfg.utils.net.URLUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class PictureWallUI extends BaseUI {

    // private ImagePullToRefreshLv lvPicWall;
    HashMap<String, String> dicmMap = new HashMap<String, String>();

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void loadViewLayout() {
        // TODO Auto-generated method stub

        setContentView(R.layout.ui_imgs_pic_wall);
        LogUtils.e("loadImgInfo(ch.getAbsolutePath());" + FileUtils.getPhonePhotoFolder().getAbsolutePath());
        final String path = FileUtils.getPhonePhotoFolder().getAbsolutePath();
        // String path = "/storage/emulated/0/Camera/P40918-232644.jpg";
        // String path = "/storage/emulated/0/Camera/P";
        LogUtils.e("loadImgInfo(ch.getAbsolutePath());" + FileUtils.getPhonePhotoFolder().getAbsolutePath());
        LogUtils.e("path" + path);

        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                loadImgInfo(path);
            }
        }).start();

    }

    @Override
    protected void findViewById() {
        // TODO Auto-generated method stub
        // lvPicWall = (ImagePullToRefreshLv) findView(R.id.lvPicWall);

    }

    @Override
    protected void setListener() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void processLogic() {
        // TODO Auto-generated method stub

        // ImageAdapter adapter = new ImageAdapter();

        // TODO Auto-generated method stub
        // String[] filePathColumn = { MediaStore.Images.Media.DATA,
        // MediaStore.Images.Media.LATITUDE, MediaStore.Images.Media.LONGITUDE,
        // MediaStore.Images.Media.SIZE, MediaStore.Images.Media.TITLE,
        // MediaStore.Images.Media.DESCRIPTION,
        // MediaStore.Images.Media.MIME_TYPE,
        // MediaStore.Images.Media.DATE_ADDED,
        // MediaStore.Images.Media.DATE_MODIFIED,
        // MediaStore.Images.Media.DATE_TAKEN,
        // MediaStore.Images.Media.DISPLAY_NAME, MediaStore.Images.Media.HEIGHT,
        // MediaStore.Images.Media.WIDTH };
        //
        // File dicmFile =
        // Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        //
        // if (null != dicmFile && dicmFile.isDirectory()) {
        // File[] chFile = dicmFile.listFiles();
        // if (null == chFile && chFile.length == 0) {
        // log("empty Dicm");
        // return;
        // }
        // for (File ch : chFile) {
        // if (null != ch && ch.isFile()) {
        // dicmMap.put(ch.getAbsolutePath(), new
        // Date(ch.lastModified()).toString());
        // // Bitmap bitmap =
        // // BitmapFactory.decodeFile(ch.getAbsolutePath());
        //
        // // Cursor cursor =
        // //
        // getContentResolver().delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        // // MediaStore.Images.Media.DATA + " LIKE ?",
        // // params);
        //
        // loadImgInfo(ch.getAbsolutePath());
        //
        // }
        // }
        // }
        // for (String info : dicmMap.keySet()) {
        // StringBuffer sb = new StringBuffer();
        // sb.append(info).append(":").append(dicmMap.get(info).toString()).append("----").append(MD5Utils.getJavaMD5(dicmMap.get(info).toString()));
        // log(sb.toString());
        //
        // }

        // lvPicWall.setAdapter(adapter);

    }

    protected void loadImgInfo(String url) {
        // TODO Auto-generated method stub
        // String params[] = new String[] {
        // "'%"+FileUtils.getPhonePhotoFolder().getAbsolutePath()+"%'" };
        String params[] = new String[]{url};
        String[] filePathColumn = {MediaStore.Images.Media.DATA, MediaStore.Images.Media.LATITUDE, MediaStore.Images.Media.LONGITUDE, MediaStore.Images.Media.SIZE, MediaStore.Images.Media.TITLE,
                MediaStore.Images.Media.DESCRIPTION, MediaStore.Images.Media.MIME_TYPE, MediaStore.Images.Media.DATE_ADDED, MediaStore.Images.Media.DATE_MODIFIED, MediaStore.Images.Media.DATE_TAKEN,
                MediaStore.Images.Media.DISPLAY_NAME, MediaStore.Images.Media.HEIGHT, MediaStore.Images.Media.WIDTH};
        // String param = MediaStore.Images.Media.DATA + " like '%" + url +
        // "%'";
        String param = MediaStore.Images.Media.DATA + " like '%" + url + "%' ";
        Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, filePathColumn, param, null, MediaStore.Images.Media.DATE_TAKEN + " desc  ");
        // Cursor cursor =
        // Cursor cursor =
        // getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        // filePathColumn, param, null,
        // MediaStore.Images.Media.DATE_TAKEN + " desc  limit 3 offset 2 ");
        // // Cursor cursor =
        // getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        // filePathColumn, null, null, null);

        // MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI

        if (null == cursor) {
            log("null == cursor ");
            return;
        }
        // ArrayList<ImageGroup> infos = new ArrayList<ImageGroup>();
        TreeMap<String, ImageGroup> treeMap = new TreeMap<String, ImageGroup>(new Comparator<String>() {

            @Override
            public int compare(String lhs, String rhs) {
                // TODO Auto-generated method stub
                return (int) (Long.parseLong(rhs) - Long.parseLong(lhs));
            }
        });
        ImageGroup group = null;
        ImageInfo picInfo = null;
        while (cursor.moveToNext()) {
            picInfo = new ImageInfo();
            picInfo.setPicPath(cursor.getString(cursor.getColumnIndex(filePathColumn[0])));
            picInfo.setLatitude(cursor.getDouble(cursor.getColumnIndex(filePathColumn[1])));
            picInfo.setLongitude(cursor.getDouble(cursor.getColumnIndex(filePathColumn[2])));
            picInfo.setSize(cursor.getLong(cursor.getColumnIndex(filePathColumn[3])));
            picInfo.setTitle(cursor.getString(cursor.getColumnIndex(filePathColumn[4])));
            picInfo.setDescription(cursor.getString(cursor.getColumnIndex(filePathColumn[5])));
            picInfo.setMimeType(cursor.getString(cursor.getColumnIndex(filePathColumn[6])));
            picInfo.setAddDate(new Date(cursor.getLong(cursor.getColumnIndex(filePathColumn[7]))));
            picInfo.setModifyDate(new Date(cursor.getLong(cursor.getColumnIndex(filePathColumn[8]))));
            picInfo.setTakenDate(new Date(cursor.getLong(cursor.getColumnIndex(filePathColumn[9]))));
            picInfo.setDisplayName(cursor.getString(cursor.getColumnIndex(filePathColumn[10])));

            picInfo.setHeight(cursor.getInt(cursor.getColumnIndex(filePathColumn[11])));
            picInfo.setWidth(cursor.getInt(cursor.getColumnIndex(filePathColumn[12])));

            if (null == treeMap || treeMap.size() <= 0) {
                group = new ImageGroup();
                group.add(picInfo);
                treeMap.put(group.getGroupId() + "", group);
                group = null;
            } else {
                if (treeMap.lastEntry().getValue().isBelong(picInfo)) {
                    treeMap.lastEntry().getValue().add(picInfo);
                } else {
                    group = new ImageGroup();
                    group.add(picInfo);
                    treeMap.put(group.getGroupId() + "", group);
                    group = null;
                }
            }
        }

        cursor.close();
        LogUtils.e("treeMap.size()---------------------------------" + treeMap.size());
        for (Map.Entry<String, ImageGroup> entry : treeMap.entrySet()) {
            LogUtils.e("GroupInfo-------------------------------------------------" + entry.getValue().toString());
            for (ImageInfo in : entry.getValue().getInfos()) {
                // LogUtils.e(in.toString());
            }
            // if (null != picInfo.getLatitude() && 0 < picInfo.getLatitude()) {
            // // loadImageInfo(picInfo);
            // }

        }

        treeMap = PhotoUtils.getAllPhotos();
    }

    private void loadImageInfo(final ImageInfo picInfo) {
        // TODO Auto-generated method stub

        RequestVO reqVo = new RequestVO();
        reqVo.requestUrl = URLUtils.URL_BAIDU_LOCATE_API;
        reqVo.requestDataMap = getLocateMap(picInfo.getLatitude().toString().concat(",").concat(picInfo.getLongitude().toString()));
        reqVo.jsonParser = new BaiDuLocationParser();
        DataUtils.getDateFromServer(reqVo, new DataCallback<BaiDuLocation>() {

            @Override
            public void processData(BaiDuLocation obj, boolean paramBoolean) {
                // TODO Auto-generated method stub

                // TODO Auto-generated method stub
                if (null != obj) {
                    log(obj.toString());
                    // tvImgInfo.setText(obj.getResult().getAddressComponent().toString());
                    log(picInfo.getDisplayName() + "----" + obj.getResult().getAddressComponent().toString());
                }

            }
        });
    }

    private HashMap<String, String> getLocateMap(String location) {
        // TODO Auto-generated method stub
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("ak", URLUtils.KEY_BAIDU_MAP);
        map.put("location", location);
        map.put("output", URLUtils.JSON);
        map.put("pois", "0");

        return map;
    }

    private class ImageAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return dicmMap.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub

            ViewHolder holder;
            if (null != convertView) {
                holder = (ViewHolder) convertView.getTag();
            } else {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.ui_imgs_pic_wall_item, null);
                holder.rlImg = (RelativeLayout) convertView.findViewById(R.id.rlImg);
                holder.tvLocation = (TextView) convertView.findViewById(R.id.tvLocation);
                holder.tvTime = (TextView) convertView.findViewById(R.id.tvTime);

                convertView.setTag(holder);
            }
            // holder.rlImg.setBackgroundDrawable(new
            // BitmapDrawable(BitmapFactory.decodeFile(new
            // ArrayList<String>(dicmMap.keySet()).get(position))));
            Bitmap bitmap = BitmapFactory.decodeFile(new ArrayList<String>(dicmMap.keySet()).get(position));
            holder.rlImg.setBackgroundDrawable(new BitmapDrawable(ImageUtils.compressBySize(bitmap, SystemUtils.getScreenWidth(), SystemUtils.getScreenHeight())));
            holder.tvTime.setText(dicmMap.get(new ArrayList<String>(dicmMap.keySet()).get(position)));

            return convertView;
        }
    }

    private static class ViewHolder {
        RelativeLayout rlImg;
        TextView tvLocation;
        TextView tvTime;
    }

}
