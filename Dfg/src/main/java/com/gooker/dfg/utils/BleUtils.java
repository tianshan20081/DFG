package com.gooker.dfg.utils;


import android.bluetooth.BluetoothDevice;

import com.gooker.dfg.utils.common.StringUtils;

public class BleUtils {

    public static final int BLE_UNDEFINE = -1;
    public static final int BLE_SHIYUN = 1;
    public static final int BLE_MIBIND = 2;
    public static final int BLE_YICHENG = 3;


    public static int getBleType(BluetoothDevice device) {
        // TODO Auto-generated method stub
        if (null == device) {
            return BLE_UNDEFINE;
        }
        if (!StringUtils.isEmpty(device.getName())) {
            String deviceName = device.getName().toLowerCase().trim();
            if ((deviceName.equals("chronocloud") || deviceName.equals("ryfit"))) {
                return BLE_SHIYUN;
            } else if (deviceName.equals("mi")) {
                return BLE_MIBIND;
            } else if (deviceName.equals("BJYC_5D8_BLE".toLowerCase())) {
                return BLE_YICHENG;
            } else {
                return BLE_UNDEFINE;
            }
        }

        return BLE_UNDEFINE;
    }

}
