package com.openld.pidangarden.garden.addplantingarden.v;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.openld.pidangarden.R;
import com.openld.pidangarden.garden.addplantingarden.AddPlantInGardenContract;
import com.openld.toolkit.CollectionUtils;
import com.openld.toolkit.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * author: lllddd
 * created on: 2021/8/10 10:43
 * description:添加植物页面
 */
public class AddPlantInGardenActivity extends AppCompatActivity implements AddPlantInGardenContract.IAddPlantInGardenView {
    private static final String TAG = "AddPlantInGardenActivity";

    // 权限请求码
    private static final int REQUEST_CODE_FOR_REQUEST_PERMISSIONS = 1 << 10;

    // 后置摄像头id
    private int mFaceBackCameraId = -1;

    // 前置摄像头id
    private int mFaceFrontCameraId = -1;

    private int mFaceBackCameraOrientation;

    private int mFaceFrontCameraOrientation;

    private Camera mCamera;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plant_in_garden);
        LogUtils.d(TAG, "onCreate");

        requestPermissions();
    }

    /**
     * 请求权限
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestPermissions() {
        boolean cameraPermissionStatus = PackageManager.PERMISSION_GRANTED == checkSelfPermission(Manifest.permission.CAMERA);
        boolean storagePermissionStatus = PackageManager.PERMISSION_GRANTED == checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        List<String> permissionList = new ArrayList<>();
        if (!cameraPermissionStatus) {
            permissionList.add(Manifest.permission.CAMERA);
        }
        if (!storagePermissionStatus) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        String[] permissions = (String[]) permissionList.toArray();
        if (CollectionUtils.isEmpty(permissions)) {
            takePhoto();
        }
        requestPermissions(permissions, REQUEST_CODE_FOR_REQUEST_PERMISSIONS);
        LogUtils.d(TAG, "开始请求权限");
    }

    /**
     * 执行拍照流程
     */
    private void takePhoto() {
        LogUtils.d(TAG, "执行拍照流程");
        // 有多少个摄像头
        int numberOfCamera = Camera.getNumberOfCameras();

        for (int i = 0; i < numberOfCamera; i++) {
            final Camera.CameraInfo cameraInfo = new Camera.CameraInfo();

            Camera.getCameraInfo(i, cameraInfo);

            if (Camera.CameraInfo.CAMERA_FACING_BACK == cameraInfo.facing) {
                mFaceBackCameraId = i;
                mFaceBackCameraOrientation = cameraInfo.orientation;
            } else if (Camera.CameraInfo.CAMERA_FACING_FRONT == cameraInfo.facing) {
                mFaceFrontCameraId = i;
                mFaceFrontCameraOrientation = cameraInfo.orientation;
            }
        }

        if (mFaceBackCameraId != -1) {
            mCamera = Camera.open(mFaceBackCameraId);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.d(TAG, "onDestroy");
        if (mCamera != null) {
            mCamera.release();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_FOR_REQUEST_PERMISSIONS) {
            for (int grantResult : grantResults) {
                if (PackageManager.PERMISSION_GRANTED != grantResult) {
                    Toast.makeText(AddPlantInGardenActivity.this, "请检查您的应用权限设置，保证相机与存储权限均处于允许状态。", Toast.LENGTH_LONG).show();
                    finish();
                    return;
                }
            }
            takePhoto();
        }
    }
}
