package com.example.rearcam

import android.Manifest
import android.hardware.Camera
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.rearcam.utils.getCameraInstance
import com.example.rearcam.utils.setCameraDisplayOrientation

class CameraActivity : AppCompatActivity() {
    var camera: Camera? = null
    var preview: FrameLayout? = null
    var cameraPreview: CameraPreview? = null
    private val MEDIA_RECORDER_REQUEST = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        requestCameraPermissions()
        init()
    }

    private fun requestCameraPermissions() {
        ActivityCompat.requestPermissions(
            this,
            requiredPermissions,
            MEDIA_RECORDER_REQUEST
        )
    }

    private val requiredPermissions = arrayOf<String>(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.CAMERA
    )

    fun init() {
        preview = findViewById(R.id.cameraPreview)
        cameraPreview = CameraPreview(this, camera)
    }

    override fun onResume() {
        super.onResume()
        if (camera == null) {
            camera = getCameraInstance(0)
            camera?.let {
                cameraPreview = CameraPreview(this, it)
                setCameraDisplayOrientation(0, it)
                preview?.addView(cameraPreview)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        releaseCamera()
        preview?.removeView(preview);
    }

    private fun releaseCamera() {
        camera?.release()
        camera = null
    }
}