package com.gooker.dfg.ui.bl;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothAdapter.LeScanCallback;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.gooker.dfg.R;
import com.gooker.dfg.adapter.BleAdapter;
import com.gooker.dfg.ui.BaseUI;
import com.gooker.dfg.utils.BleUtils;
import com.gooker.dfg.utils.common.LogUtils;
import com.gooker.dfg.utils.common.UIUtils;

import java.util.ArrayList;
import java.util.List;


public class BlHomeUI extends BaseUI {

    private static final int REQUEST_ENABLE_BT = 100;
    private Button btnBleCheck;
    private boolean isSuportBle = false;
    private boolean isBleOpen = false;
    private BluetoothAdapter mBluetoothAdapter;
    private Handler mHandler = new Handler();
    private boolean mScanning = false;
    private int mConnectionState = STATE_DISCONNECTED;
    private static final int STATE_DISCONNECTED = 0;
    private static final int STATE_CONNECTING = 1;
    private static final int STATE_CONNECTED = 2;

    private long startScan = 0;

    // public final static UUID UUID_HEART_RATE_MEASUREMENT =
    // UUID.fromString(SampleGattAttributes.HEART_RATE_MEASUREMENT);

    // Stops scanning after 10 seconds.
    private static final long SCAN_PERIOD = 100 * 10000;
    private BluetoothGatt mBluetoothGatt;
    private ListView lvBles;
    private BleAdapter mBleAdapter;
    protected boolean mConnected;
    private Button btnBleStart;
    private List<BluetoothDevice> mDevices = new ArrayList<BluetoothDevice>();

    private LeScanCallback mLeScanCallback = new LeScanCallback() {
        @Override
        public void onLeScan(final BluetoothDevice device, final int rssi, final byte[] scanRecord) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    // mLeDeviceListAdapter.addDevice(device);
                    // mLeDeviceListAdapter.notifyDataSetChanged();
                    UIUtils.toastShow("find new device" + device.getName());
                    LogUtils.e(device.getName() + "--" + device.getAddress() + "--" + device.getType() + device.getUuids());
                    // ChronoCloud--D0:39:72:BF:05:51--2null
                    // MI--88:0F:10:4A:A8:1E--2null

                    // mBluetoothGatt = device.connectGatt(BlHomeUI.this, false,
                    // mGattCallback);
                    LogUtils.e("find device take " + (System.currentTimeMillis() - startScan) / 1000 + "'s");
                    startScan = System.currentTimeMillis();
                    mBleAdapter.add(device, rssi, scanRecord);

                }
            });
        }
    };

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btnBleCheck:
                if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
                    // Toast.makeText(this, R.string.ble_not_supported,
                    // Toast.LENGTH_SHORT).show();
                    UIUtils.toastShow("Not suport ble ");
                } else {
                    isSuportBle = true;
                    BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
                    mBluetoothAdapter = bluetoothManager.getAdapter();
                    if (!isBleOpen) {
                        UIUtils.toastShow("Great your phone is  suport ble ");
                        btnBleStart.setEnabled(true);
                        btnBleStart.setText(R.string.ble_start_scan);
                        btnBleCheck.setText(R.string.ble_open);

                        if (mBluetoothAdapter != null && mBluetoothAdapter.isEnabled()) {
                            isBleOpen = true;
                            btnBleCheck.setText(R.string.ble_close);
                        } else {
                            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                        }
                    } else {
                        isBleOpen = false;
                        btnBleCheck.setText(R.string.ble_open);
                        if (null != mBleAdapter) {
                            mBluetoothAdapter.disable();
                        }
                    }
                }
                break;
            case R.id.btnBleStart:
                if (!mScanning) {
                    mBluetoothAdapter.startLeScan(mLeScanCallback);
                    startScan = System.currentTimeMillis();
                    scanLeDevice();
                    btnBleStart.setText(R.string.ble_stop_scan);
                    mScanning = true;
                } else {
                    btnBleStart.setText(R.string.ble_start_scan);
                    mBluetoothAdapter.stopLeScan(mLeScanCallback);
                    mScanning = false;
                }

                break;
            case R.id.btnFinish:
                finish();
                break;
            default:
                break;
        }
    }

    private void scanLeDevice() {
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                mScanning = false;
                mBluetoothAdapter.stopLeScan(mLeScanCallback);
                runOnUiThread(new Runnable() {
                    public void run() {
                        btnBleStart.setText(R.string.ble_start_scan);
                    }
                });
            }
        }, SCAN_PERIOD);
    }

    @Override
    protected void loadViewLayout() {
        // TODO Auto-generated method stub

        setContentView(R.layout.ui_bl_home);
    }

    @Override
    protected void findViewById() {
        // TODO Auto-generated method stub
        btnBleCheck = (Button) findView(R.id.btnBleCheck);
        btnBleStart = (Button) findView(R.id.btnBleStart);
        findView(R.id.btnFinish).setOnClickListener(this);
        lvBles = (ListView) findView(R.id.lvBles);
        btnBleStart.setEnabled(false);
    }

    @Override
    protected void setListener() {
        // TODO Auto-generated method stub
        btnBleCheck.setOnClickListener(this);
        btnBleStart.setOnClickListener(this);
        lvBles.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                BluetoothDevice device = mBleAdapter.getDevice(position);
                if (null == device) {
                    return;
                }
                mBluetoothAdapter.stopLeScan(mLeScanCallback);
                btnBleStart.setText(R.string.ble_start_scan);
                int deviceType = BleUtils.getBleType(device);
                Intent bleItemIntent = null;
                switch (deviceType) {
                    case BleUtils.BLE_MIBIND:
                        bleItemIntent = new Intent(BlHomeUI.this, MiLeHomeUI.class);
                        bleItemIntent.putExtra("deviceInfo", device);
                        startActivity(bleItemIntent);
                        break;
                    case BleUtils.BLE_SHIYUN:
                        bleItemIntent = new Intent(BlHomeUI.this, BleShiYunHomeUI.class);
                        bleItemIntent.putExtra("deviceInfo", device);
                        startActivity(bleItemIntent);
                        break;
                    case BleUtils.BLE_YICHENG:
                        bleItemIntent = new Intent(BlHomeUI.this, BleYcHomeUI.class);
                        bleItemIntent.putExtra("deviceInfo", device);
                        startActivity(bleItemIntent);
                        break;
                    default:
                        UIUtils.toastShow("unSuport device ");
                        break;
                }

            }
        });
    }

    @Override
    protected void processLogic() {
        // TODO Auto-generated method stub
        mBleAdapter = new BleAdapter(BlHomeUI.this, mDevices);
        lvBles.setAdapter(mBleAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if (null != data) {
            if (requestCode == REQUEST_ENABLE_BT) {
                btnBleCheck.setText(R.string.ble_close);
                BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
                mBluetoothAdapter = bluetoothManager.getAdapter();
                if (mBluetoothAdapter != null && mBluetoothAdapter.isEnabled()) {
                    isBleOpen = true;
                    btnBleCheck.setText(R.string.ble_close);
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
