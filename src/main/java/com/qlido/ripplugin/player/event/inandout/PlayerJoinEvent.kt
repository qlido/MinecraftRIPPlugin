package com.qlido.ripplugin.player.event.inandout

import com.qlido.ripplugin.player.PlayerManager
import org.bukkit.World
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

object PlayerJoinEvent: Listener {
    @EventHandler
    fun onPlayerJoinEvent(e : PlayerJoinEvent) {
        PlayerManager.addPlayer(e.player)
        if(e.player.world.environment != World.Environment.NORMAL)
            e.player.noDamageTicks = 0
    }
}