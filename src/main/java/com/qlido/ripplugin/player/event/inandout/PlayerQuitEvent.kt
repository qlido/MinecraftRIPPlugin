package com.qlido.ripplugin.player.event.inandout

import com.qlido.ripplugin.player.PlayerManager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

object PlayerQuitEvent: Listener {
    @EventHandler
    fun onPlayerQuitEvent(e : PlayerQuitEvent) {
        PlayerManager.deletePlayer(e.player)
    }
}