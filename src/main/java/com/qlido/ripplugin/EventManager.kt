package com.qlido.ripplugin

import com.qlido.ripplugin.player.event.PlayerEventManager

object EventManager {
    fun registerEvents(){
        PlayerEventManager.registerEvents()
    }
}