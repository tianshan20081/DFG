package com.gooker.dfg.utils.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.util.Log;


public class ImageUtils {
    // public static Bitmap getFitScreenBitmap(Bitmap bitmap) {
    // int width = SystemUtils.getScreenHeight();
    // int height = SystemUtils.getScreenHeight();
    //
    // int bitHeight = bitmap.getHeight();
    // int bitWidth = bitmap.getWidth();
    //
    // Bitmap.create
    //
    // }

    /**
     * 通过降低图片的质量来压缩图片
     * <p/>
     * 要压缩的图片
     *
     * @param maxSize 压缩后图片大小的最大值,单位KB
     * @return 压缩后的图片
     */
    public static Bitmap compressByQuality(Bitmap bitmap, int maxSize) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int quality = 100;
        bitmap.compress(CompressFormat.JPEG, quality, baos);
        System.out.println("图片压缩前大小：" + baos.toByteArray().length + "byte");
        while (baos.toByteArray().length / 1024 > maxSize) {
            quality -= 10;
            baos.reset();
            bitmap.compress(CompressFormat.JPEG, quality, baos);
            System.out.println("质量压缩到原来的" + quality + "%时大小为：" + baos.toByteArray().length + "byte");
        }
        System.out.println("图片压缩后大小：" + baos.toByteArray().length + "byte");
        bitmap = BitmapFactory.decodeByteArray(baos.toByteArray(), 0, baos.toByteArray().length);
        return bitmap;
    }

    /**
     * 通过压缩图片的尺寸来压缩图片大小
     *
     * @param pathName     图片的完整路径
     * @param targetWidth  缩放的目标宽度
     * @param targetHeight 缩放的目标高度
     * @return 缩放后的图片
     */
    public static Bitmap compressBySize(String pathName, int targetWidth, int targetHeight) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;// 不去真的解析图片，只是获取图片的头部信息，包含宽高等；
        Bitmap bitmap = BitmapFactory.decodeFile(pathName, opts);
        // 得到图片的宽度、高度；
        int imgWidth = opts.outWidth;
        int imgHeight = opts.outHeight;
        // 分别计算图片宽度、高度与目标宽度、高度的比例；取大于等于该比例的最小整数；
        int widthRatio = (int) Math.ceil(imgWidth / (float) targetWidth);
        int heightRatio = (int) Math.ceil(imgHeight / (float) targetHeight);
        if (widthRatio > 1 || widthRatio > 1) {
            if (widthRatio > heightRatio) {
                opts.inSampleSize = widthRatio;
            } else {
                opts.inSampleSize = heightRatio;
            }
        }
        // 设置好缩放比例后，加载图片进内容；
        opts.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeFile(pathName, opts);
        return bitmap;
    }

    /**
     * 通过压缩图片的尺寸来压缩图片大小
     *
     * @param bitmap       要压缩图片
     * @param targetWidth  缩放的目标宽度
     * @param targetHeight 缩放的目标高度
     * @return 缩放后的图片
     */
    public static Bitmap compressBySize(Bitmap bitmap, int targetWidth, int targetHeight) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, 100, baos);
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        bitmap = BitmapFactory.decodeByteArray(baos.toByteArray(), 0, baos.toByteArray().length, opts);
        // 得到图片的宽度、高度；
        int imgWidth = opts.outWidth;
        int imgHeight = opts.outHeight;
        // 分别计算图片宽度、高度与目标宽度、高度的比例；取大于该比例的最小整数；
        int widthRatio = (int) Math.ceil(imgWidth / (float) targetWidth);
        int heightRatio = (int) Math.ceil(imgHeight / (float) targetHeight);
        if (widthRatio > 1 && widthRatio > 1) {
            if (widthRatio > heightRatio) {
                opts.inSampleSize = widthRatio;
            } else {
                opts.inSampleSize = heightRatio;
            }
        }
        // 设置好缩放比例后，加载图片进内存；
        opts.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeByteArray(baos.toByteArray(), 0, baos.toByteArray().length, opts);
        return bitmap;
    }

    /**
     * 通过压缩图片的尺寸来压缩图片大小，通过读入流的方式，可以有效防止网络图片数据流形成位图对象时内存过大的问题；
     * <p/>
     * 要压缩图片，以流的形式传入
     *
     * @param targetWidth  缩放的目标宽度
     * @param targetHeight 缩放的目标高度
     * @return 缩放后的图片
     * @throws IOException 读输入流的时候发生异常
     */
    public static Bitmap compressBySize(InputStream is, int targetWidth, int targetHeight) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        int len = 0;
        while ((len = is.read(buff)) != -1) {
            baos.write(buff, 0, len);
        }

        byte[] data = baos.toByteArray();
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length, opts);
        // 得到图片的宽度、高度；
        int imgWidth = opts.outWidth;
        int imgHeight = opts.outHeight;
        // 分别计算图片宽度、高度与目标宽度、高度的比例；取大于该比例的最小整数；
        int widthRatio = (int) Math.ceil(imgWidth / (float) targetWidth);
        int heightRatio = (int) Math.ceil(imgHeight / (float) targetHeight);
        if (widthRatio > 1 && widthRatio > 1) {
            if (widthRatio > heightRatio) {
                opts.inSampleSize = widthRatio;
            } else {
                opts.inSampleSize = heightRatio;
            }
        }
        // 设置好缩放比例后，加载图片进内存；
        opts.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeByteArray(data, 0, data.length, opts);
        return bitmap;
    }

    // 根据路径获得图片并压缩，返回bitmap用于显示
    public static Bitmap getSmallBitmap(String filePath) {// 图片所在SD卡的路径
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        options.inSampleSize = calculateInSampleSize(options);// 自定义一个宽和高
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

    // 计算图片的缩放值
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;// 获取图片的高
        final int width = options.outWidth;// 获取图片的框
        int inSampleSize = 4;
        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;// 求出缩放值
    }

    // 计算图片的缩放值
    public static int calculateInSampleSize(BitmapFactory.Options options) {
        final int height = options.outHeight;// 获取图片的高
        final int width = options.outWidth;// 获取图片的框
        int inSampleSize = 4;
        int reqWidth = SystemUtils.getScreenWidth();
        int reqHeight = SystemUtils.getScreenHeight();
        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;// 求出缩放值
    }

    /**
     * 创建一个图片
     *
     * @param contentColor 内部填充颜色
     * @param strokeColor  描边颜色
     * @param radius       圆角
     */
    public static GradientDrawable createDrawable(int contentColor, int strokeColor, int radius) {
        GradientDrawable drawable = new GradientDrawable(); // 生成Shape
        drawable.setGradientType(GradientDrawable.RECTANGLE); // 设置矩形
        drawable.setColor(contentColor);// 内容区域的颜色
        drawable.setStroke(1, strokeColor); // 四周描边,描边后四角真正为圆角，不会出现黑色阴影。如果父窗体是可以滑动的，需要把父View设置setScrollCache(false)
        drawable.setCornerRadius(radius); // 设置四角都为圆角
        return drawable;
    }

    /**
     * 创建一个图片选择器
     *
     * @param normalState  普通状态的图片
     * @param pressedState 按压状态的图片
     */
    public static StateListDrawable createSelector(Drawable normalState, Drawable pressedState) {
        StateListDrawable bg = new StateListDrawable();
        bg.addState(new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled}, pressedState);
        bg.addState(new int[]{android.R.attr.state_enabled}, normalState);
        bg.addState(new int[]{}, normalState);
        return bg;
    }

    /**
     * 获取图片的大小
     */
    @SuppressLint("NewApi")
    public static int getDrawableSize(Drawable drawable) {
        if (drawable == null) {
            return 0;
        }
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
            return bitmap.getByteCount();
        } else {
            return bitmap.getRowBytes() * bitmap.getHeight();
        }
    }

    public static Bitmap parseBitmap(String path, int size) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        int max = options.outWidth > options.outHeight ? options.outWidth : options.outHeight;
        if (max > size) {
            int sam = max / size;
            options.inSampleSize = sam;
            int height = options.outHeight / sam;
            Log.e("height", "---" + height);
            int width = options.outWidth / sam;
            options.outWidth = width;
            options.outHeight = height;

        }
        /* 这样才能真正的返回一个Bitmap给你 */
        options.inJustDecodeBounds = false;
        return getBitmapByPath(path, options);
    }

    public static Bitmap parseBitmapToLittle(String path) {
        return parseBitmap(path, 320);
    }

    public static Bitmap parseHeadBitmapToLittle(String path) {
        return parseBitmap(path, 120);
    }

    /**
     * 获取bitmap
     *
     * @param filePath
     * @return
     */
    public static Bitmap getBitmapByPath(String filePath) {
        return getBitmapByPath(filePath, null);
    }

    public static Bitmap getBitmapByPath(String filePath, BitmapFactory.Options opts) {
        if (StringUtils.isEmpty(filePath))
            return null;
        FileInputStream fis = null;
        Bitmap bitmap = null;
        try {
            File file = new File(filePath);
            fis = new FileInputStream(file);
            bitmap = BitmapFactory.decodeStream(fis, null, opts);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (Exception e) {
            }
        }
        return bitmap;
    }

    /**
     * 根据Uri获取文件的路径
     *
     * @param uri
     * @return String
     * @Title: getUriString
     */
    public static String getUriString(Uri uri, ContentResolver cr) {
        String imgPath = null;
        if (uri != null) {
            String uriString = uri.toString();
            // 小米手机的适配问题，小米手机的uri以file开头，其他的手机都以content开头
            // 以content开头的uri表明图片插入数据库中了，而以file开头表示没有插入数据库
            // 所以就不能通过query来查询，否则获取的cursor会为null。
            if (uriString.startsWith("file")) {
                // uri的格式为file:///mnt....,将前七个过滤掉获取路径
                imgPath = uriString.substring(7, uriString.length());
                return imgPath;
            }
            Cursor cursor = cr.query(uri, null, null, null, null);
            cursor.moveToFirst();
            imgPath = cursor.getString(1); // 图片文件路径

        }
        return imgPath;
    }

    /**
     * 写图片文件到SD卡
     *
     * @throws IOException
     */
    public static void saveImageToSD(final String filePath, final Bitmap bitmap) {
        new Thread() {
            public void run() {
                try {
                    if (bitmap != null) {
                        FileOutputStream fos = new FileOutputStream(filePath);
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bitmap.compress(CompressFormat.PNG, 100, stream);
                        byte[] bytes = stream.toByteArray();
                        fos.write(bytes);
                        fos.close();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            ;
        }.start();

    }

    /**
     * 压缩图片 上传图片时调用
     *
     * @param imgPath
     * @return
     */
    public static Bitmap compressImg(String imgPath, int maxSize) {
        Bitmap resizeBitmap = null;
        if (StringUtils.isEmpty(imgPath)) {
            try {
                resizeBitmap = parseBitmap(imgPath, 1024);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                ExifInterface sourceExif = new ExifInterface(imgPath);
                int result = sourceExif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
                int rotate = 0;
                switch (result) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        rotate = 90;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        rotate = 180;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        rotate = 270;
                        break;
                }

                if (resizeBitmap != null) {
                    if (rotate > 0) {
                        Matrix matrix = new Matrix();
                        matrix.setRotate(rotate);

                        Bitmap rotateBitmap = Bitmap.createBitmap(resizeBitmap, 0, 0, resizeBitmap.getWidth(), resizeBitmap.getHeight(), matrix, true);
                        if (rotateBitmap != null) {
                            resizeBitmap.recycle();
                            resizeBitmap = rotateBitmap;
                        }
                    }
                    int options = 90;
                    resizeBitmap.compress(CompressFormat.JPEG, options, baos);
                    while (baos.toByteArray().length > maxSize) { // 循环判断如果压缩后图片是否大于200K,大于继续压缩
                        baos.reset();// 重置baos即清空baos
                        resizeBitmap.compress(CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
                        options -= 10;// 每次都减少10
                    }
                    // bmp.recycle();

                    return resizeBitmap;
                }

            } catch (OutOfMemoryError e) {
                System.gc();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static InputStream compressForUpload(String imgPath, int maxSize) {
        Bitmap resizeBitmap = null;
        if (StringUtils.isEmpty(imgPath)) {
            try {
                resizeBitmap = parseBitmap(imgPath, 1024);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                ExifInterface sourceExif = new ExifInterface(imgPath);
                int result = sourceExif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
                int rotate = 0;
                switch (result) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        rotate = 90;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        rotate = 180;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        rotate = 270;
                        break;
                }

                if (resizeBitmap != null) {
                    if (rotate > 0) {
                        Matrix matrix = new Matrix();
                        matrix.setRotate(rotate);

                        Bitmap rotateBitmap = Bitmap.createBitmap(resizeBitmap, 0, 0, resizeBitmap.getWidth(), resizeBitmap.getHeight(), matrix, true);
                        if (rotateBitmap != null) {
                            resizeBitmap.recycle();
                            resizeBitmap = rotateBitmap;
                        }
                    }
                    int options = 90;
                    resizeBitmap.compress(CompressFormat.JPEG, options, baos);
                    while (baos.toByteArray().length > maxSize) { // 循环判断如果压缩后图片是否大于200K,大于继续压缩
                        baos.reset();// 重置baos即清空baos
                        resizeBitmap.compress(CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
                        options -= 10;// 每次都减少10
                    }
                    // bmp.recycle();
                    InputStream sbs = new ByteArrayInputStream(baos.toByteArray());
                    return sbs;
                }

            } catch (OutOfMemoryError e) {
                System.gc();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static InputStream compressForUpload2(String imgPath, int maxSize) {
        Bitmap resizeBitmap = null;
        if (!StringUtils.isEmpty(imgPath)) {
            try {

                // resizeBitmap = parseBitmapByOneEdge(imgPath, 768);
                resizeBitmap = parseBitmapByOneEdge2(imgPath, 768);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                ExifInterface sourceExif = new ExifInterface(imgPath);
                int result = sourceExif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
                int rotate = 0;
                switch (result) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        rotate = 90;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        rotate = 180;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        rotate = 270;
                        break;
                }

                if (resizeBitmap != null) {
                    if (rotate > 0) {
                        Matrix matrix = new Matrix();
                        matrix.setRotate(rotate);

                        Bitmap rotateBitmap = Bitmap.createBitmap(resizeBitmap, 0, 0, resizeBitmap.getWidth(), resizeBitmap.getHeight(), matrix, true);
                        if (rotateBitmap != null) {
                            resizeBitmap.recycle();
                            resizeBitmap = rotateBitmap;
                        }
                    }
                    int options = 90;
                    resizeBitmap.compress(CompressFormat.JPEG, options, baos);
                    while (baos.toByteArray().length > maxSize) { // 循环判断如果压缩后图片是否大于200K,大于继续压缩
                        baos.reset();// 重置baos即清空baos
                        resizeBitmap.compress(CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
                        options -= 10;// 每次都减少10
                    }
                    // bmp.recycle();
                    InputStream sbs = new ByteArrayInputStream(baos.toByteArray());
                    return sbs;
                }

            } catch (OutOfMemoryError e) {
                System.gc();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static Bitmap parseBitmapByOneEdge2(String imgPath, int i) {
        // TODO Auto-generated method stub
        Bitmap bitmap = BitmapFactory.decodeFile(imgPath);
        return parseBitmap(bitmap, i);
    }

    private static Bitmap parseBitmap(Bitmap mBitmap, int unit) {
        float sx = getScaleSize(mBitmap, unit);
        if (sx > 0)
            return postScaleBitamp(mBitmap, sx, sx);
        return mBitmap;
    }

    /**
     * 图片放大缩小
     *
     * @param bmp
     * @param degree
     * @return
     */
    public static Bitmap postScaleBitamp(Bitmap bmp, float sx, float sy) {
        // 获得Bitmap的高和宽
        int bmpWidth = bmp.getWidth();
        int bmpHeight = bmp.getHeight();
        Matrix matrix = new Matrix();
        matrix.setScale(sx, sy);
        Bitmap resizeBmp = Bitmap.createBitmap(bmp, 0, 0, bmpWidth, bmpHeight, matrix, true);
        return resizeBmp;
    }

    /**
     * 对图片进行处理 1，首先判断 图片的宽和高
     */
    public static float getScaleSize(Bitmap mBitmap, int unit) {
        float imgWidth = mBitmap.getWidth();
        float imgHeight = mBitmap.getHeight();
        float sx;
        if (imgWidth < 300 || imgHeight < 300)
            return -1;
        if (imgWidth > imgHeight) {
            sx = unit / imgHeight;
            if (imgWidth * sx >= 4096) {
                /**
                 * TODO add by wxbin ImageView 最大加载 4096 * 4096
                 */
                sx = 4096 / imgWidth;
            }
        } else {
            sx = unit / imgWidth;
            if (imgHeight * sx >= 4096) {
                sx = 4096 / imgHeight;
            }
        }
        return sx;
    }

}
