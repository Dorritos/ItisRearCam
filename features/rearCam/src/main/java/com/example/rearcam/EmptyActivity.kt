package com.example.rearcam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rearcam.provider.SettingsChangedListenerProvider

class EmptyActivity : AppCompatActivity(),
    SettingsChangedListenerProvider {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty)
        onReversedStatuChange(true)
    }

    override fun onReversedStatuChange(p0: Boolean) {
       if(p0){
           val intent = Intent(this, CameraActivity::class.java)
           startActivity(intent)
       }else{

       }
    }
}