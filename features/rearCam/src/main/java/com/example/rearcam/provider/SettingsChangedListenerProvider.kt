package com.example.rearcam.provider

import com.coagent.proxy.setting.*

interface SettingsChangedListenerProvider : SettingManager.SettingChangedListener {
    override fun onAudioASPSettingsInfoChange(p0: AudioASPSettingsInfo?) = Unit

    override fun onParkingStatusChange(p0: Boolean) = Unit

    override fun onAudioSettingsInfoChange(p0: AudioSettingsInfo?) = Unit

    override fun onAudioEQSettingsInfoChange(p0: AudioEQSettingsInfo?) = Unit

    override fun onSettingsInfoChange(p0: SettingsInfo?) = Unit

    override fun onMobileyeStatusChange(p0: Boolean) = Unit
}