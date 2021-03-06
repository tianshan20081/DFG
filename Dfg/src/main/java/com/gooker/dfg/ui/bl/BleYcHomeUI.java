package com.gooker.dfg.ui.bl;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothProfile;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.gooker.dfg.R;
import com.gooker.dfg.domain.Battery;
import com.gooker.dfg.domain.LeParams;
import com.gooker.dfg.ui.BaseUI;
import com.gooker.dfg.utils.common.LogUtils;
import com.gooker.dfg.utils.common.StringUtils;

import java.util.Arrays;
import java.util.UUID;


public class BleYcHomeUI extends BaseUI {
    private static final UUID UUID_MILI_SERVICE = UUID.fromString("0000fee0-0000-1000-8000-00805f9b34fb");
    private static final UUID UUID_CHAR_pair = UUID.fromString("0000ff0f-0000-1000-8000-00805f9b34fb");
    private static final UUID UUID_CHAR_CONTROL_POINT = UUID.fromString("0000ff05-0000-1000-8000-00805f9b34fb");
    private static final UUID UUID_CHAR_REALTIME_STEPS = UUID.fromString("0000ff06-0000-1000-8000-00805f9b34fb");
    private static final UUID UUID_CHAR_ACTIVITY = UUID.fromString("0000ff07-0000-1000-8000-00805f9b34fb");
    private static final UUID UUID_CHAR_LE_PARAMS = UUID.fromString("0000ff09-0000-1000-8000-00805f9b34fb");
    private static final UUID UUID_CHAR_DEVICE_NAME = UUID.fromString("0000ff02-0000-1000-8000-00805f9b34fb");
    private static final UUID UUID_CHAR_BATTERY = UUID.fromString("0000ff0c-0000-1000-8000-00805f9b34fb");
    private TextView tvSteps;
    private TextView tvBattery;
    private TextView tvName;
    private BluetoothDevice mMiLeDevice;
    private BluetoothGatt mGatt;
    private static final int UPDATE_STEPS = 100;
    private static final int UPDATE_BATTERY = 102;
    private static final int UPDATE_NAME = 101;
    private static final int UPDATE_LEPARAM = 103;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_STEPS:
                    String steps = (String) msg.obj;
                    if (!StringUtils.isEmpty(steps)) {
                        tvSteps.setText(steps);
                    }
                    break;
                case UPDATE_BATTERY:
                    Battery battery = (Battery) msg.obj;
                    if (null != battery) {
                        tvBattery.setText(battery.mBatteryLevel + "");
                    }
                    break;
                case UPDATE_NAME:
                    String name = (String) msg.obj;
                    if (!StringUtils.isEmpty(name)) {
                        tvName.setText(name);
                    }
                    break;
                case UPDATE_LEPARAM:
                    LeParams leParams = (LeParams) msg.obj;
                    if (null != leParams) {
                        // tvParams.setText(leParams.)
                    }

                    break;
                default:
                    break;
            }

        }

        ;

    };

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void loadViewLayout() {
        // TODO Auto-generated method stub

        setContentView(R.layout.ui_ble_yc_home);

    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        mGatt = mMiLeDevice.connectGatt(BleYcHomeUI.this, false, mGattCallBack);
        mGatt.connect();
    }

    private BluetoothGattCallback mGattCallBack = new BluetoothGattCallback() {

        int state = 0;

        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
//				pair();

            }

        }

        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                gatt.discoverServices();
            }
        }

        @Override
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            // this is called tight after pair()
            // setColor((byte)127, (byte)0, (byte)0);
            request(UUID_CHAR_REALTIME_STEPS); // start with steps
        }

        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            byte[] b = characteristic.getValue();
            Log.i(characteristic.getUuid().toString(), "state: " + state + " value:" + Arrays.toString(b));

//			Message message = mHandler.obtainMessage();
            // handle value
            LogUtils.e("UUID---->" + characteristic.getUuid().toString());
//			if (characteristic.getUuid().equals(UUID_CHAR_REALTIME_STEPS)) {
//				// mMiBand.setSteps(0xff & b[0] | (0xff & b[1]) << 8);
//				message.what = UPDATE_STEPS;
//				message.obj = (0xff & b[0] | (0xff & b[1]) << 8) + "";
//				mHandler.sendMessage(message);
//			} else if (characteristic.getUuid().equals(UUID_CHAR_BATTERY)) {
//				message.what = UPDATE_BATTERY;
//				Battery battery = Battery.fromByte(b);
//				// mMiBand.setBattery(battery);
//				message.obj = battery;
//				mHandler.sendMessage(message);
//			} else if (characteristic.getUuid().equals(UUID_CHAR_DEVICE_NAME)) {
//				// mMiBand.setName(new String(b));
//				message.what = UPDATE_NAME;
//				message.obj = new String(b);
//				mHandler.sendMessage(message);
//			} else if (characteristic.getUuid().equals(UUID_CHAR_LE_PARAMS)) {
//				LeParams params = LeParams.fromByte(b);
//				// mMiBand.setLeParams(params);
//				message.what = UPDATE_LEPARAM;
//				message.obj = params;
//				mHandler.sendMessage(message);
//			}

            // proceed with state machine (called in the beginning)
            // state++;
            // switch (state) {
            // case 0:
            // request(UUID_CHAR_REALTIME_STEPS);
            // break;
            // case 1:
            // request(UUID_CHAR_BATTERY);
            // break;
            // case 2:
            // request(UUID_CHAR_DEVICE_NAME);
            // break;
            // case 3:
            // request(UUID_CHAR_LE_PARAMS);
            // break;
            // }
        }

    };

    private void pair() {

        BluetoothGattCharacteristic chrt = getMiliService().getCharacteristic(UUID_CHAR_pair);

        chrt.setValue(new byte[]{2});

        mGatt.writeCharacteristic(chrt);
        System.out.println("pair sent");
    }

    private BluetoothGattService getMiliService() {
        return mGatt.getService(UUID_MILI_SERVICE);

    }

    private void request(UUID what) {
        mGatt.readCharacteristic(getMiliService().getCharacteristic(what));
    }

    @Override
    protected void findViewById() {
        // TODO Auto-generated method stub
        tvSteps = (TextView) findView(R.id.tvSteps);
        tvBattery = (TextView) findView(R.id.tvBattery);
        tvName = (TextView) findView(R.id.tvName);

        mMiLeDevice = getIntent().getParcelableExtra("deviceInfo");
    }

    @Override
    protected void setListener() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void processLogic() {
        // TODO Auto-generated method stub

    }

}
