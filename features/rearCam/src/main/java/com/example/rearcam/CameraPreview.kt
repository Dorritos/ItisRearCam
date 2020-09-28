package com.example.rearcam

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.hardware.Camera
import android.util.Log
import android.view.Surface
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_camera.view.*

class CameraPreview(context: Context?, camera: Camera?) : SurfaceView(context),
    SurfaceHolder.Callback {
    var hold: SurfaceHolder? = null
    var cam: Camera? = null


    init {
        cam = camera
        hold = holder
        hold?.addCallback(this)
        hold?.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS)
    }


    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        if (holder.surface == null) {
            return
        }
        try {
            cam?.stopPreview();
        } catch (e: Exception) {

        }
        try {
            cam?.setPreviewDisplay(holder);

            cam?.startPreview();
        } catch (e: Exception) {
            Log.d(TAG, "Error starting camera preview: " + e.message);
        }
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        cam?.release()
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        try
        {
            cam?.setPreviewDisplay(holder);
            cam?.startPreview();
        }
        catch (e: Exception)
        {
            val toast = Toast.makeText(getContext(), "Камера нет", Toast.LENGTH_LONG)
            toast.show()
        }
    }
}