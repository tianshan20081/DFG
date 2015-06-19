package com.gooker.dfg.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;

import com.gooker.dfg.R;
import com.gooker.dfg.domain.Person;
import com.gooker.dfg.utils.common.Toaster;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import cn.jpush.android.api.JPushInterface;


public class DataSaveUI extends Activity implements OnClickListener {
    private EditText etDataInfo;
    private SharedPreferences simpleDataSp;
    private Editor simpleDataEditor;
    private String imgBase64Str;
    private ImageView imageView;
    private String personBase64Str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_data_sp);
        etDataInfo = (EditText) this.findViewById(R.id.etDataInfo);
        imageView = (ImageView) this.findViewById(R.id.imIcon);
        this.findViewById(R.id.saveData).setOnClickListener(this);
        this.findViewById(R.id.saveObjectData).setOnClickListener(this);
        this.findViewById(R.id.getData).setOnClickListener(this);
        this.findViewById(R.id.getObjectData).setOnClickListener(this);
        this.findViewById(R.id.clearData).setOnClickListener(this);
        this.findViewById(R.id.prefActivity).setOnClickListener(this);
        this.findViewById(R.id.fileSaveData).setOnClickListener(this);
        this.findViewById(R.id.fileSaveDataSD).setOnClickListener(this);
        this.findViewById(R.id.fileCompression).setOnClickListener(this);
        this.findViewById(R.id.btnSqlLiteDb).setOnClickListener(this);
        simpleDataSp = getSharedPreferences("simpleDataSave", Activity.MODE_PRIVATE);
        simpleDataEditor = simpleDataSp.edit();
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btnSqlLiteDb:
                intent = new Intent(this, SqlLiteDBUI.class);
                startActivity(intent);
                break;
            case R.id.fileCompression:
                intent = new Intent(this, FileCompressionUI.class);
                startActivity(intent);
                break;
            case R.id.fileSaveDataSD:
                intent = new Intent(this, FileSaveDataSDUI.class);
                startActivity(intent);
                break;
            case R.id.fileSaveData:
                intent = new Intent(this, FileSaveDataUI.class);
                startActivity(intent);
                break;
            case R.id.prefActivity:
                intent = new Intent(this, PreferenceUI.class);
                startActivity(intent);
                break;
            case R.id.clearData:
                Toaster.toast(this, "数据已经被清空", false);
                simpleDataEditor.clear();
                simpleDataEditor.commit();
                break;
            case R.id.saveData:
                try {
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher).compress(CompressFormat.JPEG, 50, bos);
                    imgBase64Str = Base64.encodeToString(bos.toByteArray(), Base64.DEFAULT);
                    simpleDataEditor.putString("img", imgBase64Str);
                    simpleDataEditor.putString("simpledate", etDataInfo.getText().toString().trim());
                    simpleDataEditor.commit();
                    bos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            case R.id.saveObjectData:
                try {
                    Person person = new Person();
                    person.setName(etDataInfo.getText().toString().trim());
                    person.setSex(true);
                    person.setCh('F');
                    person.setBirthDay(new Date());
                    ByteArrayOutputStream bos1 = new ByteArrayOutputStream();
                    BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher).compress(CompressFormat.PNG, 100, bos1);
                    String iconStr = Base64.encodeToString(bos1.toByteArray(), Base64.DEFAULT);
                    person.setIcon(iconStr);
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(bos);
                    oos.writeObject(person);
                    personBase64Str = Base64.encodeToString(bos.toByteArray(), Base64.DEFAULT);
                    simpleDataEditor.putString("personBase64", personBase64Str);
                    simpleDataEditor.commit();
                    oos.close();
                    bos.close();
                    bos1.close();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                break;
            case R.id.getData:
                try {
                    Toaster.toast(this, simpleDataSp.getString("simpledata", ""), true);
                    imgBase64Str = simpleDataSp.getString("img", "");
                    byte[] imgByte = Base64.decode(imgBase64Str.getBytes(), Base64.DEFAULT);
                    ByteArrayInputStream bis = new ByteArrayInputStream(imgByte);
                    imageView.setBackgroundDrawable(Drawable.createFromStream(bis, "image"));
                    bis.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            case R.id.getObjectData:
                try {
                    personBase64Str = simpleDataSp.getString("personBase64", "");
                    byte[] personBase64Byte = Base64.decode(personBase64Str.getBytes(), Base64.DEFAULT);
                    ByteArrayInputStream bis = new ByteArrayInputStream(personBase64Byte);
                    ObjectInputStream ois = new ObjectInputStream(bis);
                    Person person = (Person) ois.readObject();
                    Toaster.toast(this, person.toString(), true);
                    byte[] iconByte = Base64.decode(person.getIcon().getBytes(), Base64.DEFAULT);
                    ByteArrayInputStream bis1 = new ByteArrayInputStream(iconByte);
                    Drawable drawable = Drawable.createFromStream(bis1, "image");
                    imageView.setBackgroundDrawable(drawable);
                    /**
                     * // Drawable---->Bitmap BitmapDrawable bitmapDrawable =
                     * (BitmapDrawable) drawable; Bitmap bitmap =
                     * bitmapDrawable.getBitmap();
                     *
                     * // Bitmap---->Drawable BitmapDrawable bitmapDrawable2 = new
                     * BitmapDrawable(bitmap);
                     *
                     * // BtimapDrawable是Drawable 的子类
                     */
                    ois.close();
                    bis.close();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        JPushInterface.onPause(this);
    }
}
