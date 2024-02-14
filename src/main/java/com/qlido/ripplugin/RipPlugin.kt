package com.qlido.ripplugin

import org.bukkit.plugin.java.JavaPlugin

class RipPlugin : JavaPlugin() {
    companion object{
        var instance : RipPlugin? = null
    }
    override fun onEnable() {
        logger.info("으악으악")
        instance = this
        EventManager.registerEvents()
    }

    override fun onDisable() {
        // Plugin shutdown logic
        logger.info("난 간다")
    }
}
