package com.gooker.dfg.utils.common;

import android.os.Environment;

import com.gooker.dfg.utils.AppUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;


public class FileUtils {
    private static String ROOT_DIR = "df";
    private static String CRASH_DIR = "crash";
    private static String ROOT_IMG = "img";
    public static final String DOWNLOAD_DIR = "download";
    public static final String CACHE_DIR = "cache";
    public static final String ICON_DIR = "icon";
    private static final String ARM_DIR = "arm";
    private static final String TEMP_DIR = "temp";
    private static final String IMG_DIR = "img";

    /**
     * 用当前时间给取得的图片命名
     */
    public static String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss", Locale.CHINA);
        return dateFormat.format(date) + ".jpg";
    }

    public static String getAppCrashPath() {
        // TODO Auto-generated method
        // stub
        return getDir(CRASH_DIR);
    }

    public static File getPhonePhotoFolder() {
        // TODO Auto-generated method
        // stub
        LogUtils.i("Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)+"
                + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsoluteFile());
        LogUtils.i("Environment.getDataDirectory()" + Environment.getDataDirectory().getAbsolutePath());
        // LogUtils.i("Environment.getDataDirectory()"
        // +
        // Environment.getExternalStoragePublicDirectory(Environment.));

        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
    }

    private static boolean isSDCardAvailiable() {
        // TODO Auto-generated method
        // stub
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 读取文件 并将文件中保存的 Properties 中的 键为
     * key 的数据
     *
     * @param filePath     文件保存地址
     * @param key          需要获取的 值得键
     * @param defaultValue 取不到 键对应的值 则返回默认值
     * @return 返回需要获得 Properties 中的 key
     * 对应的 值，取不到 则返回 默认值
     */
    public static String readProperties(String filePath, String key, String defaultValue) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(filePath)) {
            return null;
        }
        String value = null;
        FileInputStream fis = null;
        File file = new File(filePath);
        try {
            if (!file.exists() || !file.isFile()) {
                file.createNewFile();
            }
            fis = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(fis);
            value = properties.getProperty(key, defaultValue);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtils.e(e);
        } finally {
            IOUtils.close(fis);
        }
        return value;
    }

    /**
     * 判斷 SD 卡 是否存在
     *
     * @return
     */
    public static boolean isSDCardAvailable() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 獲取下載地址
     *
     * @return
     */
    public static String getDownloadDir() {
        return getDir(DOWNLOAD_DIR);
    }

    /**
     * 獲取緩存目錄
     *
     * @return
     */
    public static String getCacheDir() {
        return getDir(CACHE_DIR);

    }

    /**
     * 獲取 ICON 地址
     *
     * @return
     */
    public static String getIconDir() {
        return getDir(ICON_DIR);
    }

    public static String getAppRootPath() {
        // TODO Auto-generated method
        // stub
        StringBuilder sb = new StringBuilder();
        if (isSDCardAvailable()) {
            sb.append(getExternalStoragePath());
        } else
            sb.append(getCachePath());
        if (!StringUtils.isEmpty(sb.toString())) {
            File file = new File(sb.append(File.separator).append(ROOT_DIR).toString());
            if (!file.exists()) {
                file.mkdirs();
            }
            return file.getAbsolutePath();
        }
        return null;
    }

    /**
     * 獲取應用目錄，當 SD 卡存在時 ，獲取 SD 卡上的目錄，當
     * SD卡不存在，獲取應用程序的 cache 目錄
     *
     * @return downloadDir/
     */
    private static String getDir(String name) {
        // TODO Auto-generated method
        // stub
        if (StringUtils.isEmpty(name)) {
            LogUtils.e(" folder name is null");
            return null;
        }
        String rootPath = getAppRootPath();
        if (StringUtils.isEmpty(rootPath)) {
            return null;
        }
        String path = rootPath.concat(File.separator).concat(name);
        if (createDirs(path)) {
            return path.concat(File.separator);
        } else
            return null;
    }

    /**
     * 獲取 SD 卡下的應用目錄
     *
     * @return
     */
    private static String getExternalStoragePath() {
        // TODO Auto-generated method
        // stub
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory().getAbsolutePath());
        return sb.toString();
    }

    /**
     * 創建文件夾
     *
     * @param dirPath
     * @return
     */
    private static boolean createDirs(String dirPath) {
        // TODO Auto-generated method
        // stub
        File file = new File(dirPath);
        if (!file.exists() || !file.isDirectory()) {
            return file.mkdirs();
        }
        return true;
    }

    /**
     * 獲取應用的 cache 目錄
     *
     * @return
     */
    private static String getCachePath() {
        // TODO Auto-generated method
        // stub
        File file = UIUtils.getContext().getCacheDir();
        if (null == file) {
            return null;
        } else
            return file.getAbsolutePath();
    }

    /**
     * 複製文件，可選擇是否刪除源文件
     *
     * @param srcPath
     * @param destPath
     * @param deleteSrc
     * @return
     */
    public static boolean copyFile(String srcPath, String destPath, boolean deleteSrc) {
        File srcFile = new File(srcPath);
        File destFile = new File(destPath);
        return copyFile(srcFile, destFile, deleteSrc);

    }

    /**
     * 複製文件，可選擇是否刪除源文件
     *
     * @param srcFile
     * @param destFile
     * @param deleteSrc
     * @return
     */
    private static boolean copyFile(File srcFile, File destFile, boolean deleteSrc) {
        // TODO Auto-generated method
        // stub
        if (!srcFile.exists() || !srcFile.isFile()) {
            return false;
        }
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(srcFile);
            out = new FileOutputStream(destFile);
            byte[] buffer = new byte[1024];
            int i = -1;
            while ((i = in.read(buffer)) > 0) {
                out.write(buffer, 0, i);
                out.flush();
            }
            if (deleteSrc) {
                srcFile.delete();
            }
        } catch (Exception e) {
            // TODO: handle exception
            LogUtils.e(e);
            return false;
        } finally {
            IOUtils.close(out);
            IOUtils.close(in);

        }

        return true;
    }

    /**
     * 判断文件是否可写
     *
     * @param path
     * @return
     */
    public static boolean isWriteable(String path) {
        try {
            if (StringUtils.isEmpty(path)) {
                return false;
            }
            File file = new File(path);
            return file.exists() && file.canWrite();
        } catch (Exception e) {
            // TODO: handle exception
            LogUtils.e(e);
            return false;
        }

    }

    /**
     * 修改文件权限 例如 “777” 等
     *
     * @param path
     * @param mode
     */
    public static void chmod(String path, String mode) {
        try {
            String command = "chmod " + mode + " " + path;
            Runtime runtime = Runtime.getRuntime();
            runtime.exec(command);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtils.e(e);
        }
    }

    /**
     * 把数据写入到文件
     * <p/>
     * 要写入文件的 字符串
     *
     * @param path   文件路径
     * @param append 是否以添加的模式写入
     * @return 是否写入成功
     */
    public static boolean write2File(String content, String path, boolean append) {
        // TODO Auto-generated method
        // stub
        return write2File(content.getBytes(), path, append);
    }

    /**
     * 将数据写入到文件
     * <p/>
     * 要写入文件的 字节数组
     *
     * @param path   文件路径
     * @param append 是否以追加的方式写入
     * @return 是否写入成功
     */
    private static boolean write2File(byte[] content, String path, boolean append) {
        // TODO Auto-generated method
        // stub
        boolean res = false;
        File file = new File(path);
        RandomAccessFile raf = null;
        try {
            if (file.exists()) {
                if (append) {
                    file.delete();
                    file.createNewFile();
                }
            } else {
                file.createNewFile();
            }
            if (file.canWrite()) {
                raf = new RandomAccessFile(file, "rw");
                raf.seek(raf.length());
                raf.write(content);
                res = true;
            }
        } catch (Exception e) {
            // TODO: handle exception
            LogUtils.e(e);
        } finally {
            IOUtils.close(raf);
        }
        return res;
    }

    /**
     * 将数据写入到文件
     *
     * @param is       数据输入流
     * @param path     文件路径
     * @param reCreate 如果文件存在是否需要 删除重建
     * @return 是否写入成功
     */
    public static boolean write2File(InputStream is, String path, boolean reCreate) {
        boolean res = false;
        File file = new File(path);
        FileOutputStream fos = null;
        try {
            if (reCreate && file.exists()) file.delete();
            if (!file.exists() && null != is) {
                File parentFile = new File(file.getParent());
                parentFile.mkdirs();
                int count = -1;
                byte[] buffer = new byte[1024];
                fos = new FileOutputStream(file);
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                    fos.flush();
                }
                res = true;
            }
        } catch (Exception e) {
            // TODO: handle exception
            LogUtils.e(e);
        } finally {
            IOUtils.close(fos);
            IOUtils.close(is);
        }
        return res;
    }

    /**
     * 把键值对写入到文件
     *
     * @param filePath 文件路径
     * @param key      键
     * @param value    值
     * @param comment  该键值的 注释
     */
    public void writeProperties(String filePath, String key, String value, String comment) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(filePath)) {
            return;
        }
        FileInputStream fis = null;
        FileOutputStream fos = null;
        File file = new File(filePath);
        try {
            if (!file.exists() || !file.isFile()) {
                file.createNewFile();
            }
            fis = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(fis);// 先读取文件，然后将键值追加
            properties.put(key, value);
            fos = new FileOutputStream(file);
            properties.store(fos, comment);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtils.e(e);
        } finally {
            IOUtils.close(fos);
            IOUtils.close(fis);
        }
    }

    /**
     * 将 map<String,String> 中的值放入到文件中
     *
     * @param filePath 文件保存路径
     * @param map      需要保存的 数据 map
     * @param append   是否以追加的方式保存
     * @param comment  数据保存时候的注释
     */
    public static void write2map(String filePath, Map<String, String> map, boolean append, String comment) {
        if (map == null || map.size() == 0 || StringUtils.isEmpty(filePath)) {
            return;
        }
        FileInputStream fis = null;
        FileOutputStream fos = null;
        File file = new File(filePath);
        try {
            if (!file.exists() || !file.isFile()) {
                file.createNewFile();
            }
            Properties properties = new Properties();
            if (append) {
                fis = new FileInputStream(file);
                properties.load(fis);// 先读取文件，再将键值对
                // 追加到后面
            }
            properties.putAll(map);
            fos = new FileOutputStream(file);
            properties.store(fos, comment);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtils.e(e);
        } finally {
            IOUtils.close(fos);
            IOUtils.close(fis);
        }
    }

    /**
     * 把字符串 键值对的文件保存到 map 中
     *
     * @param filePath     文件路径
     * @param defauleValue 保存信息 注释
     * @return 从键值对文件中 返回的
     * map<String,String>
     */
    public static Map<String, String> readMap(String filePath, String defauleValue) {
        if (StringUtils.isEmpty(filePath)) {
            return null;
        }
        Map<String, String> map = null;
        FileInputStream fis = null;
        File file = new File(filePath);
        try {
            if (!file.exists() || !file.isFile()) {
                file.createNewFile();
            }
            fis = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(fis);
            // 因为 Properties 继承了 Map
            // ,所以通过 properties 构造一个 map
            map = new HashMap<String, String>((Map) properties);
        } catch (Exception e) {
            // TODO: handle exception
            LogUtils.e(e);
        } finally {
            IOUtils.close(fis);
        }
        return map;

    }

    /**
     * 文件复制 将 srcPath 文件复制到 destPath
     *
     * @param srcPath   源文件
     * @param destPath  目标地址文件
     * @param srcDelete 复制完成过后是否需要删除 源文件
     * @return 文件复制是否完成
     */
    public static boolean copy(String srcPath, String destPath, boolean srcDelete) {
        File file = new File(srcPath);
        if (!file.exists()) {
            return false;
        }
        File destFile = new File(destPath);
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(file);
            fos = new FileOutputStream(destFile);
            byte[] buffer = new byte[1024];
            int count = -1;
            while ((count = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, count);
                fos.flush();
            }
        } catch (Exception e) {
            // TODO: handle exception
            LogUtils.e(e);
            return false;
        } finally {
            IOUtils.close(fos);
            IOUtils.close(fis);
        }
        if (srcDelete) {
            file.delete();
        }
        return true;
    }

    /**
     * 用当前时间给取得的图片命名
     */
    public static String getFileName(FileType fileType) {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("_yyMMdd_HHmmssSSS", Locale.CHINA);
        return dateFormat.format(date) + fileType.getValue();
    }

    public static String getArmDir() {
        // TODO Auto-generated method
        // stub
        return getDir(ARM_DIR);
    }

    public static List<String> getCarshFiles() {

        // TODO Auto-generated method
        // stub
        ArrayList<String> files = new ArrayList<String>();
        String path = FileUtils.getAppCrashPath();
        LogUtils.i("appcrashpath " + path);
        if (!StringUtils.isEmpty(path)) {
            File fileCrash = new File(path);
            if (fileCrash.exists() && fileCrash.isDirectory() && fileCrash.list().length > 0) {
                String[] loges = fileCrash.list();
                if (null == loges || loges.length == 1) {
                    return null;
                }
                File logFile = null;
                for (String fileName : loges) {
                    logFile = new File(path, fileName);
                    if (logFile.exists() && logFile.isFile()) {
                        files.add(logFile.getAbsolutePath());
                    }
                }
            }
        }

        return files;

    }

    public static File getCrashFile() {
        // TODO Auto-generated method
        // stub

        String crashPath = FileUtils.getAppCrashPath();
        String logName = AppUtils.getDeviceName() + "-crash-" + getFileName(FileType.LOG);

        return getFile(crashPath, logName);
    }

    private static File getFile(String crashPath, String logName) {
        if (!StringUtils.isEmpty(crashPath)) {
            File file = new File(crashPath);
            File logFile = new File(file, logName);
            if (!logFile.exists()) {
                try {
                    logFile.createNewFile();
                } catch (IOException e) {
                    // TODO
                    // Auto-generated
                    // catch block
                    LogUtils.e(e);
                    return null;
                }
            }
            return logFile;
        }
        return null;
    }

    public static File getTempleFile(String infos) {
        // TODO Auto-generated method
        // stub
        String tempPath = getTempPath();
        String fileName = AppUtils.getDeviceName().toUpperCase() + "-apps-" + getFileName(FileType.LOG);
        File file = getFile(tempPath, fileName);
        if (file.exists()) {
            try {
                FileOutputStream fos = new FileOutputStream(file, true);
                fos.write(infos.getBytes());
                fos.flush();
                fos.close();
            } catch (Exception e) {
                // TODO Auto-generated
                // catch block
                LogUtils.e(e);
            }
        }
        return file;
    }

    private static String getTempPath() {
        // TODO Auto-generated method
        // stub
        return getDir(TEMP_DIR);
    }

    public static String getTempleFilePath(String infos) {
        // TODO Auto-generated method
        // stub
        return getTempleFile(infos).getAbsolutePath();
    }

    /**
     * 获得 文件夹 @folderName 下面的 所有文件 集合
     *
     * @param folderName 文件夹的名称 eg .D:\\Camera
     * @return
     */
    public static List<String> getFileLists(String folderName) {
        // TODO Auto-generated method
        // stub
        List<String> filePaths = new ArrayList<String>();
        if (StringUtils.isEmpty(folderName)) {
            throw new IllegalArgumentException("the folder name is empty !!! ");
        }
        File folder = new File(folderName);
        if (!folder.exists()) {
            throw new IllegalArgumentException("the folder name is not exists !");
        }
        if (!folder.isDirectory()) {
            throw new IllegalArgumentException("the folder name is not a directory !");
        }
        File[] files = folder.listFiles();
        if (null != files && files.length > 0) {
            for (File file : files) {
                if (file.isFile()) {
                    filePaths.add(file.getAbsolutePath());
                } else {
                    filePaths.addAll(getFileLists(file.getAbsolutePath()));
                }
            }
        }
        return filePaths;
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * <p/>
     * 将要删除的文件目录
     *
     * @return boolean Returns "true" if
     * all deletions were
     * successful. If a
     * deletion fails, the
     * method stops attempting
     * to delete and returns
     * "false".
     */
    public static boolean deleteDir(String dirPath) {
        if (StringUtils.isEmpty(dirPath)) {
            return false;
        }
        File dir = new File(dirPath);
        if (!dir.exists()) {
            return false;
        }
        if (dir.isDirectory()) {
            String[] children = dir.list();
            // 递归删除目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]).getAbsolutePath());
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

    /**
     * @return
     */
    public static String getImgFile() {
        // TODO Auto-generated method
        // stub
        return getImgDir().concat(File.separator).concat(getFileName(FileType.IMG));
    }

    public static String getArmFile() {
        // TODO Auto-generated method
        // stub
        return getImgDir().concat(File.separator).concat(getFileName(FileType.AMR));
    }

    private static String getImgDir() {
        // TODO Auto-generated method
        // stub
        return getDir(IMG_DIR);
    }

    enum FileType {
        IMG(".jpg"), AMR(".amr"), APK(".apk"), LOG(".log");

        private String value;

        private String getValue() {
            return this.value;
        }

        /**
         *
         */
        private FileType(String value) {
            // TODO Auto-generated
            // constructor stub
            this.value = value;
        }

    }

}
