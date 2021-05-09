/*
 * Copyright (c) 2021 DumbDogDiner <dumbdogdiner.com>. All rights reserved.
 * Licensed under the MIT license, see LICENSE for more information.
 */
package com.dumbdogdiner.ctf

import com.dumbdogdiner.ctf.command.DebugFlag
import com.dumbdogdiner.ctf.listener.InteractionListener
import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandAPIConfig
import kr.entree.spigradle.annotations.PluginMain
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

@PluginMain
class FoxCapturePlugin : JavaPlugin() {
    companion object {
        lateinit var instance: FoxCapturePlugin
    }

    override fun onLoad() {
        // set static reference
        instance = this
        // configure command api
        val config = CommandAPIConfig()
        config.verboseOutput = true
        CommandAPI.onLoad(config)
    }

    override fun onEnable() {
        // enable command api
        CommandAPI.onEnable(this)
        // register listeners
        Bukkit.getPluginManager().registerEvents(InteractionListener(), this)
        CommandAPI.registerCommand(DebugFlag::class.java)
    }
}
