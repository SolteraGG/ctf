/*
 * Copyright (c) 2021 DumbDogDiner <dumbdogdiner.com>. All rights reserved.
 * Licensed under the MIT license, see LICENSE for more information.
 */
package com.dumbdogdiner.ctf.listener

import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EntityType
import org.bukkit.entity.Fox
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityInteractEvent
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.event.player.PlayerToggleSneakEvent

class InteractionListener : Listener {
    @EventHandler
    fun onPlayerSpookFox(ev: EntityInteractEvent) {
        // skip if not fox
        if (ev.entityType != EntityType.FOX) {
            return
        }
        println("fox did a spook")
        val fox = ev.entity as Fox
        // make fox sleep
        fox.isSleeping = true
    }

    @EventHandler
    fun onPlayerInteractEntity(ev: PlayerInteractEntityEvent) {
        // don't pick up if not fox
        if (ev.rightClicked.type != EntityType.FOX) {
            return
        }
        // if already fox, eject the fox
        if (ev.player.passengers.size > 0) {
            // eject fox
            ev.player.eject()
        }
        // set sleep mode
        val fox = ev.rightClicked as Fox
        fox.isSleeping = true

        val stand = ev.player.location.world.spawnEntity(ev.player.location, EntityType.ARMOR_STAND) as ArmorStand
        stand.isInvulnerable = true
        stand.setGravity(false)
        stand.isVisible = false
        stand.isSmall = true
        stand.isCollidable = false
        // make the fox mount the stand~
        // stack the stand on the player
        stand.addPassenger(fox)
        ev.player.addPassenger(stand)
    }

    @EventHandler
    fun onPlayerToggleSneak(ev: PlayerToggleSneakEvent) {
        // if have fox, drop fox
        if (ev.player.passengers.size > 0) {
            ev.player.eject()
        }
    }
}
