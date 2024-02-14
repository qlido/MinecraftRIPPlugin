package com.qlido.ripplugin.utils

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.block.Block


object LocationUtil {
    fun findNearestEmptyBlock(location: Location, radius: Int): Location? {
        val world: World? = location.world
        var nearestBlock: Block? = null
        var nearestDistanceSquared:Double = (radius * radius + 1).toDouble()
        var nearestYDelta = radius + 1

        if(world == null) return null
        // 주어진 반경 내에서 모든 블록을 반복
        for (x in -radius..radius) {
            for (y in -radius..radius) {
                for (z in -radius..radius) {
                    val block: Block = world.getBlockAt(location.clone().add(x.toDouble(), y.toDouble(), z.toDouble()))

                    // 빈 블록인지 확인
                    if (block.isEmpty) {
                        val distanceSquared = location.distanceSquared(block.location)
                        val yDelta = Math.abs(location.blockY - block.location.blockY)

                        // 가장 가까운 빈 블록 업데이트
                        if (distanceSquared < nearestDistanceSquared || (distanceSquared == nearestDistanceSquared && yDelta < nearestYDelta)) {
                            nearestBlock = block
                            nearestDistanceSquared = distanceSquared
                            nearestYDelta = yDelta
                        }
                    }
                }
            }
        }

        if (nearestBlock != null) {
            return nearestBlock.location
        }
        return null
    }
}