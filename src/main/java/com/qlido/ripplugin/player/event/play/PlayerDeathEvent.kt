package com.qlido.ripplugin.player.event.play

import com.qlido.ripplugin.RipPlugin
import com.qlido.ripplugin.utils.LocationUtil
import com.qlido.ripplugin.utils.PlayerUtils
import org.bukkit.*
import org.bukkit.Bukkit.getServer
import org.bukkit.block.Block
import org.bukkit.block.data.type.Chest
//import org.bukkit.block.Chest
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent


object PlayerDeathEvent : Listener {
    @EventHandler
    fun onPlayerDeathEvent(e: PlayerDeathEvent) {
        if (e.keepInventory) return
        val player: Player = e.entity

        if (player.gameMode != GameMode.SURVIVAL) return

        val world: World = player.world
        var loc: Location = player.location

        if (loc.y < world.minHeight) {
            loc.y = world.getHighestBlockYAt(loc.x.toInt(), loc.z.toInt()).toDouble()
            if (loc.y < world.minHeight)
                loc.y = world.minHeight.toDouble();
        } else if (loc.y > world.maxHeight) {
            var y = world.maxHeight - 1
            loc.y = y.toDouble()

            while (world.getBlockAt(loc).type != Material.AIR && y > 0) {
                y--
                loc.y = y.toDouble()
            }

            if (y < 1) {
                player.sendMessage("니템 엄마찾으러감 ㅅㄱ");
                return;
            }
        } else {
            if (
                world.getBlockAt(loc).type == Material.DARK_OAK_DOOR ||
                world.getBlockAt(loc).type == Material.ACACIA_DOOR ||
                world.getBlockAt(loc).type == Material.BIRCH_DOOR ||
                world.getBlockAt(loc).type == Material.CRIMSON_DOOR ||
                world.getBlockAt(loc).type == Material.IRON_DOOR ||
                world.getBlockAt(loc).type == Material.JUNGLE_DOOR ||
                world.getBlockAt(loc).type == Material.OAK_DOOR ||
                world.getBlockAt(loc).type == Material.SPRUCE_DOOR ||
                world.getBlockAt(loc).type == Material.WARPED_DOOR ||
                world.getBlockAt(loc).type == Material.VINE ||
                world.getBlockAt(loc).type == Material.LADDER
            ) {
                val tmpLoc = LocationUtil.findNearestEmptyBlock(loc, 15);

                if (tmpLoc != null) {
                    loc = tmpLoc
                }
            }
            if (world.getBlockAt(loc).type != Material.AIR
                && world.getBlockAt(loc).type != Material.CAVE_AIR
                && world.getBlockAt(loc).type != Material.VOID_AIR
                && world.getBlockAt(loc).type != Material.WATER
                && world.getBlockAt(loc).type != Material.LAVA
            ) {

                while (world.getBlockAt(loc).type != Material.AIR &&
                    loc.y < world.maxHeight
                ) {
                    loc.y += 1;
                }
            }
        }
        val groundLocation = loc.clone()
        groundLocation.y -= 1
        if (world.getBlockAt(
                groundLocation
            ).type == Material.DIRT_PATH || world.getBlockAt(groundLocation).type == Material.FARMLAND
        ) {
            loc.y += 1
        }
        if (!PlayerUtils.isInventoryEmpty(player.inventory)) {

            player.inventory.contents.map {
                if (it != null && it.enchantments.containsKey(Enchantment.VANISHING_CURSE)) {
                    player.inventory.remove(it)
                }
            }

            if (player.inventory.helmet != null && player.inventory.helmet!!.enchantments.containsKey(Enchantment.VANISHING_CURSE)) {
                player.inventory.helmet = null
            }

            if (player.inventory.chestplate != null && player.inventory.chestplate!!.enchantments.containsKey(
                    Enchantment.VANISHING_CURSE
                )
            ) {
                player.inventory.chestplate = null
            }

            if (player.inventory.leggings != null && player.inventory.leggings!!.enchantments.containsKey(Enchantment.VANISHING_CURSE)) {
                player.inventory.leggings = null
            }

            if (player.inventory.boots != null && player.inventory.boots!!.enchantments.containsKey(Enchantment.VANISHING_CURSE)) {
                player.inventory.boots = null
            }

            if (player.inventory.itemInOffHand.enchantments.containsKey(Enchantment.VANISHING_CURSE)) {
                player.inventory.setItemInOffHand(null)
            }

            val block: Block = loc.block
            val block2: Block = loc.clone().add(1.0, 0.0, 0.0).block

            block.type = Material.CHEST
            block2.type = Material.CHEST

            val chestBlockState1 = block.blockData as Chest
            chestBlockState1.type = Chest.Type.LEFT
            block.setBlockData(chestBlockState1, true)

            val chestBlockState2 = block.blockData as Chest
            chestBlockState2.type = Chest.Type.RIGHT
            block2.setBlockData(chestBlockState2, true);

            val chest = block.state as org.bukkit.block.Chest
            chest.inventory.contents = player.inventory.contents

            // 첫 번째 상자와 두 번째 상자를 큰 상자처럼 연결
            e.drops.clear()
            player.inventory.clear();

            getServer().broadcastMessage("§l${loc.x} §l${loc.y} §l${loc.z} 에 황금고블린 §6${player.name} 출현~~")
            RipPlugin.instance!!.logger.info("tlqkf")
        }

    }
}


