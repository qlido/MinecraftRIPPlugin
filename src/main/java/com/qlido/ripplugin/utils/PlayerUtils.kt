package com.qlido.ripplugin.utils

import org.bukkit.inventory.Inventory

object PlayerUtils {
    fun isInventoryEmpty(inv:Inventory): Boolean =
        inv.contents.all { it == null }

}