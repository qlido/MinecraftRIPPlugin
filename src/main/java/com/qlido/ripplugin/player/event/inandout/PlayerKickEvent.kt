package com.qlido.ripplugin.player.event.inandout

import com.qlido.ripplugin.player.PlayerManager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerKickEvent

object PlayerKickEvent: Listener {
    @EventHandler
    fun onPlayerKickEvent(e : PlayerKickEvent) {
        PlayerManager.deletePlayer(e.player)
    }
}