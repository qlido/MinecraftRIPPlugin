package com.qlido.ripplugin.player.event

import com.qlido.ripplugin.RipPlugin
import com.qlido.ripplugin.player.event.inandout.PlayerJoinEvent
import com.qlido.ripplugin.player.event.inandout.PlayerKickEvent
import com.qlido.ripplugin.player.event.inandout.PlayerQuitEvent
import com.qlido.ripplugin.player.event.play.PlayerDeathEvent

object PlayerEventManager {
    fun registerEvents() {
        RipPlugin.instance?.let {
            it.server.pluginManager.run {
                registerEvents(PlayerJoinEvent, it)
                registerEvents(PlayerKickEvent, it)
                registerEvents(PlayerQuitEvent, it)
                registerEvents(PlayerDeathEvent, it)
            }
        }
    }
}