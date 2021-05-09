/*
 * Copyright (c) 2021 DumbDogDiner <dumbdogdiner.com>. All rights reserved.
 * Licensed under the MIT license, see LICENSE for more information.
 */
package com.dumbdogdiner.ctf.command

import dev.jorel.commandapi.annotations.Command
import dev.jorel.commandapi.annotations.Default
import dev.jorel.commandapi.annotations.Permission
import dev.jorel.commandapi.annotations.Subcommand
import dev.jorel.commandapi.annotations.arguments.AStringArgument
import java.util.HashMap
import org.bukkit.Location
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@Command("warp")
object DebugFlag {
    // List of warp names and their locations
    var warps: MutableMap<String, Location> = HashMap<String, Location>()
    @Default
    @JvmStatic
    fun warp(sender: CommandSender) {
        sender.sendMessage("--- Warp help ---")
        sender.sendMessage("/warp - Show this help")
        sender.sendMessage("/warp <warp> - Teleport to <warp>")
        sender.sendMessage("/warp create <warpname> - Creates a warp at your current location")
    }

    @Default
    @JvmStatic
    fun warp(player: Player, @AStringArgument warpName: String) {
        if (!warps.containsKey(warpName)) {
            return
        }
        player.teleport(warps[warpName]!!)
    }

    @Subcommand("create")
    @Permission("warps.create")
    @JvmStatic
    fun createWarp(player: Player, @AStringArgument warpName: String) {
        warps[warpName] = player.location
    }
}
