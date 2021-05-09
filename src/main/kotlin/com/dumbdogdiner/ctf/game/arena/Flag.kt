/*
 * Copyright (c) 2021 DumbDogDiner <dumbdogdiner.com>. All rights reserved.
 * Licensed under the MIT license, see LICENSE for more information.
 */
package com.dumbdogdiner.ctf.game.arena

import com.dumbdogdiner.ctf.WithPlugin
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.EntityType
import org.bukkit.entity.Fox

/**
 * Represents a fox flag.
 */
class Flag(val location: Location) : WithPlugin {
    private val fox: Fox = location.world.spawnEntity(location, EntityType.FOX) as Fox
    init {
        Bukkit.getScheduler().runTaskTimer(this.instance, Runnable {
            this.fox.isSleeping = true
        }, 0, 1)
    }
}
