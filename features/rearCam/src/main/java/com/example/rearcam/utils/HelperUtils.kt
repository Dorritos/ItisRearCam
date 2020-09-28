package com.example.rearcam.utils

import android.app.Activity
import android.hardware.Camera
import android.view.Surface
import androidx.appcompat.app.AppCompatActivity

fun getCameraInstance(cameraId: Int): Camera? {
    var c: Camera? = null
    try {
        c = if (cameraId == -1) {
            Camera.open() // attempt to get a Camera instance
        } else {
            Camera.open(cameraId) // attempt to get a Camera instance
        }
    } catch (e: Exception) {
    }
    return c // returns null if camera is unavailable
}

fun AppCompatActivity.setCameraDisplayOrientation(cameraId: Int, camera: Camera) {
    val info = Camera.CameraInfo()
    Camera.getCameraInfo(cameraId, info)
    val rotation = this.windowManager.defaultDisplay
        .rotation
    var degrees = 0
    when (rotation) {
        Surface.ROTATION_0 -> degrees = 0
        Surface.ROTATION_90 -> degrees = 90
        Surface.ROTATION_180 -> degrees = 180
        Surface.ROTATION_270 -> degrees = 270
    }
    var result: Int
    if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
        result = (info.orientation + degrees) % 360
        result = (360 - result) % 360 // compensate the mirror
    } else {  // back-facing
        result = (info.orientation - degrees + 360) % 360
    }
    camera.setDisplayOrientation(result)
}