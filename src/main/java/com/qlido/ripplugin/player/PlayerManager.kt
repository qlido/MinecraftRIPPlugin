package com.qlido.ripplugin.player

import com.qlido.ripplugin.RipPlugin
import org.bukkit.entity.Player

object PlayerManager {
    private var onlinePlayerData = HashMap<Player, OnlinePlayer>()

    fun addPlayer(player: Player){
        val newOnlinePlayer = OnlinePlayer(player.uniqueId, player.displayName, "")
        onlinePlayerData[player] = newOnlinePlayer
        RipPlugin.instance!!.logger.info("플레이어 데이터를 추가하였습니다.")
    }

    fun deletePlayer(player: Player){
        onlinePlayerData.remove(player)
        RipPlugin.instance!!.logger.info("플레이어 데이터를 삭제하였습니다.")
    }

    fun getPlayerData(player: Player) : OnlinePlayer?{
        return onlinePlayerData[player]
    }

}